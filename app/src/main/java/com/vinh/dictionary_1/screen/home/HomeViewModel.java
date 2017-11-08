package com.vinh.dictionary_1.screen.home;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.support.v4.app.Fragment;
import android.text.TextWatcher;
import android.view.MenuItem;
import com.vinh.dictionary_1.BR;
import com.vinh.dictionary_1.R;
import com.vinh.dictionary_1.data.source.SettingRepository;
import com.vinh.dictionary_1.data.source.local.sharedpref.SettingLocalDataSource;
import com.vinh.dictionary_1.screen.home.wordlist.WordListFragment;
import com.vinh.dictionary_1.utis.rx.SchedulerProvider;

import static com.vinh.dictionary_1.data.model.annotation.DictTypeName.DB_NAME_ENGLISH_VIETNAMESE;

/**
 * Exposes the data to be used in the Home screen.
 */

public class HomeViewModel extends BaseObservable implements HomeContract.ViewModel {

    private HomeContract.Presenter mPresenter;

    private boolean mIsDrawerOpen = false;

    private Fragment mFragment;

    private String mDictType;

    private TextWatcher mTextWatcher;

    private SettingRepository mSettingRepository;
    
    private Context mContext;

    HomeViewModel(Context context) {
        mContext = context;
        mSettingRepository = new SettingRepository(SettingLocalDataSource.getInstance());
        setFragment(WordListFragment.newInstance());
        if (mSettingRepository.getCurrentDictType()
                .subscribeOn(SchedulerProvider.getInstance().io())
                .blockingSingle()
                .equals(DB_NAME_ENGLISH_VIETNAMESE)) {
            mDictType = context.getString(R.string.title_db_english_vietnamese);
        } else {
            mDictType = context.getString(R.string.title_db_vietnamese_english);
        }
    }

    @Override
    public void onStart() {
        mPresenter.onStart();
    }

    @Override
    public void onStop() {
        mPresenter.onStop();
    }

    @Override
    public void setPresenter(HomeContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                setDrawerOpen(true);
                break;
        }
        return true;
    }

    @Bindable
    public String getDictType() {
        return mDictType;
    }

    @Override
    public void onChangeDictTouched() {
        mPresenter.onChangeDictTouched();
    }

    @Override
    public void setDictType(String dictType) {
        mDictType = dictType;
        notifyPropertyChanged(BR.dictType);
    }

    @Override
    public String getStringResource(int resourceId) {
        return mContext.getString(resourceId);
    }

    @Bindable
    public boolean isDrawerOpen() {
        return mIsDrawerOpen;
    }

    public void setDrawerOpen(boolean drawerOpen) {
        mIsDrawerOpen = drawerOpen;
        notifyPropertyChanged(BR.drawerOpen);
    }

    @Bindable
    public Fragment getFragment() {
        return mFragment;
    }

    public void setFragment(Fragment fragment) {
        mFragment = fragment;
        notifyPropertyChanged(BR.fragment);
        if (fragment instanceof WordListFragment) {
            setTextWatcher((WordListFragment) fragment);
        }
    }

    @Bindable
    public TextWatcher getTextWatcher() {
        return mTextWatcher;
    }

    public void setTextWatcher(TextWatcher textWatcher) {
        mTextWatcher = textWatcher;
        notifyPropertyChanged(BR.textWatcher);
    }
}
