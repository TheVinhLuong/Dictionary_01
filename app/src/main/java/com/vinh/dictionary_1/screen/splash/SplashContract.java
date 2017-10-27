package com.vinh.dictionary_1.screen.splash;

import com.vinh.dictionary_1.screen.BasePresenter;
import com.vinh.dictionary_1.screen.BaseViewModel;

/**
 * This specifies the contract between the view and the presenter.
 */
interface SplashContract {
    /**
     * ViewModel.
     */
    interface ViewModel extends BaseViewModel<Presenter> {
        void showLoadingDialog(String message);
        void dismissLoadingDialog();
    }

    /**
     * Presenter.
     */
    interface Presenter extends BasePresenter {
        
    }
}
