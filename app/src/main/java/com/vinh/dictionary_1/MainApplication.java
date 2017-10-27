package com.vinh.dictionary_1;

import android.app.Application;
import com.vinh.dictionary_1.data.source.local.sharedpref.SharedPrefsImplement;

/**
 * Created by VinhTL on 26/10/2017.
 */

public class MainApplication extends Application {
    private static MainApplication sInstance;
    public static MainApplication getInstance(){
        return sInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;
        SharedPrefsImplement.init(this);
    }
}
