package com.vinh.dictionary_1.screen.bookmarkedwordlist;

/**
 * Listens to user actions from the UI ({@link BookmarkedWordsActivity}), retrieves the data and
 * updates
 * the UI as required.
 */
final class BookmarkedWordsPresenter implements BookmarkedWordsContract.Presenter {
    private static final String TAG = BookmarkedWordsPresenter.class.getName();

    private final BookmarkedWordsContract.ViewModel mViewModel;

    public BookmarkedWordsPresenter(BookmarkedWordsContract.ViewModel viewModel) {
        mViewModel = viewModel;
    }

    @Override
    public void onStart() {
    }

    @Override
    public void onStop() {
    }
}
