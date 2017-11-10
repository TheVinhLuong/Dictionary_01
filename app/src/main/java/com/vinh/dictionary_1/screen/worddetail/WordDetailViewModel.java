package com.vinh.dictionary_1.screen.worddetail;

import android.content.Context;
import android.webkit.WebViewClient;
import com.vinh.dictionary_1.data.model.Word;

/**
 * Exposes the data to be used in the WordDetail screen.
 */

public class WordDetailViewModel implements com.vinh.dictionary_1.screen.worddetail.WordDetailContract.ViewModel {

    private com.vinh.dictionary_1.screen.worddetail.WordDetailContract.Presenter mPresenter;
    private Context mContext;
    private Word mWord;
    private String mWordDescription;
    private WebViewClient mWordDetailWebViewClient;

    WordDetailViewModel(Context context) {
        mContext = context;
    }

    @Override
    public void onStart() {
        mPresenter.onStart();
    }

    @Override
    public void onStop() {
        mPresenter.onStop();
    }

    @Override
    public void setPresenter(com.vinh.dictionary_1.screen.worddetail.WordDetailContract.Presenter presenter) {
        mPresenter = presenter;
        mWordDetailWebViewClient = new com.vinh.dictionary_1.screen.worddetail.WordDetailPresenter.WordDetailWebViewClient(mContext,
                mPresenter.getDictRepository());
    }

    public Word getWord() {
        return mWord;
    }

    @Override
    public void setWord(Word word) {
        mWord = word;
        if (mWord.getEVDescription() != null) {
            mWordDescription = mWord.getEVDescription();
        } else {
            mWordDescription = mWord.getVEDescription();
        }
    }

    public String getWordDescription() {
        return mWordDescription;
    }

    public void setWordDescription(String wordDescription) {
        mWordDescription = wordDescription;
    }

    public WebViewClient getWordDetailWebViewClient() {
        return mWordDetailWebViewClient;
    }

    public void setWordDetailWebViewClient(
            com.vinh.dictionary_1.screen.worddetail.WordDetailPresenter.WordDetailWebViewClient wordDetailWebViewClient) {
        mWordDetailWebViewClient = wordDetailWebViewClient;
    }
}
