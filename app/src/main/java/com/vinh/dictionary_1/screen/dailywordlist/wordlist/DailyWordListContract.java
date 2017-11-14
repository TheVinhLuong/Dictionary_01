package com.vinh.dictionary_1.screen.dailywordlist.wordlist;

import com.vinh.dictionary_1.data.model.DailyWord;
import com.vinh.dictionary_1.databinding.FragmentDailyWordListBinding;
import com.vinh.dictionary_1.screen.BasePresenter;
import com.vinh.dictionary_1.screen.BaseViewModel;
import java.util.List;

/**
 * This specifies the contract between the view and the presenter.
 */
interface DailyWordListContract {
    /**
     * View.
     */
    interface ViewModel extends BaseViewModel<Presenter> {
        void changeDataSet(List<DailyWord> words);

        DailyWordListAdapter getAdapter();

        void setAdapter(DailyWordListAdapter adapter);

        void setBinding(FragmentDailyWordListBinding fragmentDailyWordListBinding);
 
    }

    /**
     * Presenter.
     */
    interface Presenter extends BasePresenter {
        void onItemWordListClicked(DailyWord word);
        
    }
}
