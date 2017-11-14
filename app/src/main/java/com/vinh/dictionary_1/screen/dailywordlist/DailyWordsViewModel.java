package com.vinh.dictionary_1.screen.dailywordlist;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import com.vinh.dictionary_1.BR;

/**
 * Exposes the data to be used in the DailyWordList screen.
 */

public class DailyWordsViewModel extends BaseObservable implements DailyWordsContract.ViewModel {

    private DailyWordsContract.Presenter mPresenter;
    private Fragment mFragment;
    private int mTitleVisibility;
    private Context mContext;

    public DailyWordsViewModel(Context context) {
        mContext = context;
        //TODO: For later use
//        setFragment(DailyWordListFragment.newInstance());
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
    public void setPresenter(DailyWordsContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Bindable
    public Fragment getFragment() {
        return mFragment;
    }

    public void setFragment(Fragment fragment) {
        mFragment = fragment;
        notifyPropertyChanged(BR.fragment);
    }

    public void onBackArrowTouched() {
        FragmentManager fragmentManager =
                ((AppCompatActivity) mContext).getSupportFragmentManager();
        if (fragmentManager.getFragments().size() > 1) {
            ((AppCompatActivity) mContext).onBackPressed();
        } else {
            ((AppCompatActivity) mContext).finish();
        }
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
