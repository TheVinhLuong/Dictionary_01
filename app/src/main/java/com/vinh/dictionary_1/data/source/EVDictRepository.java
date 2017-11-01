package com.vinh.dictionary_1.data.source;

import com.vinh.dictionary_1.data.model.Word;
import com.vinh.dictionary_1.data.source.local.evdictdatabase.EVDictLocalDatasource;
import io.reactivex.Flowable;
import java.util.List;

/**
 * Created by VinhTL on 30/10/2017.
 */

public class EVDictRepository implements EVDictDatasource.LocalDataSource{
    private EVDictLocalDatasource mEVDictLocalDatasource;

    public EVDictRepository(EVDictLocalDatasource EVDictLocalDatasource) {
        mEVDictLocalDatasource = EVDictLocalDatasource;
    }
    
    @Override
    public Flowable<List<Word>> getLocalEVWordsDetail(String queryWord, int limitCount) {
        return mEVDictLocalDatasource.getLocalEVWordsDetail(queryWord, limitCount);
    }
    
    @Override
    public Flowable<Word> getLocalEVWordDetail(String queryWord) {
        return mEVDictLocalDatasource.getLocalEVWordDetail(queryWord);
    }
    
    @Override
    public Flowable<Word> getLocalEVWordFromOffset(String queryWord, int offset) {
        return mEVDictLocalDatasource.getLocalEVWordFromOffset(queryWord, offset);
    }
}
