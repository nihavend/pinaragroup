package com.likya.pinara.infobus;

import java.util.ArrayList;

public abstract class PinaraSMSHandler {
	public abstract boolean sendSMS(String msisdn, String messageTxt);
	public abstract ArrayList<String> getMsisdnList();
}
