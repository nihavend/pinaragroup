package com.likya.myra.test;

import java.io.File;
import java.io.IOException;

import com.likya.myra.commons.grabber.StreamGrabber;
import com.likya.myra.jef.core.CoreFactory;
import com.likya.myra.jef.jobs.JobHelper;
import com.likya.xsd.myra.model.joblist.AbstractJobType;
import com.likya.xsd.myra.model.joblist.SimpleProperties;
import com.likya.xsd.myra.model.wlagen.OperatingSystemTypeEnumeration;

public class TestJobExecuter {

	public static void main(String[] args) {
		
		StreamGrabber errorGobbler;
		StreamGrabber outputGobbler;
		
		try {
			
			AbstractJobType abstractJobType = SimpleProperties.Factory.newInstance();

			StringBuilder stringBufferForERROR = new StringBuilder();
			StringBuilder stringBufferForOUTPUT = new StringBuilder();
			
			abstractJobType.addNewBaseJobInfos().setOSystem(OperatingSystemTypeEnumeration.WINDOWS);

			ProcessBuilder processBuilder = null;

			String jobCommand = "job.sh";

			processBuilder = JobHelper.parsJobCmdArgs(true, jobCommand, "");

			String jobPath = "jobs";
			if (jobPath != null) {
				jobCommand = JobHelper.removeSlashAtTheEnd(abstractJobType, jobPath, jobCommand);
				processBuilder.directory(new File(jobPath));
			}

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
