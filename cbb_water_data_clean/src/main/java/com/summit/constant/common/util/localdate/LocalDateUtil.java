package com.summit.constant.common.util.localdate;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.stereotype.Component;

/**
 *
 *  DateFormat
 */
@Component
public class LocalDateUtil {
	
	/** 日期格式 ：yyyy-MM-dd HH:mm:ss*/
	private static final String YYYYHHDDHHMMSS1 = "yyyy-MM-dd HH:mm:ss";
	
	/** 日期格式 ：yyyy/MM/dd HH:mm:ss*/
	private static final String YYYYHHDDHHMMSS2 = "yyyy/MM/dd HH:mm:ss";
	
	/** 日期格式 ：yyyy-MM-dd*/
	private static final String YYYYHHDD1 = "yyyy-MM-dd";
	
	/** 日期格式 ：yyyy/MM/dd*/
	private static final String YYYYHHDD2 = "yyyy/MM/dd";
	
	/**
	 * 日期格式化
	 * @param localDateTime
	 * @param format
	 * @return
	 */
	private static String LocalTimeFormat(LocalDateTime localDateTime,String format) {
		DateTimeFormatter sdf = DateTimeFormatter.ofPattern(format);
		return localDateTime == null ? "--" : sdf.format(localDateTime);
	}

}
