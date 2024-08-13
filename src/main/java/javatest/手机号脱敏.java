package javatest;


/**
 * @author ljx
 * @version 1.0.0
 * @create 2024/8/13 上午10:39
 */
public class 手机号脱敏 {


    public static void main(String[] args) {
        //字符替换
        String mobile = "13812345678";
        String result = mobile.replaceAll("(\\d{3})\\d{4}(\\d{4})", "$1****$2");
        System.out.println(result);
        //StringBuilder
        StringBuilder sb = new StringBuilder(mobile);
        sb.replace(3, 7, "****");
        result = sb.toString();
        System.out.println(result);
    }
}