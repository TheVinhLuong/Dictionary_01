package com.vinh.dictionary_1.screen.home.wordlist;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import com.vinh.dictionary_1.R;
import com.vinh.dictionary_1.data.model.Word;
import com.vinh.dictionary_1.databinding.FragmentWordListBinding;
import com.vinh.dictionary_1.screen.worddetail.WordDetailFragment;
import com.vinh.dictionary_1.utis.Constant;
import com.vinh.dictionary_1.utis.ViewUtils;
import java.util.List;

/**
 * Exposes the data to be used in the WordList screen.
 */

public class WordListViewModel
        implements WordListContract.ViewModel, WordListAdapter.OnRecyclerViewItemClickListener {
    private static final String TAG = WordListViewModel.class.getSimpleName();
    private WordListContract.Presenter mPresenter;
    private WordListAdapter mAdapter;
    private FragmentWordListBinding mFragmentWordListBinding;
    private RecyclerView.OnScrollListener mOnScrollListener;

    public WordListViewModel(WordListAdapter adapter) {
        mAdapter = adapter;
        mAdapter.setItemClickListener(this);
        mOnScrollListener = new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_DRAGGING) {
                    ViewUtils.hideSoftKeyboard(recyclerView.getContext());
                }
            }
        };
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
    public void setPresenter(WordListContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void onItemRecyclerViewClick(View view, Object item) {
        mPresenter.onItemWordListClicked((Word) item);
        ViewUtils.hideSoftKeyboard(view.getContext());
        Bundle bundle = new Bundle();
        bundle.putSerializable(Constant.ARGUMENT_WORD, (Word) item);
        WordDetailFragment wordDetailFragment = WordDetailFragment.newInstance();
        wordDetailFragment.setArguments(bundle);
        ((AppCompatActivity) mFragmentWordListBinding.wordRecyclerView.getContext())
                .getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.fragment_container, wordDetailFragment)
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void changeDataSet(List<Word> words) {
        mAdapter.changeDataSet(words);
        if (mFragmentWordListBinding != null) {
            mFragmentWordListBinding.wordRecyclerView.getLayoutManager().scrollToPosition(0);
        }
    }

    @Override
    public WordListAdapter getAdapter() {
        return mAdapter;
    }

    @Override
    public void setAdapter(WordListAdapter adapter) {
        mAdapter = adapter;
    }

    @Override
    public void setBinding(FragmentWordListBinding fragmentWordListBinding) {
        mFragmentWordListBinding = fragmentWordListBinding;
    }

    @Override
    public void onTextChange(String queryWord) {
        mPresenter.onTextChange(queryWord);
    }

    @Override
    public void onBroadcastReceiverReceive(Context context, Intent intent) {
        mPresenter.onBroadcastReceiverReceive(context, intent);
    }

    public RecyclerView.OnScrollListener getOnScrollListener() {
        return mOnScrollListener;
    }
}
