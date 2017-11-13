package com.vinh.dictionary_1.data.source;

import com.vinh.dictionary_1.data.model.Word;
import com.vinh.dictionary_1.data.source.local.evdictdatabase.EVDictLocalDatasource;
import io.reactivex.Flowable;
import java.util.List;

/**
 * Created by VinhTL on 30/10/2017.
 */

public class EVDictRepository implements EVDictDatasource.LocalDataSource {
    private EVDictLocalDatasource mEVDictLocalDatasource;

    public EVDictRepository(EVDictLocalDatasource EVDictLocalDatasource) {
        mEVDictLocalDatasource = EVDictLocalDatasource;
    }

    @Override
    public Flowable<List<Word>> getLocalWordsDetail(String queryWord, int limitCount) {
        return mEVDictLocalDatasource.getLocalWordsDetail(queryWord, limitCount);
    }

    @Override
    public Flowable<Word> getLocalWordDetail(String queryWord) {
        return mEVDictLocalDatasource.getLocalWordDetail(queryWord);
    }

    @Override
    public Word getLocalWordDetailSync(String queryWord) {
        return mEVDictLocalDatasource.getLocalWordDetailSync(queryWord);
    }

    @Override
    public Flowable<Word> getRandomWord() {
        return mEVDictLocalDatasource.getRandomWord();
    }
}
