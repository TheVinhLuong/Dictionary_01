package com.vinh.dictionary_1.screen.bookmarkedwordlist;

/**
 * Listens to user actions from the UI ({@link BookmarkedWordListActivity}), retrieves the data and
 * updates
 * the UI as required.
 */
final class BookmarkedWordListPresenter implements BookmarkedWordListContract.Presenter {
    private static final String TAG = BookmarkedWordListPresenter.class.getName();

    private final BookmarkedWordListContract.ViewModel mViewModel;

    public BookmarkedWordListPresenter(BookmarkedWordListContract.ViewModel viewModel) {
        mViewModel = viewModel;
    }

    @Override
    public void onStart() {
    }

    @Override
    public void onStop() {
    }
}
