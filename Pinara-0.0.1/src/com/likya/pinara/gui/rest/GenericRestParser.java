package com.likya.pinara.gui.rest;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;


public abstract class GenericRestParser {
	
	public static String getSwitchId(StringBuffer bufferString, String uriTxt, InputStream inputStream) throws IOException {

		ArrayList<Byte> byteArrLst = new ArrayList<Byte>();

		byte b;
		while ((b = (byte) inputStream.read()) != -1) {
			// bufferString = bufferString + (char) i;
			// barr[count ++] = b;
			byteArrLst.add(b);
		}

		byte[] barr = new byte[byteArrLst.size()];

		int count = 0;
		for (Object mbyte : byteArrLst.toArray()) {
			barr[count++] = ((Byte) mbyte).byteValue();
		}

		bufferString = bufferString.append(new String(barr));

		String restCommArr[] = uriTxt.split("/");

		if (restCommArr.length == 0) {
			return null;
		}

		return restCommArr[0];
	}
	
	public static boolean isInteger(String str) {

		if (str == null) {
			return false;
		}

		int length = str.length();
		if (length == 0) {
			return false;
		}

		int i = 0;
		if (str.charAt(0) == '-') {
			if (length == 1) {
				return false;
			}
			i = 1;
		}

		for (; i < length; i++) {
			char c = str.charAt(i);
			if (c <= '/' || c >= ':') {
				return false;
			}
		}

		return true;

	}
}
