package com.vinh.dictionary_1.data.source;

import com.vinh.dictionary_1.data.model.Word;
import io.reactivex.Flowable;
import java.util.List;

/**
 * Created by VinhTL on 30/10/2017.
 */

public interface EVDictDatasource {
    interface LocalDataSource{
        Flowable<List<Word>> getLocalEVWordsDetail(String queryWord, int limitCount);
        Flowable<Word> getLocalEVWordDetail(String queryWord);
        Flowable<Word> getLocalEVWordFromOffset(String queryWord, int offset);
    }
}
