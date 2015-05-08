package com.likya.pinara.utils.license;

import java.security.Key;
import java.security.spec.AlgorithmParameterSpec;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.IvParameterSpec;

public class LikyaSecurity {

	private static String encoding = "utf-8";

	// private static String algorithm = "DESede";
	private static String algorithm = "DES";
	private static byte[] iv = new byte[] { (byte) 0x8E, 0x12, 0x39, (byte) 0x9C, 0x07, 0x72, 0x6F, 0x5A };
	private static AlgorithmParameterSpec paramSpec;

	// private static String xform = "DESede";
	private static String xform = "DES/CBC/PKCS5Padding";

	private static Key key = null;
	private static Cipher cipher = null;

	public static void setUp() throws Exception {
		key = KeyGenerator.getInstance(algorithm).generateKey();
		cipher = Cipher.getInstance(xform);
		paramSpec = new IvParameterSpec(iv);
	}

	public static LicenseMap encrypt(String input) throws Exception {
		setUp();
		cipher.init(Cipher.ENCRYPT_MODE, key, paramSpec);

		byte[] inputBytes = input.getBytes(encoding);

		System.out.println(new String(inputBytes, encoding));

		LicenseMap licenseMap = new LicenseMap();
		licenseMap.setKey(key);
		licenseMap.setLicenseData(cipher.doFinal(inputBytes));

		return licenseMap;
	}

	public static String decrypt(byte[] encryptionBytes, Key key) throws Exception {

		long startTime = System.currentTimeMillis();
		cipher = Cipher.getInstance(xform);
		long duration = System.currentTimeMillis() - startTime;
		System.err.println("Cipher.getInstance(xform)" + " in " + duration + " ms");
		paramSpec = new IvParameterSpec(iv);
		cipher.init(Cipher.DECRYPT_MODE, key, paramSpec);

		byte[] recoveredBytes = cipher.doFinal(encryptionBytes);
		String recovered = new String(recoveredBytes, encoding);
		return recovered;
	}

	public static final byte[] intToByteArray(int value) {
		return new byte[] { (byte) (value >>> 24), (byte) (value >>> 16), (byte) (value >>> 8), (byte) value };
	}

	public static final int byteArrayToInt(byte[] b) {
		return (b[0] << 24) + ((b[1] & 0xFF) << 16) + ((b[2] & 0xFF) << 8) + (b[3] & 0xFF);
	}

}