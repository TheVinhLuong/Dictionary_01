package com.vinh.dictionary_1.data.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;
import android.support.annotation.NonNull;
import com.vinh.dictionary_1.data.source.local.DictTypeConverter;

/**
 * Created by VinhTL on 27/10/2017.
 */

@Entity(tableName = "word_tbl")
public class Word {
    private String mType;
    @NonNull
    @PrimaryKey
    @ColumnInfo(name = "word")
    private String mWord;
    @ColumnInfo(name = "av", typeAffinity = ColumnInfo.BLOB)
    @TypeConverters(DictTypeConverter.class)
    private String mEVDescription;
    @ColumnInfo(name = "mean")
    private String mShortVietnameseDescription;
    private String mPronounce;
    @ColumnInfo(name = "dnpn", typeAffinity = ColumnInfo.BLOB)
    @TypeConverters({ DictTypeConverter.class })
    private String mAlikeWord;
    @ColumnInfo(name = "va", typeAffinity = ColumnInfo.BLOB)
    @TypeConverters(DictTypeConverter.class)
    private String mVEDescription;
    @ColumnInfo(name = "word_ko_dau")
    private String mWordWithoutDiacritic;

    public Word(WordBuilder wordBuilder) {
        this.mWord = wordBuilder.mWord;
        this.mEVDescription = wordBuilder.mEVDescription;
        this.mShortVietnameseDescription = wordBuilder.mShortVietnameseDescription;
        this.mPronounce = wordBuilder.mPronounce;
        this.mAlikeWord = wordBuilder.mAlikeWord;
        this.mVEDescription = wordBuilder.mVeDescription;
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

    public String getEvDescription() {
        return mEVDescription;
    }

    public void setEvDescription(String evDescription) {
        this.mEVDescription = evDescription;
    }

    public String getShortVietnameseDescription() {
        return mShortVietnameseDescription;
    }

    public void setShortVietnameseDescription(String shortVietnameseDescription) {
        this.mShortVietnameseDescription = shortVietnameseDescription;
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

    public String getVeDescription() {
        return mVEDescription;
    }

    public void setVeDescription(String veDescription) {
        this.mVEDescription = veDescription;
    }

    public String getWordWithoutDiacritic() {
        return mWordWithoutDiacritic;
    }

    public void setWordWithoutDiacritic(String wordWithoutDiacritic) {
        this.mWordWithoutDiacritic = wordWithoutDiacritic;
    }

    public static class WordBuilder {
        private String mType;
        private String mWord;
        private String mEVDescription;
        private String mShortVietnameseDescription;
        private String mPronounce;
        private String mAlikeWord;
        private String mVeDescription;
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
            this.mShortVietnameseDescription = shortVietnameseDescription;
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
            this.mVeDescription = veDescription;
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
