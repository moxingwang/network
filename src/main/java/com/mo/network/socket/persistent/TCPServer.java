package com.mo.network.socket.persistent;

import com.mo.network.socket.persistent.config.NetworkConfig;
import com.mo.network.socket.persistent.manager.ClientManager;
import com.mo.network.socket.persistent.thread.ClientThread;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPServer {

    public static void main(String[] args) {
        ServerSocket serverSocket;
        try {
            serverSocket = new ServerSocket(NetworkConfig.SERVER_PORT);
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
