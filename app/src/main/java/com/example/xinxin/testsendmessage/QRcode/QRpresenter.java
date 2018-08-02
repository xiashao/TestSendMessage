package com.example.xinxin.testsendmessage.QRcode;
import android.widget.ImageView;

import com.example.xinxin.testsendmessage.Base.BasePresenter;

public class QRpresenter implements QRInteractor.OnSetFinishedListener,BasePresenter {

    private QRView qrView;
    private QRInteractor qrInteractor;

    public QRpresenter(QRView qrView, QRInteractor qrInteractor) {
        this.qrView = qrView;
        this.qrInteractor = qrInteractor;
    }

    public void getQRcode(ImageView imageView) throws InterruptedException {
        if (qrView != null) {
            qrView.showProgress();
        }
        QRInteractor.QRshow(imageView, this);
    }

    public void onDestroy() {
        qrView = null;
    }


    @Override
    public void onNullError() {
        if (qrView != null) {
            qrView.setImageError();
            qrView.hideProgress();
        }
    }

    @Override
    public void onSuccess(){
        if (qrView != null) {
            qrView.setQRimage();
        }
    }
}
