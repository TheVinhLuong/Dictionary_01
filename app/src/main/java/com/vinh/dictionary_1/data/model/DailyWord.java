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
    @ColumnInfo(name = "mean")
    private String mShortDescription;
    private String mPronounce;
    @ColumnInfo(name = "dnpn", typeAffinity = ColumnInfo.BLOB)
    @TypeConverters({ DictTypeConverter.class })
    private String mAlikeWord;
    @ColumnInfo(name = "date")
    private String mDate;
    @ColumnInfo(name = "va", typeAffinity = ColumnInfo.BLOB)
    @TypeConverters(DictTypeConverter.class)
    private String mVEDescription;
    @ColumnInfo(name = "av", typeAffinity = ColumnInfo.BLOB)
    @TypeConverters(DictTypeConverter.class)
    private String mEVDescription;
    @Ignore
    private boolean mBookmarked;

    public DailyWord() {
    }

    public DailyWord(Word word) {
        this.mType = word.getType();
        this.mWord = word.getWord();
        this.mVEDescription = word.getVEDescription();
        this.mEVDescription = word.getEVDescription();
        this.mShortDescription = word.getShortDescription();
        this.mPronounce = word.getPronounce();
        this.mAlikeWord = word.getAlikeWord();
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

    public String getDate() {
        return mDate;
    }

    public void setDate(String date) {
        mDate = date;
    }

    public String getVEDescription() {
        return mVEDescription;
    }

    public void setVEDescription(String veDescription) {
        mVEDescription = veDescription;
    }

    public String getEVDescription() {
        return mEVDescription;
    }

    public void setEVDescription(String evDescription) {
        mEVDescription = evDescription;
    }

    public boolean isBookmarked() {
        return mBookmarked;
    }

    public void setBookmarked(boolean bookmarked) {
        mBookmarked = bookmarked;
    }

    public Word toWord() {
        Word.WordBuilder wordBuilder = new Word.WordBuilder();
        wordBuilder.word(mWord)
                .alikeWord(mAlikeWord)
                .evDescription(mEVDescription)
                .veDescription(mVEDescription)
                .pronounce(mPronounce)
                .shortDescription(mShortDescription)
                .alikeWord(mAlikeWord)
                .setBookmarked(mBookmarked);
        return wordBuilder.build();
    }
}
