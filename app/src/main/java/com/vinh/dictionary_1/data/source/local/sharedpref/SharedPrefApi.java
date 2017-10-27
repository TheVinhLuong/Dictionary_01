package com.vinh.dictionary_1.data.source.local.sharedpref;

/**
 * Created by VinhTL on 26/10/2017.
 */

public interface SharedPrefApi {
    <T> T get(String key, Class<T> clazz);
    <T> void put(String key, T data);
    void clear();
}
