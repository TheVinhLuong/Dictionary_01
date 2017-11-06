package com.vinh.dictionary_1.screen.home.wordlist;

import android.content.Context;
import android.content.Intent;
import com.vinh.dictionary_1.data.model.Word;
import com.vinh.dictionary_1.data.source.DictRepository;
import com.vinh.dictionary_1.data.source.EVDictRepository;
import com.vinh.dictionary_1.data.source.SettingRepository;
import com.vinh.dictionary_1.data.source.VEDictRepository;
import com.vinh.dictionary_1.data.source.local.sharedpref.SettingLocalDataSource;
import com.vinh.dictionary_1.utis.Constant;
import com.vinh.dictionary_1.utis.rx.SchedulerProvider;

import static com.vinh.dictionary_1.data.model.annotation.DictTypeName.DB_NAME_ENGLISH_VIETNAMESE;

/**
 * Listens to user actions from the UI ({@link WordListFragment}), retrieves the data and updates
 * the UI as required.
 */
final class WordListPresenter implements WordListContract.Presenter {
    private static final String TAG = WordListPresenter.class.getName();

    private final WordListContract.ViewModel mViewModel;
    private EVDictRepository mEVDictRepository;
    private VEDictRepository mVEDictRepository;
    private DictRepository mDictRepository;
    private SettingRepository mSharedPrefRepository;
    private String mCurrentQueryWord = "";

    WordListPresenter(WordListContract.ViewModel viewModel, EVDictRepository evDictRepository,
            VEDictRepository veDictRepository) {
        mViewModel = viewModel;
        mEVDictRepository = evDictRepository;
        mVEDictRepository = veDictRepository;
        mDictRepository = new DictRepository();
        mSharedPrefRepository = new SettingRepository(SettingLocalDataSource.getInstance());
        reSetDatasource();
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
    public void onTextChange(String queryWord) {
        mCurrentQueryWord = queryWord;
        mDictRepository.getLocalWordsDetail(queryWord, Constant.DB_QUERY_MAX_RESULT_COUNT)
                .subscribeOn(SchedulerProvider.getInstance().io())
                .observeOn(SchedulerProvider.getInstance().ui())
                .subscribe(mViewModel::changeDataSet);
    }

    @Override
    public void onBroadcastReceiverReceive(Context context, Intent intent) {
        reSetDatasource();
        onTextChange(mCurrentQueryWord);
    }

    private void reSetDatasource() {
        if (mSharedPrefRepository.getCurrentDictType()
                .blockingSingle()
                .equals(DB_NAME_ENGLISH_VIETNAMESE)) {
            mDictRepository.setLocalDatasource(mEVDictRepository);
        } else {
            mDictRepository.setLocalDatasource(mVEDictRepository);
        }
    }
}
