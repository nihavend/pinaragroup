package com.likya.myra.test.variableclock;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import com.likya.commons.utils.ClockUtils;

public class TestClock {
	
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
		//ClockUtils.activeClock = new MyClock(3650, ChronoUnit.DAYS);

		// ClockUtils.activeClock = Clock.systemUTC();
		
		System.out.println(getCurrentTimeWithMilliseconds());
		System.out.println(getCurrentTimeWithMillisecondsNEW());
		System.out.println();
		System.out.println(getCalendarInstance());
		System.out.println(getCalendarInstanceNEW());
		
		System.out.println(TimeZone.getDefault().toString());
		
		//System.out.println(Instant.now().toString());
		
	}

	public static String getCalendarInstance() {
		Date timeStr = Calendar.getInstance().getTime();
		return formatter.format(timeStr);
	}

	public static String getCalendarInstanceNEW() {
		Date timeStr = ClockUtils.getCalendarInstance().getTime();
		return formatter.format(timeStr);
	}

	
	public static String getCurrentTimeWithMilliseconds() {
		Date timeStr = new Date(System.currentTimeMillis());
		return formatter.format(timeStr);
	}

	public static String getCurrentTimeWithMillisecondsNEW() {
		Date timeStr =  new Date(ClockUtils.getCurrentTimeMilliseconds());
		return formatter.format(timeStr);
	}

}
