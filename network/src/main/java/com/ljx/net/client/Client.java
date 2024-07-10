package com.ljx.net.client;


import com.ljx.net.bean.Message;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * @author ljx
 * @version 1.0.0
 * @create 2024/7/10 下午2:39
 */
public class Client {

    public void doChat(String name) {
        try (Socket client = new Socket("192.168.2.106", 8080);
             BufferedReader reader = new BufferedReader(new InputStreamReader(client.getInputStream()));
             PrintWriter writer = new PrintWriter(client.getOutputStream(), true);
             BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in))) {
            new Thread(() -> {
                try {
                    while (true) {
                        String message = reader.readLine();
                        System.out.println(message);
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }).start();
            Message message = new Message(name);
            while (true) {
                String userInput = consoleReader.readLine();
                message.setMessage(userInput);
                //往服务端输出消息
                writer.println(message);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

