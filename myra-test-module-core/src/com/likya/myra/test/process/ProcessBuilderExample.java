package com.likya.myra.test.process;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class ProcessBuilderExample {
	public static void main(String[] args) throws InterruptedException, IOException {
		//ProcessBuilder pb = new ProcessBuilder("/bin/sh","jobs/job01.sh");
		//ProcessBuilder pb = new ProcessBuilder("/bin/sh","ls"); --> HATA
		ProcessBuilder pb = new ProcessBuilder("/bin/sh","-c", "ls -ltr");
		//ProcessBuilder pb = new ProcessBuilder("ls", "-ltr");
		//ProcessBuilder pb = new ProcessBuilder("jobs/job01.sh"); --> HATA
		//ProcessBuilder pb = new ProcessBuilder("cal", "2022");
		System.out.println("Run command");
		Process process = pb.start();
		int errCode = process.waitFor();
		System.out.println("Command executed, any errors? " + (errCode == 0 ? "No" : "Yes"));
		System.out.println("Output:\n" + output(process.getInputStream()));
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