package com.vinh.dictionary_1.data.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;
import android.support.annotation.NonNull;
import com.vinh.dictionary_1.data.source.local.DictTypeConverter;

/**
 * Created by VinhTL on 06/11/2017.
 */

@Entity(tableName = "daily_word_tbl")
public class DailyWord {
    private String mType;
    @NonNull
    @PrimaryKey
    @ColumnInfo(name = "word")
    private String mWord;
    @ColumnInfo(name = "description", typeAffinity = ColumnInfo.BLOB)
    @TypeConverters(DictTypeConverter.class)
    private String mDescription;
    @ColumnInfo(name = "mean")
    private String mShortDescription;
    private String mPronounce;
    @ColumnInfo(name = "dnpn", typeAffinity = ColumnInfo.BLOB)
    @TypeConverters({ DictTypeConverter.class })
    private String mAlikeWord;
    @ColumnInfo(name = "word_ko_dau")
    private String mWordWithoutDiacritic;
    @Ignore
    private boolean mBookmarked;

    public DailyWord() {
    }

    public DailyWord(Word word) {
        this.mType = word.getType();
        this.mWord = word.getWord();
        if (word.getEVDescription() == null || word.getEVDescription().equals("")) {
            this.mDescription = word.getVEDescription();
        } else {
            this.mDescription = word.getEVDescription();
        }
        this.mShortDescription = word.getShortDescription();
        this.mPronounce = word.getPronounce();
        this.mAlikeWord = word.getAlikeWord();
        this.mWordWithoutDiacritic = word.getWordWithoutDiacritic();
        this.mBookmarked = word.isBookmarked();
    }

    public String getType() {
        return mType;
    }

    public void setType(String type) {
        this.mType = type;
    }

    public String getWord() {
        return mWord;
    }

    public void setWord(String word) {
        this.mWord = word;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String evDescription) {
        this.mDescription = evDescription;
    }

    public String getShortDescription() {
        return mShortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.mShortDescription = shortDescription;
    }

    public String getPronounce() {
        return mPronounce;
    }

    public void setPronounce(String pronounce) {
        this.mPronounce = pronounce;
    }

    public String getAlikeWord() {
        return mAlikeWord;
    }

    public void setAlikeWord(String alikeWord) {
        this.mAlikeWord = alikeWord;
    }

    public String getWordWithoutDiacritic() {
        return mWordWithoutDiacritic;
    }

    public void setWordWithoutDiacritic(String wordWithoutDiacritic) {
        mWordWithoutDiacritic = wordWithoutDiacritic;
    }

    public boolean isBookmarked() {
        return mBookmarked;
    }

    public void setBookmarked(boolean bookmarked) {
        mBookmarked = bookmarked;
    }
}
