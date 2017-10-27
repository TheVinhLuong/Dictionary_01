package com.vinh.dictionary_1.screen.splash;

import android.app.ProgressDialog;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.util.Log;
import com.vinh.dictionary_1.R;
import com.vinh.dictionary_1.databinding.ActivitySplashBinding;
import com.vinh.dictionary_1.screen.BaseActivity;
import com.vinh.dictionary_1.utis.DialogManager;

/**
 * Splash Screen.
 */
public class SplashActivity extends BaseActivity implements SplashContract.View{
    private static final String TAG = SplashActivity.class.getSimpleName();
    private SplashContract.ViewModel mViewModel;
    private ProgressDialog mLoadingDialog;

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

    @Override
    public void showLoadingDialog(String message) {
        mLoadingDialog = DialogManager.getSpinningDialog(SplashActivity.this, message);
        mLoadingDialog.show();
    }

    @Override
    public void dismissLoadingDialog() {
        Log.d(TAG, "dismiss dialog");
        if(mLoadingDialog != null){
            mLoadingDialog.dismiss();
            Log.d(TAG, "dialog dismissed");
        }
    }
}
