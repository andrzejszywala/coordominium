package com.coordominium.dates;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DatesUtil {
	private static final DateFormat longFormat = new SimpleDateFormat("yyyy.MM.dd'T'HH:mm:ss.SSSZ");
	private static final DateFormat shortFormat = new SimpleDateFormat("yyyy.MM.dd");
	private static final DateFormat simpleFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");

	public static final Calendar END = new GregorianCalendar(9999, 11, 31, 23, 59, 59);
	
	public static Calendar endOfDay(Calendar date) {
		if (date == null) {
			return null;
		}
		Calendar result = Calendar.getInstance();
		result.setTime(date.getTime());
		result.set(Calendar.HOUR_OF_DAY, 23);
		result.set(Calendar.MINUTE, 59);
		result.set(Calendar.SECOND, 59);
		result.set(Calendar.MILLISECOND, 999);
		return result;
	}
	
	public static Calendar beginOfDay(Calendar date) {
		if (date == null) {
			return null;
		}
		Calendar result = Calendar.getInstance();
		result.setTime(date.getTime());
		result.set(Calendar.HOUR_OF_DAY, 0);
		result.set(Calendar.MINUTE, 0);
		result.set(Calendar.SECOND, 0);
		result.set(Calendar.MILLISECOND, 0);
		return result;
	}
	
	public static String dateTimeString(Calendar time) {
		if (time == null) {
			return null;
		}
		return javax.xml.bind.DatatypeConverter.printDateTime(time);
	}
	
	public static Calendar asCalendar(String date) {
		if (date == null || date.trim().isEmpty()) {
			return null;
		}
		
		Calendar result = null;
		try {
			result = javax.xml.bind.DatatypeConverter.parseDateTime(date);
		} catch (IllegalArgumentException e) {
			try {
				result = javax.xml.bind.DatatypeConverter.parseDate(date);
			} catch (IllegalArgumentException e1) {
				try {
					Calendar tmp = Calendar.getInstance();
					tmp.setTime(longFormat.parse(date));
					result = tmp;
				} catch (ParseException e2) {
					return null;
				}
			}
		}
		return result;
	}
}
