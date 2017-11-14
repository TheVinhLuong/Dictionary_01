package com.vinh.dictionary_1.data.source.local.dailyworddatabase;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import com.vinh.dictionary_1.data.model.DailyWord;
import static com.vinh.dictionary_1.data.source.local.dailyworddatabase.DailyWordDatabase
        .DATABASE_VERSION;

/**
 * Created by VinhTL on 06/11/2017.
 */
@Database(entities = DailyWord.class, version = DATABASE_VERSION)
public abstract class DailyWordDatabase extends RoomDatabase {

    private static DailyWordDatabase sDailyWordDatabase;

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "daily-word-database";

    public abstract DailyWordDAO dailyWordDAO();

    public static DailyWordDatabase getInstance(Context context) {
        if (sDailyWordDatabase == null) {
            sDailyWordDatabase =
                    Room.databaseBuilder(context, DailyWordDatabase.class, DATABASE_NAME)
                            .fallbackToDestructiveMigration()
                            .allowMainThreadQueries()
                            .build();
            sDailyWordDatabase.query(
                    "CREATE TRIGGER IF NOT EXISTS delete_all INSERT ON daily_word_tbl WHEN (SELECT "
                            + "count(*) "
                            + "from daily_word_tbl)"
                            + "> 62806 BEGIN DELETE FROM daily_word_tbl; END;", null);
        }
        return sDailyWordDatabase;
    }
}
