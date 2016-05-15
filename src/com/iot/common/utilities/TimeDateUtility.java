package com.iot.common.utilities;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
/**
 * *****************************************************************
 * Created on 2016年1月18日 上午10:42:26
 * @author 
 * 功能说明： 处理时间工具类
 *
 * 修改历史
 * Revision 
 * Update: ------ empty log ------
 ******************************************************************
 */
public class TimeDateUtility {
	public static Timestamp getCurrentTimestamp() {
		Date currentDate = new Date();
		return new Timestamp(currentDate.getTime());
	}

	public static String getFormattedTimestamp() {
		SimpleDateFormat df = new SimpleDateFormat("yyMMddHHmmssSSS");
		return df.format(getCurrentTimestamp());
	}
	
	public static String getFormattedTime() {
		SimpleDateFormat df = new SimpleDateFormat("ss");
		return df.format(getCurrentTimestamp());
	}

	public static Integer getCurrentYear() {
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(getCurrentTimestamp().getTime());
		return Integer.valueOf(cal.get(1));
	}
	
	public static Date getFormattedDate(Date date) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		try {
			date = df.parse(df.format(date));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return date;
	}
}