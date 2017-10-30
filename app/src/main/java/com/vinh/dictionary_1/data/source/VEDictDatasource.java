package com.vinh.dictionary_1.data.source;

import com.vinh.dictionary_1.data.model.Word;
import io.reactivex.Flowable;
import java.util.List;

/**
 * Created by VinhTL on 30/10/2017.
 */

public interface VEDictDatasource {
    interface LocalDataSource{
        Flowable<List<Word>> getLocalVEWordsDetail(String queryWord, int limitCount);
        Flowable<Word> getLocalVEWordDetail(String queryWord);
    }
}
