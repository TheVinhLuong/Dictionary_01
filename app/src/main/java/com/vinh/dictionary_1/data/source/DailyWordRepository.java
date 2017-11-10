package com.vinh.dictionary_1.data.source;

import com.vinh.dictionary_1.data.model.DailyWord;
import io.reactivex.Flowable;
import java.util.List;

/**
 * Created by VinhTL on 06/11/2017.
 */

public class DailyWordRepository implements DailyWordDatasource{
    private static DailyWordRepository sInstance;
    private DailyWordDatasource mLocalDatasource;

    public static DailyWordRepository getInstance(DailyWordDatasource localDatasource) {
        if (sInstance == null) {
            sInstance = new DailyWordRepository(localDatasource);
        }
        return sInstance;
    }

    public DailyWordRepository(DailyWordDatasource localDatasource) {
        mLocalDatasource = localDatasource;
    }

    @Override
    public Flowable<List<DailyWord>> getAllDailyWord() {
        return mLocalDatasource.getAllDailyWord();
    }

    @Override
    public void insertDailyWord(DailyWord... dailyWord) {
        mLocalDatasource.insertDailyWord(dailyWord);
    }

    @Override
    public DailyWord getWord(String queryWord) {
        return mLocalDatasource.getWord(queryWord);
    }
}
