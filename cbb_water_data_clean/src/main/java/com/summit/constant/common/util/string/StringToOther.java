package com.summit.constant.common.util.string;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;

/**
 *
 *  DateFormat
 */
@Component
public class StringToOther {
	/**
	 * String 转 List<map>
	 * @param str
	 * @return
	 */
	private static List<Map<String, Object>> StringToListMap(String str) {
		List<Object> list = JSON.parseArray(str);
		List<Map<String, Object>> listw = new ArrayList<Map<String, Object>>();
		for (Object object : list) {
			Map<String, Object> ageMap = new HashMap<String, Object>();
			Map<String, Object> ret = (Map<String, Object>) object;// 取出list里面的值转为map
			listw.add(ret);
		}
		return listw;

	}
}
