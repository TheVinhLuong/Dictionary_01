package com.vinh.dictionary_1.data.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

/**
 * Created by VinhTL on 05/11/2017.
 */

@Entity(tableName = "bookmarked_words")
public class BookmarkedWord {
    @NonNull
    @PrimaryKey
    @ColumnInfo(name = "word")
    private String mWord;

    public BookmarkedWord() {
    }

    public BookmarkedWord(@NonNull String word) {
        this.mWord = word;
    }

    @NonNull
    public String getWord() {
        return mWord;
    }

    public void setWord(@NonNull String word) {
        this.mWord = word;
    }
}
