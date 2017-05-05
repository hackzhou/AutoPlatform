package com.auto.test.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
	
	public static String date2String(Date date, String format){
		return new SimpleDateFormat(format).format(date);
	}

}
