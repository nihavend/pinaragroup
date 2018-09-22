package com.likya.myra.test.vaibaleclock;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.likya.commons.utils.ClockUtils;

public class TestClock {

	public static void main(String[] args) {
		
		System.out.println(getCurrentTimeWithMilliseconds());
		System.out.println(getCurrentTimeWithMillisecondsNEW());

	}
	
	
	public static String getCurrentTimeWithMilliseconds() {
		SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss.S");
		Date timeStr = Calendar.getInstance().getTime();
		return formatter.format(timeStr);
	}

	public static String getCurrentTimeWithMillisecondsNEW() {
		SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss.S");
		Date timeStr = ClockUtils.getCalendarGetInstance().getTime();
		return formatter.format(timeStr);
	}

}
