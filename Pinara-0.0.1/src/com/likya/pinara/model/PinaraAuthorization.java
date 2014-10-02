package com.likya.pinara.model;

import java.io.Serializable;

public class PinaraAuthorization implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String userName;
	private String passWord;

	public PinaraAuthorization(String userName, String passWord) {
		this.userName = userName;
		this.passWord = passWord;
	}
	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
}
