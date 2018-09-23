package com.likya.commons.utils;

import java.time.Clock;
import java.time.ZonedDateTime;
import java.util.Calendar;
import java.util.GregorianCalendar;

import com.likya.commons.models.LikyaCustomClock;

public class ClockUtils {
	
	private static Clock activeClock = LikyaCustomClock.getInstance();
	
	public static long getCurrentTimeMilliseconds() {
		return activeClock.millis();
	}
	
	public static Calendar getCalendarInstance() {
		
		ZonedDateTime zdt = ZonedDateTime.now(activeClock);
		GregorianCalendar cal = GregorianCalendar.from(zdt);
		return cal;
	}

}
