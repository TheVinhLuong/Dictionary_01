package com.vinh.dictionary_1.screen.home.homelist;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.vinh.dictionary_1.R;
import com.vinh.dictionary_1.data.model.Word;
import com.vinh.dictionary_1.databinding.ItemWordListHomeBinding;
import com.vinh.dictionary_1.screen.BaseRecyclerViewAdapter;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by VinhTL on 01/11/2017.
 */

public class HomeListSearchedWordAdapter
        extends BaseRecyclerViewAdapter<HomeListSearchedWordAdapter.ItemViewHolder> {
    private List<Word> mWords;
    private OnRecyclerViewItemClickListener<Word> mItemClickListener;

    protected HomeListSearchedWordAdapter(@NonNull Context context) {
        super(context);
        mWords = new ArrayList<>();
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemWordListHomeBinding binding =
                DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                        R.layout.item_word_list_home, parent, false);
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

    public void setItemClickListener(OnRecyclerViewItemClickListener<Word> itemClickListener) {
        mItemClickListener = itemClickListener;
    }

    public void changeDataSet(List<Word> words) {
        mWords.clear();
        if (words != null) {
            mWords.addAll(words);
        }
        notifyDataSetChanged();
    }

    class ItemViewHolder extends RecyclerView.ViewHolder {
        private ItemWordListHomeBinding mBinding;
        private OnRecyclerViewItemClickListener<Word> mItemClickListener;

        ItemViewHolder(ItemWordListHomeBinding binding,
                OnRecyclerViewItemClickListener<Word> listener) {
            super(binding.getRoot());
            mBinding = binding;
            mItemClickListener = listener;
        }

        void bind(Word word) {
            mBinding.setItem(word);
            mBinding.setItemListener(mItemClickListener);
            mBinding.executePendingBindings();
        }
    }
}
