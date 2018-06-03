package com.mo.network.bio.simplechart;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/**
 * @author MoXingwang on 2018/6/2.
 */
public class BaseClient {
    private String serverIp;
    private int serverPort;
    private String toClientIp;
    private int toClientPort;
    private int clientPort;
    private String clientName;

    public BaseClient(String serverIp, int serverPort, String toClientIp, int toClientPort, int clientPort, String clientName) {
        this.serverIp = serverIp;
        this.serverPort = serverPort;
        this.toClientIp = toClientIp;
        this.toClientPort = toClientPort;
        this.clientPort = clientPort;
        this.clientName = clientName;
    }

    public void scan() {
        new Thread(()->{
            Scanner scan = new Scanner(System.in); // 从键盘接收数据
            String str = scan.next();  // 接收数据
            do {
                SocketUtil.send(serverIp, serverPort, JSON.toJSONString(new Message(clientName, toClientIp, toClientPort, str)));
            } while ((str = scan.next()) != null);
        }).start();
    }

    public void listen() throws IOException {
        ServerSocket serverSocket = new ServerSocket(clientPort);

        while (true){
            Socket socket = serverSocket.accept();

            BufferedReader bufferedReader = null;
            try {
                bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

                String message = "";
                String line = bufferedReader.readLine();
                while (line != null) {
                    message += line;
                    line = bufferedReader.readLine();
                }

                Message messageResult = JSONObject.parseObject(message, Message.class);


                System.out.println("来自" + messageResult.getFromClientName() + "的消息：" + messageResult.getMessage());
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
