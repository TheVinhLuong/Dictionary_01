package com.vinh.dictionary_1.screen.home.homelist;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.PagerSnapHelper;
import android.support.v7.widget.SnapHelper;
import android.view.View;
import com.vinh.dictionary_1.R;
import com.vinh.dictionary_1.data.model.DailyWord;
import com.vinh.dictionary_1.data.model.Word;
import com.vinh.dictionary_1.screen.worddetail.WordDetailFragment;
import com.vinh.dictionary_1.utis.Constant;
import com.vinh.dictionary_1.utis.ViewUtils;
import java.util.List;

/**
 * Exposes the data to be used in the HomeList screen.
 */

public class HomeListViewModel implements HomeListContract.ViewModel,
        HomeListDailyWordAdapter.OnRecyclerViewItemClickListener {

    private HomeListContract.Presenter mPresenter;
    private HomeListDailyWordAdapter mDailyWordAdapter;
    private HomeListSearchedWordAdapter mSearchedWordAdapter;
    private SnapHelper mSnapHelper;

    public HomeListViewModel(HomeListDailyWordAdapter dailyWordAdapter,
            HomeListSearchedWordAdapter homeListSearchedWordAdapter) {
        mDailyWordAdapter = dailyWordAdapter;
        mDailyWordAdapter.setItemClickListener(this);
        mSearchedWordAdapter = homeListSearchedWordAdapter;
        mSearchedWordAdapter.setItemClickListener(this);
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
        Word word;
        if (item instanceof DailyWord) {
            word = ((DailyWord) item).toWord();
            mPresenter.onItemDailyWordListClicked((DailyWord) item);
        } else {
            word = (Word) item;
            mPresenter.onItemSearchedWordListClicked((Word) item);
        }
        ViewUtils.hideSoftKeyboard(view.getContext());
        Bundle bundle = new Bundle();
        bundle.putSerializable(Constant.ARGUMENT_WORD, word);
        WordDetailFragment wordDetailFragment = WordDetailFragment.newInstance();
        wordDetailFragment.setArguments(bundle);
        ((AppCompatActivity) view.getContext()).getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.fragment_container, wordDetailFragment)
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void changeDailyWordDataSet(List<DailyWord> words) {
        mDailyWordAdapter.changeDataSet(words);
    }

    @Override
    public void changeSearchedWordDataSet(List<Word> words) {
        mSearchedWordAdapter.changeDataSet(words);
    }

    @Override
    public void onBroadcastReceiverReceive(Context context, Intent intent) {
        mPresenter.onBroadcastReceiverReceive(context, intent);
    }

    public HomeListDailyWordAdapter getDailyWordAdapter() {
        return mDailyWordAdapter;
    }

    public void setDailyWordAdapter(HomeListDailyWordAdapter dailyWordAdapter) {
        mDailyWordAdapter = dailyWordAdapter;
    }

    public HomeListSearchedWordAdapter getSearchedWordAdapter() {
        return mSearchedWordAdapter;
    }

    public void setSearchedWordAdapter(HomeListSearchedWordAdapter searchedWordAdapter) {
        mSearchedWordAdapter = searchedWordAdapter;
    }

    public SnapHelper getSnapHelper() {
        return mSnapHelper;
    }

    public void setSnapHelper(SnapHelper snapHelper) {
        mSnapHelper = snapHelper;
    }
}
