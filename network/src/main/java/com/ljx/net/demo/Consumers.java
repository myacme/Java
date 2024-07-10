package com.ljx.net.demo;


import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author ljx
 * @version 1.0.0
 * @create 2024/7/10 上午9:57
 */
public class Consumers {

    public static void main(String[] args) throws IOException {
        try (ServerSocket serverSocket = new ServerSocket(8080);
             //2. 获取客户端的连接：Socket对象。是阻塞方法。如果没有客户端连接进来，就一直阻塞等待
             Socket socket = serverSocket.accept();
             //3. 通过Socket，接收客户端发过来的数据
             DataInputStream dis = new DataInputStream(socket.getInputStream());
             DataOutputStream dos = new DataOutputStream(socket.getOutputStream());) {
            while (true) {
                String data = dis.readUTF();
                System.out.println("接收到客户端发来的数据：" + data);
                dos.writeUTF("接收到的数据：" + data);
                if ("exit".equals(data)){
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
