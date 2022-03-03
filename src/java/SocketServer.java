package java; /**
 * Copyright (C), 2015-2020, XXX有限公司
 * <p>
 * FileName: java.SocketServer
 * <p>
 * Author:   MyAcme
 * <p>
 * Date:     2020/1/2 16:30
 * <p>
 * Description:
 * <p>
 * History:
 *
 * <author>          <time>          <version>          <desc>
 * <p>
 * 作者姓名           修改时间           版本号              描述
 */


import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author MyAcme

 * @create 2020/1/2

 * @since 1.0.0

 */

public class SocketServer {
    public static void main(String[] args) {
        ServerSocket serverSocket = null;
        Socket socket =null;
        BufferedWriter bw = null;
        try {
            serverSocket = new ServerSocket(9999);
            socket = serverSocket.accept();
            OutputStream outputStream = socket.getOutputStream();
            bw = new BufferedWriter(new OutputStreamWriter(outputStream));
            bw.write("1234567890");
            bw.flush();
            bw.close();
            serverSocket.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}