package com.vinh.dictionary_1.screen.dailywordlist;

/**
 * Listens to user actions from the UI ({@link DailyWordsActivity}), retrieves the data and
 * updates
 * the UI as required.
 */
final class DailyWordsPresenter implements DailyWordsContract.Presenter {
    private static final String TAG = DailyWordsPresenter.class.getName();

    private final DailyWordsContract.ViewModel mViewModel;

    DailyWordsPresenter(DailyWordsContract.ViewModel viewModel) {
        mViewModel = viewModel;
    }

    @Override
    public void onStart() {
    }

    @Override
    public void onStop() {
    }
}
