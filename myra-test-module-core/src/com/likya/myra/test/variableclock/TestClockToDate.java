package com.likya.myra.test.variableclock;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.likya.commons.utils.ClockUtils;
import com.likya.commons.utils.DateUtils;

public class TestClockToDate {
	
	static SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss.S");

	public static void main(String[] args) {

		Date date = DateUtils.getDate();
		System.out.println(formatter.format(date));
		
		date = ClockUtils.getDate();
		System.out.println(formatter.format(date));
	}

}
