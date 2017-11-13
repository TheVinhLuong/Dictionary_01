package com.vinh.dictionary_1.screen.splash;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import com.vinh.dictionary_1.R;
import com.vinh.dictionary_1.data.source.DailyWordRepository;
import com.vinh.dictionary_1.data.source.EVDictRepository;
import com.vinh.dictionary_1.data.source.local.dailyworddatabase.DailyWordDatabase;
import com.vinh.dictionary_1.data.source.local.dailyworddatabase.DailyWordLocalDatasource;
import com.vinh.dictionary_1.data.source.local.evdictdatabase.EVDictDatabase;
import com.vinh.dictionary_1.data.source.local.evdictdatabase.EVDictLocalDatasource;
import com.vinh.dictionary_1.databinding.ActivitySplashBinding;
import com.vinh.dictionary_1.screen.BaseActivity;

/**
 * Splash Screen.
 */
public class SplashActivity extends BaseActivity {
    private static final String TAG = SplashActivity.class.getSimpleName();
    private SplashContract.ViewModel mViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivitySplashBinding binding =
                DataBindingUtil.setContentView(this, R.layout.activity_splash);
        mViewModel = new SplashViewModel(this);

        EVDictRepository evDictRepository = new EVDictRepository(
                EVDictLocalDatasource.getInstance(EVDictDatabase.getInstance(this).evDictDAO()));
        DailyWordRepository dailyWordDatabase = new DailyWordRepository(
                DailyWordLocalDatasource.getInstance(
                        DailyWordDatabase.getInstance(this).dailyWordDAO()));
        SplashContract.Presenter presenter =
                new SplashPresenter(mViewModel, evDictRepository, dailyWordDatabase);
        mViewModel.setPresenter(presenter);

        binding.setViewModel((SplashViewModel) mViewModel);
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
