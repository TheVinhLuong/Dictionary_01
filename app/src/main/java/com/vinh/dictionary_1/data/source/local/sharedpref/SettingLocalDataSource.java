package com.vinh.dictionary_1.data.source.local.sharedpref;

import com.vinh.dictionary_1.data.source.SettingDataSource;
import com.vinh.dictionary_1.utis.Constant;
import io.reactivex.Observable;

/**
 * Created by VinhTL on 03/11/2017.
 */

public class SettingLocalDataSource implements SettingDataSource {
    private static SettingLocalDataSource sSettingLocalDataSource;
    private SharedPrefsApi mSharedPrefsApi;

    public static SettingLocalDataSource getInstance() {
        if (sSettingLocalDataSource == null) {
            sSettingLocalDataSource =
                    new SettingLocalDataSource(SharedPrefsImplement.getInstance());
        }
        return sSettingLocalDataSource;
    }

    private SettingLocalDataSource(SharedPrefsApi sharedPrefsApi) {
        mSharedPrefsApi = sharedPrefsApi;
    }

    @Override
    public Observable<String> getCurrentDictType() {
        return Observable.just(mSharedPrefsApi.get(Constant.PREF_CHOSEN_DICT_TYPE, String.class));
    }

    @Override
    public void setCurrentDictType(String dictType) {
        mSharedPrefsApi.put(Constant.PREF_CHOSEN_DICT_TYPE, dictType);
    }

    @Override
    public Observable<Boolean> isDatabaseCopied() {
        return Observable.just(mSharedPrefsApi.get(Constant.PREF_DICT_DB_COPIED, Boolean.class));
    }

    @Override
    public void setDatabaseState(boolean isCopied) {
        mSharedPrefsApi.put(Constant.PREF_DICT_DB_COPIED, isCopied);
    }
}
