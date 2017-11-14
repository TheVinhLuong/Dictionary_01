package com.vinh.dictionary_1.screen.home.homelist;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.vinh.dictionary_1.R;
import com.vinh.dictionary_1.data.source.DailyWordRepository;
import com.vinh.dictionary_1.data.source.local.dailyworddatabase.DailyWordDatabase;
import com.vinh.dictionary_1.data.source.local.dailyworddatabase.DailyWordLocalDatasource;
import com.vinh.dictionary_1.databinding.FragmentHomeListBinding;
import com.vinh.dictionary_1.screen.BaseFragment;

/**
 * HomeList Screen.
 */
public class HomeListFragment extends BaseFragment {

    private HomeListContract.ViewModel mViewModel;

    public static HomeListFragment newInstance() {
        return new HomeListFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        HomeListDailyWordAdapter homeListDailyWordAdapter =
                new HomeListDailyWordAdapter(getActivity());
        DailyWordRepository dailyWordRepository = new DailyWordRepository(
                DailyWordLocalDatasource.getInstance(
                        DailyWordDatabase.getInstance(getActivity()).dailyWordDAO()));
        mViewModel = new HomeListViewModel(homeListDailyWordAdapter);
        HomeListContract.Presenter presenter =
                new HomeListPresenter(mViewModel, dailyWordRepository);
        mViewModel.setPresenter(presenter);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {

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
}
