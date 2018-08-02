package com.example.xinxin.testsendmessage.DownloadFile;
import com.example.xinxin.testsendmessage.Base.BasePresenter;
/*Created By smx in 2018/8/1
 *心若冰清,天塌不惊; 万变犹定,神怡气静.
 *  .--,       .--,
 * ( (  \.---./  ) )
 *  '.__/o   o\__.'
 *     {=  ^  =}
 *      >  -  <
 *     /       \
 *    //       \\
 *   //|   .   |\\
 *   "'\       /'"_.-~^`'-.
 *      \  _  /--'         `
 *    ___)( )(___
 *   (((__) (__)))
 */
public class GetFilePresenter implements GetFileInteractor.DownloadFinishedListener,BasePresenter{
    private GetFilehView getFilehView;
    private GetFileInteractor getFileInteractor;

    public GetFilePresenter(GetFilehView getFilehView, GetFileInteractor getFileInteractor) {
        this.getFilehView=getFilehView;
        this.getFileInteractor=getFileInteractor;
    }

    public void getFileStart() throws InterruptedException {
        if (getFilehView != null) {
            getFilehView.showProgress();
        }
        getFileInteractor.getFileShow(this);
    }

    @Override
    public void onDestroy() {
            getFilehView = null;
    }


    @Override
    public void onNullError() {
        if (getFilehView != null) {
            getFilehView.downloadError();
            getFilehView.hideProgress();
        }
    }

    @Override
    public void onSuccess(){
        if (getFilehView != null) {
            getFilehView.downloadFileFinished();
        }
    }
}
