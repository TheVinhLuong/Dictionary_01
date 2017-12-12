package com.vinh.dictionary_1.data.source;

import com.vinh.dictionary_1.data.model.Word;
import io.reactivex.Flowable;
import java.util.List;

/**
 * Created by VinhTL on 02/11/2017.
 */

public interface DictDatasource {
    interface LocalDatasource{
        Flowable<List<Word>> getLocalWordsDetail(String queryWord, int limitCount);
        Flowable<Word> getLocalWordDetail(String queryWord);
        Word getLocalWordDetailSync(String queryWord);
    }
}