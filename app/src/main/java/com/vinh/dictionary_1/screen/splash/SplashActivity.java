package com.vinh.dictionary_1.screen.splash;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import com.vinh.dictionary_1.R;
import com.vinh.dictionary_1.databinding.ActivitySplashBinding;
import com.vinh.dictionary_1.screen.BaseActivity;

/**
 * Splash Screen.
 */
public class SplashActivity extends BaseActivity{
    private static final String TAG = SplashActivity.class.getSimpleName();
    private SplashContract.ViewModel mViewModel;
    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mViewModel = new SplashViewModel(this);

        SplashContract.Presenter presenter = new SplashPresenter(mViewModel);
        mViewModel.setPresenter(presenter);

        ActivitySplashBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_splash);
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
