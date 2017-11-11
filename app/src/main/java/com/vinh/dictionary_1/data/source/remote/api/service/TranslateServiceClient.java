package com.vinh.dictionary_1.data.source.remote.api.service;

import android.app.Application;
import android.support.annotation.NonNull;
import com.vinh.dictionary_1.utis.Constant;

public class TranslateServiceClient extends ServiceClient {

    private static TranslateApi mTranslateApiInstance;

    public static void initialize(@NonNull Application application) {
        mTranslateApiInstance = createService(application, Constant.END_POINT_URL, TranslateApi.class);
    }

    public static TranslateApi getInstance() {
        if (mTranslateApiInstance == null) {
            throw new RuntimeException("Need call method TranslateServiceClient#initialize() first");
        }
        return mTranslateApiInstance;
    }
}
