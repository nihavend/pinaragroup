package com.likya.pinara.model;

import java.util.StringTokenizer;

public class LicenseInfo {

	private String clientId;
	private String productName;
	private String versionId;
	private String clientName;
	private String copyRight;
	private String ownerInfo;
	private String county;
	private String city;
	private String country;
	private String licenseType;
	private String operatingSystem;
	private String command;
	private String key;
	private String expireDate;
	private boolean smsPermit = false;
	private boolean useManagementConsole = false;

	public LicenseInfo() {
	}

	public LicenseInfo init(String stringTrail) {
		StringTokenizer stringTokenizer = new StringTokenizer(stringTrail, ","); //$NON-NLS-1$
		clientId = stringTokenizer.nextToken().toString().substring(1);
		productName = stringTokenizer.nextToken().toString().substring(1);
		versionId = stringTokenizer.nextToken().toString().trim();
		clientName = stringTokenizer.nextToken().toString().trim();
		copyRight = stringTokenizer.nextToken().toString().trim();
		ownerInfo = stringTokenizer.nextToken().toString().trim();
		county = stringTokenizer.nextToken().toString().trim();
		city = stringTokenizer.nextToken().toString().trim();
		country = stringTokenizer.nextToken().toString().trim();
		licenseType = stringTokenizer.nextToken().toString().trim();
		operatingSystem = stringTokenizer.nextToken().toString().trim();
		command = stringTokenizer.nextToken().toString().trim();
		key = stringTokenizer.nextToken().toString().trim().replace("||", ","); //$NON-NLS-1$ //$NON-NLS-2$
		expireDate = stringTokenizer.nextToken().toString().trim();
		expireDate = expireDate.substring(0, expireDate.length());
		String smsPermitTxt = stringTokenizer.nextToken().toString().trim();
		smsPermit = new Boolean(smsPermitTxt.substring(0, smsPermitTxt.length())).booleanValue();
		String useManagementConsoleTxt = stringTokenizer.nextToken().toString().trim();
		useManagementConsole = new Boolean(useManagementConsoleTxt.substring(0, useManagementConsoleTxt.length() - 1)).booleanValue();
		return this;
	}

	public String getClientId() {
		return clientId;
	}

	public String getVersionId() {
		return versionId;
	}

	public String getClientName() {
		return clientName;
	}

	public String getCopyRight() {
		return copyRight;
	}

	public String getOwnerInfo() {
		return ownerInfo;
	}

	public String getCounty() {
		return county;
	}

	public String getCity() {
		return city;
	}

	public String getCountry() {
		return country;
	}

	public String getProductName() {
		return productName;
	}

	public String getLicenseType() {
		return licenseType;
	}

	public String getExpireDate() {
		return expireDate;
	}

	public String getOperatingSystem() {
		return operatingSystem;
	}

	public String getCommand() {
		return command;
	}

	public String getKey() {
		return key;
	}

	public boolean isSmsPermit() {
		return smsPermit;
	}

	public void setSmsPermit(boolean smsPermit) {
		this.smsPermit = smsPermit;
	}

	public void setUseManagementConsole(boolean useManagementConsole) {
		this.useManagementConsole = useManagementConsole;
	}

	public boolean isUseManagementConsole() {
		return useManagementConsole;
	}
}
