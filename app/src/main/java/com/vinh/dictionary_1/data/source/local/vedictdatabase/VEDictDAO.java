package com.vinh.dictionary_1.data.source.local.vedictdatabase;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;
import com.vinh.dictionary_1.data.model.Word;
import io.reactivex.Flowable;
import java.util.List;

/**
 * Created by VinhTL on 27/10/2017.
 */

@Dao
public interface VEDictDAO {
    @Query("SELECT word, va, mean FROM word_tbl WHERE word >= :queryWord LIMIT :limitCount")
    Flowable<List<Word>> getVEWordsDetail(String queryWord, int limitCount);

    @Query("SELECT word, va ,mean FROM word_tbl WHERE word = :queryWord " 
            + "OR word_ko_dau = :queryWord LIMIT 1")
    Flowable<Word> getVEWordDetail(String queryWord);
}
