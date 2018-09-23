package com.likya.commons.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.StringTokenizer;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DateUtils {

	public static long dateDiffWithNow(Calendar sDate) {

		Date now = getCalendarInstance().getTime();
		long timeDiff = now.getTime() - sDate.getTimeInMillis();

		return timeDiff;
	}

	public static long dateDiffWithNow(Date sDate) {

		Date now = getCalendarInstance().getTime();
		long timeDiff = now.getTime() - sDate.getTime();

		return timeDiff;
	}

	public static long dateDiffWithNow(long sDate) {

		Date now = getCalendarInstance().getTime();
		long timeDiff = now.getTime() - sDate;

		return timeDiff;
	}

	public static int [] getFormattedDuration(Date sDate) {
		if (sDate == null) {
			return null;
		}
		return DateUtils.getFormattedElapsedTime((int) dateDiffWithNow(sDate) / 1000);
	}

	public static String getUnFormattedDuration(Date sDate) {
		if (sDate == null) {
			return null;
		}
		return DateUtils.getUnFormattedElapsedTime((int) dateDiffWithNow(sDate) / 1000);
	}

	public static String getFormattedElapsedTimeMS(long timeInMilliSeconds) {

		long hours, minutes, seconds, milliseconds;

		hours = timeInMilliSeconds / (1000 * 3600);

		timeInMilliSeconds = timeInMilliSeconds - (hours * 1000 * 3600);

		minutes = timeInMilliSeconds / (1000 * 60);

		timeInMilliSeconds = timeInMilliSeconds - (minutes * 1000 * 60);

		seconds = timeInMilliSeconds / 1000;

		timeInMilliSeconds = timeInMilliSeconds - (seconds * 1000);

		milliseconds = timeInMilliSeconds;

		// System.out.println(hours + " hour(s) " + minutes + " minute(s) " +
		// seconds + " second(s)");

		return hours + " saat " + minutes + " dakika " + seconds + " saniye " + milliseconds + " milisaniye";
	}

	public static Date getDateTime(String dateTimeInString) {

		DateFormat dateTimeFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
		Date dateTime = null;

		try {

			dateTime = dateTimeFormat.parse(dateTimeInString);

			// System.out.println("Date and Time: " + date);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return dateTime;

	}

	public static Date getTime(String timeInString) {

		DateFormat dateFormat = new SimpleDateFormat("hh:mm:ss");
		Date date = null;

		try {

			date = dateFormat.parse(timeInString);

			// System.out.println("Date and Time: " + date);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return date;

	}

	public static Calendar normalizeDate(Calendar calendar) {

		Calendar tmpCalendar = getCalendarInstance();

		tmpCalendar.set(Calendar.HOUR_OF_DAY, calendar.get(Calendar.HOUR_OF_DAY));
		tmpCalendar.set(Calendar.MINUTE, calendar.get(Calendar.MINUTE));
		tmpCalendar.set(Calendar.SECOND, calendar.get(Calendar.SECOND));

		return tmpCalendar;
	}

	public static String calendarToString(Calendar date, boolean transformToLocalTime) {
		// clienttan alacagimiz degerlerle dolduracagiz
		int clientZoneOffset = 7200000; // milisecond
		int clientDSTOffset = 3600000; // milisecond

		String stringDate = "";

		if (!transformToLocalTime) {

			stringDate = appendZero(date.get(Calendar.DATE)) + "." + appendZero(date.get(Calendar.MONTH) + 1) + "." + date.get(Calendar.YEAR);
			stringDate += " " + appendZero(date.get(Calendar.HOUR_OF_DAY)) + ":" + appendZero(date.get(Calendar.MINUTE)) + ":" + appendZero(date.get(Calendar.SECOND));

			int localTimeShift = clientDSTOffset + clientZoneOffset;
			int dateTimeShift = date.get(Calendar.DST_OFFSET) + date.get(Calendar.ZONE_OFFSET);

			if (localTimeShift != dateTimeShift) {
				stringDate += getTimeZoneStr(dateTimeShift);
			}

		} else {
			SimpleDateFormat formatter = null;
			formatter = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");

			TimeZone timeZone = TimeZone.getDefault();
			if (clientDSTOffset == timeZone.getDSTSavings()) {
				timeZone.setRawOffset(clientZoneOffset);
			} else if (clientDSTOffset > timeZone.getDSTSavings()) {
				timeZone.setRawOffset(clientZoneOffset + clientDSTOffset);
			} else {
				timeZone.setRawOffset(clientZoneOffset - timeZone.getDSTSavings());
			}
			formatter.setTimeZone(timeZone);

			stringDate = formatter.format(date.getTime());
		}

		return stringDate;
	}

	public static String jobTimeToString(Calendar timeCalendar, boolean transformToLocalTime) {

		int clientZoneOffset = 7200000; // milisecond
		int clientDSTOffset = 3600000; // milisecond

		Calendar jobCalendar = getCalendarInstance();

		jobCalendar.set(Calendar.HOUR_OF_DAY, timeCalendar.get(Calendar.HOUR_OF_DAY));
		jobCalendar.set(Calendar.MINUTE, timeCalendar.get(Calendar.MINUTE));
		jobCalendar.set(Calendar.SECOND, timeCalendar.get(Calendar.SECOND));
		jobCalendar.set(Calendar.ZONE_OFFSET, timeCalendar.get(Calendar.ZONE_OFFSET));
		jobCalendar.set(Calendar.DST_OFFSET, 0);

		int localTimeShift = clientDSTOffset + clientZoneOffset;
		int jobTimeShift = jobCalendar.get(Calendar.ZONE_OFFSET);

		if (!transformToLocalTime) {
			String jobTime = appendZero(jobCalendar.get(Calendar.HOUR_OF_DAY)) + ":" + appendZero(jobCalendar.get(Calendar.MINUTE)) + ":" + appendZero(jobCalendar.get(Calendar.SECOND));

			if (jobTimeShift != localTimeShift) {
				jobTime += getTimeZoneStr(jobTimeShift);
			}

			return jobTime;

		} else {
			SimpleDateFormat formatter = null;

			// formatter = new SimpleDateFormat("HH:mm:ss Z");
			formatter = new SimpleDateFormat("HH:mm:ss");

			TimeZone timeZone = TimeZone.getDefault();
			if (clientDSTOffset == timeZone.getDSTSavings()) {
				timeZone.setRawOffset(clientZoneOffset);
			} else if (clientDSTOffset > timeZone.getDSTSavings()) {
				timeZone.setRawOffset(clientZoneOffset + clientDSTOffset);
			} else {
				timeZone.setRawOffset(clientZoneOffset - timeZone.getDSTSavings());
			}
			formatter.setTimeZone(timeZone);

			return formatter.format(jobCalendar.getTime());
		}
	}

	protected static String getTimeZoneStr(int timeShift) {

		String timeZome = "";

		if (timeShift > 0) {
			timeZome += "+";

		} else {
			timeZome += "-";

			timeShift = timeShift * (-1);
		}

		int zoneHour = timeShift / 3600000;
		int zoneMin = (timeShift - zoneHour * 3600000) / 60000;

		timeZome += appendZero(zoneHour) + ":" + appendZero(zoneMin);

		return timeZome;
	}

	public static String appendZero(int value) {
		String zeroAppendedValue = "" + value;

		if (value < 10 && (value + "").length() == 1) {
			zeroAppendedValue = "0" + value;
		}

		return zeroAppendedValue;
	}

	/*
	 * public static Long timeToLong(Time time) {
	 * Long longTime = Long.parseLong(time.getHour()) * 3600 + Long.parseLong(time.getMinute()) * 60
	 * + Long.parseLong(time.getSecond());
	 * 
	 * return longTime;
	 * }
	 */


	public static String getCurrentTimeWithMilliseconds() {
		SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss.S");
		Date timeStr = getCalendarInstance().getTime();
		return formatter.format(timeStr);
	}

	public static String getCurrentTimeForFileName() {
		SimpleDateFormat formatter = new SimpleDateFormat("ddMMyyyy_HHmmssS");
		Date timeStr = getCalendarInstance().getTime();
		return formatter.format(timeStr);
	}

	public static String getW3CDateTime() {
		Date date = getDate();
		SimpleDateFormat dateFormater = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ", Locale.ENGLISH);
		TimeZone zone = dateFormater.getTimeZone();
		dateFormater.setTimeZone(zone);
		return dateFormater.format(date);
	}

	public static long getCurrentTimeMilliseconds() {
		// return System.currentTimeMillis();
		return ClockUtils.getCurrentTimeMilliseconds();
	}

	public static Calendar getCalendarInstance() {
		// return Calendar.getInstance();
		return ClockUtils.getCalendarInstance();
	}
	
	public static Date changeDateValue(Date firstDate, Date secondDate) {

		Calendar calendarFirst = getCalendarInstance();
		calendarFirst.setTime(firstDate);

		Calendar calendarSecond = getCalendarInstance();
		calendarSecond.setTime(secondDate);

		calendarSecond.set(Calendar.YEAR, calendarFirst.get(Calendar.YEAR));
		calendarSecond.set(Calendar.MONTH, calendarFirst.get(Calendar.MONTH));
		calendarSecond.set(Calendar.DAY_OF_MONTH, calendarFirst.get(Calendar.DAY_OF_MONTH));

		return calendarSecond.getTime();
	}

	public static boolean checkStayInDay(Date date) {

		Calendar cal = getCalendarInstance();
		cal.setTime(date);

		if (cal.get(Calendar.DAY_OF_MONTH) != getCalendarInstance().get(Calendar.DAY_OF_MONTH)) {
			return false;
		}

		return true;
	}

	public static boolean dateValidator(String dateToValidate, String dateFromat) {

		/**
		 * Thanks to;
		 * http://www.mkyong.com/java/how-to-check-if-date-is-valid-in-java/
		 */

		if (dateToValidate == null) {
			return false;
		}

		SimpleDateFormat sdf = new SimpleDateFormat(dateFromat);
		sdf.setLenient(false);

		try {

			//if not valid, it will throw ParseException
			// Date date = 
			sdf.parse(dateToValidate);
			// System.out.println(date);

		} catch (ParseException e) {
			// e.printStackTrace();
			return false;
		}

		return true;
	}
	
	
	public static boolean dateValidator(String dateToValidate) {
		return dateValidator(dateToValidate, "dd/MM/yyyy");
	}
	
	public static long daysBetween(Date d1, Date d2) {
		//return ((d2.getTime() - d1.getTime() + ONE_HOUR) / (ONE_HOUR * 24));
		return ((d2.getTime() - d1.getTime() + (60 * 60 * 1000L)) / (60 * 60 * 1000L * 24));
	}
	
	public static long diff(Date sDate, Date fDate) {
		
		long timeDiff = fDate.getTime() - sDate.getTime();
		return timeDiff;
	}
	
//	public static Date findNextPeriod(Date nextPeriodTime, Long period) {
//		
//		period = period * 1000; // Convert to milliseconds
//		Date currentTime = getCalendarInstance().getTime();
//		// System.out.println(currentTime + "\n" + nextPeriodTime);
//		
//		long diffDate = currentTime.getTime() - nextPeriodTime.getTime();
//		
//		if(diffDate < 0) {
//			return nextPeriodTime;
//		}
//		
//		long divDate = diffDate / period;
//		// System.out.println(diffDate);
//		// System.out.println(divDate * period);
//		if((divDate * period) < diffDate) {
//			++ divDate;
//		}
//		
//		long newTime = divDate * period;
//		// System.out.println(newTime);
//		nextPeriodTime = new Date(nextPeriodTime.getTime() + newTime);
//		// System.out.println(nextPeriodTime.getTime() + newTime);
//		
//		// System.out.println(nextPeriodTime);
//		return nextPeriodTime;
//	}
	
	public static Date findNextPeriod(Date nextPeriodTime, Long period) {
		boolean loop = true;

		while (loop) {
			Date currentTime = getCalendarInstance().getTime();
			if (nextPeriodTime.before(currentTime)) {
				nextPeriodTime = DateUtils.longtoDate(nextPeriodTime.getTime() + period);
			} else {
				loop = false;
			}

		}

		return nextPeriodTime;
	}

	public static Date generateJobDate(String dateTimeInfo) {

		StringTokenizer dateTokenizer = new StringTokenizer(dateTimeInfo, " "); //$NON-NLS-1$
		
		Calendar calendar = getCalendarInstance();
		
		String timeToken = null;
		String dateToken = null;
		
		/**
		 * Eğer tarih de verildiyse token = 2 olacak Sadece saat
		 * verildiyse token = 1 olacak
		 */
		if (dateTokenizer.countTokens() == 1) {
			timeToken = dateTokenizer.nextToken();
		} else {
			dateToken = dateTokenizer.nextToken();
			timeToken = dateTokenizer.nextToken();
		}
		
		if((dateToken != null && !DateUtils.dateValidator(dateToken)) || !DateUtils.timeValidator(timeToken)) {
			return null;
		}
		
		if (dateToken != null) {
			StringTokenizer dTokenizer = new StringTokenizer(dateToken, "/"); //$NON-NLS-1$
			calendar.set(Calendar.DAY_OF_MONTH, Integer.parseInt(dTokenizer.nextToken()));
			calendar.set(Calendar.MONTH, Integer.parseInt(dTokenizer.nextToken()) - 1);
			calendar.set(Calendar.YEAR, Integer.parseInt(dTokenizer.nextToken()));
		}

		StringTokenizer timeTokenizer = new StringTokenizer(timeToken, ":"); //$NON-NLS-1$

		int hour = Integer.parseInt(timeTokenizer.nextToken());
		int minute = Integer.parseInt(timeTokenizer.nextToken());
		int second = Integer.parseInt(timeTokenizer.nextToken());

		calendar.set(Calendar.HOUR_OF_DAY, hour);
		calendar.set(Calendar.MINUTE, minute);
		calendar.set(Calendar.SECOND, second);

		calendar.set(Calendar.MILLISECOND, 0);

	    return  calendar.getTime();
	}

	public static Date setForToday(Date myDate) {
		
		Calendar todayCalendar = getCalendarInstance();
		Calendar myCalendar = getCalendarInstance();
		myCalendar.setTime(myDate);
		
		myCalendar.set(Calendar.MONTH, todayCalendar.get(Calendar.MONTH));
		myCalendar.set(Calendar.DAY_OF_MONTH, todayCalendar.get(Calendar.DAY_OF_MONTH));
		myCalendar.set(Calendar.YEAR, todayCalendar.get(Calendar.YEAR));
		
		return myCalendar.getTime();
	}
	
	public static long getDurationNumeric(Date sDate) {
		Date now = getCalendarInstance().getTime();
		long timeDiff = now.getTime() - sDate.getTime();
		return timeDiff;
	}
	
	@Deprecated
	/**
	 * Use one of the new methods getFormattedDuration or getUnFormattedDuration
	 * getDuration is mapped to getFormattedDuration due to the backward compatibility
	 * restrictions
	 * @param sDate
	 * @return
	 */
	public static String getDuration(Date sDate) {
		return getUnFormattedDuration(sDate);
	}

	public static String getUnFormattedElapsedTime(long timeInSeconds) {
		long hours, minutes, seconds;

		hours = timeInSeconds / 3600;
		timeInSeconds = timeInSeconds - (hours * 3600);
		minutes = timeInSeconds / 60;
		timeInSeconds = timeInSeconds - (minutes * 60);
		seconds = timeInSeconds;
		// System.out.println(hours + " hour(s) " + minutes + " minute(s) " +
		// seconds + " second(s)");

		return getDigitStr(hours) + ":" + getDigitStr(minutes) + ":" + getDigitStr(seconds);
	}
	
	public static String getUnFormattedElapsedTimeInMilliSec(long timeInMilliSeconds) {
		
		long timeInSeconds = (long) timeInMilliSeconds / 1000;
		long milliSeconds = timeInMilliSeconds - (timeInSeconds * 1000);
		
		return getUnFormattedElapsedTime(timeInSeconds) + "." + milliSeconds;
	}
	
	public static int [] getFormattedElapsedTime(int timeInSeconds) {
		int hours, minutes, seconds;
		hours = timeInSeconds / 3600;
		timeInSeconds = timeInSeconds - (hours * 3600);
		minutes = timeInSeconds / 60;
		timeInSeconds = timeInSeconds - (minutes * 60);
		seconds = timeInSeconds;
//		System.out.println(hours + " hour(s) " + minutes + " minute(s) " + seconds + " second(s)");
		// return hours + " saat " + minutes + " dakika " + seconds + " saniye";
		
		int values [] = {hours, minutes, seconds};
		
		return  values;// hours + LocaleMessages.getString(localePath, "DateUtils.2") + minutes + LocaleMessages.getString(localePath, "DateUtils.3") + seconds + LocaleMessages.getString(localePath, "DateUtils.4");
	}

	private static String getDigitStr(long digit) {
		if (digit == 0) {
			return "00";
		} else if (digit < 10) {
			return "0" + digit;
		} else {
			return "" + digit;
		}
	}
	
	public static Date getDate() {
		//return new Date() 
		return ClockUtils.getDate();
	}
	
	public static String getDate(Calendar executionTime) {
		return getDate(executionTime.getTime());
	}
	
	public static String getDate(Date executionTime) {
		SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
		return formatter.format(executionTime);
	}
	
	public static String getTime(Date executionTime) {
		SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
		return formatter.format(executionTime);
	}

	public static Date longtoDate(Long longDate) {
		Date date = getDate();
	    date.setTime(longDate);

	    return  date;
	}

	public static boolean timeValidator(String timeToValidate) {

		/**
		 * Thanks to;
		 * http://www.mkyong.com/regular-expressions/how-to-validate-time-in-24-
		 * hours-format-with-regular-expression/
		 */

		final String TIME24HOURS_PATTERN = "([01]?[0-9]|2[0-3]):[0-5][0-9]:[0-5][0-9]";
		
		Pattern pattern;
		Matcher matcher;
		  
		pattern = Pattern.compile(TIME24HOURS_PATTERN);

		matcher = pattern.matcher(timeToValidate);
		
		return matcher.matches();

	}
}
