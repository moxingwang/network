package com.mo.network.bio.simplechart;

/**
 * @author MoXingwang on 2018/6/2.
 */
public class ClientA {
    public static void main(String args[]) {

        BaseClient baseClient = new BaseClient("127.0.0.1", Server.SERVER_PORT, "127.0.0.1", 54100, 54101,"小明");
        baseClient.scan();
        try {
            baseClient.listen();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
