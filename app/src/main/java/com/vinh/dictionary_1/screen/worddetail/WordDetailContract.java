package com.vinh.dictionary_1.screen.worddetail;

import com.vinh.dictionary_1.data.model.Word;
import com.vinh.dictionary_1.data.source.DictRepository;
import com.vinh.dictionary_1.data.source.SearchedWordRepository;
import com.vinh.dictionary_1.screen.BasePresenter;
import com.vinh.dictionary_1.screen.BaseViewModel;

/**
 * This specifies the contract between the view and the presenter.
 */
interface WordDetailContract {
    /**
     * View.
     */
    interface ViewModel extends BaseViewModel<Presenter> {
        void setWord(Word word);
        void speakUS(String word);
        void speakUK(String word);
        void onBookmarkIconTouch();
        Word getWord();
    }

    /**
     * Presenter.
     */
    interface Presenter extends BasePresenter {
        DictRepository getDictRepository();
        SearchedWordRepository getSearchedWordRepository();
        void speakUS(String word);
        void speakUK(String word);
        boolean isWordBookmarked(Word word);
        void onBookmarkIconTouch(boolean bookmark);
    }
}
