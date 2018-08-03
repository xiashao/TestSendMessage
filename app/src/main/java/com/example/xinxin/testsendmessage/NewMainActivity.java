package com.example.xinxin.testsendmessage;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import com.example.xinxin.testsendmessage.DownloadFile.GetFileInteractor;
import com.example.xinxin.testsendmessage.DownloadFile.GetFilePresenter;
import com.example.xinxin.testsendmessage.DownloadFile.GetFilehView;
import com.example.xinxin.testsendmessage.QRcode.QRInteractor;
import com.example.xinxin.testsendmessage.QRcode.QRView;
import com.example.xinxin.testsendmessage.QRcode.QRpresenter;
import com.example.xinxin.testsendmessage.SearchInfo.SearchInteractor;
import com.example.xinxin.testsendmessage.SearchInfo.SearchView;
import com.example.xinxin.testsendmessage.SearchInfo.Searchpresenter;
public class NewMainActivity extends AppCompatActivity implements QRView, SearchView,GetFilehView{
    private ProgressBar progressBar;
    private QRpresenter qRpresenter;
    private Searchpresenter searchpresenter;
    private GetFilePresenter getFilePresenter;
    private ImageView imageView;
    private TextView textView;
    private EditText editText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.qrlayout);
        progressBar = findViewById(R.id.progress);
        imageView= findViewById(R.id.imageView);
        textView=findViewById(R.id.seachText);
        editText=findViewById(R.id.editSearch);
        findViewById(R.id.button1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    search(editText,textView);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    getQRcode();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        findViewById(R.id.button2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    downloadFile();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        qRpresenter = new QRpresenter(this, new QRInteractor());
        searchpresenter=new Searchpresenter(this,new SearchInteractor());
        getFilePresenter=new GetFilePresenter(this,new GetFileInteractor());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        qRpresenter.onDestroy();
        searchpresenter.onDestroy();
        getFilePresenter.onDestroy();
    }

    private void getQRcode() throws InterruptedException {
        qRpresenter.getQRcode(imageView);
    }
    private void search(EditText editText,TextView textView) throws InterruptedException {
        searchpresenter.searchStart(editText,textView);
    }
    private void downloadFile() throws InterruptedException {
       getFilePresenter.getFileStart();
    }
    @Override
    public void setImageError() {
        Toast.makeText(NewMainActivity.this, "图片设置失败", Toast.LENGTH_SHORT).show();
    }
    @Override
    public void setQRimage() {
        Toast.makeText(NewMainActivity.this, "获取二维码成功", Toast.LENGTH_SHORT).show();
        hideProgress();
    }
    @Override
    public void searchError() {
        Toast.makeText(NewMainActivity.this, "查询失败", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void searchInfo() {
        Toast.makeText(NewMainActivity.this, "查询成功", Toast.LENGTH_SHORT).show();
        hideProgress();
    }
    @Override
    public void downloadError() {
        Toast.makeText(NewMainActivity.this, "下载失败", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void downloadFileFinished() {
        Toast.makeText(NewMainActivity.this, "下载成功", Toast.LENGTH_SHORT).show();
        hideProgress();
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }
}
