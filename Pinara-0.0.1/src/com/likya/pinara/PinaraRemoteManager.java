/*******************************************************************************
 * Copyright 2014 Likya Teknoloji
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/
package com.likya.pinara;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.ConnectException;
import java.net.InetSocketAddress;
import java.nio.channels.Channels;
import java.nio.channels.SocketChannel;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;

import com.likya.commons.utils.LocaleMessages;
import com.likya.pinara.model.CommandType;
import com.likya.pinara.model.TrxInfo;


public class PinaraRemoteManager {

	protected final static String localePath = "com.likya.pinara.resources.messages";

	private static String USAGE_MSG; //$NON-NLS-1$
	private static final int retryCountMax = 10;
	
	public static void main(String[] args) throws Exception {

		LocaleMessages.registerBundle(localePath);
		USAGE_MSG = Pinara.getMessage("PinaraRemoteManager.0");

		int i = 0;
		String arg;

		String port = null;
		String host = null;

		String jobname = null;
		String terminate = null;
		String fterminate = null;
		
		String unit = null;
		String amount = null;
		
		boolean vflag = false;
		boolean jflag = false;

		while (i < args.length && args[i].startsWith("-")) { //$NON-NLS-1$

			arg = args[i++];
			// use this type of check for "wordy" arguments
			if (arg.equals("-verbose")) { //$NON-NLS-1$
				System.out.println(Pinara.getMessage("PinaraRemoteManager.3")); //$NON-NLS-1$
				vflag = true;

			} else if (arg.equals("-port")) { //$NON-NLS-1$
				if (i < args.length)
					port = args[i++];
				else
					System.err.println(Pinara.getMessage("PinaraRemoteManager.5")); //$NON-NLS-1$
				if (vflag)
					System.out.println(Pinara.getMessage("PinaraRemoteManager.6") + port); //$NON-NLS-1$
			} else if (arg.equals("-host")) { //$NON-NLS-1$
				if (i < args.length)
					host = args[i++];
				else
					System.err.println(Pinara.getMessage("PinaraRemoteManager.8")); //$NON-NLS-1$
				if (vflag)
					System.out.println(Pinara.getMessage("PinaraRemoteManager.9") + host); //$NON-NLS-1$

			} else if (arg.equals("-jobname")) { //$NON-NLS-1$

				if (i < args.length) {
					jobname = args[i++];
				} else {
					System.err.println(Pinara.getMessage("PinaraRemoteManager.11")); //$NON-NLS-1$

					System.exit(0);
				}
			} else if (arg.equals("-dumpjoblist")) { //$NON-NLS-1$
				System.out.println(Pinara.getMessage("PinaraRemoteManager.13")); //$NON-NLS-1$
				jflag = true;
				
			} else if(arg.equals("-terminate")) { //$NON-NLS-1$
				terminate = arg;
			} else if(arg.equals("-fterminate")) { //$NON-NLS-1$
				fterminate = arg;
			} else if (arg.equals("-unit")) { //$NON-NLS-1$
				if (i < args.length)
					unit = args[i++];
				else
					System.err.println(Pinara.getMessage("PinaraRemoteManager.8")); //$NON-NLS-1$
				if (vflag)
					System.out.println(Pinara.getMessage("PinaraRemoteManager.9") + unit); //$NON-NLS-1$
			} else if (arg.equals("-amount")) { //$NON-NLS-1$
				if (i < args.length)
					amount = args[i++];
				else
					System.err.println(Pinara.getMessage("PinaraRemoteManager.8")); //$NON-NLS-1$
				if (vflag)
					System.out.println(Pinara.getMessage("PinaraRemoteManager.9") + amount); //$NON-NLS-1$
			} else {
				System.err.println(USAGE_MSG);
				System.exit(0);
			}

		}

		if ((host == null) || (port) == null) {
			System.err.println(USAGE_MSG);
			System.exit(0);
		} else {
			
			SocketChannel socketChannel = null;
			int retryCount = 0;
			while (true) {
				
				try {
					if (socketChannel == null || !socketChannel.isOpen()) {
						socketChannel = SocketChannel.open();
					}
					socketChannel.connect(new InetSocketAddress(host, Integer.parseInt(port)));
				} catch (ConnectException ce) {
					socketChannel.close();
					if (retryCount++ < retryCountMax) {
						// TICServer.println("Server connection is being retried!");
						System.out.println(Pinara.getMessage("PinaraRemoteManager.16")); //$NON-NLS-1$
						Thread.sleep(1000);
						continue;
					}
				}
				if(retryCount > retryCountMax) {
					System.out.println(Pinara.getMessage("PinaraRemoteManager.xx"));
					System.exit(0);
				}
				break;
			}
			
			TrxInfo pinaraInfo = new TrxInfo();
			pinaraInfo.setClientId("PinaraRemoteManager"); //$NON-NLS-1$
			
			CommandType commandType = new CommandType();
			
			if (jobname != null) {
				commandType.setId(TrxInfo.RESUME_JOB);
				commandType.setDescription(TrxInfo.getCommandTypeDescription(TrxInfo.RESUME_JOB));
				pinaraInfo.setJobId(jobname);
				
			} else if(terminate != null) {
				commandType.setId(TrxInfo.NORMAL_TERMINATE);
				commandType.setDescription(TrxInfo.getCommandTypeDescription(TrxInfo.NORMAL_TERMINATE));
				System.out.println(Pinara.getMessage("PinaraRemoteManager.18")); //$NON-NLS-1$
			} else if(fterminate != null) {
				commandType.setId(TrxInfo.FORCED_TERMINATE);
				commandType.setDescription(TrxInfo.getCommandTypeDescription(TrxInfo.FORCED_TERMINATE));
				System.out.println(Pinara.getMessage("PinaraRemoteManager.19")); //$NON-NLS-1$
			} else if(unit != null) {
				commandType.setId(TrxInfo.CHANGE_CLOCK);
				TemporalUnit temporalUnit = null;
				long amountToAdd = 0;
				if(amount != null) { // amount verilmezse bir değişmeyecek 0  set edecek, yani çalışmayacak // 22.09.2018 16:28 Serkan Taş
					amountToAdd = Long.parseLong(amount);
				}

				switch (unit.toUpperCase()) {
				case "NANOS":
					temporalUnit = ChronoUnit.DAYS;
					break;
				case "MICROS":
					temporalUnit = ChronoUnit.MICROS;
					break;
				case "MILLIS":
					temporalUnit = ChronoUnit.MILLIS;
					break;
				case "SECONDS":
					temporalUnit = ChronoUnit.SECONDS;
					break;
				case "MINUTES":
					temporalUnit = ChronoUnit.MINUTES;
					break;
				case "HOURS":
					temporalUnit = ChronoUnit.HOURS;
					break;
				case "HALF_DAYS":
					temporalUnit = ChronoUnit.HALF_DAYS;
					break;
				case "DAYS":
					temporalUnit = ChronoUnit.DAYS;
					break;
				default:
					temporalUnit = ChronoUnit.MILLIS;
					break;
				}
				pinaraInfo.setAmountToAdd(amountToAdd);
				pinaraInfo.setTemporalUnit(temporalUnit);
			} else if(jflag) {
				commandType.setId(TrxInfo.DUMP_JOB_LIST);
				commandType.setDescription(TrxInfo.getCommandTypeDescription(TrxInfo.DUMP_JOB_LIST));
				System.out.println(Pinara.getMessage("PinaraRemoteManager.20")); //$NON-NLS-1$
			} else {
				System.err.println(USAGE_MSG);
				System.exit(0);
			}
			
			pinaraInfo.setCommandType(commandType);
			writeData(socketChannel, pinaraInfo);
			
			TrxInfo tlosInfoRx = readData(socketChannel);
			
			if(tlosInfoRx.getErrCode() != 0) {
				System.out.println(Pinara.getMessage("PinaraRemoteManager.21") + tlosInfoRx.getErrCode() + Pinara.getMessage("PinaraRemoteManager.22") + tlosInfoRx.getErrDesc()); //$NON-NLS-1$ //$NON-NLS-2$
			}

		}

	}
	
	public static void writeData(SocketChannel socketChannel, TrxInfo pinaraInfo) throws IOException, ClassNotFoundException {

		OutputStream is = Channels.newOutputStream(socketChannel);
		ObjectOutputStream ois = new ObjectOutputStream(is);
		ois.writeObject(pinaraInfo);

		return;
	}
	
	public static TrxInfo readData(SocketChannel socketChannel) throws IOException, ClassNotFoundException {

		InputStream is = Channels.newInputStream(socketChannel);
		ObjectInputStream ois = new ObjectInputStream(is);
		TrxInfo pinaraInfo = (TrxInfo) ois.readObject();

		return pinaraInfo;
	}
}
