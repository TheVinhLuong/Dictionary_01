package com.vinh.dictionary_1.screen.home.homelist;

import com.vinh.dictionary_1.data.model.DailyWord;
import com.vinh.dictionary_1.screen.BasePresenter;
import com.vinh.dictionary_1.screen.BaseViewModel;
import java.util.List;

/**
 * This specifies the contract between the view and the presenter.
 */
interface HomeListContract {
    /**
     * View.
     */
    interface ViewModel extends BaseViewModel<Presenter> {
        void changeDataSet(List<DailyWord> words);
    }

    /**
     * Presenter.
     */
    interface Presenter extends BasePresenter {
        void onItemWordListClicked(DailyWord word);
    }
}
