package com.vinh.dictionary_1.utis;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

/**
 * Created by VinhTL on 15/11/2017.
 */

public class OnEndScrollListener extends RecyclerView.OnScrollListener {
    private OnEndScroll mOnEndScroll;

    public OnEndScrollListener(OnEndScroll onEndScroll) {
        mOnEndScroll = onEndScroll;
    }

    @Override
    public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
        super.onScrollStateChanged(recyclerView, newState);
        if (newState == RecyclerView.SCROLL_STATE_IDLE) {
            int totalItemCount = recyclerView.getLayoutManager().getItemCount();
            int lastVisibleItem =
                    ((LinearLayoutManager) recyclerView.getLayoutManager())
                            .findLastVisibleItemPosition();
            if (totalItemCount <= lastVisibleItem + 1) {
                mOnEndScroll.onEndScrolled();
            }
        }
    }
    
    public interface OnEndScroll{
        void onEndScrolled();
    }
}
