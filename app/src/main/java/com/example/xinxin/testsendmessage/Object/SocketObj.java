package com.example.xinxin.testsendmessage.Object;

import android.content.Context;

public class SocketObj {
    private String mac;//当前账号 mac
    //	private String ip;//长连接Ip
//	private int port;//长连接Port
    private String ip = "42.96.153.238";//长连接Ip
    //private int port = 30005;//长连接Port
    private int port = 40003;//长连接Port
    private String deviceId;

    private Context context;//上下文对象

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }
}
