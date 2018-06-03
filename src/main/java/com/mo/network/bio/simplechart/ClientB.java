package com.mo.network.bio.simplechart;

import java.io.IOException;

/**
 * @author MoXingwang on 2018/6/2.
 */
public class ClientB {
    public static void main(String args[]) {

        BaseClient baseClient = new BaseClient("127.0.0.1", Server.SERVER_PORT, "127.0.0.1", 54101, 54100, "小红");
        baseClient.scan();
        try {
            baseClient.listen();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}
