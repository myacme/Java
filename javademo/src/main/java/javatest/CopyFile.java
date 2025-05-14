package javatest; /**
 * Copyright (C), 2015-2020, XXX有限公司
 * <p>
 * FileName: java.CopyFile
 * <p>
 * Author:   MyAcme
 * <p>
 * Date:     2020/1/2 15:52
 * <p>
 * Description:
 * <p>
 * History:
 *
 * <author>          <time>          <version>          <desc>
 * <p>
 * 作者姓名           修改时间           版本号              描述
 */


import java.io.*;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author MyAcme

 * @create 2020/1/2

 * @since 1.0.0

 */

public class CopyFile {
    public static void main(String[] args) {
        try {
            BufferedInputStream bis = new BufferedInputStream(new FileInputStream("E:\\text.txt"));
            BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream("E:\\text1.txt"));
            byte[] byte1 = new byte[1024];
            int len = 0;
            while ((len = bis.read(byte1)) != -1){
                bos.write(byte1, 0, len);
            }
            bis.close();
            bos.close();
        }catch (IOException e){

        }



    }

}