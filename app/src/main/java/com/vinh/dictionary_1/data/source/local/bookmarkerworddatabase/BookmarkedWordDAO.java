package com.vinh.dictionary_1.data.source.local.bookmarkerworddatabase;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;
import com.vinh.dictionary_1.data.model.Word;
import io.reactivex.Flowable;
import java.util.List;

/**
 * Created by VinhTL on 05/11/2017.
 */
@Dao
public interface BookmarkedWordDAO {
    @Query("SELECT * FROM word_tbl WHERE word = :queryWord")
    Word getBookmarkedWordByWord(String queryWord);
    
    @Query("SELECT * FROM word_tbl")
    Flowable<List<Word>> getAllBookmarkedWord();
    
    @Delete
    void deleteBookmarkedWord(Word bookmarkedWord);
    
    @Insert
    void insertBookmarkedWord(Word... bookmarkedWord);
    
    @Update
    void updateWord(Word... bookmarkedWord);
}
