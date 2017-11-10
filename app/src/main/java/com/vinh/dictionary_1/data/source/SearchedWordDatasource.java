package com.vinh.dictionary_1.data.source;

import com.vinh.dictionary_1.data.model.Word;
import io.reactivex.Flowable;
import java.util.List;

/**
 * Created by VinhTL on 06/11/2017.
 */

public interface SearchedWordDatasource {
    Flowable<List<Word>> getAllSeachedWord();
    
    void insertSearchedWord(Word... bookmarkedWord);
}
