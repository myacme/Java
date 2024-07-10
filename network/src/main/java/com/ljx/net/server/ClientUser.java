package com.ljx.net.server;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Set;

/**
 * @author ljx
 * @version 1.0.0
 * @create 2024/7/10 下午4:18
 */
public class ClientUser implements Runnable {
    //socket客户端
    private Socket clientSocket;
    //客户端集
    private Set<ClientUser> clientUsers;
    //缓冲输入字符流
    private BufferedReader reader;
    //输出字符流
    private PrintWriter writer;
    //添加用户名字段
    private String username;

    /**
     * 消息处理
     *
     * @param socket
     * @param clientUsers
     */
    public ClientUser(Socket socket, Set<ClientUser> clientUsers) {
        this.clientSocket = socket;
        this.clientUsers = clientUsers;
        try {
            //输入流与输出流
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            writer = new PrintWriter(socket.getOutputStream(), true);
            this.username = "User" + socket.getPort();
            sendMessage("您的客户端连接成功！");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 获取用户名
    public String getUsername() {
        return username;
    }

    /**
     * 开启消息处理
     */
    @Override
    public void run() {
        try {
            while (true) {
                //读取消息
                String message = reader.readLine();
                System.out.println(message);
                // 广播消息给所有客户端
                broadcast(message, this);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                clientSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void sendMessage(String message) {
        writer.println(message);
    }

    public void broadcast(String message, ClientUser sender) {
        for (ClientUser clientUser : clientUsers) {
            // 避免将消息发回给发送者
            if (clientUser != sender) {
                clientUser.sendMessage(message);
            }
        }
    }
}
