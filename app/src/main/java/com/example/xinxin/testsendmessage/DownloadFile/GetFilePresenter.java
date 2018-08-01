package com.example.xinxin.testsendmessage.DownloadFile;
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
public class GetFilePresenter implements GetFileInteractor.DownloadFinishedListener{
    private GetFilehView getFilehView;
    private GetFileInteractor getFileInteractor;

    public GetFilePresenter(GetFilehView getFilehView, GetFileInteractor getFileInteractor) {
        this.getFilehView=getFilehView;
        this.getFileInteractor=getFileInteractor;
    }

    public void downloadStart() throws InterruptedException {
        if (getFilehView != null) {
            getFilehView.showDownlaodProgress();
        }
        getFileInteractor.getFileShow(this);
    }

    public void onDestroy() {
        getFilehView = null;
    }


    @Override
    public void onNullError() {
        if (getFilehView != null) {
            getFilehView.downloadError();
            getFilehView.hideDownloadProgress();
        }
    }

    @Override
    public void onSuccess(){
        if (getFilehView != null) {
            getFilehView.downloadFileFinished();
        }
    }
}
