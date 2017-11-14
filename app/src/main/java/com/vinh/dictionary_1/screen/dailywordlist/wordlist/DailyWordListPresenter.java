package com.vinh.dictionary_1.screen.dailywordlist.wordlist;

import com.vinh.dictionary_1.data.model.DailyWord;
import com.vinh.dictionary_1.data.source.DailyWordRepository;
import com.vinh.dictionary_1.data.source.SearchedWordRepository;
import com.vinh.dictionary_1.utis.rx.SchedulerProvider;

/**
 * Listens to user actions from the UI ({@link DailyWordListFragment}), retrieves the data and
 * updates
 * the UI as required.
 */
final class DailyWordListPresenter implements DailyWordListContract.Presenter {
    private static final String TAG = DailyWordListPresenter.class.getName();

    private final DailyWordListContract.ViewModel mViewModel;
    private SearchedWordRepository mSearchedWordRepository;
    private DailyWordRepository mDailyWordRepository;
    private int mDailyWordCurrentPage = 0;
    private boolean mIsFetchingDailyWord = false;

    DailyWordListPresenter(DailyWordListContract.ViewModel viewModel,
            SearchedWordRepository searchedWordRepository,
            DailyWordRepository dailyWordRepository) {
        mViewModel = viewModel;
        mSearchedWordRepository = searchedWordRepository;
        mDailyWordRepository = dailyWordRepository;
        getDailyWords();
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {
    }

    @Override
    public void onItemWordListClicked(DailyWord word) {
        mSearchedWordRepository.insertSearchedWord(word.toWord());
    }
    
    @Override
    public void getDailyWords() {
        if (mIsFetchingDailyWord) {
            return;
        }
        mIsFetchingDailyWord = true;
        mDailyWordRepository.getAllDailyWord(mDailyWordCurrentPage++)
                .subscribeOn(SchedulerProvider.getInstance().io())
                .observeOn(SchedulerProvider.getInstance().ui())
                .subscribe(dailyWords -> {
                    if (dailyWords == null || dailyWords.size() == 0) {
                        --mDailyWordCurrentPage;
                        mIsFetchingDailyWord = false;
                        return;
                    }
                    mViewModel.appendDailyWordDataSet(dailyWords);
                    mIsFetchingDailyWord = false;
                });
    }
}
