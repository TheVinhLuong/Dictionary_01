package com.vinh.dictionary_1.screen.home.homelist;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.vinh.dictionary_1.R;
import com.vinh.dictionary_1.data.model.DailyWord;
import com.vinh.dictionary_1.databinding.ItemDailyWordListHomeBinding;
import com.vinh.dictionary_1.screen.BaseRecyclerViewAdapter;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by VinhTL on 01/11/2017.
 */

public class HomeListDailyWordAdapter
        extends BaseRecyclerViewAdapter<HomeListDailyWordAdapter.ItemViewHolder> {
    private List<DailyWord> mDailyWord;
    private OnRecyclerViewItemClickListener<DailyWord> mItemClickListener;

    HomeListDailyWordAdapter(@NonNull Context context) {
        super(context);
        mDailyWord = new ArrayList<>();
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemDailyWordListHomeBinding binding =
                DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                        R.layout.item_daily_word_list_home, parent, false);
        return new ItemViewHolder(binding, mItemClickListener);
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        int drawableId;
        if (position % 2 == 0) {
            drawableId = R.drawable.ic_penguin;
        } else {
            drawableId = R.drawable.ic_parrot;
        }
        holder.bind(mDailyWord.get(position), drawableId);
    }

    @Override
    public int getItemCount() {
        return mDailyWord.size();
    }

    void setItemClickListener(OnRecyclerViewItemClickListener<DailyWord> itemClickListener) {
        mItemClickListener = itemClickListener;
    }

    void appendDataSet(List<DailyWord> words) {
        if (words != null) {
            mDailyWord.addAll(words);
        }
        notifyDataSetChanged();
    }
    
    void clearDataSet(){
        mDailyWord.clear();
    }

    class ItemViewHolder extends RecyclerView.ViewHolder {
        private ItemDailyWordListHomeBinding mBinding;
        private OnRecyclerViewItemClickListener<DailyWord> mItemClickListener;

        ItemViewHolder(ItemDailyWordListHomeBinding binding,
                OnRecyclerViewItemClickListener<DailyWord> listener) {
            super(binding.getRoot());
            mBinding = binding;
            mItemClickListener = listener;
        }

        void bind(DailyWord word, int drawableId) {
            mBinding.setItem(word);
            mBinding.setItemListener(mItemClickListener);
            mBinding.setDailyWordDrawable(drawableId);
            mBinding.executePendingBindings();
        }
    }
}
