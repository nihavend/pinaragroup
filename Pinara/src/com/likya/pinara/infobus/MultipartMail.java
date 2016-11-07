package com.likya.pinara.infobus;

import javax.mail.Multipart;

public class MultipartMail extends PinaraMail {

	private String mailSubject;
	private Multipart multipart;

	public String getMailSubject() {
		return mailSubject;
	}

	public void setMailSubject(String mailSubject) {
		this.mailSubject = mailSubject;
	}

	public Multipart getMultipart() {
		return multipart;
	}

	public void setMultipart(Multipart multipart) {
		this.multipart = multipart;
	}

}
