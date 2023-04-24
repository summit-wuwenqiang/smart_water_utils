package com.summit.constant.common.util.date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Year;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

/**
 *
 *  DateFormat
 */
@Component
public class DateUtil {
	
	private static Map<String,Object> getYearStartAndEnd(String parameter) throws ParseException{
		Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy");
		String cyear = formatter.format(date);
		if(StringUtils.isBlank(parameter)) {
			parameter = cyear;
		}
		Map<String,Object> map = new HashMap<>();
			DateTimeFormatter dailyFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			Year year = Year.now().minusYears(Long.valueOf(cyear) - Long.valueOf(parameter));
			String start = year.atDay(1).format(dailyFormatter) + " 00:00:00";
			String end = year.atMonth(12).atEndOfMonth().format(dailyFormatter) + " 23:59:59";
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			long timeStart = sdf.parse(start).getTime();
			long timeEnd = sdf.parse(end).getTime();
			map.put("timeStart", timeStart);
			map.put("timeEnd", timeEnd);
		return map;
	}
	/**
	 * 
	 * 方法querPresentYear ：获取当前年
	 * 
	 * @return
	 */
	public static String getPresentYear() {
		Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy");
		return formatter.format(date);
	}

	/**
	 * 
	 * 方法querNextYear ：获取下一年度
	 * 
	 * @return
	 */
	public static String getNextYear() {
		Calendar cal = Calendar.getInstance();
		cal.add(cal.YEAR, 1);
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy");
		return formatter.format(cal.getTime());
	}

	/**
	 * 
	 * 方法querNextYear ：获取上一年度
	 * 
	 * @return
	 */
	public static String getLastYear() {
		Calendar cal = Calendar.getInstance();
		cal.add(cal.YEAR, -1);
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy");
		return formatter.format(cal.getTime());
	}

	/**
	 * 
	 * 方法querynextMonth ：获取下一个月
	 * 
	 * @return
	 */
	public static String getNextMonth() {
		Calendar cal = Calendar.getInstance();
		cal.add(cal.MONTH, 1);
		SimpleDateFormat formatter = new SimpleDateFormat("MM");
		return formatter.format(cal.getTime());
	}

	/**
	 * 
	 * 方法querynextMonth ：获取当前月
	 * 
	 * @return
	 */
	public static String getPresentMonth() {
		Calendar cal = Calendar.getInstance();
		cal.add(cal.MONTH, 0);
		SimpleDateFormat formatter = new SimpleDateFormat("MM");
		return formatter.format(cal.getTime());
	}

	/**
	 * 
	 * 方法querynextMonth ：获取上一个月
	 * 
	 * @return
	 */
	public static String getLastMonth() {
		Calendar cal = Calendar.getInstance();
		cal.add(cal.MONTH, -1);
		SimpleDateFormat formatter = new SimpleDateFormat("MM");
		return formatter.format(cal.getTime());
	}

	/**
	 * 
	 * 方法querynextMonth ：获取当前月第一天
	 * 
	 * @return
	 */
	public static String getPresentMonthOneDay() {
		Calendar cal = Calendar.getInstance();
		cal.add(cal.MONTH, 0);
		cal.set(Calendar.DAY_OF_MONTH, 1);// 设置为1号,当前日期既为本月第一天
		SimpleDateFormat formatter = new SimpleDateFormat("dd");
		return formatter.format(cal.getTime());
	}

	/**
	 * 
	 * 方法querynextMonth ：获取当前月最后一天
	 * 
	 * @return
	 */
	public  static String getPresentMonthEndDay() {
		Calendar cal = Calendar.getInstance();
		cal.add(cal.MONTH, 0);
		cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));// 设置为1号,当前日期既为本月第一天
		SimpleDateFormat formatter = new SimpleDateFormat("dd");
		return formatter.format(cal.getTime());
	}

	/**
	 * 
	 * 方法DateFormat ：日期格式化
	 * 
	 * @param date
	 * @param formatStail
	 * @return
	 */
	public static String dateFormat(Date date, String formatStail) {
		SimpleDateFormat sdf = new SimpleDateFormat(formatStail);
		return sdf.format(date);
	}
	/**
	 * 
	 * 方法DateParse ：字符串转日期
	 * @param formatStail
	 * @return
	 * @throws ParseException 
	 */
	public static Date dateParseFromDate(String formatStail) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		if(StringUtils.isNotBlank(formatStail)) {
			try {
				return sdf.parse(formatStail);
			} catch (ParseException e) {
				//日期转换异常
			}
		}
		return null;
				
	}

}
