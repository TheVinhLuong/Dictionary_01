package com.vinh.dictionary_1.screen.bookmarkedwordlist;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import com.vinh.dictionary_1.R;
import com.vinh.dictionary_1.databinding.ActivityBookmarkedWordListBinding;
import com.vinh.dictionary_1.screen.BaseActivity;

/**
 * BookmarkedWordList Screen.
 */
public class BookmarkedWordListActivity extends BaseActivity {

    private BookmarkedWordListContract.ViewModel mViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mViewModel = new BookmarkedWordListViewModel(this);

        BookmarkedWordListContract.Presenter presenter =
                new BookmarkedWordListPresenter(mViewModel);
        mViewModel.setPresenter(presenter);

        ActivityBookmarkedWordListBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_bookmarked_word_list);
        binding.setViewModel((BookmarkedWordListViewModel) mViewModel);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mViewModel.onStart();
    }

    @Override
    protected void onStop() {
        mViewModel.onStop();
        super.onStop();
    }
}
