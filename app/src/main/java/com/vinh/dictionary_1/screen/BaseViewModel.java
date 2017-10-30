package com.vinh.dictionary_1.screen;

/**
 * Created by VinhTL on 09/10/2017.
 */
public interface BaseViewModel<T extends BasePresenter> {
    void onStart();

    void onStop();

    void setPresenter(T presenter);
}
