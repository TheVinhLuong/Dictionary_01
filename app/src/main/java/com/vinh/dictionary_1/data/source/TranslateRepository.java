package com.vinh.dictionary_1.data.source;

import io.reactivex.Observable;

/**
 * Created by VinhTL on 11/11/2017.
 */

public class TranslateRepository {
    private TranslateDatasource mTranslateDatasource;

    public TranslateRepository(TranslateDatasource translateDatasource) {
        mTranslateDatasource = translateDatasource;
    }

    public Observable<String> translate(String targetLanguage, String sourceLanguage, String text){
        return mTranslateDatasource.translate(targetLanguage, sourceLanguage, text);
    }
}
