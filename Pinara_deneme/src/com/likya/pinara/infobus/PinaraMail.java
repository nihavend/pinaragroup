package com.likya.pinara.infobus;

public abstract class PinaraMail {
	
	public static final int SIMPLE = 0;
	public static final int WELCOME = 1;
	public static final int ENDOFCYCLE = 2;
	
	private int MAIL_TYPE = -1;

	public int getMAIL_TYPE() {
		return MAIL_TYPE;
	}

	public void setMAIL_TYPE(int mail_type) {
		MAIL_TYPE = mail_type;
	}
}
