package com.emicalculation.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {

	public static String getTimeStamp(){
		Date date = new Date();
		SimpleDateFormat ft = new SimpleDateFormat ("dd.MM.yyyy_HH.mm.ss");
		String str = ft.format(date);
		return str;
	}
	
	public static String getTodayDate() {
		Date date = new Date();
		SimpleDateFormat ft = new SimpleDateFormat ("E',' dd MMM");
		String str = ft.format(date);
		return str;
	}
}
