package com.summit.constant.common.util.comparator;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;

import lombok.extern.slf4j.Slf4j;
/**
 * 
 * 项目名称：
 * 类名称：ComparatorUtil  比较器工具类
 * 类描述:
 */
@Slf4j
public class ComparatorUtil {
	/**
	 * 
	 * 方法sortListEntityForProperty ：根据java bean中的某个属性排序
	 * 
	 * @param entityList 实体类集合
	 * @param property   属性名
	 * @return List<T>
	 */
	public <T> List<T> sortListEntityForProperty(List<T> entityList, final String property) throws Exception {
		// 获取属性类型
		if(CollectionUtils.isEmpty(entityList)) {
			return new ArrayList<T>();
		}
		String type = "";
		Field[] field = entityList.get(0).getClass().getDeclaredFields();
		for (int i = 0; i < field.length; i++) {
			String name = field[i].getName();
			if (name.equals(property)) {
				// 获取属性类型
				type = field[i].getGenericType().toString();
			}
		}
		//根据属性的类型比较
		if (type.equals("class java.lang.Integer") || type.equals("class java.lang.Short")
				|| type.equals("class java.lang.Double") || type.equals("class java.util.Date")) {
			Collections.sort(entityList, new Comparator<T>() {
				public int compare(T o1, T o2) {
					try {
						if (Double.valueOf(getPropertyValue(o1, property))
								- Double.valueOf(getPropertyValue(o2, property)) > 0) {
							return 1;
						} else if (Double.valueOf(getPropertyValue(o1, property))
								- Double.valueOf(getPropertyValue(o2, property)) < 0) {
							return -1;
						}
					} catch (Exception e) {
						log.error(e.getMessage());
					}
					return 0;
				}
			});
		} else if (type.equals("class java.lang.String")) {
			Collections.sort(entityList, new Comparator<T>() {
				public int compare(T o1, T o2) {
					try {
						if (getPropertyValue(o1, property).compareToIgnoreCase(getPropertyValue(o2, property)) < 0){  
						    return -1;  
						} else {
							return 1;
						}
					} catch (Exception e) {
						log.error(e.getMessage());
					}
					return -1;
				}
			});
		}
	
		return entityList;
	}

	/**
	 * 
	 * 方法getPropertyValue ：根据映射获取实体 bean 属性值
	 * @param t 对象
	 * @param property 属性名
	 * @return
	 * @throws Exception
	 */
	public <T> String getPropertyValue(T t, String property) throws Exception {
		Class entityClass = t.getClass();// 动态加载类，获取当前类的Class对象
		// 获取属性类型
		String type = "";
		Field[] field = t.getClass().getDeclaredFields();
		for (int i = 0; i < field.length; i++) {
			String name = field[i].getName();
			if (name.equals(property)) {
				// 获取属性类型
				type = field[i].getGenericType().toString();
			}
		}
		String value = "";
		// 获取Student类名称为printinfo地方法
		Method methods = entityClass.getDeclaredMethod(
				"get" + property.substring(0, 1).toUpperCase() + property.substring(1, property.length()));
		// 调用frintInfo方法
		if (type.equals("class java.util.Date")) {
			Date date = (Date) methods.invoke(t);
			value = String.valueOf(date.getTime());
		} else {
			value = String.valueOf(methods.invoke(t));
		}
		return value;
	}
	
	
	/**
	 * 
	 * 方法setPropertyValue ：根据映射为实体 bean 属性值赋值
	 * @param t 对象
	 * @param property 属性名
	 * @return
	 * @throws Exception
	 */
	public <T> void setPropertyValue(T t, String property,Object  value) throws Exception {
		if (null != t && null != property && null != value) {
			PropertyDescriptor pd = new PropertyDescriptor(property, t.getClass());
			Method setMethod = pd.getWriteMethod();
			setMethod.invoke(t, value);
		}
	}
	
}
