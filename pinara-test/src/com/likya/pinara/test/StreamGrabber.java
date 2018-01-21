package com.likya.pinara.test;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.InterruptedIOException;

import org.apache.log4j.Logger;

import com.likya.commons.utils.CyclicStringBuilder;

public class StreamGrabber extends Thread {

	private InputStream is;
	// private String type;
	private CyclicStringBuilder cyclicStringBuilder;
	private BufferedReader bufferedReader;

	private Logger myLogger;

	boolean live = true;

	public StreamGrabber(InputStream is, String type, Logger myLogger, int logBufferSize) {
		this.is = is;
		// this.type = type;
		this.cyclicStringBuilder = new CyclicStringBuilder(logBufferSize);
		this.myLogger = myLogger;
	}

	public synchronized void stopStreamGobbler() {
		live = false;
	}

	public void run() {
		try {
			InputStreamReader inputStreamReader = new InputStreamReader(is); //, "Cp1254");
			
			bufferedReader = new BufferedReader(inputStreamReader);
			String bufferString = null;

			/*
			 * Not 1 : Eğer, bufferedReader.ready() olmadan
			 * bufferedReader.readLine() yapacak olur isek, thread okuma
			 * satırında takıldığında, terminate etmek işe yaramıyor.
			 */
			while (!bufferedReader.ready()) {
				if (!live) {
					bufferedReader.close();
					inputStreamReader.close();
					exitClass();
					return;
				}
				// System.out.println("StreamGobbler : Checking buffer if ready ...");
				Thread.sleep(200);
			}

			while (true) {
				bufferString = bufferedReader.readLine();
				if(bufferString == null) continue;
			// int i = 0;
			// while ( (i = bufferedReader.read()) != -1) {
				//SpaceWideRegistry.getSpaceWideLogger().debug(type + ">" + bufferString);
				// System.out.println("ş".getBytes());
				// System.out.print((byte)i);
				// System.out.print("(" + (byte)i + ")" + (char)i);
				myLogger.info("" + bufferString);
				// System.out.println(bufferString.getBytes()[15]);
				cyclicStringBuilder.append("" + bufferString + "\n");
				while (!bufferedReader.ready()) {
					if (!live) {
						bufferedReader.close();
						inputStreamReader.close();
						// System.err.println("cyclicStringBuilder : " + cyclicStringBuilder.toString());
						exitClass();
						return;
					}
					// System.out.println("StreamGobbler : Checking buffer if ready ...");
					Thread.sleep(200);
				}
			}
		} catch (InterruptedIOException iioe) {
			myLogger.debug("StreamGrabber : Terminating " + iioe.getMessage(), iioe);
		} catch (IOException ioe) {
			myLogger.debug("StreamGrabber : Terminating " + ioe.getMessage(), ioe);
		} catch (InterruptedException e) {
			myLogger.debug("StreamGrabber : Terminating " + this.getName());
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

	public StringBuilder getOutputBuffer() {
		return cyclicStringBuilder.toStringBuilder();
	}
}