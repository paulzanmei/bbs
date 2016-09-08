package com.paul.bs.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
	
	private static SimpleDateFormat YMDHMS = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	public static String formatYMDHMS(Date date){
		return YMDHMS.format(date);
	}
}
