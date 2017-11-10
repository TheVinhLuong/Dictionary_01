package com.vinh.dictionary_1.data.source;

/**
 * Created by VinhTL on 07/11/2017.
 */

public class UserInfoRepository implements UserInfoDatasource {
    private UserInfoDatasource mLocalDatasource;

    public UserInfoRepository(UserInfoDatasource localDatasource) {
        mLocalDatasource = localDatasource;
    }

    @Override
    public String getLastTimeUsageInfo() {
        return mLocalDatasource.getLastTimeUsageInfo();
    }

    @Override
    public void setLastTimeUsageInfo(String date) {
        mLocalDatasource.setLastTimeUsageInfo(date);
    }
}
