package com.likya.myra.commons.utils;

import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.TimeZone;

import org.apache.xmlbeans.GDate;
import org.apache.xmlbeans.GDuration;

import com.likya.commons.utils.DateUtils;
import com.likya.xsd.myra.model.generics.TypeOfTimeType;
import com.likya.xsd.myra.model.wlagen.JsRecordedTimeDocument.JsRecordedTime;

public class MyraDateUtils extends DateUtils {
	
	public static String jobRecordedTimeToString(JsRecordedTime jsRecordedTime, boolean startTime, boolean transformToLocalTime) {
		// clienttan alacagimiz degerlerle dolduracagiz
		int clientZoneOffset = 7200000; // milisecond
		int clientDSTOffset = 3600000; // milisecond

		Calendar jobCalendar = DateUtils.getCalendarInstance();

		if (startTime) {
			jobCalendar.set(Calendar.YEAR, jsRecordedTime.getStartTime().get(Calendar.YEAR));
			jobCalendar.set(Calendar.MONTH, jsRecordedTime.getStartTime().get(Calendar.MONTH));
			jobCalendar.set(Calendar.DAY_OF_MONTH, jsRecordedTime.getStartTime().get(Calendar.DAY_OF_MONTH));
			jobCalendar.set(Calendar.HOUR_OF_DAY, jsRecordedTime.getStartTime().get(Calendar.HOUR_OF_DAY));
			jobCalendar.set(Calendar.MINUTE, jsRecordedTime.getStartTime().get(Calendar.MINUTE));
			jobCalendar.set(Calendar.SECOND, jsRecordedTime.getStartTime().get(Calendar.SECOND));
			jobCalendar.set(Calendar.ZONE_OFFSET, jsRecordedTime.getStartTime().get(Calendar.ZONE_OFFSET));
			jobCalendar.set(Calendar.DST_OFFSET, 0);
		} else {
			jobCalendar.set(Calendar.YEAR, jsRecordedTime.getStartTime().get(Calendar.YEAR));
			jobCalendar.set(Calendar.MONTH, jsRecordedTime.getStartTime().get(Calendar.MONTH));
			jobCalendar.set(Calendar.DAY_OF_MONTH, jsRecordedTime.getStartTime().get(Calendar.DAY_OF_MONTH));
			jobCalendar.set(Calendar.HOUR_OF_DAY, jsRecordedTime.getStopTime().get(Calendar.HOUR_OF_DAY));
			jobCalendar.set(Calendar.MINUTE, jsRecordedTime.getStopTime().get(Calendar.MINUTE));
			jobCalendar.set(Calendar.SECOND, jsRecordedTime.getStopTime().get(Calendar.SECOND));
			jobCalendar.set(Calendar.ZONE_OFFSET, jsRecordedTime.getStopTime().get(Calendar.ZONE_OFFSET));
			jobCalendar.set(Calendar.DST_OFFSET, 0);
		}

		int localTimeShift = clientDSTOffset + clientZoneOffset;
		int jobTimeShift = jobCalendar.get(Calendar.ZONE_OFFSET);

		if (!transformToLocalTime) {
			String todayDate = appendZero(jobCalendar.get(Calendar.DATE)) + "." + appendZero(jobCalendar.get(Calendar.MONTH) + 1) + "." + jobCalendar.get(Calendar.YEAR);
			String jobTime = todayDate + " " + appendZero(jobCalendar.get(Calendar.HOUR_OF_DAY)) + ":" + appendZero(jobCalendar.get(Calendar.MINUTE)) + ":" + appendZero(jobCalendar.get(Calendar.SECOND));

			if (jobTimeShift != localTimeShift) {
				jobTime += getTimeZoneStr(jobTimeShift);
			}

			return jobTime;

		} else {
			SimpleDateFormat formatter = null;

			// formatter = new SimpleDateFormat("HH:mm:ss Z");
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

			return formatter.format(jobCalendar.getTime());
		}
	}

	// jobin baslangic ve bitis zamanlarindan calisma suresi hesaplaniyor
	public static String getJobWorkDuration(JsRecordedTime jsRecordedTime, boolean pastExecution) {

		if (jsRecordedTime == null) {
			return "-";
		}

		Calendar startCalendar = DateUtils.getCalendarInstance();
		startCalendar.set(Calendar.HOUR_OF_DAY, jsRecordedTime.getStartTime().get(Calendar.HOUR_OF_DAY));
		startCalendar.set(Calendar.MINUTE, jsRecordedTime.getStartTime().get(Calendar.MINUTE));
		startCalendar.set(Calendar.SECOND, jsRecordedTime.getStartTime().get(Calendar.SECOND));

		Calendar stopCalendar = DateUtils.getCalendarInstance();

		// is bitmisse
		if (jsRecordedTime.getStopTime() != null) {
			stopCalendar.set(Calendar.HOUR_OF_DAY, jsRecordedTime.getStopTime().get(Calendar.HOUR_OF_DAY));
			stopCalendar.set(Calendar.MINUTE, jsRecordedTime.getStopTime().get(Calendar.MINUTE));
			stopCalendar.set(Calendar.SECOND, jsRecordedTime.getStopTime().get(Calendar.SECOND));
		} else {
			if (pastExecution) {
				return "-";
			}
		}

		Long diff = (stopCalendar.getTimeInMillis() - startCalendar.getTimeInMillis()) / 1000;

		Long hour = diff / 3600;
		Long min = (diff - hour * 3600) / 60;
		Long sec = diff - hour * 3600 - min * 60;

		String workDuration = appendZero(hour.intValue()) + ":" + appendZero(min.intValue()) + ":" + appendZero(sec.intValue());

		return workDuration;
	}

//  JODA
//	public static String getServerW3CDateTime() {
//
//		String serverTimeZone = new String("Europe/Istanbul");
//
//		TimeZone timeZone = TimeZone.getTimeZone(serverTimeZone);
//		Calendar calendar = Calendar.getInstance(timeZone);
//
//		DateTimeZone zone = DateTimeZone.forID(serverTimeZone);
//
//		LocalTime jobLocalTime = new LocalTime(calendar.getTime(), zone);
//		LocalDate t = new LocalDate(calendar.getTime(), zone);
//		DateTime dt = t.toDateTime(jobLocalTime, zone);
//
//		//		boolean isStandardOffset = zone.isStandardOffset(dt.getMillis());
//		//		boolean isDaylightOfset = !isStandardOffset;
//
//		String outputFormat = new String("yyyy-MM-dd'T'HH:mm:ss.SSSZZ");
//		String dateStr = dt.toString(outputFormat);
//
//		return dateStr;
//	}
	
	public static String getServerW3CDateTime() {

		String serverTimeZone = new String("Europe/Istanbul");

		ZoneId zone = ZoneId.of(serverTimeZone);

		ZonedDateTime zonedDateTime = ZonedDateTime.now(zone);
				
		// String outputFormat1 = new String("yyyy-MM-dd'T'HH:mm:ss.SSSZZ");
		// String dateStr1 = zonedDateTime.format(DateTimeFormatter.ofPattern(outputFormat1));
		// OUT : 2017-10-29T01:22:11.046+0300

		String outputFormat = new String("yyyy-MM-dd'T'HH:mm:ss.SSS[XXX]");
		String dateStr = zonedDateTime.format(DateTimeFormatter.ofPattern(outputFormat));
		// OUT : 2017-10-29T01:22:55.907+03:00
		
		return dateStr;
	}

	// JODA
//	public static String getW3CDateTime(Calendar calendar, String selectedTZone, TypeOfTimeType.Enum myType) {
//
//
//		String selectedTimeZone = new String(selectedTZone);
//
//
//		DateTimeZone zone = DateTimeZone.forID(selectedTimeZone);
//
//		int timeOffSet = calendar.getTimeZone().getRawOffset() / 3600000; // İşin tanımlandığı andaki Offset i, ornek +02:00
//		LocalDateTime localDateTime = null;
//		switch (myType.intValue()) {
//		case TypeOfTimeType.INT_ACTUAL:
//			localDateTime = new LocalDateTime(calendar.getTime(), zone);
//			break;
//
//		case TypeOfTimeType.INT_RECURRING:
//			localDateTime = new LocalDateTime(calendar.getTime(), DateTimeZone.forOffsetHours(timeOffSet));
//			break;
//
//		case TypeOfTimeType.INT_BROADCAST: // Burası nasıl olacak tam karar vermedim.
//			localDateTime = new LocalDateTime(calendar.getTime(), DateTimeZone.UTC);
//			break;
//
//		default:
//			break;
//		}
//
//		//LocalDate t = new LocalDate(calendar.getTime(), zone);
//		DateTime dt = localDateTime.toDateTime(zone);
//
//		String outputFormat = new String("yyyy-MM-dd'T'HH:mm:ss.SSSZZ");
//		String dateStr = dt.toString(outputFormat);
//
//		return dateStr;
//	}

	public static String getW3CDateTime(Calendar calendar, String selectedTZone, TypeOfTimeType.Enum myType) {

		String selectedTimeZone = new String(selectedTZone);

		ZoneId zone = ZoneId.of(selectedTimeZone);

		int timeOffSet = calendar.getTimeZone().getRawOffset() / 3600000; // İşin tanımlandığı andaki Offset i, ornek +02:00

		ZonedDateTime zonedDateTime = null;
		// Seçilen zaman tipine göre işin çalışma zamanını belirleyelim
		switch (myType.intValue()) {
		case TypeOfTimeType.INT_ACTUAL:
			zonedDateTime = ZonedDateTime.ofInstant(calendar.toInstant(), zone);
			break;

		case TypeOfTimeType.INT_RECURRING:
			zonedDateTime = ZonedDateTime.ofInstant(calendar.toInstant(), ZoneOffset.ofHours(timeOffSet));
			break;

		case TypeOfTimeType.INT_BROADCAST:
			zonedDateTime = ZonedDateTime.ofInstant(calendar.toInstant(), ZoneOffset.UTC);
			break;

		default:
			break;
		}

		String outputFormat = new String("yyyy-MM-dd'T'HH:mm:ss.SSSZZ");
		String dateStr = zonedDateTime.format(DateTimeFormatter.ofPattern(outputFormat));
		
		return dateStr;
	}

	
	public static long getDurationInMilliSecs(GDuration gDuration) {

		// GDuration gDuration = new GDuration(durationString);

		Calendar cal = DateUtils.getCalendarInstance();
		cal.setTimeInMillis(0);

		GDate base = new GDate(cal);
		GDate d = base.add(gDuration);

		long durationInMillis = d.getDate().getTime();

		return durationInMillis;
	}
	
	public static Calendar setTimePart(Calendar srcCal) {
		return setTimePart(srcCal, DateUtils.getCalendarInstance());
	}
	
	public static Calendar setTimePart(Calendar srcCal, Calendar destCal) {
		
		int hourOfPlannedTime = srcCal.get(Calendar.HOUR_OF_DAY);
		int minuteOfPlannedTime = srcCal.get(Calendar.MINUTE);
		int secondOfPlannedTime = srcCal.get(Calendar.SECOND);
		
		destCal.set(Calendar.HOUR_OF_DAY, hourOfPlannedTime);
		destCal.set(Calendar.MINUTE, minuteOfPlannedTime);
		destCal.set(Calendar.SECOND, secondOfPlannedTime);
		destCal.set(Calendar.MILLISECOND, 0);
		
		return destCal;
	}
}
