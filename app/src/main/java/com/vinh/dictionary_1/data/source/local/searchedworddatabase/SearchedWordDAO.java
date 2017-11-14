package com.vinh.dictionary_1.data.source.local.searchedworddatabase;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import com.vinh.dictionary_1.data.model.Word;
import io.reactivex.Flowable;
import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

/**
 * Created by VinhTL on 06/11/2017.
 */
@Dao
public interface SearchedWordDAO {

    @Query("SELECT * FROM word_tbl LIMIT 30")
    Flowable<List<Word>> getAllSeachedWord();

    @Insert(onConflict = REPLACE)
    void insertSearchedWord(Word... searchedWord);
}
