package com.vinh.dictionary_1.data.source.local.vedictdatabase;

import com.vinh.dictionary_1.data.model.Word;
import com.vinh.dictionary_1.data.source.VEDictDatasource;
import io.reactivex.Flowable;
import java.util.List;

/**
 * Created by VinhTL on 27/10/2017.
 */

public class VEDictLocalDatasource implements VEDictDatasource.LocalDataSource{
    private static VEDictLocalDatasource
            sInstance;
    private VEDictDAO mVEDictDAO;

    public static VEDictLocalDatasource getInstance(VEDictDAO VEDictDAO) {
        if (sInstance == null) {
            sInstance = new VEDictLocalDatasource(VEDictDAO);
        }
        return sInstance;
    }

    private VEDictLocalDatasource(VEDictDAO VEDictDAO) {
        mVEDictDAO = VEDictDAO;
    }

    @Override
    public Flowable<List<Word>> getVEWordsDetail(String queryWord, int limitCount) {
        return mVEDictDAO.getVEWordsDetail(queryWord, limitCount);
    }

    @Override
    public Flowable<Word> getVEWordDetail(String queryWord) {
        return mVEDictDAO.getVEWordDetail(queryWord);
    }
}
