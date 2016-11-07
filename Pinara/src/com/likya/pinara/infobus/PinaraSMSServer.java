package com.likya.pinara.infobus;

import java.util.ArrayList;
import java.util.Iterator;

import com.likya.pinara.Pinara;
import com.likya.xsd.pinara.model.config.SmsInfoDocument.SmsInfo;

/**
 * @author vista
 * 
 */
public class PinaraSMSServer implements Runnable {

	private final int timeout = 10000;
	private boolean executePermission = true;
	private ArrayList<SMSType> smsQueue = new ArrayList<SMSType>();
	
	private ArrayList<String> msisdnList;

	private PinaraSMSHandler tlosSMSHandler;

	public PinaraSMSServer(SmsInfo smsInfo, PinaraSMSHandler tlosSMSHandler) {
		this.tlosSMSHandler = tlosSMSHandler;
		this.msisdnList = tlosSMSHandler.getMsisdnList();
	}

	public void terminate(boolean forcedTerminate) {
		synchronized (this) {
			if (forcedTerminate) {
				smsQueue.clear();
			}
			this.executePermission = false;
		}
	}

	public void run() {
		Thread.currentThread().setName("PinaraSMSServer");
		while (executePermission || (smsQueue.size() > 0 && msisdnList.size() > 0)) {
			
			while (smsQueue.size() > 0) {
				SMSType smsType = (SMSType) smsQueue.get(0);
				
				Iterator<String> msisdnIterator = msisdnList.iterator();
				
				while (msisdnIterator.hasNext()) {
					
					Pinara.getLogger().debug(Pinara.getMessage("TlosSMSServer.0"));
					try {
						tlosSMSHandler.sendSMS(msisdnIterator.next(), smsType.getMessageTxt());
					} catch (Exception e) {
						e.printStackTrace();
						Pinara.getLogger().info(Pinara.getMessage("TlosSMSServer.1") + e.getMessage() + Pinara.getMessage("TlosSMSServer.2"));
					}
				}
				smsQueue.remove(0);
			}
			try {
				// TlosServer.getLogger().debug("Mail server sleeping !");
				Thread.sleep(timeout);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		Pinara.getLogger().info(Pinara.getMessage("TlosSMSServer.3"));
		Pinara.getLogger().info(Pinara.getMessage("TlosSMSServer.4") + smsQueue.size());
	}

	private synchronized void addSMS(SMSType smsType) {
		smsQueue.add(smsType);
	}

	public void sendSMS(SMSType smsType) {
		addSMS(smsType);
	}

	public int getQueueSize() {
		return smsQueue.size();
	}
}
