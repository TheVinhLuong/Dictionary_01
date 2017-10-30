package com.vinh.dictionary_1.data.source.local.evdictdatabase;

import com.vinh.dictionary_1.data.model.Word;
import com.vinh.dictionary_1.data.source.EVDictDatasource;
import io.reactivex.Flowable;
import java.util.List;

/**
 * Created by VinhTL on 27/10/2017.
 */

public class EVDictLocalDatasource implements EVDictDatasource.LocalDataSource {
    private static EVDictLocalDatasource sInstance;
    private EVDictDAO mEVDictDAO;

    public static EVDictLocalDatasource getInstance(EVDictDAO EVDictDAO) {
        if (sInstance == null) {
            sInstance = new EVDictLocalDatasource(EVDictDAO);
        }
        return sInstance;
    }

    private EVDictLocalDatasource(EVDictDAO EVDictDAO) {
        mEVDictDAO = EVDictDAO;
    }

    @Override
    public Flowable<List<Word>> getLocalEVWordsDetail(String queryWord, int limitCount) {
        return mEVDictDAO.getEVWordsDetail(queryWord, limitCount);
    }

    @Override
    public Flowable<Word> getLocalEVWordDetail(String queryWord) {
        return mEVDictDAO.getEVWordDetail(queryWord);
    }

    @Override
    public Flowable<Word> getLocalEVWordFromOffset(String queryWord, int offset) {
        return mEVDictDAO.getEVWordFromOffset(queryWord, offset);
    }
}
