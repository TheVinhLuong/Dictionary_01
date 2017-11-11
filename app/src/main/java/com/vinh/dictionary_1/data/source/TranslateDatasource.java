package com.vinh.dictionary_1.data.source;

import io.reactivex.Observable;


/**
 * Created by VinhTL on 11/11/2017.
 */

public interface TranslateDatasource {
    Observable<String> translate(String targetLanguage, String sourceLanguage, String text);
}
