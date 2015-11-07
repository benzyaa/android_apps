package com.moedojo.colorclick.util;

import android.annotation.SuppressLint;
import java.text.SimpleDateFormat;

@SuppressLint("SimpleDateFormat")
public class DateUtil {
	public static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd 00:00:00";
	public static final String DEFAULT_QUIZ_DISPLAY_DATE_FORMAT = "mm:ss";
	
	public static java.util.Date convertStringToDateUtil(String dateStr) throws Exception{
		return convertStringToDateUtil(dateStr,DateUtil.DEFAULT_DATE_FORMAT);
	}
	
	public static java.util.Date convertStringToDateUtil(String dateStr,String pattern) throws Exception{
		SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
		return dateFormat.parse(dateStr);
	}

	public static String convertDateToString(java.util.Date date){
		return convertDateToString(date, DateUtil.DEFAULT_DATE_FORMAT);
	}
	
	public static String convertDateToString(java.util.Date date, String pattern){
		SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
		return dateFormat.format(date);
	}
}
