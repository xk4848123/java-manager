package com.nidecai.managerndc.common.codeutil;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;


public class DateUtil {
	public static int getTimeBeforeDay(int days) {
		int currentTime = (int) (System.currentTimeMillis() / 1000);
		int seconds = days * 24 * 3600;
		return currentTime - seconds;
		
	}

	public static int getUnixTime(String dateStr) {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		int unixTime = 0;
		try {
			long time = df.parse(dateStr).getTime();
			unixTime = (int) (time / 1000);
			
		} catch (ParseException e) {
		}
		return unixTime;
	}
	
	public static String getLastSaturday() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DAY_OF_WEEK,7);
		calendar.add(Calendar.WEEK_OF_YEAR, -1);
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		return df.format(calendar.getTime());
	}
}
