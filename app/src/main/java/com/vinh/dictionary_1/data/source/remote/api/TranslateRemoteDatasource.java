package com.vinh.dictionary_1.data.source.remote.api;

import com.vinh.dictionary_1.data.source.TranslateDatasource;
import com.vinh.dictionary_1.data.source.remote.api.service.TranslateApi;
import io.reactivex.Observable;

/**
 * Created by VinhTL on 11/11/2017.
 */

public class TranslateRemoteDatasource implements TranslateDatasource {
    private TranslateApi mTranslateApi;

    public TranslateRemoteDatasource(TranslateApi translateApi) {
        mTranslateApi = translateApi;
    }

    @Override
    public Observable<String> translate(String targetLanguage, String sourceLanguage, String text) {
        return mTranslateApi.translate(targetLanguage, sourceLanguage, text).map(jsonArray -> {
            if (jsonArray != null) {
                return jsonArray
                        .get(0)
                        .getAsJsonArray()
                        .get(0)
                        .getAsJsonArray()
                        .get(0)
                        .getAsString();
            }
            return null;
        });
    }
}
