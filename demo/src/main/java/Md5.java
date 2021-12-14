/**
 * Copyright (C), 2015-2020, XXX有限公司
 * <p>
 * FileName: Md5
 * <p>
 * Author:   MyAcme
 * <p>
 * Date:     2020/9/11 9:53
 * <p>
 * Description:
 * <p>
 * History:
 *
 * <author>          <time>          <version>          <desc>
 * <p>
 * 作者姓名           修改时间           版本号              描述
 */


import org.springframework.util.DigestUtils;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author MyAcme

 * @create 2020/9/11

 * @since 1.0.0

 */

public class Md5 {
    public static String getMd5ByJava(String s) {


        byte[] secretBytes = null;
        try {
            secretBytes = MessageDigest.getInstance("md5").digest(
                    s.getBytes());
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("没有这个md5算法！");
        }
        String md5code = new BigInteger(1, secretBytes).toString(16);
        for (int i = 0; i < 32 - md5code.length(); i++) {
            md5code = "0" + md5code;
        }
        return md5code;
    }
    public static String getMd5BySpring(String s) {
        String resultString = DigestUtils.md5DigestAsHex(s.getBytes());
        return resultString;
    }

    public static void main(String[] args) {
        System.out.println(getMd5ByJava("123456"));
        System.out.println(getMd5BySpring("123456"));
    }


}