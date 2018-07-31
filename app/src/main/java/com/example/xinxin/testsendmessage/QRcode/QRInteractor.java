package com.example.xinxin.testsendmessage.QRcode;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import com.vmeet.netsocket.bean.InfoType;
import com.vmeet.netsocket.bean.PkgHead;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.InetSocketAddress;
import java.net.Socket;

public class QRInteractor {
    Bitmap bitmap;
    interface OnLoginFinishedListener {
        void onNullError();
        void onSuccess();
    }

    public static void QRshow(final ImageView imageView, final OnLoginFinishedListener listener) {
        final Handler handler=new Handler(){
            public void handleMessage (Message msg){
                switch(msg.what) {
                    case 0:
                        Log.e("smx","Handler传过来了");
                        imageView.setImageBitmap((Bitmap)msg.obj);
                        break;
                    case 1:
                        Log.e("smx","获取失败");
                }
            }
        };
        new Thread(new Runnable() {
            @Override
            public void run() {
                Log.e("smx", "4444444");
                PkgHead head = new PkgHead();
                head.set_InfoType(InfoType.GetQrCode);
                byte[] bs = null;
                byte[] bs1 = new byte[30000];
                String message="123";
                Socket socket;
                try {
                    try {
                        bs = message.getBytes("UTF-8");
                        Log.d("smx", "run: " + message);
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
                    int c=inputStream.read(bs1, 0, bs1.length);
                    Bitmap bitmap = BitmapFactory.decodeByteArray(bs1, 0, c);
                    Log.e("smx","图片已经生成");
                    handler.obtainMessage(0,bitmap).sendToTarget();
                    Log.e("smx","hanlder传值失败");
            }catch (Exception e) {
                    e.printStackTrace();
                   // finalHandler.obtainMessage(1).sendToTarget();
                }
        }
        }).start();
        Log.e("smx","线程完成");
        listener.onSuccess();
    }
}
