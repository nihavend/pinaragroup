package com.likya.pinara.utils.license;

import java.io.Serializable;
import java.security.Key;

public class LicenseMap implements Serializable {

	private static final long serialVersionUID = 8574481443624165592L;

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
