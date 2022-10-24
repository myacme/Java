package util;


import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * 〈公用工具类〉
 *
 * @author ljx
 * @create 2021/8/27 9:26
 * @since 1.0.0
 */

public class CommonUtil {

	private CommonUtil() {
	}

	/**
	 * 生产UUID
	 *
	 * @return
	 */
	public static String getUuid32() {
		return UUID.randomUUID().toString().replace("-", "");
	}

	/**
	 * 获取时间（yyyyMMdd）
	 *
	 * @return
	 */
	public static String getNowData() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
		// 日期时间转字符串
		return LocalDateTime.now().format(formatter);
	}

	public static String getData() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		// 日期时间转字符串
		return LocalDateTime.now().format(formatter);
	}

	public static String getsData() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
		// 日期时间转字符串
		return LocalDateTime.now().format(formatter);
	}

	public static Long getLongData(String data) throws ParseException {
		String format = "yyyy-MM-dd HH:mm:ss";
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		Long date = sdf.parse(data).getTime() / 1000L;
		return date;
	}

	/**
	 * 翻转字符串并拼接
	 *
	 * @param str           字符串
	 * @param fromSeparator 原分隔符
	 * @param toSeparator   生成字符串的分隔符
	 * @return 翻转的字符串
	 */
	public static String reverseSplicing(String str, String fromSeparator, String toSeparator) {
		String[] split = str.split(fromSeparator);
		List<String> list = Arrays.asList(split);
		Collections.reverse(list);
		return String.join(toSeparator, list);
	}

	public static <T> List<T> deepCopy(List<T> src) {
		try {
			ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
			ObjectOutputStream out = new ObjectOutputStream(byteOut);
			out.writeObject(src);
			ByteArrayInputStream byteIn = new ByteArrayInputStream(byteOut.toByteArray());
			ObjectInputStream in = new ObjectInputStream(byteIn);
			@SuppressWarnings("unchecked")
			List<T> dest = (List<T>) in.readObject();
			return dest;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}


	/**
	 * 获取月份的天数(YYYYMM)
	 *
	 * @param dateString
	 * @return int
	 */
	public static int getDaysOfMonth(String dateString) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMM");
		try {
			Date date = simpleDateFormat.parse(dateString);
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
		} catch (ParseException e) {
			e.printStackTrace();
			return 0;
		}
	}
}
