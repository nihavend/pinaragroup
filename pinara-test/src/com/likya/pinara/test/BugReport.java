package com.likya.pinara.test;

import java.io.IOException;

public class BugReport {

	public static void main(String[] args) {
		
		
		try {
			String[] commandArr = {"cmd.exe", "/C", "nodepad.exe"};

			ProcessBuilder processBuilder = new ProcessBuilder(commandArr);
			
			Process process = processBuilder.start();
			
			BugGrabber errorGobbler = new BugGrabber(process.getErrorStream()); 
			BugGrabber outputGobbler = new BugGrabber(process.getInputStream());

			errorGobbler.start();
			outputGobbler.start();
			
			process.waitFor();

		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
