package com.vinh.dictionary_1.data.source.local.bookmarkerworddatabase;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import com.vinh.dictionary_1.data.model.BookmarkedWord;

import static com.vinh.dictionary_1.data.source.local.bookmarkerworddatabase
        .BookmarkedWordDatabase.DATABASE_VERSION;

/**
 * Created by VinhTL on 05/11/2017.
 */
@Database(entities = BookmarkedWord.class, version = DATABASE_VERSION)
public abstract class BookmarkedWordDatabase extends RoomDatabase{
    private static BookmarkedWordDatabase sBookmarkedWordDatabase;

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "bookmarked-word-database";

    public abstract BookmarkedWordDAO bookmarkedWordDAO();

    public static BookmarkedWordDatabase getInstance(Context context) {
        if (sBookmarkedWordDatabase == null) {
            sBookmarkedWordDatabase = Room.databaseBuilder(context, 
                    BookmarkedWordDatabase.class, DATABASE_NAME)
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build();
        }
        return sBookmarkedWordDatabase;
    }
}
