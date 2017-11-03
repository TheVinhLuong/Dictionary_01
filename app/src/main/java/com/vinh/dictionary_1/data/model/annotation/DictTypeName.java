package com.vinh.dictionary_1.data.model.annotation;

import android.support.annotation.StringDef;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import static com.vinh.dictionary_1.data.model.annotation.DictTypeName.DB_NAME_ENGLISH_VIETNAMESE;
import static com.vinh.dictionary_1.data.model.annotation.DictTypeName.DB_NAME_VIETNAMESE_ENGLISH;

/**
 * Created by VinhTL on 03/11/2017.
 */

@Retention(RetentionPolicy.SOURCE)
@StringDef({DB_NAME_ENGLISH_VIETNAMESE, DB_NAME_VIETNAMESE_ENGLISH})
public @interface DictTypeName {
    String DB_NAME_ENGLISH_VIETNAMESE = "ev.db";
    String DB_NAME_VIETNAMESE_ENGLISH = "ve.db";
}
