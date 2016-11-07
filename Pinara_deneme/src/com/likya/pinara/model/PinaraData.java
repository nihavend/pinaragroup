package com.likya.pinara.model;

import java.io.Serializable;
import java.util.ArrayList;

public class PinaraData implements Serializable{
	
	private static final long serialVersionUID = 6818222400249346723L;
	
	public static final int POPUP_JOBSCRIPT = 1000;
	public static final int POPUP_JOBLOG = 2000;
	public static final int POPUP_SYSTEMSETTINGS = 3000;
	public static final int POPUP_SCENARIOS = 4000;
	public static final int POPUP_TLOSLOG = 5000;
	public static final int POPUP_SYSTEMLOG = 6000;
	
	private int tlosId;
	private String tlosName;
	private String tlosVersion;
	private int numOfTotalJobs;
	private int numOfJobListed;
	private int tlosStatus;
	private String tlosStatusMessageFirstLine = "";
	private String tlosStatusMessageSecondLine = "";
	private String totalWorkingTime;
	private ArrayList<FlexJob> tlosJobs = new ArrayList<FlexJob>();
	private ArrayList<String> tlosMenuGroup = new ArrayList<String>();
	private boolean tlosConnection;
	private boolean isAuthenticated;
	private String logOrScriptText = "";
	private String ipAddress;
	private int port;
	
	public String getTlosName() {
		return tlosName;
	}
	
	public void setTlosName(String tlosName) {
		this.tlosName = tlosName;
	}
	
	public int getNumOfTotalJobs() {
		return numOfTotalJobs;
	}
	
	public void setNumOfTotalJobs(int numOfTotalJobs) {
		this.numOfTotalJobs = numOfTotalJobs;
	}
	
	public int getNumOfJobListed() {
		return numOfJobListed;
	}
	
	public void setNumOfJobListed(int numOfJobListed) {
		this.numOfJobListed = numOfJobListed;
	}
	
	public String getTotalWorkingTime() {
		return totalWorkingTime;
	}
	
	public void setTotalWorkingTime(String totalWorkingTime) {
		this.totalWorkingTime = totalWorkingTime;
	}
	
	public ArrayList<FlexJob> getTlosJobs() {
		return tlosJobs;
	}
	
	public void setTlosJobs(ArrayList<FlexJob> tlosJobs) {
		this.tlosJobs = tlosJobs;
	}
	
	public ArrayList<String> getTlosMenuGroup() {
		return tlosMenuGroup;
	}
	
	public void setTlosMenuGroup(ArrayList<String> tlosMenuGroup) {
		this.tlosMenuGroup = tlosMenuGroup;
	}
	
	public int getTlosStatus() {
		return tlosStatus;
	}
	
	public void setTlosStatus(int tlosStatus) {
		this.tlosStatus = tlosStatus;
	}

	public void setTlosConnection(boolean tlosConnection) {
		this.tlosConnection = tlosConnection;
	}

	public boolean isTlosConnection() {
		return tlosConnection;
	}

	public void setAuthenticated(boolean isAuthenticated) {
		this.isAuthenticated = isAuthenticated;
	}

	public boolean isAuthenticated() {
		return isAuthenticated;
	}

	public String getTlosStatusMessageFirstLine() {
		return tlosStatusMessageFirstLine;
	}

	public void setTlosStatusMessageFirstLine(String tlosStatusMessageFirstLine) {
		this.tlosStatusMessageFirstLine = tlosStatusMessageFirstLine;
	}

	public String getTlosStatusMessageSecondLine() {
		return tlosStatusMessageSecondLine;
	}

	public void setTlosStatusMessageSecondLine(String tlosStatusMessageSecondLine) {
		this.tlosStatusMessageSecondLine = tlosStatusMessageSecondLine;
	}

	public void setLogOrScriptText(String logOrScriptText) {
		this.logOrScriptText = logOrScriptText;
	}

	public String getLogOrScriptText() {
		return logOrScriptText;
	}

	public String getTlosVersion() {
		return tlosVersion;
	}

	public void setTlosVersion(String tlosVersion) {
		this.tlosVersion = tlosVersion;
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

	public int getTlosId() {
		return tlosId;
	}

	public void setTlosId(int tlosId) {
		this.tlosId = tlosId;
	}

	
}
