package com.likya.pinara.model;

import java.io.Serializable;

public class PinaraConnection implements Serializable {

	private static final long serialVersionUID = -7233406693169321070L;

	private int id;
	private String myName;
	private String ipAddress;
	private int port;
	private String userName;
	private String password;
	private boolean isConnected;
	private String enable;

	public PinaraConnection() {
	}

	public PinaraConnection(int id, String myName, String ipAddress, int port, String userName, String password, boolean isConnected, String enable) {
		super();
		this.id = id;
		this.myName = myName;
		this.ipAddress = ipAddress;
		this.port = port;
		this.setUserName(userName);
		this.setPassword(password);
		this.isConnected = isConnected;
		this.enable = enable;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public void setConnected(boolean isConnected) {
		this.isConnected = isConnected;
	}

	public boolean isConnected() {
		return isConnected;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public String getEnable() {
		return enable;
	}

	public void setEnable(String enable) {
		this.enable = enable;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMyName() {
		return myName;
	}

	public void setMyName(String myName) {
		this.myName = myName;
	}

}
