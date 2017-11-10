package com.vinh.dictionary_1.data.source.local.dailyworddatabase;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import com.vinh.dictionary_1.data.model.DailyWord;
import io.reactivex.Flowable;
import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

/**
 * Created by VinhTL on 06/11/2017.
 */
@Dao
public interface DailyWordDAO {
    @Query("SELECT * FROM daily_word_tbl")
    Flowable<List<DailyWord>> getAllDailyWord();

    @Insert(onConflict = REPLACE)
    void insertDailyWord(DailyWord... dailyWord);

    @Query("SELECT * FROM daily_word_tbl WHERE word = :queryWord")
    DailyWord getWord(String queryWord);
}
