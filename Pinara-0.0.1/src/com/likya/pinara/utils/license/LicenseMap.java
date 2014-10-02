package com.likya.pinara.utils.license;

import java.security.Key;

public class LicenseMap {
	private Key key;
	private byte[] licenseData;

	public Key getKey() {
		return key;
	}

	public void setKey(Key key) {
		this.key = key;
	}

	public byte[] getLicenseData() {
		return licenseData;
	}

	public void setLicenseData(byte[] licenseData) {
		this.licenseData = licenseData;
	}

}
