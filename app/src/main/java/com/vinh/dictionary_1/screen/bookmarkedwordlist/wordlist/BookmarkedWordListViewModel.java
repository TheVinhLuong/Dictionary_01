package com.vinh.dictionary_1.screen.bookmarkedwordlist.wordlist;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import com.vinh.dictionary_1.R;
import com.vinh.dictionary_1.data.model.Word;
import com.vinh.dictionary_1.databinding.FragmentBookmarkedWordListBinding;
import com.vinh.dictionary_1.screen.worddetail.WordDetailFragment;
import com.vinh.dictionary_1.utis.Constant;
import com.vinh.dictionary_1.utis.ViewUtils;
import java.util.List;

/**
 * Exposes the data to be used in the WordList screen.
 */

public class BookmarkedWordListViewModel
        implements BookmarkedWordListContract.ViewModel, BookmarkedWordListAdapter.OnRecyclerViewItemClickListener {
    private static final String TAG = BookmarkedWordListViewModel.class.getSimpleName();
    private BookmarkedWordListContract.Presenter mPresenter;
    private BookmarkedWordListAdapter mAdapter;
    private FragmentBookmarkedWordListBinding mFragmentBookmarkedWordListBinding;
    private RecyclerView.OnScrollListener mOnScrollListener;

    BookmarkedWordListViewModel(BookmarkedWordListAdapter adapter) {
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
    public void setPresenter(BookmarkedWordListContract.Presenter presenter) {
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
        ((AppCompatActivity) mFragmentBookmarkedWordListBinding.wordRecyclerView.getContext())
                .getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.fragment_container, wordDetailFragment)
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void changeDataSet(List<Word> words) {
        mAdapter.changeDataSet(words);
        if (mFragmentBookmarkedWordListBinding != null) {
            mFragmentBookmarkedWordListBinding.wordRecyclerView.getLayoutManager().scrollToPosition(0);
        }
    }

    @Override
    public BookmarkedWordListAdapter getAdapter() {
        return mAdapter;
    }

    @Override
    public void setAdapter(BookmarkedWordListAdapter adapter) {
        mAdapter = adapter;
    }

    @Override
    public void setBinding(FragmentBookmarkedWordListBinding fragmentWordListBinding) {
        mFragmentBookmarkedWordListBinding = fragmentWordListBinding;
    }

    public RecyclerView.OnScrollListener getOnScrollListener() {
        return mOnScrollListener;
    }
}
