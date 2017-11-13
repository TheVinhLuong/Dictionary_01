package com.vinh.dictionary_1.data.source;

import com.vinh.dictionary_1.data.model.Word;
import io.reactivex.Flowable;
import java.util.List;

/**
 * Created by VinhTL on 05/11/2017.
 */

public interface BookmarkedWordDatasource {
    Word getBookmarkedWordByWord(String queryWord);
    
    Flowable<List<Word>> getAllBookmarkedWord();

    void deleteBookmarkedWord(Word bookmarkedWord);

    void insertBookmarkedWord(Word bookmarkedWord);

    void updateWord(Word... bookmarkedWord);
}
