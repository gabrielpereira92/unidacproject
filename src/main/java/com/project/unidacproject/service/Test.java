package com.project.unidacproject.service;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class Test {



	public static void main(String[] args) throws NoSuchAlgorithmException, UnsupportedEncodingException, ParseException {

		String datatest = "28-12-2019";
		
		Calendar pDate = parserDate(datatest);
		
		Date d = new Date();

		Calendar cal = new GregorianCalendar();

//		cal.setTime(d.getTime());
		
		if(cal.compareTo(pDate) < 0 ) {
			System.out.println("Data válida");
			
		}
		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		System.out.println(formatter.format(cal.getTime()));
	}
	
	private final static String DATE_PATTERN = "yyyy-MM-dd";
	public static Calendar parserDate(String dateStr) throws java.text.ParseException {
		Date date = new SimpleDateFormat(DATE_PATTERN).parse(dateStr);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar;
	}
}

