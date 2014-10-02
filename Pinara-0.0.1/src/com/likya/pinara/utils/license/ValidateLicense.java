package com.likya.pinara.utils.license;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.security.Key;

public class ValidateLicense {

	private static String licensePath = null;
	
	public static String getLicenseStringBuffer() throws Exception {

		byte[] licenseData;

		LicenseMap licenseMap = loadLicenseData();
		Key key = licenseMap.getKey();

		licenseData = licenseMap.getLicenseData();
		byte[] sizeOfLicenseDataArray = new byte[4];
		System.arraycopy(licenseData, 0, sizeOfLicenseDataArray, 0, 4);
		int sizeOfLicenseData = LikyaSecurity.byteArrayToInt(sizeOfLicenseDataArray);

		ByteArrayOutputStream tempBaos = new ByteArrayOutputStream();
		tempBaos.write(licenseData, 4, sizeOfLicenseData);
		licenseData = tempBaos.toByteArray();

		String licenseDataString = LikyaSecurity.decrypt(licenseData, key);

		return licenseDataString;
	}

	private static LicenseMap loadLicenseData() throws Exception {

		LicenseMap licenseMap = new LicenseMap();

		String securityStorage = "license.tlos"; //$NON-NLS-1$
		FileInputStream in;
		if(licensePath != null) {
			securityStorage = licensePath + "/" + securityStorage; //$NON-NLS-1$
		}
		in = new FileInputStream(securityStorage);

		ObjectInputStream s = new ObjectInputStream(in);

		licenseMap.setKey((Key) s.readObject());
		licenseMap.setLicenseData((byte[]) s.readObject());
		s.close();

		return licenseMap;
	}
	
	public static String getLicensePath() {
		return licensePath;
	}

	public static void setLicensePath(String licensePath) {
		ValidateLicense.licensePath = licensePath;
	}
}
