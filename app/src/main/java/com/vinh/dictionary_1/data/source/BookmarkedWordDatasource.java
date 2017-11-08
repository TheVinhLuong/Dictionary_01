package com.vinh.dictionary_1.data.source;

import com.vinh.dictionary_1.data.model.BookmarkedWord;
import io.reactivex.Flowable;
import java.util.List;

/**
 * Created by VinhTL on 05/11/2017.
 */

public interface BookmarkedWordDatasource {
    BookmarkedWord getBookmarkedWordByWord(String queryWord);
    
    Flowable<List<BookmarkedWord>> getAllBookmarkedWord();

    void deleteBookmarkedWord(BookmarkedWord bookmarkedWord);

    void insertBookmarkedWord(BookmarkedWord bookmarkedWord);

    void updateWord(BookmarkedWord... bookmarkedWord);
}
