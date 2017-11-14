package com.vinh.dictionary_1.screen.bookmarkedwordlist.wordlist;

import com.vinh.dictionary_1.data.model.Word;
import com.vinh.dictionary_1.databinding.FragmentBookmarkedWordListBinding;
import com.vinh.dictionary_1.screen.BasePresenter;
import com.vinh.dictionary_1.screen.BaseViewModel;
import java.util.List;

/**
 * This specifies the contract between the view and the presenter.
 */
interface BookmarkedWordListContract {
    /**
     * View.
     */
    interface ViewModel extends BaseViewModel<Presenter> {
        void appendBookmarkedWordDataSet(List<Word> words);

        BookmarkedWordListAdapter getAdapter();

        void setAdapter(BookmarkedWordListAdapter adapter);

        void setBinding(FragmentBookmarkedWordListBinding fragmentWordListBinding);

        void clearBookmarkedWordDataSet();

        void refreshList();
    }

    /**
     * Presenter.
     */
    interface Presenter extends BasePresenter {
        void onItemWordListClicked(Word word);

        void getBookmarkedWords();

        void refreshList();
    }
}
