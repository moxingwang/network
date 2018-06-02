package com.mo.network.socket.chart;

/**
 * @author MoXingwang on 2018/6/2.
 */
public class Message {
    private String fromClientName;
    private String toIp;
    private int toPort;
    private String message;

    public Message() {
    }

    public Message(String fromClientName, String toIp, int toPort, String message) {
        this.fromClientName = fromClientName;
        this.toIp = toIp;
        this.toPort = toPort;
        this.message = message;
    }

    public String getFromClientName() {
        return fromClientName;
    }

    public void setFromClientName(String fromClientName) {
        this.fromClientName = fromClientName;
    }

    public String getToIp() {
        return toIp;
    }

    public void setToIp(String toIp) {
        this.toIp = toIp;
    }

    public int getToPort() {
        return toPort;
    }

    public void setToPort(int toPort) {
        this.toPort = toPort;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
