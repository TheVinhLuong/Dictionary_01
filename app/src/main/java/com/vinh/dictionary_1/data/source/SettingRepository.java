package com.vinh.dictionary_1.data.source;

import io.reactivex.Observable;

/**
 * Created by VinhTL on 03/11/2017.
 */

public class SettingRepository implements SettingDataSource{
    private SettingDataSource mLocalDataSource;

    public SettingRepository(SettingDataSource localDataSource) {
        mLocalDataSource = localDataSource;
    }

    @Override
    public Observable<String> getCurrentDictType() {
        return mLocalDataSource.getCurrentDictType();
    }

    @Override
    public void setCurrentDictType(String dictType) {
        mLocalDataSource.setCurrentDictType(dictType);
    }

    @Override
    public Observable<Boolean> isDatabaseCopied() {
        return  mLocalDataSource.isDatabaseCopied();
    }

    @Override
    public void setDatabaseState(boolean isCopied) {
        mLocalDataSource.setDatabaseState(isCopied);
    }
}
