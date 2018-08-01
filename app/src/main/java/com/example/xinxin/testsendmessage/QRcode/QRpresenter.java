package com.example.xinxin.testsendmessage.QRcode;
import android.widget.ImageView;
public class QRpresenter implements QRInteractor.OnSetFinishedListener {

    private QRView qrView;
    private QRInteractor qrInteractor;

    public QRpresenter(QRView qrView, QRInteractor qrInteractor) {
        this.qrView = qrView;
        this.qrInteractor = qrInteractor;
    }

    public void getQRcode(ImageView imageView) throws InterruptedException {
        if (qrView != null) {
            qrView.showQRProgress();
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
            qrView.hideQRProgress();
        }
    }

    @Override
    public void onSuccess(){
        if (qrView != null) {
            qrView.setQRimage();
        }
    }
}
