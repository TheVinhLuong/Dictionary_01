package com.vinh.dictionary_1.screen.bookmarkedwordlist;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.View;
import com.vinh.dictionary_1.R;
import com.vinh.dictionary_1.databinding.ActivityBookmarkedWordsBinding;
import com.vinh.dictionary_1.screen.BaseActivity;

/**
 * BookmarkedWordList Screen.
 */
public class BookmarkedWordsActivity extends BaseActivity {

    private BookmarkedWordsContract.ViewModel mViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mViewModel = new BookmarkedWordsViewModel(this);

        BookmarkedWordsContract.Presenter presenter =
                new BookmarkedWordsPresenter(mViewModel);
        mViewModel.setPresenter(presenter);

        ActivityBookmarkedWordsBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_bookmarked_words);
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
      if(getSupportFragmentManager().getFragments().size() == 2){
          mViewModel.setTitleVisibility(View.INVISIBLE);
      }
      
    }

    @Override
    public void onFragmentDetach() {
        if(getSupportFragmentManager().getFragments().size() == 1){
            mViewModel.setTitleVisibility(View.VISIBLE);
        }
    }
}
