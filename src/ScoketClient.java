/**
 * Copyright (C), 2015-2020, XXX有限公司
 * <p>
 * FileName: ScoketClient
 * <p>
 * Author:   MyAcme
 * <p>
 * Date:     2020/1/2 16:38
 * <p>
 * Description:
 * <p>
 * History:
 *
 * <author>          <time>          <version>          <desc>
 * <p>
 * 作者姓名           修改时间           版本号              描述
 */


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author MyAcme

 * @create 2020/1/2

 * @since 1.0.0

 */

public class ScoketClient {
    public static void main(String[] args) {
        Socket socket = null;
        BufferedReader br = null;
        try {
            socket = new Socket("192.168.168.120",9999);
            InputStream inputStream = socket.getInputStream();
            br = new BufferedReader(new InputStreamReader(inputStream));
            String m = br.readLine();
            System.out.println("hello:"+m);
            br.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}