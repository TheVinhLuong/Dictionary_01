package com.vinh.dictionary_1.screen.home.homelist;

import android.content.Context;
import android.content.Intent;
import com.vinh.dictionary_1.data.model.DailyWord;
import com.vinh.dictionary_1.data.model.Word;
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
        void changeDailyWordDataSet(List<DailyWord> words);
        void changeSearchedWordDataSet(List<Word> words);
        void onBroadcastReceiverReceive(Context context, Intent intent);
    }

    /**
     * Presenter.
     */
    interface Presenter extends BasePresenter {
        void onItemDailyWordListClicked(DailyWord word);
        void onItemSearchedWordListClicked(Word word);
        void onBroadcastReceiverReceive(Context context, Intent intent);
    }
}
