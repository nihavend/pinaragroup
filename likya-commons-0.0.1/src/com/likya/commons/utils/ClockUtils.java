package com.likya.commons.utils;

import java.time.Clock;
import java.time.ZonedDateTime;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class ClockUtils {
	
	public static Clock activeClock = Clock.systemDefaultZone();
	
	public static long getCurrentTimeMilliseconds() {
		return activeClock.millis();
	}
	
	public static Calendar getCalendarGetInstance() {
		
		ZonedDateTime zdt = ZonedDateTime.now(activeClock);
		GregorianCalendar cal = GregorianCalendar.from(zdt);
		return cal;
	}

}
