package com.example.xinxin.testsendmessage.SearchInfo;

import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

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
public class Searchpresenter implements SearchInteractor.SeachListener {

    private SearchView searchView;
    private SearchInteractor searchInteractor;

    public Searchpresenter(SearchView searchView, SearchInteractor searchInteractor) {
        this.searchView=searchView;
        this.searchInteractor=searchInteractor;
    }

    public void searchStart(EditText editText,TextView textView) throws InterruptedException {
        if (searchView != null) {
            searchView.showSearchProgress();
        }
        Log.e("smx","searchStart");
        searchInteractor.Searchshow(textView,editText, this);
    }

    public void onDestroy() {
        searchView = null;
    }


    @Override
    public void onNullError() {
        if (searchView != null) {
            searchView.searchError();
            searchView.hideSearchProgress();
        }
    }

    @Override
    public void onSuccess(){
        if (searchView != null) {
            searchView.searchInfo();
        }
    }
}