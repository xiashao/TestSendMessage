package com.example.xinxin.testsendmessage.DownloadFile;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

import com.example.xinxin.testsendmessage.Function.SocketFuncation;
import com.example.xinxin.testsendmessage.Object.MessageObj;
import com.vmeet.netsocket.bean.InfoType;

public class GetFileInteractor {
    interface DownloadFinishedListener {
        void onNullError();
        void onSuccess() throws InterruptedException;
    }
    public static void getFileShow(final DownloadFinishedListener listener) throws InterruptedException {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    MessageObj messageObj=new MessageObj(InfoType.GetFile);
                    messageObj.content="epuserlist.txt§Data//Rows//BASE//§";
                    Log.e("smx","messageObj.content:"+messageObj.content);
                    SocketFuncation socketFuncation=new SocketFuncation();
                    socketFuncation.GetFile(messageObj);
            }catch (Exception e) {
                    e.printStackTrace();
                 //   listener.onNullError();
                }
        }
        }).start();
        listener.onSuccess();
    }
}
