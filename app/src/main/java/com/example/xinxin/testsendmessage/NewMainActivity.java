package com.example.xinxin.testsendmessage;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;
import com.example.xinxin.testsendmessage.QRcode.QRInteractor;
import com.example.xinxin.testsendmessage.QRcode.QRView;
import com.example.xinxin.testsendmessage.QRcode.QRpresenter;
import com.example.xinxin.testsendmessage.SearchInfo.SearchView;

public class NewMainActivity extends AppCompatActivity implements QRView,SearchView{
    private ProgressBar progressBar;
    private QRpresenter qRpresenter;
    private ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.qrlayout);
        progressBar = findViewById(R.id.progress);
        imageView= findViewById(R.id.imageView);
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
        qRpresenter = new QRpresenter(this, new QRInteractor());
    }

    @Override
    protected void onDestroy() {
        qRpresenter.onDestroy();
        super.onDestroy();
    }
    @Override
    public void showQRProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideQRProgress() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void setImageError() {
        Toast.makeText(NewMainActivity.this, "图片设置失败", Toast.LENGTH_SHORT).show();
    }
    @Override
    public void setQRimage() {
        Toast.makeText(NewMainActivity.this, "获取二维码成功", Toast.LENGTH_SHORT).show();
        progressBar.setVisibility(View.GONE);
    }
    private void getQRcode() throws InterruptedException {
        qRpresenter.getQRcode(imageView);
    }

    @Override
    public void showSearchProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideSearchProgress() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void searchError() {
        Toast.makeText(NewMainActivity.this, "查询失败", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void searchInfo() {
        Toast.makeText(NewMainActivity.this, "查询成功", Toast.LENGTH_SHORT).show();
        progressBar.setVisibility(View.GONE);
    }
}
