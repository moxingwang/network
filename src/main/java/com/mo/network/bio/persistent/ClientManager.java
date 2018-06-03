package com.mo.network.bio.persistent;

import java.net.Socket;
import java.util.concurrent.ConcurrentHashMap;

public class ClientManager {


    private static final ConcurrentHashMap<Socket, Object> map = new ConcurrentHashMap<>();

    public static void addClient(Socket client) {
        map.put(client, null);
    }

    public static void removeClient(Socket client) {
        map.remove(client);
    }

    public static int getClientCount() {
        return map.keySet().size();
    }

}
