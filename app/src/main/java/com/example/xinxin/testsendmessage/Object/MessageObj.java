package com.example.xinxin.testsendmessage.Object;
import com.vmeet.netsocket.bean.InfoType;
public class MessageObj{
    public String content;
    public InfoType msgType;
    public MessageObj(InfoType msgType, String content) {
        this.content = content;
        this.msgType = msgType;
    }
    public MessageObj(InfoType msgType) {
        this.msgType = msgType;
    }
    public void setContent(String content) {
        this.content = content;
    }

    public void setMsgType(InfoType msgType) {
        this.msgType = msgType;
    }

    public String getContent() {
        return content;
    }

    public InfoType getMsgType() {
        return msgType;
    }
}
