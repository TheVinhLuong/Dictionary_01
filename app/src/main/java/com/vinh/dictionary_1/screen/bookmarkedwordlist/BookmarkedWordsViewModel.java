package com.vinh.dictionary_1.screen.bookmarkedwordlist;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import com.vinh.dictionary_1.BR;

/**
 * Exposes the data to be used in the BookmarkedWordList screen.
 */

public class BookmarkedWordsViewModel extends BaseObservable
        implements BookmarkedWordsContract.ViewModel {

    private BookmarkedWordsContract.Presenter mPresenter;
    private Context mContext;

    private Fragment mFragment;
    private int mTitleVisibility;

    BookmarkedWordsViewModel(Context context) {
        mContext = context;
        //TODO: For later use
//        setFragment(BookmarkedWordListFragment.newInstance());
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
    public void setPresenter(BookmarkedWordsContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Bindable
    public Fragment getFragment() {
        return mFragment;
    }

    public void setFragment(Fragment fragment) {
        mFragment = fragment;
    }
    
    public void onBackArrowTouched() {
        ((AppCompatActivity) mContext).finish();
    }

    @Bindable
    public int getTitleVisibility() {
        return mTitleVisibility;
    }

    public void setTitleVisibility(int titleVisibility) {
        mTitleVisibility = titleVisibility;
        notifyPropertyChanged(BR.titleVisibility);
    }
}
