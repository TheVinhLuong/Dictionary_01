package com.vinh.dictionary_1.data.source.local.searchedworddatabase;

import com.vinh.dictionary_1.data.model.Word;
import com.vinh.dictionary_1.data.source.SearchedWordDatasource;
import com.vinh.dictionary_1.utis.Constant;
import io.reactivex.Flowable;
import java.util.List;

/**
 * Created by VinhTL on 06/11/2017.
 */

public class SearchedWordLocalDatasource implements SearchedWordDatasource {
    private static SearchedWordLocalDatasource sInstance;
    private SearchedWordDAO mSearchedWordDAO;

    public static SearchedWordLocalDatasource getInstance(SearchedWordDAO searchedWordDAO) {
        if (sInstance == null) {
            sInstance = new SearchedWordLocalDatasource(searchedWordDAO);
        }
        return sInstance;
    }

    SearchedWordLocalDatasource(SearchedWordDAO searchedWordDAO) {
        mSearchedWordDAO = searchedWordDAO;
    }

    @Override
    public Flowable<List<Word>> getSeachedWord(int page) {
        return mSearchedWordDAO.getSeachedWord(page * Constant.NUM_OF_SEARCHED_WORD_PER_PAGE);
    }

    @Override
    public void insertSearchedWord(Word... searchedWord) {
        mSearchedWordDAO.insertSearchedWord(searchedWord);
    }
}
