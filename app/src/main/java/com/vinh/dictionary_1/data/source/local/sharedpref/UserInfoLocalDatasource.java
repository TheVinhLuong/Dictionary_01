package com.vinh.dictionary_1.data.source.local.sharedpref;

import com.vinh.dictionary_1.data.source.UserInfoDatasource;
import com.vinh.dictionary_1.utis.Constant;

/**
 * Created by VinhTL on 07/11/2017.
 */

public class UserInfoLocalDatasource implements UserInfoDatasource {
    private static UserInfoLocalDatasource sUserInfoLocalDatasource;
    private SharedPrefsApi mSharedPrefsApi;

    public static UserInfoLocalDatasource getInstance() {
        if (sUserInfoLocalDatasource == null) {
            sUserInfoLocalDatasource =
                    new UserInfoLocalDatasource(SharedPrefsImplement.getInstance());
        }
        return sUserInfoLocalDatasource;
    }

    public UserInfoLocalDatasource(SharedPrefsApi sharedPrefsApi) {
        mSharedPrefsApi = sharedPrefsApi;
    }

    @Override
    public String getLastTimeUsageInfo() {
        return mSharedPrefsApi.get(Constant.PREF_USER_LAST_TIME_USAGE, String.class);
    }

    @Override
    public void setLastTimeUsageInfo(String date) {
        mSharedPrefsApi.put(Constant.PREF_USER_LAST_TIME_USAGE, date);
    }
}
