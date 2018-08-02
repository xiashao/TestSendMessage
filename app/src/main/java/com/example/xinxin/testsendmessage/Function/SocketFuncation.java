package com.example.xinxin.testsendmessage.Function;
/*Created By smx in 2018/8/1
 *心若冰清,天塌不惊; 万变犹定,神怡气静.
 *  .--,       .--,
 * ( (  \.---./  ) )
 *  '.__/o   o\__.'
 *     {=  ^  =}
 *      >  -  <
 *     /       \
 *    //       \\
 *   //|   .   |\\
 *   "'\       /'"_.-~^`'-.
 *      \  _  /--'         `
 *    ___)( )(___
 *   (((__) (__)))
 */
import com.example.xinxin.testsendmessage.Object.MessageObj;
import com.vmeet.netsocket.bean.Constants;
import com.vmeet.netsocket.bean.PkgHead;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
public class SocketFuncation {
    public byte[] bs1 = new byte[30000];
    Socket socket=new Socket();
    //发送头信息
    public void sendHeadMessage(MessageObj messageObj) throws IOException {
        PkgHead head = new PkgHead();
        head.set_InfoType(messageObj.msgType);
        head.set_InfoLen(messageObj.content.getBytes().length);
        outputToServer(head.get_NetHeadByte(),head.get_NetHeadByte().length);
    }
    //从服务器接收头
    public void recHeadMessage() throws IOException {
        PkgHead head=new PkgHead();
        int headLength=intputToClient(head.get_NetHeadByte(),head.get_NetHeadByte().length);
    }
    //从服务器接收info
    public int recinfoMessage() throws IOException {
        int infoLengh=intputToClient(bs1,bs1.length);
        return infoLengh;
    }
    //发送info到服务器
    public void sendInfoMessage(MessageObj messageObj) throws IOException {
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
            outputStream.write(bs, 0, length);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    //读取流到客户端
    public int intputToClient(byte[] bs,int length) throws IOException {
        int n = 0;
        try {
            socket.connect(new InetSocketAddress(Constants.ip, Constants.port), 2000);
        }catch (Exception e){
            e.printStackTrace();
        }
        try {
            InputStream inputStream = socket.getInputStream();
            n=inputStream.read(bs, 0, length);
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
    public void GetFile(MessageObj messageObj,String FileName,String FilePath) throws IOException {
        IOfuncition(messageObj);
        InputStream inputStream = socket.getInputStream();
        File dir = new File(FilePath); // 创建文件的存储路径
        if (!dir.exists()) {
            dir.mkdir();
        }
        String savePath = FilePath+"/"+FileName; // 定义完整的存储路径

        FileOutputStream file = new FileOutputStream(savePath, false);
        byte[] buffer = new byte[1024];
        int size = -1;//我赋值的很随意
        while ((size = inputStream.read(buffer)) != -1) {
            file.write(buffer, 0, size);
        }
        file.close();
        inputStream.close();
    }
}
