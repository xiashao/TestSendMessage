/*
package com.example.xinxin.testsendmessage;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import com.vmeet.netsocket.bean.InfoType;
import com.vmeet.netsocket.bean.PkgHead;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.InetSocketAddress;
import java.net.Socket;
public class SendFunction {
    byte[] bs = null;
    byte[] bs1 = new byte[30000];
    private Bitmap bitmap;
    //接收消息
                           */
/* InputStream inputStream = socket.getInputStream();
                            int n = inputStream.read(bs1);
                            // 输出反馈数据
                            String Msg = new String(bs1, 0, n, "UTF-8");
                            Log.e("smx","服务器——————"+Msg);*//*


    //接收二维码
                        */
/*    InputStream inputStream = socket.getInputStream();
                            byte[] HeadBytes = new byte[32];
                            int n=inputStream.read(HeadBytes, 0, 32);
                            //int n=inputStream.read(head.get_NetHeadByte(), 0, head.get_NetHeadByte().length);
                            Log.d("NNNNN", "NLength :"+n);
                            int c=inputStream.read(bs1, 0, bs1.length);
                            bitmap = BitmapFactory.decodeByteArray(bs1,0,c);
                            runOnUiThread(new Runnable() {
                                public void run() {
                                    imageView.setImageBitmap(bitmap);

                                }
                            });*//*

    //查询row
                            */
/*InputStream inputStream = socket.getInputStream();
                            byte[] HeadBytes = new byte[32];
                            int n=inputStream.read(HeadBytes, 0, 32);
                            //int n=inputStream.read(head.get_NetHeadByte(), 0, head.get_NetHeadByte().length);
                            Log.d("NNNNN", "NLength :"+n);
                            int c=inputStream.read(bs1, 0, bs1.length);
                            String Msg = new String(bs1, 0, c, "UTF-8");
                            Log.e("smx","服务器——————"+Msg);*//*

    public void getMessage(String messageType) {
        String str = "默认消息";
        PkgHead head = new PkgHead();
        switch (messageType) {
            case "二维码":
                head.set_InfoType(InfoType.GetQrCode);
                connectSocket(messageType,head);
                bitmap = BitmapFactory.decodeByteArray(bs1,0,c);
                runOnUiThread(new Runnable() {
                    public void run() {
                        imageView.setImageBitmap(bitmap);

                    }
                });
                break;
            case "文件":
                head.set_InfoType(InfoType.GetFile);
                str = "epuserlist.txt§Data//Rows//BASE//§";
                break;
            case "检索":
                head.set_InfoType(InfoType.GetRowById);
                //弹出框获取str
                break;
            default:
                head.set_InfoType(InfoType.DataObj);
                break;
        }
        // head.set_InfoType(InfoType.GetQrCode);


}
public void connectSocket(String str, PkgHead head){
    //连接服务器
    Socket socket;
    try {

        try {
            bs = str.getBytes("UTF-8");
            Log.d("Str", "run: " + str);
            head.set_InfoLen(bs.length);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        socket = new Socket();
        socket.connect(new InetSocketAddress("123.56.3.72", 30003), 2000);
        //Log.e("smx", head.toString());
        OutputStream outputStream = socket.getOutputStream();
        outputStream.write(head.get_NetHeadByte(), 0, head.get_NetHeadByte().length);
        outputStream.write(bs, 0, bs.length);
        InputStream inputStream = socket.getInputStream();
        byte[] HeadBytes = new byte[32];
        int n=inputStream.read(HeadBytes, 0, 32);
        Log.d("NNNNN", "NLength :"+n);
        int c=inputStream.read(bs1, 0, bs1.length);
    } catch (IOException e) {
        e.printStackTrace();
    }

}
}
                              */
/*  // head.set_InfoType(InfoType.GetQrCode);
                                byte[] bs = null;
                                byte[] bs1 = new byte[30000];
                                try {
                                    bs = str.getBytes("UTF-8");
                                    Log.d("Str", "run: "+str);
                                    head.set_InfoLen(bs.length);
                                } catch (UnsupportedEncodingException e) {
                                    e.printStackTrace();
                                }
                                //连接服务器
                                Socket socket;
                                try {
                                    socket = new Socket();
                                    socket.connect(new InetSocketAddress("123.56.3.72", 30003), 2000);
                                    Log.e("smx", head.toString());
                                    OutputStream outputStream = socket.getOutputStream();
                                    outputStream.write(head.get_NetHeadByte(), 0, head.get_NetHeadByte().length);
                                    outputStream.write(bs, 0, bs.length);
                            }


}
*//*



*/
/*

                new Thread(new Runnable() {
@Override
public void run() {
        // SendFunction send = new SendFunction();
        PkgHead head = new PkgHead();
        head.set_InfoType(InfoType.GetFile);
        // head.set_InfoType(InfoType.GetQrCode);
        byte[] bs = null;
        byte[] bs1 = new byte[30000];
        try {
        bs = str.getBytes("UTF-8");
        Log.d("Str", "run: "+str);
        head.set_InfoLen(bs.length);
        } catch (UnsupportedEncodingException e) {
        e.printStackTrace();
        }
        //连接服务器
        Socket socket;
        try {
        socket = new Socket();
        socket.connect(new InetSocketAddress("123.56.3.72", 30003), 2000);
        Log.e("smx", head.toString());
        OutputStream outputStream = socket.getOutputStream();
        outputStream.write(head.get_NetHeadByte(), 0, head.get_NetHeadByte().length);
        outputStream.write(bs, 0, bs.length);

        //
        //下载文件
        InputStream inputStream = socket.getInputStream();
        byte[] HeadBytes = new byte[32];
        int n=inputStream.read(HeadBytes, 0, 32);
        //int n=inputStream.read(head.get_NetHeadByte(), 0, head.get_NetHeadByte().length);
        Log.d("NNNNN", "NLength :"+n);
        //int c=inputStream.read(bs1, 0, bs1.length);





        InputStream dataStream = socket.getInputStream();
        File dir = new File("/sdcard/Mydata/smx.txt"); // 创建文件的存储路径
        if (!dir.exists()) {
        return;
        }
        String savePath = "/sdcard/Mydata/" + "smx.txt"; // 定义完整的存储路径
        FileOutputStream file = new FileOutputStream(savePath, false);
        byte[] buffer = new byte[1024];
        int size = -1;
        while ((size = dataStream.read(buffer)) != -1) {
        file.write(buffer, 0, size);
        Msg = Msg+new String(buffer, 0, 10,"utf-8");
        Log.e("smx",Msg);

        }
        file.close();
        dataStream.close();
        socket.close();
        runOnUiThread(new Runnable() {
public void run() {
        textView.setText(Msg);
        }
        });

        Log.e("smx","接到了");






        //  String Msg = new String(bs1, 0, 20, "UTF-8");
        // Log.e("smx","服务器——————"+Msg);
        //
        // socket.close();
        } catch (IOException e) {
        e.printStackTrace();
        }
        }
        }).start();*/
