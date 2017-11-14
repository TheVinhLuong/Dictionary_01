package com.vinh.dictionary_1.screen.bookmarkedwordlist.wordlist;

import com.vinh.dictionary_1.data.model.Word;
import com.vinh.dictionary_1.data.source.BookmarkedWordRepository;
import com.vinh.dictionary_1.utis.rx.SchedulerProvider;

/**
 * Listens to user actions from the UI ({@link BookmarkedWordListFragment}), retrieves the data and
 * updates
 * the UI as required.
 */
final class BookmarkedWordListPresenter implements BookmarkedWordListContract.Presenter {
    private static final String TAG = BookmarkedWordListPresenter.class.getName();

    private final BookmarkedWordListContract.ViewModel mViewModel;
    private BookmarkedWordRepository mBookmarkedWordRepository;
    private int mBookmarkedWordCurrentPage = 0;
    private boolean mIsFetchingBookmarkedWord = false;

    BookmarkedWordListPresenter(BookmarkedWordListContract.ViewModel viewModel,
            BookmarkedWordRepository bookmarkedWordRepository) {
        mViewModel = viewModel;
        mBookmarkedWordRepository = bookmarkedWordRepository;
        getBookmarkedWords();
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {
    }

    @Override
    public void onItemWordListClicked(Word word) {
    }

    @Override
    public void getBookmarkedWords() {
        if (mIsFetchingBookmarkedWord) {
            return;
        }
        mIsFetchingBookmarkedWord = true;
        mBookmarkedWordRepository.getBookmarkedWord(mBookmarkedWordCurrentPage++)
                .subscribeOn(SchedulerProvider.getInstance().io())
                .observeOn(SchedulerProvider.getInstance().ui())
                .subscribe(words -> {
                    if (words == null || words.size() == 0) {
                        mIsFetchingBookmarkedWord = false;
                        --mBookmarkedWordCurrentPage;
                        return;
                    }
                    mViewModel.appendBookmarkedWordDataSet(words);
                    mIsFetchingBookmarkedWord = false;
                });
    }

    @Override
    public void refreshList(){
        mViewModel.clearBookmarkedWordDataSet();
        mBookmarkedWordCurrentPage = 0;
        getBookmarkedWords();
    }
}
