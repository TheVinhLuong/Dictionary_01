package com.vinh.dictionary_1.screen.splash;

import android.app.ProgressDialog;
import android.content.Context;
import com.vinh.dictionary_1.utis.DialogManager;

/**
 * Exposes the data to be used in the Splash screen.
 */

public class SplashViewModel implements SplashContract.ViewModel {

    private SplashContract.Presenter mPresenter;
    private Context mContext;
    private ProgressDialog mLoadingDialog;

    public SplashViewModel(Context context) {
        mContext = context;
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
    public void setPresenter(SplashContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void showLoadingDialog(String message) {
        mLoadingDialog = DialogManager.getSpinningDialog(mContext, message);
        mLoadingDialog.show();
    }

    @Override
    public void dismissLoadingDialog() {
        if (mLoadingDialog != null && mLoadingDialog.isShowing()) {
            mLoadingDialog.dismiss();
        }
    }
}
