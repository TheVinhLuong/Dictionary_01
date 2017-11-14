package com.vinh.dictionary_1.data.source.local.searchedworddatabase;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import com.vinh.dictionary_1.data.model.Word;

import static com.vinh.dictionary_1.data.source.local.searchedworddatabase.SearchedWordDatabase
        .DATABASE_VERSION;

/**
 * Created by VinhTL on 06/11/2017.
 */

@Database(entities = Word.class, version = DATABASE_VERSION)
public abstract class SearchedWordDatabase extends RoomDatabase {
    private static SearchedWordDatabase sSearchedWordDatabase;

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "searched-word-database";

    public abstract SearchedWordDAO searchedWordDAO();

    public static SearchedWordDatabase getInstance(Context context) {
        if (sSearchedWordDatabase == null) {
            sSearchedWordDatabase =
                    Room.databaseBuilder(context, SearchedWordDatabase.class, DATABASE_NAME)
                            .fallbackToDestructiveMigration()
                            .allowMainThreadQueries()
                            .build();
            sSearchedWordDatabase.query(
                    "CREATE TRIGGER IF NOT EXISTS delete_till_40 INSERT ON word_tbl WHEN (SELECT " 
                            + "count(*) "
                            + "from word_tbl)"
                            + ">79 BEGIN DELETE FROM word_tbl WHERE rowid IN ( SELECT rowid FROM " 
                            + "word_tbl ORDER "
                            + "BY rowid ASC LIMIT 40); END;", null);
        }
        return sSearchedWordDatabase;
    }
}
