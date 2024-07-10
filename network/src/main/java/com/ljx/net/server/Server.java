package com.ljx.net.server;


import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashSet;
import java.util.Set;

/**
 * @author ljx
 * @version 1.0.0
 * @create 2024/7/10 下午2:24
 */
public class Server {

    private static Set<ClientUser> clientUsers = new HashSet<>();


    public static void main(String[] args) throws IOException {
        try (ServerSocket client = new ServerSocket(8080);) {
            while (true) {
                Socket socket = client.accept();
                System.out.println("新的客户端已连接：" + socket);
                ClientUser clientUser = new ClientUser(socket, clientUsers);
                clientUsers.add(clientUser);
                new Thread(clientUser).start();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}
