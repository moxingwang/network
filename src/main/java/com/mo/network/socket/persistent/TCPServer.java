package com.mo.network.socket.persistent;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 长连接server
 */
public class TCPServer {

    public static void main(String[] args) {
        ServerSocket serverSocket;
        try {
            serverSocket = new ServerSocket(55101);
            while (true) {
                Socket clientSocket = serverSocket.accept();
                ClientManager.addClient(clientSocket);
                System.out.println("In!" + ClientManager.getClientCount());
                new ClientThread(clientSocket).start();
            }
        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
    }

}
