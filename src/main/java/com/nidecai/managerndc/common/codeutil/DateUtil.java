package com.nidecai.managerndc.common.codeutil;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


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

	public static String getCurrentTime(Date date) {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format = df.format(date);
        return format;
	}
	
	public static String getCurrentTimeByDay(Date date) {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String format = df.format(date);
        return format;
	}
    public static String getCurrentTimeByMonth(Date date) {
        DateFormat df = new SimpleDateFormat("yyyy-MM");
        String format = df.format(date);
        return format;
    }
    public static String getCurrentTimeByYear(Date date) {
        DateFormat df = new SimpleDateFormat("yyyy");
        String format = df.format(date);
        return format;
    }
    public static int getCurrentTime() {
		return (int) (System.currentTimeMillis() / 1000);
	}
    public static String timeStamp2Date(String seconds,String format) {  
        if(seconds == null || seconds.isEmpty() || seconds.equals("null")){  
            return "";  
        }  
        if(format == null || format.isEmpty()){
            format = "yyyy-MM-dd HH:mm:ss";
        }   
        SimpleDateFormat sdf = new SimpleDateFormat(format);  
        return sdf.format(new Date(Long.valueOf(seconds+"000")));  
    }
}
