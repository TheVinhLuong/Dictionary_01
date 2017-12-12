package com.vinh.dictionary_1.data.source;

import com.vinh.dictionary_1.data.model.Word;
import com.vinh.dictionary_1.data.source.local.vedictdatabase.VEDictLocalDatasource;
import io.reactivex.Flowable;
import java.util.List;

/**
 * Created by VinhTL on 30/10/2017.
 */

public class VEDictRepository implements VEDictDatasource.LocalDataSource{
    private VEDictLocalDatasource mVEDictLocalDatasource;

    public VEDictRepository(VEDictLocalDatasource VEDictLocalDatasource) {
        mVEDictLocalDatasource = VEDictLocalDatasource;
    }

    @Override
    public Flowable<List<Word>> getLocalWordsDetail(String queryWord, int limitCount) {
        return mVEDictLocalDatasource.getLocalWordsDetail(queryWord, limitCount);
    }

    @Override
    public Flowable<Word> getLocalWordDetail(String queryWord) {
        return mVEDictLocalDatasource.getLocalWordDetail(queryWord);
    }

    @Override
    public Word getLocalWordDetailSync(String queryWord) {
        return mVEDictLocalDatasource.getLocalWordDetailSync(queryWord);
    }
}
