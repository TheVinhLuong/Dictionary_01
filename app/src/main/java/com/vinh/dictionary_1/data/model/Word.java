package com.vinh.dictionary_1.data.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;
import android.support.annotation.NonNull;
import com.vinh.dictionary_1.data.source.local.DictTypeConverter;
import java.io.Serializable;

/**
 * Created by VinhTL on 27/10/2017.
 */

@Entity(tableName = "word_tbl")
public class Word implements Serializable{
    private String mType;
    @NonNull
    @PrimaryKey
    @ColumnInfo(name = "word")
    private String mWord;
    @ColumnInfo(name = "av", typeAffinity = ColumnInfo.BLOB)
    @TypeConverters(DictTypeConverter.class)
    private String mEVDescription;
    @ColumnInfo(name = "mean")
    private String mShortDescription;
    private String mPronounce;
    @ColumnInfo(name = "dnpn", typeAffinity = ColumnInfo.BLOB)
    @TypeConverters({ DictTypeConverter.class })
    private String mAlikeWord;
    @ColumnInfo(name = "va", typeAffinity = ColumnInfo.BLOB)
    @TypeConverters(DictTypeConverter.class)
    private String mVEDescription;
    @ColumnInfo(name = "word_ko_dau")
    private String mWordWithoutDiacritic;
    @Ignore
    private boolean mBookmarked;

    public Word() {
    }

    

    public Word(WordBuilder wordBuilder) {
        this.mType = wordBuilder.mType;
        this.mWord = wordBuilder.mWord;
        this.mEVDescription = wordBuilder.mEVDescription;
        this.mShortDescription = wordBuilder.mShortDescription;
        this.mPronounce = wordBuilder.mPronounce;
        this.mAlikeWord = wordBuilder.mAlikeWord;
        this.mVEDescription = wordBuilder.mVEDescription;
        this.mWordWithoutDiacritic = wordBuilder.mWordWithoutDiacritic;
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

    public String getEVDescription() {
        return mEVDescription;
    }

    public void setEVDescription(String evDescription) {
        this.mEVDescription = evDescription;
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

    public String getVEDescription() {
        return mVEDescription;
    }

    public void setVEDescription(String VEDescription) {
        mVEDescription = VEDescription;
    }

    public void setWordWithoutDiacritic(String wordWithoutDiacritic) {
        mWordWithoutDiacritic = wordWithoutDiacritic;
    }

    public boolean isBookmarked() {
        return mBookmarked;
    }

    public void setBookmarked(boolean bookmarked) {
        this.mBookmarked = bookmarked;
    }

    public static class WordBuilder {
        private String mType;
        private String mWord;
        private String mEVDescription;
        private String mShortDescription;
        private String mPronounce;
        private String mAlikeWord;
        private String mVEDescription;
        private String mWordWithoutDiacritic;

        public WordBuilder() {
        }

        public WordBuilder type(String type) {
            this.mType = type;
            return this;
        }

        public WordBuilder word(String word) {
            this.mWord = word;
            return this;
        }

        public WordBuilder evDescription(String evDescription) {
            this.mEVDescription = evDescription;
            return this;
        }

        public WordBuilder shortVietnameseDescription(String shortVietnameseDescription) {
            this.mShortDescription = shortVietnameseDescription;
            return this;
        }

        public WordBuilder pronounce(String pronounce) {
            this.mPronounce = pronounce;
            return this;
        }

        public WordBuilder alikeWord(String alikeWord) {
            this.mAlikeWord = alikeWord;
            return this;
        }

        public WordBuilder veDescription(String veDescription) {
            this.mVEDescription = veDescription;
            return this;
        }

        public WordBuilder wordWithoutDiacritic(String wordWithoutDiacritic) {
            this.mWordWithoutDiacritic = wordWithoutDiacritic;
            return this;
        }

        public Word build() {
            return new Word(this);
        }
    }
}
