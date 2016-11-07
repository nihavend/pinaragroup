package com.likya.pinara.model;

public class PinaraXMLValidationException extends Throwable {

	private static final long serialVersionUID = -4479492898487738442L;

	public PinaraXMLValidationException() {
		super("Authentication failed, user name or password is invalid !");
	}

	public PinaraXMLValidationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public PinaraXMLValidationException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public PinaraXMLValidationException(String [] message) {
		super(arrayParser(message));
	}

	private static String arrayParser(String [] message) {
		
		StringBuilder builder = new StringBuilder();
		for(String s : message) {
		    builder.append(s);
		}
		
		return builder.toString();
	}

	public PinaraXMLValidationException(String message) {
		super(message);
	}

	public PinaraXMLValidationException(Throwable cause) {
		super(cause);
	}

}
