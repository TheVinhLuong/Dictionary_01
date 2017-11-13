package com.vinh.dictionary_1.screen.bookmarkedwordlist;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;

/**
 * Exposes the data to be used in the BookmarkedWordList screen.
 */

public class BookmarkedWordListViewModel implements BookmarkedWordListContract.ViewModel {

    private BookmarkedWordListContract.Presenter mPresenter;
    private Context mContext;

    BookmarkedWordListViewModel(Context context) {
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
    public void setPresenter(BookmarkedWordListContract.Presenter presenter) {
        mPresenter = presenter;
    }

    public void onBackArrowTouched() {
        ((AppCompatActivity) mContext).finish();
    }
}
