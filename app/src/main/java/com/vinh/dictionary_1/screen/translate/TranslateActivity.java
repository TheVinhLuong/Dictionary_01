package com.vinh.dictionary_1.screen.translate;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import com.vinh.dictionary_1.R;
import com.vinh.dictionary_1.data.source.TranslateRepository;
import com.vinh.dictionary_1.data.source.remote.api.TranslateRemoteDatasource;
import com.vinh.dictionary_1.data.source.remote.api.service.TranslateServiceClient;
import com.vinh.dictionary_1.databinding.ActivityTranslateBinding;
import com.vinh.dictionary_1.screen.BaseActivity;

/**
 * Translate Screen.
 */
public class TranslateActivity extends BaseActivity {

    private TranslateContract.ViewModel mViewModel;
    private ActivityTranslateBinding mActivityTranslateBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TranslateRepository translateRepository = new TranslateRepository(
                new TranslateRemoteDatasource(TranslateServiceClient.getInstance()));

        mViewModel = new TranslateViewModel(this);

        TranslateContract.Presenter presenter =
                new TranslatePresenter(mViewModel, translateRepository);
        mViewModel.setPresenter(presenter);

        mActivityTranslateBinding =
                DataBindingUtil.setContentView(this, R.layout.activity_translate);
        mActivityTranslateBinding.setViewModel((TranslateViewModel) mViewModel);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mViewModel.onStart();
    }

    @Override
    protected void onStop() {
        mViewModel.onStop();
        super.onStop();
    }

    public ActivityTranslateBinding getActivityTranslateBinding() {
        return mActivityTranslateBinding;
    }
}
