package com.vinh.dictionary_1.screen.home.wordlist;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.vinh.dictionary_1.R;
import com.vinh.dictionary_1.data.model.Word;
import com.vinh.dictionary_1.data.source.EVDictRepository;
import com.vinh.dictionary_1.data.source.VEDictRepository;
import com.vinh.dictionary_1.data.source.local.evdictdatabase.EVDictDatabase;
import com.vinh.dictionary_1.data.source.local.evdictdatabase.EVDictLocalDatasource;
import com.vinh.dictionary_1.data.source.local.vedictdatabase.VEDictDatabase;
import com.vinh.dictionary_1.data.source.local.vedictdatabase.VEDictLocalDatasource;
import com.vinh.dictionary_1.databinding.FragmentWordListBinding;
import com.vinh.dictionary_1.screen.BaseFragment;
import com.vinh.dictionary_1.utis.Constant;
import java.util.ArrayList;
import java.util.List;

/**
 * WordList Screen.
 */
public class WordListFragment extends BaseFragment implements TextWatcher {

    private WordListContract.ViewModel mViewModel;
    private BroadcastReceiver mBroadcastReceiver;

    public static WordListFragment newInstance() {
        return new WordListFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        List<Word> words = new ArrayList<>();
        WordListAdapter
                wordListAdapter = new WordListAdapter(getActivity(), words);
        EVDictRepository evDictRepository = new EVDictRepository(EVDictLocalDatasource.getInstance(
                EVDictDatabase.getInstance(getActivity()).evDictDAO()));
        VEDictRepository veDictRepository = new VEDictRepository(VEDictLocalDatasource.getInstance(
                VEDictDatabase.getInstance(getActivity()).veDictDAO()));
        mViewModel = new WordListViewModel(wordListAdapter);

        WordListContract.Presenter presenter =
                new WordListPresenter(mViewModel, evDictRepository, veDictRepository);
        mViewModel.setPresenter(presenter);
        setUpBroadcastReceiver();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        FragmentWordListBinding fragmentWordListBinding =
                DataBindingUtil.inflate(inflater, R.layout.fragment_word_list, container, false);
        fragmentWordListBinding.setViewModel((WordListViewModel) mViewModel);
        mViewModel.setBinding(fragmentWordListBinding);
        return fragmentWordListBinding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();
        mViewModel.onStart();
    }

    @Override
    public void onStop() {
        mViewModel.onStop();
        super.onStop();
    }
    
    @Override
    public void onDestroy() {
        super.onDestroy();
        getActivity().unregisterReceiver(mBroadcastReceiver);
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        mViewModel.onTextChange(charSequence.toString());
    }

    @Override
    public void afterTextChanged(Editable editable) {

    }
    
    public void setUpBroadcastReceiver() {
        mBroadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                mViewModel.onBroadcastReceiverReceive(context, intent);
            }
        };
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(Constant.INTENT_ACTION_CHANGE_DICT);
        getActivity().registerReceiver(mBroadcastReceiver, intentFilter);
    }
}
