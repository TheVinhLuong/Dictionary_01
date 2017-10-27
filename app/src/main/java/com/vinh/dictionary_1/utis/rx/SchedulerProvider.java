package com.vinh.dictionary_1.utis.rx;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.annotations.Nullable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by VinhTL on 26/10/2017.
 */

public class SchedulerProvider implements BaseSchedulerProvider {
    @Nullable
    private static SchedulerProvider sInstance;
    
    private SchedulerProvider() {
    }

    public static synchronized SchedulerProvider getInstance() {
        if (sInstance == null) {
            sInstance = new SchedulerProvider();
        }
        return sInstance;
    }

    @Override
    @NonNull
    public Scheduler computation() {
        return Schedulers.computation();
    }

    @Override
    @NonNull
    public Scheduler io() {
        return Schedulers.io();
    }

    @Override
    @NonNull
    public Scheduler ui() {
        return AndroidSchedulers.mainThread();
    }
}
