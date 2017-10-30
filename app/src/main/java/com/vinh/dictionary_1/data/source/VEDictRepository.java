package com.vinh.dictionary_1.data.source;

import com.vinh.dictionary_1.data.model.Word;
import com.vinh.dictionary_1.data.source.local.vedictdatabase.VEDictLocalDatasource;
import io.reactivex.Flowable;
import com.vinh.dictionary_1.MainApplication;
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
    public Flowable<List<Word>> getLocalVEWordsDetail(String queryWord, int limitCount) {
        return mVEDictLocalDatasource.getLocalVEWordsDetail(queryWord, limitCount);
    }
    
    @Override
    public Flowable<Word> getLocalVEWordDetail(String queryWord) {
        return mVEDictLocalDatasource.getLocalVEWordDetail(queryWord);
    }
}
