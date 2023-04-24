package com.summit.constant.common.util.bean;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;


public class BeanUtil {

    /**
     * 属性 logger : logger日志
     */
    private static Logger logger = LoggerFactory.getLogger(BeanUtil.class);

    /**
     * 
     * 方法convertBean ：apache对象copy
     * 
     * @param dest
     * @param orig
     * @throws BusException
     */
    public static void convertBean(final Object dest, final Object orig) {
        BeanUtils.copyProperties(dest, orig);
    }

    /**
     * 将源Bean转成目标Bean
     * 
     * @param source
     *            源对象
     * @param targetClazz
     *            目标对象
     * @return
     */
    public static <T, E> T convertBean(E source, Class<T> targetClazz) {
        if (null == source) {
            return null;
        }
        T t = null;
        try {
            t = targetClazz.newInstance();
            BeanUtils.copyProperties(source, t);
        } catch (InstantiationException e) {
            logger.error("错误信息:{}",e);
        } catch (IllegalAccessException e) {
            logger.error("错误信息:{}",e);
        }
        return t;
    }

    /**
     * 将源List Bean 转成目标List Bean
     * 
     * @param sources
     *            源对象
     * @param targetClazz
     *            目标对象
     * @return
     */
    public static <T, E> List<T> convertListBean(List<E> sources, Class<T> targetClazz) {
        if (null == sources) {
            return null;
        }
        List<T> listT = new ArrayList<T>();
        for (E source : sources) {
            try {
                T t = targetClazz.newInstance();
                BeanUtils.copyProperties(source, t);
                listT.add(t);
            } catch (InstantiationException e) {
                logger.error("错误信息:{}",e);
            } catch (IllegalAccessException e) {
                logger.error("错误信息:{}",e);
            }
        }
        return listT;
    }

    /**
     * Map转成JavaBean
     * 
     * @param map
     * @param clz
     * @return
     */
    public static <T> T map2bean(Map<String, Object> map, Class<T> clz) {
        if (null == map) {
            return null;
        }
        // 创建JavaBean对象
        T obj = null;
        try {
            obj = clz.newInstance();
            // 获取指定类的BeanInfo对象
            BeanInfo beanInfo = Introspector.getBeanInfo(clz, Object.class);
            // 获取所有的属性描述器
            PropertyDescriptor[] pds = beanInfo.getPropertyDescriptors();
            for (PropertyDescriptor pd : pds) {
                Object value = map.get(pd.getName());
                if (value != null) {
                    Method setter = pd.getWriteMethod();
                    setter.invoke(obj, value);
                }
            }
        } catch (InstantiationException e) {
            logger.error("错误信息:{}",e);
        } catch (IllegalAccessException e) {
            logger.error("错误信息:{}",e);
        } catch (IllegalArgumentException e) {
            logger.error("错误信息:{}",e);
        } catch (InvocationTargetException e) {
            logger.error("错误信息:{}",e);
        } catch (IntrospectionException e) {
            logger.error("错误信息:{}",e);
        }

        return obj;
    }

    /**
     * List Map转成List Bean
     * 
     * @param map
     * @param clz
     * @return
     */
    public static <T> List<T> listMap2JavaBean(List<Map<String, Object>> map, Class<T> clz) {
        if (null == map) {
            return null;
        }
        List<T> listT = new ArrayList<T>();
        try {
            T t = null;
            for (Map<String, Object> mp : map) {
                t = clz.newInstance();
                BeanInfo beanInfo = Introspector.getBeanInfo(clz, Object.class);
                // 获取所有的属性描述器
                PropertyDescriptor[] pds = beanInfo.getPropertyDescriptors();
                for (PropertyDescriptor pd : pds) {
                    Object value = mp.get(pd.getName());
                    if (value != null) {
                        Method setter = pd.getWriteMethod();
                        setter.invoke(t, value);
                    }
                }
                listT.add(t);
            }
        } catch (InstantiationException e) {
            logger.error("错误信息:{}",e);
        } catch (IllegalAccessException e) {
            logger.error("错误信息:{}",e);
        } catch (IllegalArgumentException e) {
            logger.error("错误信息:{}",e);
        } catch (InvocationTargetException e) {
            logger.error("错误信息:{}",e);
        } catch (IntrospectionException e) {
            logger.error("错误信息:{}",e);
        }
        return listT;
    }

    /**
     * 
     * Java Bean 转成Map
     * 
     * @param t
     *            JavaBean
     * @return
     */
    public static <T> Map<String, Object> bean2Map(T t) {
        if (t == null) {
            return null;
        }
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            BeanInfo beanInfo = Introspector.getBeanInfo(t.getClass());
            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
            for (PropertyDescriptor property : propertyDescriptors) {
                String key = property.getName();
                if (key.compareToIgnoreCase("class") == 0) {
                    continue;
                }
                Method getter = property.getReadMethod();
                Object value = getter != null ? getter.invoke(t) : null;
                map.put(key, value);
            }
        } catch (IllegalAccessException e) {
            logger.error("错误信息:{}",e);
        } catch (IllegalArgumentException e) {
            logger.error("错误信息:{}",e);
        } catch (InvocationTargetException e) {
            logger.error("错误信息:{}",e);
        } catch (IntrospectionException e) {
            logger.error("错误信息:{}",e);
        }
        return map;
    }
}
