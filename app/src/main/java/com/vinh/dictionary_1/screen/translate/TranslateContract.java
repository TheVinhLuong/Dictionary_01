package com.vinh.dictionary_1.screen.translate;

import com.vinh.dictionary_1.screen.BasePresenter;
import com.vinh.dictionary_1.screen.BaseViewModel;

/**
 * This specifies the contract between the view and the presenter.
 */
interface TranslateContract {
    /**
     * View.
     */
    interface ViewModel extends BaseViewModel<Presenter> {
        void showLoadingDialog();

        void dismissLoadingDialog();

        void setTranslatedText(String translatedText);

        void showErrorToast();
    }

    /**
     * Presenter.
     */
    interface Presenter extends BasePresenter {
        void onTranslateButtonTouch(String sourceText, String sourceLangCode,
                String targetLangCode);
    }
}
