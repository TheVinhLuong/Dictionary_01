package com.vinh.dictionary_1.data.model.annotation;

import android.support.annotation.StringDef;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import static com.vinh.dictionary_1.data.model.annotation.DictTypeTitle.DB_TITLE_ENGLISH_VIETNAMESE;
import static com.vinh.dictionary_1.data.model.annotation.DictTypeTitle.DB_TITLE_VIETNAMESE_ENGLISH;

/**
 * Created by VinhTL on 03/11/2017.
 */

@Retention(RetentionPolicy.SOURCE)
@StringDef({DB_TITLE_VIETNAMESE_ENGLISH, DB_TITLE_ENGLISH_VIETNAMESE})
public @interface DictTypeTitle {
    String DB_TITLE_VIETNAMESE_ENGLISH = "V-A";
    String DB_TITLE_ENGLISH_VIETNAMESE = "A-V";
}
