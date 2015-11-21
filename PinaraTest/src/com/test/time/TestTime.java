package com.test.time;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TestTime {

	public static void main(String[] args) {
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
		Date now = new Date();
	    String strDate = sdf.format(now);
		System.out.println(strDate);
	}
}
