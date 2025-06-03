package util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.DecimalFormat;
import java.text.FieldPosition;
import java.text.NumberFormat;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

/**
 * string 工具类
 *
 * @author ljx
 * @version 1.0.0
 * @create 2022/7/21 10:41
 */
public class StringUtils {

    private final static Logger logger = LoggerFactory.getLogger(StringUtils.class);

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
        return str == null || str.trim().isEmpty() || "null".equals(str);
    }

    /**
     * 判断字符串对象是否为空
     *
     * @param str 字符串
     * @return boolean
     */
    public static boolean isEmpty(Object str) {
        return str == null || str.toString().trim().isEmpty() || "null".equals(str);
    }


    /**
     * 有一个为空则返回true
     *
     * @param str 字符串
     * @return boolean
     */
    public static boolean isAnyEmpty(Object... str) {
        return Arrays.stream(str).anyMatch(StringUtils::isEmpty);
    }

    /**
     * 判断字符串对象是否为空
     *
     * @param str 字符串
     * @return boolean
     */
    public static boolean isNotEmpty(Object str) {
        return !isEmpty(str);
    }


    public static boolean equals(Object str1, Object str2) {
        return String.valueOf(str1).equals(String.valueOf(str2));
    }

    public static boolean isEmpty(CharSequence cs) {
        return cs == null || cs.length() == 0;
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
        String a = "null";
        if (StringUtils.isEmpty(str)) {
            return "";
        } else if (a.equals(String.valueOf(str))) {
            return "";
        } else {
            return str.toString();
        }
    }

    /**
     * int 转字符串
     *
     * @param i
     * @return
     */
    public static String getString(int i) {
        return i == 0 ? "" : String.valueOf(i);
    }

    /**
     * double 转字符串  不显示为0的小数
     *
     * @param i
     * @return
     */
    public static String getString(double i) {
        DecimalFormat decimalFormat = new DecimalFormat("###################.###########");
        return i == 0 ? "" : decimalFormat.format(i);
    }

    /**
     * string转int
     *
     * @param object
     * @return int
     */
    public static int getIntDefault0(Object object) {
        try {
            return isEmpty(object) ? 0 : Integer.parseInt(getString(object));
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    /**
     * string转int
     *
     * @param object
     * @return int
     */
    public static Double getDoubleDefault0(Object object) {
        try {
            return isEmpty(object) ? 0 : Double.parseDouble(getString(object));
        } catch (NumberFormatException e) {
            return 0.0;
        }
    }

    /**
     * string转int
     *
     * @param object
     * @return int
     */
    public static int getIntDefault1(Object object) {
        try {
            return isEmpty(object) ? 1 : Integer.parseInt(getString(object));
        } catch (NumberFormatException e) {
            return 0;
        }
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

    public static String jxjss(String jssstr) {
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine se = manager.getEngineByName("js");
        try {
            DecimalFormat df = new DecimalFormat("#.00");
            //2023年3月3日10:22:48保留2位小数
//			DecimalFormat df = new DecimalFormat("#.000000");
            return df.format(Double.valueOf(se.eval(jssstr).toString()));
        } catch (ScriptException e) {
            logger.error("jxjss eval error {}, message={}", jssstr, e.getMessage());
            return "0";
        }
    }

    public static String format(String message, Object[] params) {
        int currentParamNumber = 0;
        StringBuffer formattedMessage = new StringBuffer();
        for (int i = 0; i < message.length(); ++i) {
            if (message.charAt(i) == '%') {
                if (currentParamNumber >= params.length) {
                    formattedMessage.append("?missing data?");
                } else if (params[currentParamNumber] instanceof Number && i + 1 < message.length()) {
                    i += matchOptionalFormatting((Number) params[currentParamNumber++], message.substring(i + 1), formattedMessage);
                } else {
                    formattedMessage.append(params[currentParamNumber++].toString());
                }
            } else if (message.charAt(i) == '\\' && i + 1 < message.length() && message.charAt(i + 1) == '%') {
                formattedMessage.append('%');
                ++i;
            } else {
                formattedMessage.append(message.charAt(i));
            }
        }
        return formattedMessage.toString();
    }

    private static int matchOptionalFormatting(Number number, String formatting, StringBuffer outputTo) {
        NumberFormat numberFormat = NumberFormat.getInstance(Locale.US);
        if (0 < formatting.length() && Character.isDigit(formatting.charAt(0))) {
            numberFormat.setMinimumIntegerDigits(Integer.parseInt(formatting.charAt(0) + ""));
            if (2 < formatting.length() && formatting.charAt(1) == '.' && Character.isDigit(formatting.charAt(2))) {
                numberFormat.setMaximumFractionDigits(Integer.parseInt(formatting.charAt(2) + ""));
                numberFormat.format(number, outputTo, new FieldPosition(0));
                return 3;
            } else {
                numberFormat.format(number, outputTo, new FieldPosition(0));
                return 1;
            }
        } else if (0 < formatting.length() && formatting.charAt(0) == '.' && 1 < formatting.length() && Character.isDigit(formatting.charAt(1))) {
            numberFormat.setMaximumFractionDigits(Integer.parseInt(formatting.charAt(1) + ""));
            numberFormat.format(number, outputTo, new FieldPosition(0));
            return 2;
        } else {
            numberFormat.format(number, outputTo, new FieldPosition(0));
            return 1;
        }
    }
}
