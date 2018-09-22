package com.likya.bugreport.JDK_8199849;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

//import sun.misc.BASE64Encoder;
import org.apache.commons.codec.binary.Base64;

public final class PasswordService {

	public static synchronized String encrypt(String plaintext) throws Exception {
		MessageDigest md = null;
		try {
			md = MessageDigest.getInstance("SHA"); // step 2 //$NON-NLS-1$
		} catch (NoSuchAlgorithmException e) {
			throw new Exception(e.getMessage());
		}
		try {
			md.update(plaintext.getBytes("UTF-8")); // step 3 //$NON-NLS-1$
		} catch (UnsupportedEncodingException e) {
			throw new Exception(e.getMessage());
		}
		byte raw[] = md.digest(); // step 4

		/**
		 * Sun uygulamasından vazgeçilip apache uygulaması kullanıldı
		 * 16.03.2011 
		 * 
		 */
		// String hash = (new BASE64Encoder()).encode(raw); // step 5
		
		String hash = Base64.encodeBase64String(raw);
			
		return hash; // step 6
	}

}