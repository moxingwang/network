package com.mo.network.socket.simplechart;

import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author MoXingwang on 2018/6/2.
 */
public class Server {
    private static final Logger logger = LoggerFactory.getLogger(Server.class);

    public static final int SERVER_PORT = 53103;

    private static ExecutorService threadPool = new ThreadPoolExecutor(10, 50, 50, TimeUnit.MINUTES, new LinkedBlockingDeque<Runnable>(10000));


    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(SERVER_PORT);

        while (true) {
            Socket socket = serverSocket.accept();
            threadPool.execute(new SocketTask(socket));
        }
    }

    static class SocketTask implements Runnable {
        Socket socket;

        public SocketTask(Socket socket) {
            this.socket = socket;
        }


        @Override
        public void run() {
            BufferedReader bufferedReader = null;
            try {
                bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

                String message = "";
                String line = bufferedReader.readLine();
                while (line != null) {
                    message += line;
                    line = bufferedReader.readLine();
                }

                logger.info("接收到客户端消息{}", message);
                Message messageResult = JSONObject.parseObject(message, Message.class);
                SocketUtil.send(messageResult.getToIp(), messageResult.getToPort(), message);
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
    }


}
