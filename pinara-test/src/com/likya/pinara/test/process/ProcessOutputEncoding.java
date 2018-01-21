package com.likya.pinara.test.process;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.Locale;

import com.likya.myra.commons.ValidPlatforms;
import com.likya.myra.jef.core.CoreFactory;
import com.likya.pinara.test.StreamGrabber;

public class ProcessOutputEncoding {

	
	// Windows-1252 ISO-8859-9 "ISO-8859-9"
	
	public static void main(String[] args) {
		
		/*
		String str = "i� ya da d�� komut, �al��t�r�labilir";
		String str2 = "i� dosyas� olarak tan�nm�yor";

		try {
			System.out.println(new String(str.getBytes("windows-1252"), Charset.forName("utf-8")));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println(str2);
		*/
		
		
		
		// Locale.setDefault(new Locale("tr_TR.utf-8"));
		System.out.println(Locale.getDefault());
		
		try {
			String[] commandArr = ValidPlatforms.getCommand("nodepad.exe");

			ProcessBuilder processBuilder = new ProcessBuilder(commandArr);
			
			processBuilder.environment().put("LANG", "tr_TR"); 
			
			// processBuilder.environment();

			Process process = processBuilder.start();
			
			StreamGrabber errorGobbler = new StreamGrabber(process.getErrorStream(), "ERROR", CoreFactory.getLogger(), 800); 
			StreamGrabber outputGobbler = new StreamGrabber(process.getInputStream(), "OUTPUT", CoreFactory.getLogger(), 800);

			errorGobbler.start();
			outputGobbler.start();
			
			process.waitFor();

			int processExitValue = process.exitValue();

			System.out.println(processExitValue);
			
			cleanUpFastEndings(errorGobbler, outputGobbler);
			
			StringBuilder stringBufferForERROR = errorGobbler.getOutputBuffer();
			StringBuilder stringBufferForOUTPUT = outputGobbler.getOutputBuffer();
			
			// System.out.println("stringBufferForERROR => " + new String(stringBufferForERROR.toString().getBytes(Charset.forName("utf-8")), Charset.forName("utf-8") ));
			System.out.println("stringBufferForERROR => " + stringBufferForERROR.toString().replace((char)-17, 'ç'));
			System.out.println("stringBufferForOUTPUT => " + stringBufferForOUTPUT);
			
			System.out.println();
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	static void cleanUpFastEndings(StreamGrabber errorGobbler, StreamGrabber outputGobbler) throws InterruptedException {
		if (errorGobbler.isAlive()) {
			errorGobbler.stopStreamGobbler();
			while (errorGobbler.isAlive()) {
				Thread.sleep(10);
			}
		}
		if (outputGobbler.isAlive()) {
			outputGobbler.stopStreamGobbler();
			while (outputGobbler.isAlive()) {
				Thread.sleep(10);
			}
		}
	}
}
