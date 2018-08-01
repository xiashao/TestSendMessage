package com.example.xinxin.testsendmessage.QRcode;
import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.xinxin.testsendmessage.Function.SocketFuncation;
import com.example.xinxin.testsendmessage.Object.MessageObj;
import com.vmeet.netsocket.bean.InfoType;
import com.vmeet.netsocket.bean.PkgHead;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.InetSocketAddress;
import java.net.Socket;
public class QRInteractor {
    interface OnLoginFinishedListener {
        void onNullError();
        void onSuccess() throws InterruptedException;
    }
    public static void QRshow(final ImageView imageView, final OnLoginFinishedListener listener) throws InterruptedException {
        @SuppressLint("HandlerLeak") final Handler handler=new Handler(){
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
                String message="二维码";
                try {
                    MessageObj messageObj=new MessageObj(InfoType.GetQrCode,message);
                    SocketFuncation socketFuncation=new SocketFuncation();
                    int c =socketFuncation.IOfuncition(messageObj);
                    Bitmap bitmap = BitmapFactory.decodeByteArray(socketFuncation.bs1, 0, c);
                    handler.obtainMessage(0,bitmap).sendToTarget();
            }catch (Exception e) {
                    e.printStackTrace();
                    handler.obtainMessage(1).sendToTarget();
                    listener.onNullError();
                }
        }
        }).start();
        listener.onSuccess();
    }
}
