package com.example.boot.util;

import java.util.UUID;

/**
 * string 工具类
 *
 * @author ljx
 * @version 1.0.0
 * @create 2022/7/21 10:41
 */
public class StringUtils {
	private StringUtils() {
	}

	/**
	 * 生成UUID
	 *
	 * @return
	 */
	public static String getUuid32() {
		return UUID.randomUUID().toString().replace("-", "");
	}

	/**
	 * 判断字符串是否为空
	 *
	 * @param str 字符串
	 * @return boolean
	 */
	public static boolean isEmpty(String str) {
		return str == null || str.trim().length() == 0 || "null".equals(str);
	}

	/**
	 * 判断字符串对象是否为空
	 *
	 * @param str 字符串
	 * @return boolean
	 */
	public static boolean isEmpty(Object str) {
		return str == null || str.toString().trim().length() == 0 || "null".equals(str);
	}

	/**
	 * 将逗号分隔的字符串格式化成"'"包裹切逗号分隔的字符串
	 *
	 * @param str 字符串
	 * @return 字符串
	 */
	public static String formatString(String str) {
		String[] split = str.split(",");
		String[] temp = new String[split.length];
		for (int i = 0; i < split.length; i++) {
			temp[i] = "'" + split[i] + "'";
		}
		return String.join(",", temp);
	}


	/**
	 * object 转字符串
	 *
	 * @param str
	 * @return
	 */
	public static String getString(Object str) {
		if (StringUtils.isEmpty(str)) {
			return "";
		} else {
			return str.toString();
		}
	}
}
