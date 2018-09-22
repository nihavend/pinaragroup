package com.likya.myra.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

import junit.framework.TestCase;

import com.likya.myra.commons.grabber.StreamGrabber;
import com.likya.myra.jef.core.CoreFactory;
import com.likya.myra.jef.jobs.JobHelper;
import com.likya.xsd.myra.model.joblist.AbstractJobType;
import com.likya.xsd.myra.model.joblist.SimpleProperties;
import com.likya.xsd.myra.model.wlagen.OperatingSystemTypeEnumeration;

public class TestJobExecuter extends TestCase {

	protected void setUp() {
	}

	public void testExecuteSingleJob() {

		StreamGrabber errorGobbler;
		StreamGrabber outputGobbler;

		try {

			AbstractJobType abstractJobType = SimpleProperties.Factory.newInstance();

			StringBuilder stringBufferForERROR = new StringBuilder();
			StringBuilder stringBufferForOUTPUT = new StringBuilder();

			String osName = System.getProperty("os.name");

			System.out.println("Working for os : " + osName);

			switch (osName) {
			case "Mac OS X":
				abstractJobType.addNewBaseJobInfos().setOSystem(OperatingSystemTypeEnumeration.MACOS);
				break;

			default:
				abstractJobType.addNewBaseJobInfos().setOSystem(OperatingSystemTypeEnumeration.WINDOWS);
				break;
			}

			ProcessBuilder processBuilder = null;

			System.out.println("Enter command to execute w/o full path : ");
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
			String jobCommand = bufferedReader.readLine();
			// String jobCommand = "job.sh";

			processBuilder = JobHelper.parsJobCmdArgs(true, jobCommand, "");

			String jobPath = null;
			System.out.println("Enter data path for command if exist - enter to bypass : ");
			// String jobPath = "/Users/serkan/git/pinaragroup/Myra-0.0.1-Test/jobs";
			jobPath = bufferedReader.readLine();
			// String jobCommand = "job.sh";

			if (jobPath != null && !jobPath.equals("")) {
				// Bu kısım luzumsuz :  jobCommand = JobHelper.removeSlashAtTheEnd(abstractJobType, jobPath, jobCommand);
				processBuilder.directory(new File(jobPath));
			}

			processBuilder = JobHelper.parsJobCmdArgs(true, jobCommand, "");

			Process process = processBuilder.start();

			errorGobbler = new StreamGrabber(process.getErrorStream(), "ERROR", CoreFactory.getLogger(), 100);
			outputGobbler = new StreamGrabber(process.getInputStream(), "OUTPUT", CoreFactory.getLogger(), 100);

			// kick them off
			errorGobbler.start();
			outputGobbler.start();

			process.waitFor();

			Thread.sleep(1000);

			int processExitValue = process.exitValue();

			System.out.println("Exit Value : " + processExitValue);

			stringBufferForERROR = errorGobbler.getOutputBuffer();
			stringBufferForOUTPUT = outputGobbler.getOutputBuffer();

			System.out.println("stringBufferForERROR Value : " + stringBufferForERROR);
			System.out.println("stringBufferForOUTPUT Value : " + stringBufferForOUTPUT);

		} catch (InterruptedException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
