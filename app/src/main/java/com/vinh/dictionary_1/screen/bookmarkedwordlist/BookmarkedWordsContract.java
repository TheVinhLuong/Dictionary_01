package com.vinh.dictionary_1.screen.bookmarkedwordlist;

import com.vinh.dictionary_1.screen.BasePresenter;
import com.vinh.dictionary_1.screen.BaseViewModel;

/**
 * This specifies the contract between the view and the presenter.
 */
interface BookmarkedWordsContract {
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
