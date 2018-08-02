package com.example.xinxin.testsendmessage.DownloadFile;

import android.util.Log;

import com.example.xinxin.testsendmessage.Function.SocketFuncation;
import com.example.xinxin.testsendmessage.Object.MessageObj;
import com.vmeet.netsocket.bean.InfoType;

public class GetFileInteractor{
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
                    socketFuncation.GetFile(messageObj,"smx.txt","/sdcard/Mydata");
            }catch (Exception e) {
                    e.printStackTrace();
                    listener.onNullError();
                }
        }
        }).start();
        listener.onSuccess();
    }
}
