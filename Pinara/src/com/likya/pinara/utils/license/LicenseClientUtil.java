package com.likya.pinara.utils.license;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.codec.binary.Base64;

import com.likya.commons.utils.DateUtils;
import com.likya.commons.utils.FileUtils;
import com.likya.pinara.Pinara;

public class LicenseClientUtil {

	private static final String fileName = Pinara.DATA_PATH + File.separator + "tmp.dfe";

	public static void serialize(String expDate) throws Exception {

		LicenseMap licenseMap = LikyaSecurity.encrypt(expDate);

		FileOutputStream outputStream = new FileOutputStream(fileName);

		ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
		
		objectOutputStream.writeObject(licenseMap);

		objectOutputStream.close();
		
		outputStream.close();
	}

	public static String deserialize() throws Exception {

		if (!FileUtils.checkFile(fileName)) {
			return null;
		}

		FileInputStream inputStream = new FileInputStream(fileName);

		ObjectInputStream s = new ObjectInputStream(inputStream);

		LicenseMap licenseMap = (LicenseMap) s.readObject();
		
		s.close();
		
		String decryptedString = LikyaSecurity.decrypt(licenseMap.getLicenseData(), licenseMap.getKey());
		
		if(!validateDate(decryptedString, false)) {
			return null;
		}
		
		return decryptedString;
	}

	public static boolean validate(String bufferString) throws Exception {

		String data = bufferString.split("%5B")[1].substring(0, bufferString.split("%5B")[1].length() - 9);

		byte[] b = Base64.decodeBase64(data);
		String userpass = new String(b);
		int colon = userpass.indexOf(':');
		String key = userpass.substring(0, colon);
		String value = userpass.substring(colon + 1);

		if ("expdate".equals(key)) {
			return validateDate(value, true);
		}

		return false;

	}

	private static boolean validateDate(String value, boolean serialize) throws Exception {

		DateFormat format = new SimpleDateFormat("ddMMyyyy");
		Date date;

		// System.out.println("Expire Date : " + value);
		// System.out.println("Current Date : " + format.format(Calendar.getInstance().getTime()));

		try {
			date = format.parse(value);
			
			if(DateUtils.dateDiffWithNow(date) < 0) {
				if(serialize) {
					serialize(value);
				}
				return true;
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return false;
	}
}
