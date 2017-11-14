package com.vinh.dictionary_1.screen.dailywordlist.wordlist;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.vinh.dictionary_1.R;
import com.vinh.dictionary_1.data.model.DailyWord;
import com.vinh.dictionary_1.databinding.ItemDailyWordListBinding;
import com.vinh.dictionary_1.screen.BaseRecyclerViewAdapter;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by VinhTL on 01/11/2017.
 */

public class DailyWordListAdapter
        extends BaseRecyclerViewAdapter<DailyWordListAdapter.ItemViewHolder> {
    private List<DailyWord> mWords;
    private OnRecyclerViewItemClickListener<DailyWord> mItemClickListener;

    DailyWordListAdapter(@NonNull Context context) {
        super(context);
        mWords = new ArrayList<>();
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemDailyWordListBinding binding =
                DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                        R.layout.item_daily_word_list, parent, false);
        return new ItemViewHolder(binding, mItemClickListener);
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        holder.bind(mWords.get(position));
    }

    @Override
    public int getItemCount() {
        return mWords.size();
    }

    public void setItemClickListener(OnRecyclerViewItemClickListener<DailyWord> itemClickListener) {
        mItemClickListener = itemClickListener;
    }

    public void changeDataSet(List<DailyWord> words) {
        mWords.clear();
        if (words != null) {
            mWords.addAll(words);
        }
        notifyDataSetChanged();
    }

    class ItemViewHolder extends RecyclerView.ViewHolder {
        private ItemDailyWordListBinding mBinding;
        private OnRecyclerViewItemClickListener<DailyWord> mItemClickListener;

        public ItemViewHolder(ItemDailyWordListBinding binding,
                OnRecyclerViewItemClickListener<DailyWord> listener) {
            super(binding.getRoot());
            mBinding = binding;
            mItemClickListener = listener;
        }

        void bind(DailyWord word) {
            mBinding.setItem(word);
            mBinding.setItemListener(mItemClickListener);
            mBinding.executePendingBindings();
        }
    }
}
