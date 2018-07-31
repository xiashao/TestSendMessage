package com.example.xinxin.testsendmessage.Object;
import com.vmeet.netsocket.bean.InfoType;
public class MessageObj{
    public String content;
    public InfoType msgType;
    public String serverIp;//短连接Ip
    public int serverPort;//短连接port

    public MessageObj(InfoType msgType,  String content, String serverIp, int serverPort) {
        this.content = content;
        this.msgType = msgType;
        this.serverIp = serverIp;
        this.serverPort = serverPort;
    }
    public void setContent(String content) {
        this.content = content;
    }

    public void setMsgType(InfoType msgType) {
        this.msgType = msgType;
    }

    public void setServerIp(String serverIp) {
        this.serverIp = serverIp;
    }

    public void setServerPort(int serverPort) {
        this.serverPort = serverPort;
    }

    public String getContent() {
        return content;
    }

    public InfoType getMsgType() {
        return msgType;
    }

    public String getServerIp() {
        return serverIp;
    }

    public int getServerPort() {
        return serverPort;
    }

}
