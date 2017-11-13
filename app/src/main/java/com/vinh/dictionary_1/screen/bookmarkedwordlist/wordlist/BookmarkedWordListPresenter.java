package com.vinh.dictionary_1.screen.bookmarkedwordlist.wordlist;

import com.vinh.dictionary_1.data.model.Word;
import com.vinh.dictionary_1.data.source.BookmarkedWordRepository;
import com.vinh.dictionary_1.data.source.DictRepository;
import com.vinh.dictionary_1.data.source.EVDictRepository;
import com.vinh.dictionary_1.data.source.SearchedWordRepository;
import com.vinh.dictionary_1.data.source.SettingRepository;
import com.vinh.dictionary_1.data.source.VEDictRepository;
import com.vinh.dictionary_1.data.source.local.sharedpref.SettingLocalDataSource;
import com.vinh.dictionary_1.utis.rx.SchedulerProvider;

/**
 * Listens to user actions from the UI ({@link BookmarkedWordListFragment}), retrieves the data and
 * updates
 * the UI as required.
 */
final class BookmarkedWordListPresenter implements BookmarkedWordListContract.Presenter {
    private static final String TAG = BookmarkedWordListPresenter.class.getName();

    private final BookmarkedWordListContract.ViewModel mViewModel;
    private EVDictRepository mEVDictRepository;
    private VEDictRepository mVEDictRepository;
    private DictRepository mDictRepository;
    private SettingRepository mSharedPrefRepository;
    private SearchedWordRepository mSearchedWordRepository;
    private BookmarkedWordRepository mBookmarkedWordRepository;
    private String mCurrentQueryWord = "";

    BookmarkedWordListPresenter(BookmarkedWordListContract.ViewModel viewModel,
            EVDictRepository evDictRepository, VEDictRepository veDictRepository,
            SearchedWordRepository searchedWordRepository,
            BookmarkedWordRepository bookmarkedWordRepository) {
        mViewModel = viewModel;
        mEVDictRepository = evDictRepository;
        mVEDictRepository = veDictRepository;
        mDictRepository = new DictRepository();
        mSearchedWordRepository = searchedWordRepository;
        mSharedPrefRepository = new SettingRepository(SettingLocalDataSource.getInstance());
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
