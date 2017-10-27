package com.vinh.dictionary_1.screen.splash;

/**
 * Exposes the data to be used in the Splash screen.
 */

public class SplashViewModel implements SplashContract.ViewModel {

    private SplashContract.Presenter mPresenter;
    private SplashContract.View mView;

    public SplashViewModel(SplashContract.View view) {
        mView = view;
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
        mView.showLoadingDialog(message);
    }

    @Override
    public void dismissLoadingDialog() {
        mView.dismissLoadingDialog();
    }
}
