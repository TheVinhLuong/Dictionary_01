package com.vinh.dictionary_1.utis.binding;

import android.annotation.SuppressLint;
import android.databinding.BindingAdapter;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;
import android.support.v7.widget.Toolbar;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import com.vinh.dictionary_1.R;
import com.vinh.dictionary_1.screen.home.HomeActivity;
import com.vinh.dictionary_1.screen.home.wordlist.WordListFragment;
import java.util.List;

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
    public static void setSupportActionBar(Toolbar toolbar, HomeActivity activity) {
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

    @BindingAdapter({ "fragment" })
    public static void setFragment(FrameLayout frameLayout, Fragment fragment) {
        FragmentManager fragmentManager =
                ((AppCompatActivity) frameLayout.getContext()).getSupportFragmentManager();
        if (fragment instanceof WordListFragment && fragmentManager.getFragments().size() > 1) {
            fragmentManager.beginTransaction().show(fragment).commit();
            return;
        }
        fragmentManager.beginTransaction()
                .add(frameLayout.getId(), fragment)
                .addToBackStack(null)
                .commit();
    }

    @BindingAdapter({ "textWatcher" })
    public static void setTextWatcher(EditText editText, TextWatcher textWatcher) {
        if (textWatcher != null) {
            editText.addTextChangedListener(textWatcher);
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    @BindingAdapter({ "onTouchListener" })
    public static void setOnTouchListener(EditText editText, View.OnTouchListener onTouchListener) {
        editText.setOnTouchListener(onTouchListener);
    }

    @BindingAdapter({ "recyclerViewOnScrollListener" })
    public static void setRecyclerViewOnScrollListener(RecyclerView recyclerView,
            RecyclerView.OnScrollListener onScrollListener) {
        recyclerView.addOnScrollListener(onScrollListener);
    }

    @BindingAdapter({ "webViewContent" })
    public static void setWebViewContent(WebView webView, String content) {
        webView.loadDataWithBaseURL("file:///android_asset/", 
                content, "text/html", "UTF-8", null);
    }

    @BindingAdapter({ "webViewClient" })
    public static void setWebViewClient(WebView webView, WebViewClient webViewClient) {
        webView.setWebViewClient(webViewClient);
    }

    @BindingAdapter({ "bookmarkState" })
    public static void setBookmarkState(ImageView imageView, boolean isBookmarked) {
        if (isBookmarked) {
            imageView.setImageResource(R.drawable.ic_star_bookmarked);
        } else {
            imageView.setImageResource(R.drawable.ic_star_bookmark);
        }
    }

    @BindingAdapter({ "snapHelper" })
    public static void setSnapHelper(RecyclerView recyclerView, SnapHelper snapHelper) {
        snapHelper.attachToRecyclerView(recyclerView);
    }

    @BindingAdapter("imageResource")
    public static void setImageResource(ImageView imageView, int resource) {
        imageView.setImageResource(resource);
    }

    @BindingAdapter("spinnerData")
    public static void setSpinnerData(Spinner spinner, List<String> data) {
        ArrayAdapter<String> dataAdapter =
                new ArrayAdapter<String>(spinner.getContext(), android.R.layout.simple_spinner_item,
                        data) {
                    @Override
                    public boolean isEnabled(int position) {
                        return position != 0;
                    }

                    @Override
                    public View getDropDownView(int position, @Nullable View convertView,
                            @NonNull ViewGroup parent) {
                        View view = super.getDropDownView(position, convertView, parent);
                        TextView tv = (TextView) view;
                        if (position == 0) {
                            tv.setTextColor(Color.GRAY);
                        } else {
                            tv.setTextColor(Color.BLACK);
                        }
                        return view;
                    }
                };
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);
    }

    @BindingAdapter({ "scrollListener" })
    public static void setScrollListener(RecyclerView recyclerView,
            RecyclerView.OnScrollListener onScrollListener) {
        recyclerView.addOnScrollListener(onScrollListener);
    }
}
