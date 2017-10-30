package com.vinh.dictionary_1.utis.binding;

import android.databinding.BindingAdapter;
import android.graphics.drawable.Drawable;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TextInputLayout;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.widget.ImageView;
import com.vinh.dictionary_1.R;

public final class BindingUtils {

    private BindingUtils() {
        // No-op
    }

    @BindingAdapter({ "errorText" })
    public static void setErrorMessage(TextInputLayout view, String errorMessage) {
        view.setError(errorMessage);
    }

    @BindingAdapter({ "recyclerAdapter" })
    public static void setAdapterForRecyclerView(RecyclerView recyclerView,
            RecyclerView.Adapter adapter) {
        recyclerView.setAdapter(adapter);
    }

    @BindingAdapter({ "supportActionBar" })
    public static void setSupportActionBar(Toolbar toolbar, AppCompatActivity activity) {
        activity.setSupportActionBar(toolbar);
        activity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        activity.getSupportActionBar().setHomeButtonEnabled(true);
    }

    @BindingAdapter({ "supportActionBarToggle" })
    public static void setSupportActionBarToggle(DrawerLayout drawerLayout,
            ActionBarDrawerToggle drawerToggle) {
        drawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.syncState();
    }

    @BindingAdapter({ "navigationListener" })
    public static void setNavigationListener(NavigationView navigationView,
            NavigationView.OnNavigationItemSelectedListener listener) {
        navigationView.setNavigationItemSelectedListener(listener);
    }

    @BindingAdapter({ "quickSearchBadge" })
    public static void setQuickSearchBadgeResource(NavigationView navigationView,
            Drawable drawable) {
        ((ImageView) MenuItemCompat.getActionView(navigationView.getMenu()
                .findItem(R.id.item_nav_quick_search_window))).setImageDrawable(drawable);
    }

    @BindingAdapter({ "drawerState" })
    public static void toggleDrawerState(DrawerLayout drawerLayout, boolean isOpen) {
        if (isOpen) {
            drawerLayout.openDrawer(Gravity.START);
        } else {
            drawerLayout.closeDrawers();
        }
    }
}
