package com.vinh.dictionary_1.data.source.local.dailyworddatabase;

import com.vinh.dictionary_1.data.model.DailyWord;
import com.vinh.dictionary_1.data.source.DailyWordDatasource;
import com.vinh.dictionary_1.utis.Constant;
import io.reactivex.Flowable;
import java.util.List;

/**
 * Created by VinhTL on 06/11/2017.
 */

public class DailyWordLocalDatasource implements DailyWordDatasource {
    private static DailyWordLocalDatasource sInstance;
    private DailyWordDAO mDailyWordDAO;

    public static DailyWordLocalDatasource getInstance(DailyWordDAO dailyWordDAO) {
        if (sInstance == null) {
            sInstance = new DailyWordLocalDatasource(dailyWordDAO);
        }
        return sInstance;
    }

    DailyWordLocalDatasource(DailyWordDAO dailyWordDAO) {
        mDailyWordDAO = dailyWordDAO;
    }

    @Override
    public Flowable<List<DailyWord>> getAllDailyWord(int page) {
        return mDailyWordDAO.getAllDailyWord(page * Constant.NUM_OF_DAILY_WORD_PER_PAGE);
    }

    @Override
    public void insertDailyWord(DailyWord... dailyWord) {
        mDailyWordDAO.insertDailyWord(dailyWord);
    }

    @Override
    public DailyWord getWord(String queryWord) {
        return mDailyWordDAO.getWord(queryWord);
    }
}
