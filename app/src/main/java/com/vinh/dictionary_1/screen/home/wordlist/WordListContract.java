package com.vinh.dictionary_1.screen.home.wordlist;

import android.content.Context;
import android.content.Intent;
import com.vinh.dictionary_1.data.model.Word;
import com.vinh.dictionary_1.databinding.FragmentWordListBinding;
import com.vinh.dictionary_1.screen.BasePresenter;
import com.vinh.dictionary_1.screen.BaseViewModel;
import java.util.List;

/**
 * This specifies the contract between the view and the presenter.
 */
interface WordListContract {
    /**
     * View.
     */
    interface ViewModel extends BaseViewModel<Presenter> {
        void changeDataSet(List<Word> words);

        WordListAdapter getAdapter();

        void setAdapter(WordListAdapter adapter);

        void setBinding(FragmentWordListBinding fragmentWordListBinding);

        void onTextChange(String queryWord);

        void onBroadcastReceiverReceive(Context context, Intent intent);
    }

    /**
     * Presenter.
     */
    interface Presenter extends BasePresenter {
        void onItemWordListClicked(Word word);

        void onTextChange(String queryWord);

        void onBroadcastReceiverReceive(Context context, Intent intent);
    }
}
