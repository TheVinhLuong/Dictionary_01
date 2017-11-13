package com.vinh.dictionary_1.data.source;

import com.vinh.dictionary_1.data.model.DailyWord;
import io.reactivex.Flowable;
import java.util.List;

/**
 * Created by VinhTL on 06/11/2017.
 */

public interface DailyWordDatasource {
    Flowable<List<DailyWord>> getAllDailyWord();
    
    void insertDailyWord(DailyWord... dailyWord);

    DailyWord getWord(String queryWord);
}
