package com.example.boot;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author yinm
 * @Date 2021/8/26 14:32
 * @Version 1.0
 */

public class Base64Converter {

	static final Base64.Encoder ENCODER = Base64.getEncoder();
	static final Base64.Decoder DECODER = Base64.getDecoder();
	static final String KEY = "key";
	private static final ObjectMapper JACKSON = new ObjectMapper();


	private Base64Converter() {
	}

	/**
	 * 给字符串加密
	 *
	 * @param object
	 * @return
	 */
	public static String encode(Object object) {
		byte[] textByte = new byte[0];
		try {
			//Object转json类型String
			String text = JACKSON.writeValueAsString(object);
			textByte = text.getBytes(StandardCharsets.UTF_8);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ENCODER.encodeToString(textByte);
	}

	/**
	 * 将加密后的字符串进行解密
	 *
	 * @param param
	 * @return
	 */
	public static <T> T decode(Map<String, Object> param, Class<T> a) {
		if (param == null || param.get(KEY) == null || param.get(KEY).toString().equals("")) {
			Map<String, Object> map = new HashMap<>(0);
			return (T) map;
		}
		String encodedText = param.get(KEY).toString();
		Object obj = new Object();
		try {
			String text = new String(DECODER.decode(encodedText), StandardCharsets.UTF_8);
			obj = JACKSON.readValue(text, a);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return (T) obj;
	}
}
