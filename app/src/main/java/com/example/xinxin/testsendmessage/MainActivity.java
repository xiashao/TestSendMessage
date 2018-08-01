package com.example.xinxin.testsendmessage;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.vmeet.netsocket.bean.InfoType;
import com.vmeet.netsocket.bean.PkgHead;

import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
public class MainActivity extends AppCompatActivity {

    private FileInputStream fis;

    private DataOutputStream dos;


    // 聊天信息输入框
    private EditText mInputEdit;
    // 发送按钮
    private Button mSendBtn;
    private ImageView imageView;
    //private TextView textView;
    private String rowContant;//所查询的内容
    private Bitmap bitmap;
    private int c;//除了头，所接收到的字节数
    private String sendmsg;//传入的值
    private String tdlog;//弹出框的值
    private String Msg = "ssssssss";
    byte[] bs = null;
    byte[] bs1 = new byte[30000];
    private TextDialog tDialog;
    PkgHead head = new PkgHead();
    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            switch (v.getId()) {
                case R.id.yes:
                    //TextDialog a=new TextDialog(MainActivity.this);
                    tdlog = tDialog.text.getText().toString();
                    Log.e("smx", "idididididid" + sendmsg);
                    tDialog.cancel();
                    tdlog = "BASE∈rolelist∈" + tdlog;
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            connectSocket(tdlog, head);
                        }
                    }).start();
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mSendBtn = findViewById(R.id.ec_btn_send);
        mInputEdit = findViewById(R.id.ec_edit_message_input);
        imageView = findViewById(R.id.imageView);
        mSendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendmsg = mInputEdit.getText().toString();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Log.e("smx", "4444444" + sendmsg);
                        getMessage(sendmsg);
                    }
                }).start();
                mInputEdit.getText().clear();
            }
        });

    }

    public void getMessage(String messageType) {
        String str = "默认消息";
        Log.e("smx", "000000" + messageType);

        switch (messageType) {
            case "二维码":
                head.set_InfoType(InfoType.GetQrCode);
                Log.e("smx", "二维码程序开始");
                connectSocket(str, head);
                bitmap = BitmapFactory.decodeByteArray(bs1, 0, c);
                Log.e("smx", "已经生成图片");
                runOnUiThread(new Runnable() {
                    public void run() {
                        imageView.setImageBitmap(bitmap);
                    }
                });
                break;
            case "文件":
                head.set_InfoType(InfoType.GetFile);
                str = "epuserlist.txt§Data//Rows//BASE//§";
                Log.e("smx", "文件ok");
                connectSocket(str, head);
                break;
            case "检索":
                head.set_InfoType(InfoType.GetRowById);
                runOnUiThread(new Runnable() {
                    public void run() {
                        //弹出框获取str
                        tDialog = new TextDialog(MainActivity.this, onClickListener);
                        tDialog.show();
                    }
                });
                break;
            case "上传文件":
                head.set_InfoType(InfoType.SendFile);
               str = "smx.txt§img/008734";
                //str = "Data//Rows//BASE//smx.txt";
                Log.e("smx", "文件ok");
                connectSocket(str, head);
                break;
            default:
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(MainActivity.this, sendmsg, Toast.LENGTH_SHORT).show();
                    }
                });
                break;
        }
    }
    public void connectSocket(String message, PkgHead head) {
        //连接服务器
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

            if (sendmsg.equals("文件")) {
                InputStream inputStream = socket.getInputStream();
                byte[] HeadBytes = new byte[32];
                int n = inputStream.read(HeadBytes, 0, 32);
                c = inputStream.read(bs1, 0, bs1.length);

                Log.d("smx", "文件开始下载了");
                File dir = new File("/sdcard/Mydata/smx.txt"); // 创建文件的存储路径
                if (!dir.exists()) {
                    dir.mkdir();
                }
                String savePath = "/sdcard/Mydata/" + "smx.txt"; // 定义完整的存储路径
                FileOutputStream file = new FileOutputStream(savePath, false);
                byte[] buffer = new byte[1024];
                int size = -1;
                while ((size = inputStream.read(buffer)) != -1) {
                    file.write(buffer, 0, size);
                    Msg = new String(buffer, 0, 10, "utf-8");
                    Log.e("smx", Msg);
                }
                file.close();
                inputStream.close();
            } else if (sendmsg.equals("检索")) {
                InputStream inputStream = socket.getInputStream();
                byte[] HeadBytes = new byte[32];
                int n = inputStream.read(HeadBytes, 0, 32);
                c = inputStream.read(bs1, 0, bs1.length);
                rowContant = new String(bs1, 0, c, "UTF-8");
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(MainActivity.this, rowContant, Toast.LENGTH_SHORT).show();
                    }
                });
            }
            else if (sendmsg.equals("二维码")){
                InputStream inputStream = socket.getInputStream();
                byte[] HeadBytes = new byte[32];
                int n=inputStream.read(HeadBytes, 0, 32);
                c=inputStream.read(bs1, 0, bs1.length);
            }
            else if(sendmsg.equals("上传文件")){
                try {
                    File file = new File("/sdcard/Mydata/smx.txt");
                    if (file.exists()) {
                        Log.e("smx","文件存在");
                        fis = new FileInputStream(file);
                        dos = new DataOutputStream(socket.getOutputStream());
                        // 文件名和长度
                        dos.writeUTF(file.getName());
                        dos.flush();
                        dos.writeLong(file.length());
                        dos.flush();
                        // 开始传输文件
                        Log.e("smx","======== 开始传输文件 ========");
                        byte[] bytes = new byte[1024];
                        int length = -1;
                        long progress = 0;
                        while ((length = fis.read(bytes, 0, bytes.length)) != -1) {
                            dos.write(bytes, 0, length);
                            dos.flush();
                            progress += length;
                            System.out.print("| " + (100 * progress / file.length()) + "% |");
                        }
                        Log.e("smx","======== 传输文件成功 ========");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (fis != null)
                        fis.close();
                    if (dos != null)
                        dos.close();
                }
            }
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
