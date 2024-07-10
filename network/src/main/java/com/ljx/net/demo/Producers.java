package com.ljx.net.demo;


import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;


/**
 * @author ljx
 * @version 1.0.0
 * @create 2024/7/10 上午9:46
 */
public class Producers {
    public static void main(String[] args) throws IOException {
        //2. 通过Socket，把数据发出去到服务端
        try (Socket socket = new Socket("192.168.2.106", 8080);
             DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
             DataInputStream dis = new DataInputStream(socket.getInputStream());) {
            while (true) {
                Scanner scanner = new Scanner(System.in);
                String nextLine = scanner.nextLine();
                dos.writeUTF(nextLine);
                //3. 通过Socket，接收服务端返回的数据
                String answer = dis.readUTF();
                System.out.println("收到服务端返回结果：" + answer);
                if ("exit".equals(nextLine)){
                    break;
                }
            }
        } catch (Exception e) {
        }
    }
}
