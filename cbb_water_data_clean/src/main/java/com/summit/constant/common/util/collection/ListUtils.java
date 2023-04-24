package com.summit.constant.common.util.collection;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;

import com.summit.exception.SummitException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@Slf4j
public class ListUtils {
	
	/**
	 * 
	 * 方法stringToList ：String转list
	 * @param str
	 * @return
	 */
	public static List<String> stringToList(String str) {
		if (StringUtils.isBlank(str)) {
			return new ArrayList<>();
		}
		str = str.replace("[", "");
		str = str.replace("]", "");

		String[] strss = str.split(",");
		for (int i= 0 ; i<strss.length ;i++ ) {
			strss[i] = strss[i].trim();
		}
		return Arrays.asList(strss);
	}
	/**
	 * 
	 * 方法stringToList ：String转[]
	 * @param str
	 * @return
	 */
	public static String[] stringToArray(String str) {
		if (StringUtils.isBlank(str)) {
			return new String[0];
		}
		str = str.replace("[", "");
		str = str.replace("]", "");
		str = str.trim();
		if(StringUtils.isNotBlank(str)) {
			String[] strs = str.split(",");
			for (int i= 0 ; i<strs.length ;i++ ) {
				strs[i] = strs[i].trim();
			}	
			return strs;
		}else {
			return new String[0];
		}	
	}
 
	
}
