package com.example.xinxin.testsendmessage.QRcode;

import android.widget.ImageView;
public class QRpresenter implements QRInteractor.OnLoginFinishedListener {

    private QRView qrView;
    private QRInteractor qrInteractor;

    public QRpresenter(QRView qrView, QRInteractor qrInteractor) {
        this.qrView = qrView;
        this.qrInteractor = qrInteractor;
    }

    public void validateCredentials(ImageView imageView) {
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
    public void onSuccess() {
        if (qrView != null) {
            qrView.setQRimage();
        }
    }
}
