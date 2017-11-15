package com.vinh.dictionary_1.screen.home.homelist;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.vinh.dictionary_1.R;
import com.vinh.dictionary_1.data.source.DailyWordRepository;
import com.vinh.dictionary_1.data.source.SearchedWordRepository;
import com.vinh.dictionary_1.data.source.local.dailyworddatabase.DailyWordDatabase;
import com.vinh.dictionary_1.data.source.local.dailyworddatabase.DailyWordLocalDatasource;
import com.vinh.dictionary_1.data.source.local.searchedworddatabase.SearchedWordDatabase;
import com.vinh.dictionary_1.data.source.local.searchedworddatabase.SearchedWordLocalDatasource;
import com.vinh.dictionary_1.databinding.FragmentHomeListBinding;
import com.vinh.dictionary_1.screen.BaseFragment;
import com.vinh.dictionary_1.screen.home.HomeActivity;
import com.vinh.dictionary_1.utis.Constant;

/**
 * HomeList Screen.
 */
public class HomeListFragment extends BaseFragment {

    private HomeListContract.ViewModel mViewModel;
    private BroadcastReceiver mBroadcastReceiver;
    
    public static HomeListFragment newInstance() {
        return new HomeListFragment();
    }

    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        HomeListDailyWordAdapter homeListDailyWordAdapter =
                new HomeListDailyWordAdapter(getActivity());
        HomeListSearchedWordAdapter homeListSearchedWordAdapter =
                new HomeListSearchedWordAdapter(getActivity());
        DailyWordRepository dailyWordRepository = new DailyWordRepository(
                DailyWordLocalDatasource.getInstance(
                        DailyWordDatabase.getInstance(getActivity()).dailyWordDAO()));
        SearchedWordRepository searchedWordRepository = new SearchedWordRepository(
                SearchedWordLocalDatasource.getInstance(
                        SearchedWordDatabase.getInstance(getActivity()).searchedWordDAO()));
        mViewModel = new HomeListViewModel(homeListDailyWordAdapter, homeListSearchedWordAdapter);
        HomeListContract.Presenter presenter =
                new HomeListPresenter(mViewModel, dailyWordRepository, searchedWordRepository);
        mViewModel.setPresenter(presenter);
        setUpBroadcastReceiver();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        ((HomeActivity) getActivity()).onFragmentCreated();
        FragmentHomeListBinding binding =
                DataBindingUtil.inflate(inflater, R.layout.fragment_home_list,
                        container, false);
        binding.setViewModel((HomeListViewModel) mViewModel);
        return binding.getRoot();
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
    public void onDestroyView() {
        super.onDestroyView();
        getActivity().unregisterReceiver(mBroadcastReceiver);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        ((HomeActivity) getActivity()).onFragmentDetach(this);
    }

    public void setUpBroadcastReceiver() {
        mBroadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                mViewModel.onBroadcastReceiverReceive(context, intent);
            }
        };
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(Constant.INTENT_ACTION_UPDATE_SEARCHED_WORDS);
        getActivity().registerReceiver(mBroadcastReceiver, intentFilter);
    }
}
