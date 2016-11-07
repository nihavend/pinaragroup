package com.likya.pinara.model;

public class PinaraAuthenticationException extends Throwable {

	private static final long serialVersionUID = -4479492898487738442L;

	public PinaraAuthenticationException() {
		super("Authentication failed, user name or password is invalid !");
	}

	public PinaraAuthenticationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public PinaraAuthenticationException(String message, Throwable cause) {
		super(message, cause);
	}

	public PinaraAuthenticationException(String message) {
		super(message);
	}

	public PinaraAuthenticationException(Throwable cause) {
		super(cause);
	}

}
