package com.likya.myra.test;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

import org.apache.xmlbeans.GDate;
import org.apache.xmlbeans.GDuration;

import com.likya.myra.commons.utils.MyraDateUtils;
import com.likya.xsd.myra.model.generics.TypeOfTimeType;
import com.likya.xsd.myra.model.joblist.AbstractJobType;
import com.likya.xsd.myra.model.jobprops.PeriodInfoDocument.PeriodInfo;
import com.likya.xsd.myra.model.wlagen.TimeManagementDocument.TimeManagement;

public class CopyOfPeriodCalculations {

	public static Calendar forward(AbstractJobType abstractJobType, ArrayList<String> messages) {
		
		PeriodInfo periodInfo = abstractJobType.getManagement().getPeriodInfo();
		
		if(!checkMaxCount(periodInfo)) {
			return null;
		}
		
		TimeManagement timeManagement = abstractJobType.getManagement().getTimeManagement();

		String selectedTZone = timeManagement.getTimeZone();
		TypeOfTimeType.Enum typeOfTimeType = timeManagement.getTypeOfTime();
		Calendar startTime = timeManagement.getJsScheduledTime().getStartTime();

		// System.out.println("periodInfo.getMaxCount().intValue() : " + periodInfo.getMaxCount().intValue());
		// System.out.println("periodInfo.getCounter().intValue() : " + periodInfo.getCounter().intValue());

		periodInfo.setCounter(BigInteger.valueOf(periodInfo.getCounter().intValue() + 1));

		GDuration gDuration = periodInfo.getStep();

		long periodOfRepeatance = getDurationInMilliSecs(gDuration);

		if(selectedTZone == null) {
			selectedTZone= TimeZone.getDefault().getID();
		}
		
		if(typeOfTimeType == null) {
			typeOfTimeType= TypeOfTimeType.ACTUAL;
		}
		
		String startTimeStr = MyraDateUtils.getW3CDateTime(startTime, selectedTZone, typeOfTimeType);
		
		Calendar startDateTime = dateToXmlTime(startTimeStr, selectedTZone);

		Calendar newDateTime = findNextPeriod(startDateTime, periodOfRepeatance, selectedTZone, periodInfo.getRelativeStart());
		
		if (/*!checkStayInDay(newDateTime) ||*/ newDateTime.before(startTime)) {
			if(messages != null) {
				messages.add("Calculated schedule >> " + MyraDateUtils.getDate(newDateTime) + " is before startTime : " + MyraDateUtils.getDate(startTime));	
			}
			return null;
		}

		// System.err.println("before : " + MyraDateUtils.getDate(timeManagement.getJsPlannedTime().getStartTime().getTime()));
		timeManagement.getJsActualTime().setStartTime(newDateTime);
		// System.err.println("real : " + MyraDateUtils.getDate(newDateTime.getTime()));
		// System.err.println("after : " + MyraDateUtils.getDate(timeManagement.getJsPlannedTime().getStartTime().getTime()));
		/**
		 * @author serkan
		 * 05.11.2016
		 * TODO Aşağıdaki kısmı kaldırdım, çünkü gerçekten bir işin başlangıç bitiş zamanları ile
		 * Çalışma aralığı tanımları ayrılmamış ve birbirine girmiş durumda.
		 */
		/*
		if (timeManagement.getBornedPlannedTime().getStopTime() != null) {
			Calendar stopTime = timeManagement.getBornedPlannedTime().getStopTime();
			if (stopTime.before(newDateTime)) {
				if(messages != null) {
					messages.add("Calculated schedule >> " + MyraDateUtils.getDate(newDateTime) + " is after stopTime : " + MyraDateUtils.getDate(stopTime));	
				}
				return null;
			}
		}
		*/
		
		return newDateTime;
	}

	private static Calendar findNextPeriod(Calendar startDateTime, long period, String selectedTZone, boolean isRelativeStart) {

		Date currentTime = Calendar.getInstance().getTime();

		if (isRelativeStart) {
			startDateTime.setTime(currentTime);
		} else {

			long diffDate = currentTime.getTime() - startDateTime.getTimeInMillis();

			if (diffDate < 0) {
				return startDateTime;
			}

			long divDate = diffDate / period;

			if ((divDate * period) < diffDate) {
				++divDate;
			}

			period = divDate * period;

		}
		
		
		// System.err.println(MyraDateUtils.getDate(startDateTime.getTime()));

		Calendar returnCal = addPeriod(startDateTime, period, selectedTZone);

		// System.err.println(MyraDateUtils.getDate(returnCal.getTime()));
		
		return returnCal;

	}

	protected static Calendar addPeriod(Calendar startDateTime, long period, String selectedTZone) {

		ZoneId zone = ZoneId.of(selectedTZone);

		LocalDateTime dateTime = LocalDateTime.ofInstant(startDateTime.toInstant(), zone);

		dateTime = dateTime.plus(period, ChronoUnit.MILLIS);

		ZonedDateTime zdt = ZonedDateTime.of(dateTime, zone);

		GregorianCalendar cal = GregorianCalendar.from(zdt);
		
		return cal;
	}

	public static Date changeYMDPart(Date firstDate, Date secondDate) {

		Calendar calendarFirst = Calendar.getInstance();
		calendarFirst.setTime(firstDate);

		Calendar calendarSecond = Calendar.getInstance();
		calendarSecond.setTime(secondDate);

		calendarSecond.set(Calendar.YEAR, calendarFirst.get(Calendar.YEAR));
		calendarSecond.set(Calendar.MONTH, calendarFirst.get(Calendar.MONTH));
		calendarSecond.set(Calendar.DAY_OF_MONTH, calendarFirst.get(Calendar.DAY_OF_MONTH));

		return calendarSecond.getTime();
	}

//	private static boolean checkStayInDay(Calendar date) {
//
//		Calendar cal = Calendar.getInstance();
//		cal.setTime(date.getTime());
//
//		if (cal.get(Calendar.DAY_OF_MONTH) != Calendar.getInstance().get(Calendar.DAY_OF_MONTH)) {
//			return false;
//		}
//
//		return true;
//	}

	public static long getDurationInMilliSecs(GDuration gDuration) {

		// GDuration gDuration = new GDuration(durationString);

		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(0);

		GDate base = new GDate(cal);
		GDate d = base.add(gDuration);

		long durationInMillis = d.getDate().getTime();

		return durationInMillis;
	}

	protected static Calendar dateToXmlTime(String time, String selectedTZone) {
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(
			    "[yyyy-MM-dd'T'HH:mm:ss.SSSZZ]"
			  + "[HH:mm:ss.SSSZZ]"
			  + "[HH:mm:ss.SSS]"
			  + "[HH:mm:ss]"
			);
		
		DateTimeFormatter dtf = new DateTimeFormatterBuilder().append(formatter).toFormatter();
		
		LocalDateTime localDateTime = LocalDateTime.parse(time, dtf);
		
		ZoneId zone = ZoneId.of(selectedTZone);
		
		ZonedDateTime zdt = ZonedDateTime.of(localDateTime, zone);
		
		GregorianCalendar cal = GregorianCalendar.from(zdt);
		
		return cal;

	}

	// Alternative way of finding datetime from time with timeZone
//	public static String calendarToStringTimeFormat(Calendar time, String selectedTZone, String timeOutputFormat) {
//
//		DateTimeZone zone = DateTimeZone.forID(selectedTZone);
//		LocalTime jobLocalTime = new LocalTime(time);
//		DateTimeFormatter formatter = DateTimeFormat.forPattern(timeOutputFormat);
//		String timeString = jobLocalTime.toDateTimeToday(zone).toString(formatter);
//
//		return timeString;
//	}
	
	public static boolean checkMaxCount(PeriodInfo periodInfo) {
		
		if (periodInfo != null && periodInfo.getMaxCount() != null && periodInfo.getMaxCount().intValue() > 0 && (periodInfo.getCounter().intValue() + 1) >= periodInfo.getMaxCount().intValue()) {
			periodInfo.setCounter(BigInteger.valueOf(0));
			return false;
			
		}
		
		return true;
	}

}
