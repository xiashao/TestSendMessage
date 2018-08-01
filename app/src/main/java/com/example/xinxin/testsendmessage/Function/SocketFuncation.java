package com.example.xinxin.testsendmessage.Function;
import android.util.Log;
import com.example.xinxin.testsendmessage.Object.MessageObj;
import com.vmeet.netsocket.bean.Constants;
import com.vmeet.netsocket.bean.PkgHead;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
public class SocketFuncation {
    public byte[] bs = null;
    public byte[] bs1 = new byte[30000];
    Socket socket=new Socket();
    //发送头信息
    public void sendHeadMessage(MessageObj messageObj) throws IOException {
        PkgHead head = new PkgHead();
        head.set_InfoType(messageObj.msgType);
        Log.e("smx","sendHeadMessage的消息类型"+messageObj.msgType);
        head.set_InfoLen(messageObj.content.getBytes().length);
        outputToServer(head.get_NetHeadByte(),head.get_NetHeadByte().length);
        Log.e("smx","head.get_NetHeadByte().length"+head.get_NetHeadByte().length);
    }
    //从服务器接收头
    public void recHeadMessage() throws IOException {
        PkgHead head=new PkgHead();
        Log.e("smx","recHeadMessage的长度："+head.get_NetHeadByte().length);
        int headLength=intputToClient(head.get_NetHeadByte(),head.get_NetHeadByte().length);
    }
    //从服务器接收info
    public int recinfoMessage() throws IOException {
        int infoLengh=intputToClient(bs1,bs1.length);
        return infoLengh;
    }
    //发送info到服务器
    public void sendInfoMessage(MessageObj messageObj) throws IOException {
        Log.e("smx","messageObj.content:"+messageObj.content);
        outputToServer(messageObj.content.getBytes(),messageObj.content.getBytes().length);
    }
    //读取流到服务器
    public void outputToServer(byte[] bs,int length) throws IOException {

        try {
            socket.connect(new InetSocketAddress(Constants.ip, Constants.port), 2000);
        }catch (Exception e){
            e.printStackTrace();
        }
        try {
            OutputStream outputStream = socket.getOutputStream();
            Log.e("smx","length:"+length);
            outputStream.write(bs, 0, length);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    //读取流到客户端
    public int intputToClient(byte[] bs,int length) throws IOException {
        Log.e("smx","intputToClient传入的bs长度："+bs.length);
        int n = 0;
        try {
            socket.connect(new InetSocketAddress(Constants.ip, Constants.port), 2000);
            Log.e("smx","intputToClient连接成功");
        }catch (Exception e){
            e.printStackTrace();
        }
        try {
            InputStream inputStream = socket.getInputStream();
            Log.e("smx","intputToClient输入流成功");
            n=inputStream.read(bs, 0, length);
            Log.e("smx","intputToClient字节数n的长度为"+n);
        }catch (Exception e){
            e.printStackTrace();
        }
        return n;
    }
    //发送和读取方法集成
    public int IOfuncition(MessageObj messageObj) throws IOException {
        sendHeadMessage(messageObj);
        sendInfoMessage(messageObj);
        recHeadMessage();
        return recinfoMessage();
    }
}
