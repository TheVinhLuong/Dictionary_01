package com.vinh.dictionary_1.screen.home;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.view.MenuItem;
import com.vinh.dictionary_1.BR;

/**
 * Exposes the data to be used in the Home screen.
 */

public class HomeViewModel extends BaseObservable implements HomeContract.ViewModel {

    private HomeContract.Presenter mPresenter;

    private boolean mIsDrawerOpen = false;

    HomeViewModel() {
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
    public boolean isDrawerOpen() {
        return mIsDrawerOpen;
    }

    public void setDrawerOpen(boolean drawerOpen) {
        mIsDrawerOpen = drawerOpen;
        notifyPropertyChanged(BR.drawerOpen);
    }
    
}
