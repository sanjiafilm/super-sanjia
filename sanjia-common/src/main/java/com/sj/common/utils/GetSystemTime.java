package com.sj.common.utils;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class GetSystemTime {
	private static Date date;
	
	public static Date getCurrentTime(){
		date = new Date();
		return date;
	}
	
	public static Date getTomorrow(){		
			Calendar calendar = Calendar.getInstance();
 	        calendar.setTime(date);
 	        calendar.set(Calendar.DATE, calendar.get(Calendar.DATE) + 1);
 	        Date tomorrow = calendar.getTime();	
			return tomorrow;
	}
}
