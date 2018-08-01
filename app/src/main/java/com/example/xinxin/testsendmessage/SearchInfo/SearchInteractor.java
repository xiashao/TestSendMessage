package com.example.xinxin.testsendmessage.SearchInfo;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.xinxin.testsendmessage.Function.SocketFuncation;
import com.example.xinxin.testsendmessage.Object.MessageObj;
import com.vmeet.netsocket.bean.InfoType;

public class SearchInteractor {
    interface OnLoginFinishedListener {
        void onNullError();
        void onSuccess() throws InterruptedException;
    }
    public static void Searchshow(final TextView textView, final EditText editText,final OnLoginFinishedListener listener) throws InterruptedException {
        @SuppressLint("HandlerLeak") final Handler handler=new Handler(){
            public void handleMessage (Message msg){
                switch(msg.what) {
                    case 0:
                        //imageView.setImageBitmap((Bitmap)msg.obj);
                        textView.setText((String)msg.obj);
                        break;
                    case 1:
                        Log.e("smx","获取失败");
                }
            }
        };
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    MessageObj messageObj=new MessageObj(InfoType.GetQrCode);
                    messageObj.content="BASE∈rolelist∈"+editText.getText().toString();
                    SocketFuncation socketFuncation=new SocketFuncation();
                    int c =socketFuncation.IOfuncition(messageObj);
                    String searchResult=new String(socketFuncation.bs1,0,c);
                    handler.obtainMessage(0,searchResult).sendToTarget();
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
