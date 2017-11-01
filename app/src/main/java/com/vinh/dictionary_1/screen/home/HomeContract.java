package com.vinh.dictionary_1.screen.home;

import android.view.MenuItem;
import com.vinh.dictionary_1.screen.BasePresenter;
import com.vinh.dictionary_1.screen.BaseViewModel;

/**
 * This specifies the contract between the view and the presenter.
 */
interface HomeContract {
    /**
     * View.
     */
    interface ViewModel extends BaseViewModel<Presenter> {
        boolean onOptionsItemSelected(MenuItem item);
    }

    /**
     * Presenter.
     */
    interface Presenter extends BasePresenter {
    }
}
