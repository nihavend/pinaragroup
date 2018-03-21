package com.likya.bugreport.JDK_8194673;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

public class BugGrabber extends Thread {

	private InputStream is;
	private BufferedReader bufferedReader;

	boolean live = true;

	public BugGrabber(InputStream is) {
		this.is = is;
	}

	public synchronized void stopStreamGobbler() {
		live = false;
	}

	public void run() {
		try {
			InputStreamReader inputStreamReader = new InputStreamReader(is);
			
			bufferedReader = new BufferedReader(inputStreamReader);
			String bufferString = null;

			while (!bufferedReader.ready()) {
				if (!live) {
					bufferedReader.close();
					inputStreamReader.close();
					exitClass();
					return;
				}
				Thread.sleep(200);
			}
			
			File targetFile = new File("D:\\dev\\git\\pinaragroup\\pinara-test\\output.txt");
		    OutputStream outStream = new FileOutputStream(targetFile);
		    

			while (true) {
				bufferString = bufferedReader.readLine();
				if(bufferString == null) continue;
				System.out.println(bufferString);
				outStream.write(bufferString.getBytes());
				while (!bufferedReader.ready()) {
					if (!live) {
						bufferedReader.close();
						inputStreamReader.close();
						exitClass();
						return;
					}
					Thread.sleep(200);
				}
			}
		} catch (Exception iioe) {
		}

		exitClass();
	}

	private void exitClass() {
		try {
			is.close();
		} catch (IOException e) {
		}
		is = null;
	}
}