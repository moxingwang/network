package com.mo.network.socket.chart;

import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.Socket;

/**
 * @author MoXingwang on 2018/6/2.
 */
public class SocketUtil {
    public static void send(String ip, int port, String message) {
        try {
            Socket client = new Socket(ip, port);
            client.setSoTimeout(3000);
            Writer writer = new OutputStreamWriter(client.getOutputStream());
            writer.write(message);
            writer.flush();
            writer.close();
            client.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
