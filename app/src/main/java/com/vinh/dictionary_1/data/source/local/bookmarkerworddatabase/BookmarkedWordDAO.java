package com.vinh.dictionary_1.data.source.local.bookmarkerworddatabase;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;
import com.vinh.dictionary_1.data.model.BookmarkedWord;
import io.reactivex.Flowable;
import java.util.List;

/**
 * Created by VinhTL on 05/11/2017.
 */
@Dao
public interface BookmarkedWordDAO {
    @Query("SELECT * FROM bookmarked_words WHERE word = :queryWord")
    BookmarkedWord getBookmarkedWordByWord(String queryWord);
    
    @Query("SELECT * FROM bookmarked_words")
    Flowable<List<BookmarkedWord>> getAllBookmarkedWord();
    
    @Delete
    void deleteBookmarkedWord(BookmarkedWord bookmarkedWord);
    
    @Insert
    void insertBookmarkedWord(BookmarkedWord... bookmarkedWord);
    
    @Update
    void updateWord(BookmarkedWord... bookmarkedWord);
}
