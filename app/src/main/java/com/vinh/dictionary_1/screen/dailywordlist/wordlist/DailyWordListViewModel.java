package com.vinh.dictionary_1.screen.dailywordlist.wordlist;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import com.vinh.dictionary_1.R;
import com.vinh.dictionary_1.data.model.DailyWord;
import com.vinh.dictionary_1.databinding.FragmentDailyWordListBinding;
import com.vinh.dictionary_1.screen.worddetail.WordDetailFragment;
import com.vinh.dictionary_1.utis.Constant;
import com.vinh.dictionary_1.utis.OnEndScrollListener;
import com.vinh.dictionary_1.utis.ViewUtils;
import java.util.List;

/**
 * Exposes the data to be used in the WordList screen.
 */

public class DailyWordListViewModel implements DailyWordListContract.ViewModel,
        DailyWordListAdapter.OnRecyclerViewItemClickListener {
    private static final String TAG = DailyWordListViewModel.class.getSimpleName();
    private DailyWordListContract.Presenter mPresenter;
    private DailyWordListAdapter mAdapter;
    private FragmentDailyWordListBinding mFragmentDailyWordListBinding;
    private RecyclerView.OnScrollListener mOnScrollListener;

    DailyWordListViewModel(DailyWordListAdapter adapter) {
        mAdapter = adapter;
        mAdapter.setItemClickListener(this);
        mOnScrollListener = new OnEndScrollListener(() -> {
            mPresenter.getDailyWords();
        });
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
    public void setPresenter(DailyWordListContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void onItemRecyclerViewClick(View view, Object item) {
        mPresenter.onItemWordListClicked((DailyWord) item);
        ViewUtils.hideSoftKeyboard(view.getContext());
        Bundle bundle = new Bundle();
        bundle.putSerializable(Constant.ARGUMENT_WORD, ((DailyWord) item).toWord());
        WordDetailFragment wordDetailFragment = WordDetailFragment.newInstance();
        wordDetailFragment.setArguments(bundle);
        ((AppCompatActivity) mFragmentDailyWordListBinding.wordRecyclerView.getContext())
                .getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.fragment_container, wordDetailFragment)
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void appendDailyWordDataSet(List<DailyWord> words) {
        mAdapter.appendDataSet(words);
        if (mFragmentDailyWordListBinding != null) {
            mFragmentDailyWordListBinding.wordRecyclerView.getLayoutManager().scrollToPosition(0);
        }
    }

    @Override
    public void clearDailyWordDataSet() {
        mAdapter.clearDataSet();
    }

    @Override
    public DailyWordListAdapter getAdapter() {
        return mAdapter;
    }

    @Override
    public void setAdapter(DailyWordListAdapter adapter) {
        mAdapter = adapter;
    }

    @Override
    public void setBinding(FragmentDailyWordListBinding fragmentDailyWordListBinding) {
        mFragmentDailyWordListBinding = fragmentDailyWordListBinding;
    }

    public RecyclerView.OnScrollListener getOnScrollListener() {
        return mOnScrollListener;
    }
}
