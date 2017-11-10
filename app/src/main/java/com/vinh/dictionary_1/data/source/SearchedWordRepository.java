package com.vinh.dictionary_1.data.source;

import com.vinh.dictionary_1.data.model.Word;
import io.reactivex.Flowable;
import java.util.List;

/**
 * Created by VinhTL on 06/11/2017.
 */

public class SearchedWordRepository implements SearchedWordDatasource{
    private static SearchedWordRepository sInstance;
    private SearchedWordDatasource mLocalDatasource;

    public static SearchedWordRepository getInstance(SearchedWordDatasource localDatasource) {
        if (sInstance == null) {
            sInstance = new SearchedWordRepository(localDatasource);
        }
        return sInstance;
    }

    public SearchedWordRepository(SearchedWordDatasource localDatasource) {
        mLocalDatasource = localDatasource;
    }

    @Override
    public Flowable<List<Word>> getAllSeachedWord() {
        return mLocalDatasource.getAllSeachedWord();
    }

    @Override
    public void insertSearchedWord(Word... searchedWord) {
        mLocalDatasource.insertSearchedWord(searchedWord);
    }
}
