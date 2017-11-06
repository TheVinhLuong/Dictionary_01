package com.vinh.dictionary_1.screen.home;

import android.content.Intent;
import com.vinh.dictionary_1.MainApplication;
import com.vinh.dictionary_1.R;
import com.vinh.dictionary_1.data.model.annotation.DictTypeName;
import com.vinh.dictionary_1.data.source.SettingRepository;
import com.vinh.dictionary_1.data.source.local.sharedpref.SettingLocalDataSource;
import com.vinh.dictionary_1.utis.Constant;

import static com.vinh.dictionary_1.data.model.annotation.DictTypeName.DB_NAME_ENGLISH_VIETNAMESE;
import static com.vinh.dictionary_1.data.model.annotation.DictTypeName.DB_NAME_VIETNAMESE_ENGLISH;

/**
 * Listens to user actions from the UI ({@link HomeActivity}), retrieves the data and updates
 * the UI as required.
 */
final class HomePresenter implements HomeContract.Presenter {
    private static final String TAG = HomePresenter.class.getName();

    private final HomeContract.ViewModel mViewModel;
    private final SettingRepository mSettingRepository;

    HomePresenter(HomeContract.ViewModel viewModel) {
        mViewModel = viewModel;
        mSettingRepository = new SettingRepository(SettingLocalDataSource.getInstance());
    }

    @Override
    public void onStart() {
    }

    @Override
    public void onStop() {
    }

    @Override
    public void onChangeDictTouched() {
        if (mSettingRepository.getCurrentDictType()
                .blockingSingle()
                .equals(DB_NAME_ENGLISH_VIETNAMESE)) {
            switchDictionary(DB_NAME_VIETNAMESE_ENGLISH);
        } else {
            switchDictionary(DB_NAME_ENGLISH_VIETNAMESE);
        }
        Intent intent = new Intent(Constant.INTENT_ACTION_CHANGE_DICT);
        MainApplication.getInstance().sendBroadcast(intent);
    }

    public void switchDictionary(@DictTypeName String toDictName) {
        mSettingRepository.setCurrentDictType(toDictName);
        mViewModel.setDictType(mViewModel.getStringResource(R.string.title_db_english_vietnamese));
    }
}
