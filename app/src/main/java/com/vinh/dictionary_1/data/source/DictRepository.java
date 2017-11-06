package com.vinh.dictionary_1.data.source;

import com.vinh.dictionary_1.data.model.Word;
import io.reactivex.Flowable;
import java.util.List;

/**
 * Created by VinhTL on 02/11/2017.
 */

public class DictRepository implements DictDatasource.LocalDatasource {
    private DictDatasource.LocalDatasource mLocalDatasource;

    public DictRepository() {
    }

    public DictRepository(DictDatasource.LocalDatasource localDatasource) {

        mLocalDatasource = localDatasource;
    }

    public void setLocalDatasource(DictDatasource.LocalDatasource localDatasource) {
        mLocalDatasource = localDatasource;
    }

    @Override
    public Flowable<List<Word>> getLocalWordsDetail(String queryWord, int limitCount) {
        return mLocalDatasource.getLocalWordsDetail(queryWord, limitCount);
    }

    @Override
    public Flowable<Word> getLocalWordDetail(String queryWord) {
        return mLocalDatasource.getLocalWordDetail(queryWord);
    }
}
