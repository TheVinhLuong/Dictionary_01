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

    BookmarkedWordListPresenter(BookmarkedWordListContract.ViewModel viewModel,
            BookmarkedWordRepository bookmarkedWordRepository) {
        mViewModel = viewModel;
        mBookmarkedWordRepository = bookmarkedWordRepository;
        setData();
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

    private void setData() {
        mBookmarkedWordRepository.getAllBookmarkedWord()
                .subscribeOn(SchedulerProvider.getInstance().io())
                .observeOn(SchedulerProvider.getInstance().ui())
                .subscribe(mViewModel::changeDataSet);
    }
}
