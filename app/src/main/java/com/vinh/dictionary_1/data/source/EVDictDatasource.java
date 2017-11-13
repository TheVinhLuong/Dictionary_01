package com.vinh.dictionary_1.data.source;

import com.vinh.dictionary_1.data.model.Word;
import io.reactivex.Flowable;

/**
 * Created by VinhTL on 30/10/2017.
 */

public interface EVDictDatasource {
    interface LocalDataSource extends DictDatasource.LocalDatasource {
        Word getLocalWordDetailSync(String queryWord);

        Flowable<Word> getRandomWord();
    }
}
