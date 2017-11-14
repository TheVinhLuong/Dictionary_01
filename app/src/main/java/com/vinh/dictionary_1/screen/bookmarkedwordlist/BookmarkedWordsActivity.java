package com.vinh.dictionary_1.screen.bookmarkedwordlist;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.view.View;
import com.vinh.dictionary_1.R;
import com.vinh.dictionary_1.databinding.ActivityBookmarkedWordsBinding;
import com.vinh.dictionary_1.screen.BaseActivity;
import com.vinh.dictionary_1.screen.bookmarkedwordlist.wordlist.BookmarkedWordListFragment;

/**
 * BookmarkedWordList Screen.
 */
public class BookmarkedWordsActivity extends BaseActivity {

    private BookmarkedWordsContract.ViewModel mViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mViewModel = new BookmarkedWordsViewModel(this);

        BookmarkedWordsContract.Presenter presenter = new BookmarkedWordsPresenter(mViewModel);
        mViewModel.setPresenter(presenter);

        ActivityBookmarkedWordsBinding binding =
                DataBindingUtil.setContentView(this, R.layout.activity_bookmarked_words);
        binding.setViewModel((BookmarkedWordsViewModel) mViewModel);
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

    @Override
    public void onFragmentCreated() {
        if (getSupportFragmentManager().getFragments().size() == 2) {
            mViewModel.setTitleVisibility(View.INVISIBLE);
        }
    }

    @Override
    public void onFragmentDetach() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        if (fragmentManager.getFragments().size() == 1) {
            ((BookmarkedWordListFragment)fragmentManager.getFragments().get(0)).onVisibleAgain();
            mViewModel.setTitleVisibility(View.VISIBLE);
        }
    }
    
    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getFragments().size() == 1) {
            finish();
        } else {
            super.onBackPressed();
        }
    }
}
