package com.likya.commons.utils;

public class CyclicStringBuilder {

	private static int MAX_LENGTH = 1600;
	
	private StringBuilder stringBuilder = new StringBuilder();
	
	
	public CyclicStringBuilder(int maxLength) {
		super();
		if(maxLength > Integer.MAX_VALUE) {
			MAX_LENGTH = Integer.MAX_VALUE;
		} if(maxLength < 80) {
		} else {
			MAX_LENGTH = maxLength;
		}
	}

	public void append(String s) {
		if (stringBuilder.length() + s.length() > MAX_LENGTH) {
			stringBuilder.delete(0, stringBuilder.length() + s.length() - MAX_LENGTH);
		}
		stringBuilder.append(s, Math.max(0, s.length() - MAX_LENGTH), s.length());
	}
	
	public StringBuilder toStringBuilder() {
		return stringBuilder;
	}
	
	public String toString() {
		return stringBuilder.toString();
	}

	public static int getMAX_LENGTH() {
		return MAX_LENGTH;
	}
}
