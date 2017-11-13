package com.vinh.dictionary_1.screen.home;

import android.animation.ValueAnimator;
import android.content.Intent;
import android.content.res.Configuration;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import com.vinh.dictionary_1.R;
import com.vinh.dictionary_1.databinding.ActivityHomeBinding;
import com.vinh.dictionary_1.screen.BaseActivity;
import com.vinh.dictionary_1.screen.bookmarkedwordlist.BookmarkedWordListActivity;
import com.vinh.dictionary_1.screen.translate.TranslateActivity;
import com.vinh.dictionary_1.screen.worddetail.WordDetailFragment;
import com.vinh.dictionary_1.utis.Constant;
import com.vinh.dictionary_1.utis.ViewUtils;
import com.vinh.dictionary_1.utis.navigator.Navigator;
import java.util.List;

/**
 * Home Screen.
 */
public class HomeActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private HomeContract.ViewModel mViewModel;
    private ActivityHomeBinding mBinding;
    private ActionBarDrawerToggle mActionBarDrawerToggle;
    private ValueAnimator mBurgerToArrowAnimator;
    private boolean mustPreventDrawerOpenOnNavIconTouch = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_home);
        mViewModel = new HomeViewModel(this);

        HomeContract.Presenter presenter = new HomePresenter(mViewModel);
        mViewModel.setPresenter(presenter);
        mActionBarDrawerToggle =
                new ActionBarDrawerToggle(this, mBinding.navigationDrawerHome, mBinding.toolbarHome,
                        R.string.drawer_open_content_desc_res,
                        R.string.drawer_close_content_desc_res);
        mActionBarDrawerToggle.setDrawerIndicatorEnabled(true);
        mBurgerToArrowAnimator = ValueAnimator.ofFloat(1, 0);
        mBurgerToArrowAnimator.addUpdateListener(valueAnimator -> {
            float slideOffset = (Float) valueAnimator.getAnimatedValue();
            mActionBarDrawerToggle.onDrawerSlide(mBinding.navigationDrawerHome, slideOffset);
        });
        mBurgerToArrowAnimator.setInterpolator(new DecelerateInterpolator());
        mBurgerToArrowAnimator.setDuration(500);

        mBinding.setViewModel((HomeViewModel) mViewModel);
        mBinding.setActivity(this);
        mBinding.setToolbarDrawerToggle(mActionBarDrawerToggle);
        mBinding.setOnTouchListener((HomeViewModel) mViewModel);
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mActionBarDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mActionBarDrawerToggle.syncState();
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

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Navigator navigator = new Navigator(this);
        switch (item.getItemId()) {
            case R.id.item_nav_bookmarked_words:
                navigator.startActivity(BookmarkedWordListActivity.class);
                mBinding.navigationDrawerHome.closeDrawers();
                break;
            case R.id.item_nav_daily_words:
                //TODO: Implement handler;
                break;
            case R.id.item_nav_online_translate:
                navigator.startActivity(TranslateActivity.class);
                mBinding.navigationDrawerHome.closeDrawers();
                break;
            case R.id.item_nav_quick_search_window:
                toggleQuickSearch();
                //TODO: Implement handler;
                break;
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (!mustPreventDrawerOpenOnNavIconTouch) {
            return mViewModel.onOptionsItemSelected(item);
        } else {
            onBackPressed();
        }
        return false;
    }

    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() > 1) {
            super.onBackPressed();
            ViewUtils.hideSoftKeyboard(this);
            mBinding.inputField.setText("");
        }
    }

    public void toggleQuickSearch() {
        ImageView quickTranslate = ((ImageView) MenuItemCompat.getActionView(
                mBinding.navigationHome.getMenu().findItem(R.id.item_nav_quick_search_window)));
        if (quickTranslate.getVisibility() == View.GONE) {
            quickTranslate.setVisibility(View.VISIBLE);
        } else {
            quickTranslate.setVisibility(View.GONE);
        }
    }

    public void onFragmentCreated() {
        List<Fragment> fragments = getSupportFragmentManager().getFragments();
        if (fragments.size() == 2) {
            mBinding.navigationDrawerHome.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
            mustPreventDrawerOpenOnNavIconTouch = true;
            mBurgerToArrowAnimator.start();
            mBurgerToArrowAnimator.setFloatValues(0, 1);
        }
        for (Fragment fragment : fragments) {
            if (fragment instanceof WordDetailFragment) {
                mBinding.linearLayoutSearchHelper.setVisibility(View.INVISIBLE);
                break;
            }
        }
    }

    public void onFragmentDetach(Fragment detachfragment) {
        List<Fragment> fragments = getSupportFragmentManager().getFragments();
        if (fragments.size() > 2) {
            return;
        } else {
            for (Fragment fragment : fragments) {
                if (fragment instanceof WordDetailFragment) {
                    return;
                }
            }
        }
        mBinding.linearLayoutSearchHelper.setVisibility(View.VISIBLE);
        if (fragments.size() == 1) {
            Intent intent = new Intent(Constant.INTENT_ACTION_UPDATE_SEARCHED_WORDS);
            sendBroadcast(intent);
            mBinding.navigationDrawerHome.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
            mustPreventDrawerOpenOnNavIconTouch = false;
            mBurgerToArrowAnimator.start();
            mBurgerToArrowAnimator.setFloatValues(1, 0);
        }
    }
}
