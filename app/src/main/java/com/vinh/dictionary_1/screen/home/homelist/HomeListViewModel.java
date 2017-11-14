package com.vinh.dictionary_1.screen.home.homelist;

import android.support.v7.widget.PagerSnapHelper;
import android.support.v7.widget.SnapHelper;
import android.view.View;
import com.vinh.dictionary_1.data.model.DailyWord;
import java.util.List;

/**
 * Exposes the data to be used in the HomeList screen.
 */

public class HomeListViewModel implements HomeListContract.ViewModel,
        HomeListDailyWordAdapter.OnRecyclerViewItemClickListener {

    private HomeListContract.Presenter mPresenter;
    private HomeListDailyWordAdapter mDailyWordAdapter;
    private SnapHelper mSnapHelper;

    public HomeListViewModel(HomeListDailyWordAdapter dailyWordAdapter) {
        mDailyWordAdapter = dailyWordAdapter;
        mDailyWordAdapter.setItemClickListener(this);
        mSnapHelper = new PagerSnapHelper();
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
    public void setPresenter(HomeListContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void onItemRecyclerViewClick(View view, Object item) {
        mPresenter.onItemWordListClicked((DailyWord) item);
    }

    @Override
    public void changeDataSet(List<DailyWord> words) {
        mDailyWordAdapter.changeDataSet(words);
    }

    public HomeListDailyWordAdapter getDailyWordAdapter() {
        return mDailyWordAdapter;
    }

    public void setDailyWordAdapter(HomeListDailyWordAdapter dailyWordAdapter) {
        mDailyWordAdapter = dailyWordAdapter;
    }

    public SnapHelper getSnapHelper() {
        return mSnapHelper;
    }

    public void setSnapHelper(SnapHelper snapHelper) {
        mSnapHelper = snapHelper;
    }
}
