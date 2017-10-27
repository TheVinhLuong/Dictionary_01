package com.vinh.dictionary_1.utis.rx;

import android.support.annotation.NonNull;
import io.reactivex.Scheduler;

/**
 * Created by VinhTL on 26/10/2017.
 */

public interface BaseSchedulerProvider {
    @NonNull
    Scheduler computation();

    @NonNull
    Scheduler io();

    @NonNull
    Scheduler ui();
}
