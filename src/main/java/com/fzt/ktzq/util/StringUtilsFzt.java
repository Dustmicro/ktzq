package com.fzt.ktzq.util;

import org.apache.commons.lang3.StringUtils;

import java.util.Collection;
import java.util.Map;

/**
 * 字符串工具类
 * @author 黄弋峰  2022/11/27
 */
public class StringUtilsFzt extends StringUtils {

    /**空字符串**/
    private static final String NULLSTR = "";

    /**下划线**/
    private static final char SEPARATOR = '_';

    /**
     * 判断一个Collection是否为空
     * @param coll
     * @return  true空  false非空
     */
    public static boolean isEmpty(Collection<?> coll){
        return isNull(coll) || coll.isEmpty();
    }

    /**
     * 判断一个Collection是否非空
     * @param coll
     * @return true非空  false空
     */
    public static boolean isNotEmpty(Collection<?> coll){
        return !isEmpty(coll);
    }

    /**
     * 判断一个对象数组是否为空
     * @param objects
     * @return true为空  false非空
     */
    public static boolean isEmpty(Object[] objects){
        return isNull(objects) || (objects.length == 0);
    }

    /**
     * 判断一个对象数组是否非空
     * @param objects
     * @return  true非空  false空
     */
    public static boolean isNotEmpty(Object[] objects){
        return !isEmpty(objects);
    }

    /**
     * 判断一个Map是否为空
     * @param map
     * @return  true空  false非空
     */
    public static boolean isEmpty(Map<?, ?> map){
        return isNull(map) || map.isEmpty();
    }

    /**
     * 判断一个Map是否为非空
     * @param map
     * @return true非空  false空
     */
    public static boolean isNotEmpty(Map<?, ?> map){
        return !isEmpty(map);
    }

    /**
     * 判断一个字符串是否为空
     * @param str
     * @return true空  false非空
     */
    public static boolean isEmpty(String str){
        return isNull(str) || NULLSTR.equals(str.trim());
    }

    /**
     * 判断一个字符串是否为非空
     * @param str
     * @return  true非空  false空
     */
    public static boolean isNotEmpty(String str){
        return !isEmpty(str);
    }

    /**
     * 判断一个对象是否为空
     * @param object
     * @return  true空  false非空
     */
    public static boolean isNull(Object object){
        return object == null;
    }

    /**
     * 判断一个对象是否为非空
     * @param object
     * @return true非空  false空
     */
    public static boolean isNotNull(Object object){
        return !isNull(object);
    }

    /**
     * 去空格
     * @param str
     * @return
     */
    public static String trim(String str){
        return (str == null ? "" : str.trim());
    }
}
