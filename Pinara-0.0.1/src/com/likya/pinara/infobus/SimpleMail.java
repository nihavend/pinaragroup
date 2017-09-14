package com.likya.pinara.infobus;

public class SimpleMail extends PinaraMail {

	private String mailSubject;
	private String mailText;

	public SimpleMail(String mailSubject, String mailText) {
		this.mailSubject = mailSubject;
		this.mailText = mailText;
		setMAIL_TYPE(PinaraMail.SIMPLE);
	}

	public String getMailSubject() {
		return mailSubject;
	}

	public String getMailText() {
		return mailText;
	}

}
