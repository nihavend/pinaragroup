package com.likya.myra.test.variableclock;

import java.text.SimpleDateFormat;
import java.time.Clock;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class TestSingleton {
	
	static Clock singletonClock;
	static SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss.S");

	public static void main(String[] args) {
		/**
		 * 
		 * 	NANOS - Returns a Instant with the specified number of nanoseconds added. This is equivalent to plusNanos(long).
			MICROS - Returns a Instant with the specified number of microseconds added. This is equivalent to plusNanos(long) with the amount multiplied by 1,000.
			MILLIS - Returns a Instant with the specified number of milliseconds added. This is equivalent to plusNanos(long) with the amount multiplied by 1,000,000.
			SECONDS - Returns a Instant with the specified number of seconds added. This is equivalent to plusSeconds(long).
			MINUTES - Returns a Instant with the specified number of minutes added. This is equivalent to plusSeconds(long) with the amount multiplied by 60.
			HOURS - Returns a Instant with the specified number of hours added. This is equivalent to plusSeconds(long) with the amount multiplied by 3,600.
			HALF_DAYS - Returns a Instant with the specified number of half-days added. This is equivalent to plusSeconds(long) with the amount multiplied by 43,200 (12 hours).
			DAYS - Returns a Instant with the specified number of days added. This is equivalent to plusSeconds(long) with the amount multiplied by 86,400 (24 hours).
		 */

		System.out.println();
		singletonClock = MyClockSingleton.getInstance(); 
		
		Date timeStr = getCalendarInstance().getTime();
		System.out.println(formatter.format(timeStr));
		
		timeStr = new Date(getCurrentTimeMilliseconds());
		System.out.println(formatter.format(timeStr));
		
		System.out.println();
		singletonClock = MyClockSingleton.getInstance(366, ChronoUnit.HOURS);
		
		timeStr = getCalendarInstance().getTime();
		System.out.println(formatter.format(timeStr));
		
		timeStr = new Date(getCurrentTimeMilliseconds());
		System.out.println(formatter.format(timeStr));

		System.out.println();
		singletonClock = MyClockSingleton.getInstance(); 
		
		timeStr = getCalendarInstance().getTime();
		System.out.println(formatter.format(timeStr));
		
		timeStr = new Date(getCurrentTimeMilliseconds());
		System.out.println(formatter.format(timeStr));

	}

	public static long getCurrentTimeMilliseconds() {
		return singletonClock.millis();
	}
	
	public static Calendar getCalendarInstance() {
		
		ZonedDateTime zdt = ZonedDateTime.now(singletonClock);
		GregorianCalendar cal = GregorianCalendar.from(zdt);
		return cal;
	}
}
