package com.vinh.dictionary_1.data.source.remote.api.service;

import com.google.gson.JsonArray;
import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by le.quang.dao on 10/03/2017.
 */

public interface TranslateApi {

    @POST("/translate_a/single?client=gtx&dt=t")
    @FormUrlEncoded
    Observable<JsonArray> translate(@Field("tl") String targetLanguage,
            @Field("sl") String sourceLanguage, @Field("q") String text);
}
