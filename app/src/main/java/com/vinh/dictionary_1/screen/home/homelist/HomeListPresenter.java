package com.vinh.dictionary_1.screen.home.homelist;

import android.content.Context;
import android.content.Intent;
import com.vinh.dictionary_1.data.model.DailyWord;
import com.vinh.dictionary_1.data.model.Word;
import com.vinh.dictionary_1.data.source.DailyWordRepository;
import com.vinh.dictionary_1.data.source.SearchedWordRepository;
import com.vinh.dictionary_1.utis.rx.SchedulerProvider;

/**
 * Listens to user actions from the UI ({@link HomeListFragment}), retrieves the data and updates
 * the UI as required.
 */
final class HomeListPresenter implements HomeListContract.Presenter {
    private static final String TAG = HomeListPresenter.class.getName();

    private final HomeListContract.ViewModel mViewModel;
    private DailyWordRepository mDailyWordRepository;
    private SearchedWordRepository mSearchedWordRepository;
    private int mDailyWordCurrentPage = 0;
    private int mSearchedWordCurrentPage = 0;
    private boolean mIsFetchingDailyWord = false;
    private boolean mIsFetchingSearchedWord = false;

    HomeListPresenter(HomeListContract.ViewModel viewModel, DailyWordRepository dailyWordRepository,
            SearchedWordRepository searchedWordRepository) {
        mViewModel = viewModel;
        mDailyWordRepository = dailyWordRepository;
        getDailyWord();
        mSearchedWordRepository = searchedWordRepository;
        getSearchedWord();
    }

    @Override
    public void onStart() {
    }

    @Override
    public void onStop() {
    }

    @Override
    public void onItemDailyWordListClicked(DailyWord word) {

    }

    @Override
    public void onItemSearchedWordListClicked(Word word) {

    }

    @Override
    public void onBroadcastReceiverReceive(Context context, Intent intent) {
        mViewModel.clearDailyWordDataSet();
        getDailyWord();
        getSearchedWord();
    }

    @Override
    public void getDailyWord() {
        if (mIsFetchingDailyWord) {
            return;
        }
        mIsFetchingDailyWord = true;
        mDailyWordRepository.getAllDailyWord(mDailyWordCurrentPage++)
                .subscribeOn(SchedulerProvider.getInstance().io())
                .observeOn(SchedulerProvider.getInstance().ui())
                .subscribe(dailyWords -> {
                    if(dailyWords == null || dailyWords.size() == 0){
                        --mDailyWordCurrentPage;
                    }
                    mViewModel.appendDailyWordDataSet(dailyWords);
                    mIsFetchingDailyWord = false;
                });
    }

    @Override
    public void getSearchedWord() {
        if(mIsFetchingSearchedWord){
            return;
        }
        mSearchedWordRepository.getAllSeachedWord()
                .subscribeOn(SchedulerProvider.getInstance().io())
                .observeOn(SchedulerProvider.getInstance().ui())
                .subscribe(mViewModel::appendSearchedWordDataSet);
    }
}
