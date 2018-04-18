package com.likya.myra.test.process;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import com.likya.xsd.myra.model.generics.EntryDocument.Entry;
import com.likya.xsd.myra.model.generics.EnvVariablesDocument.EnvVariables;


public class ProcessTest {
	
	transient static protected Process process;

	public static void main(String[] args) {
		int x = startProcess();
		System.out.println(x);
	}
	
	public static Map<String, String> entryToMap(EnvVariables envVariables) {

		Map<String, String> envMap = new HashMap<String, String>();

		if (envVariables != null) {
			Entry[] envVars = envVariables.getEntryArray();

			for (Entry myEntry : envVars) {
				envMap.put(myEntry.getKey(), myEntry.getStringValue());
			}
		}

		return envMap;

	}

	
	public static int startProcess() {
		ProcessBuilder processBuilder = null;

		//String jobCommand = jobTypeDetails.getJobCommand();
		//String jobCommand = "ls -ltr";
		String jobCommand = "job1.sh";


		//processBuilder = JobHelper.parsJobCmdArgs(isShell, jobCommand, jobTypeDetails.getArgValues());
		processBuilder = parsJobCmdArgs(true, jobCommand, null);

		//String jobWorkDir = abstractJobType.getBaseJobInfos().getJobTypeDetails().getJobWorkDir();
		String jobWorkDir = "jobs/";//"/Users/sahin/dev/eclipseworkspaces/oxygen/jdk1.8/test/jobs";
		if (jobWorkDir != null && !jobWorkDir.equals("")) {
			processBuilder.directory(new File(jobWorkDir));
		}

		Map<String, String> tempEnv = new HashMap<String, String>();

		Map<String, String> environmentVariables = new HashMap<String, String>();

		if (environmentVariables != null && environmentVariables.size() > 0) {
			tempEnv.putAll(environmentVariables);
		}

		//tempEnv.putAll(entryToMap(jobTypeDetails.getEnvVariables()));
		//tempEnv.putAll(entryToMap((EnvVariables) new EnvVariablesDocumentImpl(null)));

		processBuilder.environment().putAll(tempEnv);

		//setRunning(abstractJobType);

		try {
			process = processBuilder.start();
			process.waitFor();
			
			System.out.println("Echo Output:\n" + output(process.getInputStream()));
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		int processExitValue = process.exitValue();
		return processExitValue;	

	}
	
	public static ProcessBuilder parsJobCmdArgs(boolean isShell, String jobCommand, String extArgValues) {

		ProcessBuilder processBuilder;

		String realCommand = "";
		String[] inlineArgs = null;

		boolean isUnixShScript = (jobCommand.indexOf(".sh") == -1) ? false : true;
		int indexOfSpace = jobCommand.indexOf(" ");

		if (indexOfSpace > 0) {
			realCommand = jobCommand.substring(0, indexOfSpace).trim();
			inlineArgs = jobCommand.substring(jobCommand.indexOf(" ")).trim().split(" ");
		} else {
			realCommand = jobCommand.trim();
		}

		String[] commandArr;

		if (isShell) {
			commandArr = ValidPlatforms.getCommand(realCommand, isUnixShScript);
		} else {
			commandArr = new String[] { realCommand };
		}

		if (inlineArgs != null && inlineArgs.length > 0) {
			commandArr = concat2(commandArr, inlineArgs);
		}

		if (extArgValues != null && extArgValues.length() > 0) {
			commandArr = concat2(commandArr, extArgValues.trim().split(" "));
		}

		processBuilder = new ProcessBuilder(commandArr);

		return processBuilder;

	}
	
	public static String[] concat2(String[] first, String[] second) {
		for(String inlineArg : second) {
			first[first.length-1] = first[first.length-1] + " " + inlineArg;
		}

		return first;
	}
	
	public static String[] concat(String[] first, String[] second) {

		String[] result = new String[first.length + second.length];

		System.arraycopy(first, 0, result, 0, first.length);
		System.arraycopy(second, 0, result, first.length, second.length);

		return result;
	}
	
	private static String output(InputStream inputStream) throws IOException {
		StringBuilder sb = new StringBuilder();
		BufferedReader br = null;
		try {
			br = new BufferedReader(new InputStreamReader(inputStream));
			String line = null;
			while ((line = br.readLine()) != null) {
				sb.append(line + System.getProperty("line.separator"));
			}
		} finally {
			br.close();
		}
		return sb.toString();
	}


}
