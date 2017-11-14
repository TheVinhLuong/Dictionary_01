package com.vinh.dictionary_1.screen.dailywordlist;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.View;
import com.vinh.dictionary_1.R;
import com.vinh.dictionary_1.databinding.ActivityDailyWordsBinding;
import com.vinh.dictionary_1.screen.BaseActivity;

/**
 * DailyWordList Screen.
 */
public class DailyWordsActivity extends BaseActivity {

    private DailyWordsContract.ViewModel mViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mViewModel = new DailyWordsViewModel(this);

        DailyWordsContract.Presenter presenter = new DailyWordsPresenter(mViewModel);
        mViewModel.setPresenter(presenter);

        ActivityDailyWordsBinding binding =
                DataBindingUtil.setContentView(this, R.layout.activity_daily_words);
        binding.setViewModel((DailyWordsViewModel) mViewModel);
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
        if (getSupportFragmentManager().getFragments().size() == 1) {
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
