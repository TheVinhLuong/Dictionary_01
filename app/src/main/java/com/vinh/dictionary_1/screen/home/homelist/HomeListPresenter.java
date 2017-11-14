package com.vinh.dictionary_1.screen.home.homelist;

import com.vinh.dictionary_1.data.model.DailyWord;
import com.vinh.dictionary_1.data.source.DailyWordRepository;
import com.vinh.dictionary_1.utis.rx.SchedulerProvider;

/**
 * Listens to user actions from the UI ({@link HomeListFragment}), retrieves the data and updates
 * the UI as required.
 */
final class HomeListPresenter implements HomeListContract.Presenter {
    private static final String TAG = HomeListPresenter.class.getName();

    private final HomeListContract.ViewModel mViewModel;
    private DailyWordRepository mDailyWordRepository;

    HomeListPresenter(HomeListContract.ViewModel viewModel,
            DailyWordRepository dailyWordRepository) {
        mViewModel = viewModel;
        mDailyWordRepository = dailyWordRepository;
        getDailyWord();
    }

    @Override
    public void onStart() {
    }

    @Override
    public void onStop() {
    }

    @Override
    public void onItemWordListClicked(DailyWord word) {

    }

    private void getDailyWord() {
        mDailyWordRepository.getAllDailyWord()
                .subscribeOn(SchedulerProvider.getInstance().io())
                .observeOn(SchedulerProvider.getInstance().ui())
                .subscribe(dailyWords -> mViewModel.changeDataSet(dailyWords));
    }
}
