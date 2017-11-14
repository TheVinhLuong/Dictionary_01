package com.vinh.dictionary_1.screen.dailywordlist;

import com.vinh.dictionary_1.screen.BasePresenter;
import com.vinh.dictionary_1.screen.BaseViewModel;

/**
 * This specifies the contract between the view and the presenter.
 */
interface DailyWordsContract {
    /**
     * View.
     */
    interface ViewModel extends BaseViewModel<Presenter> {
        void setTitleVisibility(int titleVisibility);
    }

    /**
     * Presenter.
     */
    interface Presenter extends BasePresenter {
    }
}
