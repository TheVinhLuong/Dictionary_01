package com.vinh.dictionary_1.screen.dailywordlist.wordlist;

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
import com.vinh.dictionary_1.databinding.FragmentDailyWordListBinding;
import com.vinh.dictionary_1.screen.BaseActivity;
import com.vinh.dictionary_1.screen.BaseFragment;

/**
 * WordList Screen.
 */
public class DailyWordListFragment extends BaseFragment {
    private DailyWordListContract.ViewModel mViewModel;

    public static DailyWordListFragment newInstance() {
        return new DailyWordListFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DailyWordListAdapter dailyWordListAdapter = new DailyWordListAdapter(getActivity());
        SearchedWordRepository searchedWordRepository = new SearchedWordRepository(
                SearchedWordLocalDatasource.getInstance(
                        SearchedWordDatabase.getInstance(getActivity()).searchedWordDAO()));
        DailyWordRepository dailyWordRepository = new DailyWordRepository(
                DailyWordLocalDatasource.getInstance(
                        DailyWordDatabase.getInstance(getActivity()).dailyWordDAO()));
        mViewModel = new DailyWordListViewModel(dailyWordListAdapter);

        DailyWordListContract.Presenter presenter =
                new DailyWordListPresenter(mViewModel, searchedWordRepository, dailyWordRepository);
        mViewModel.setPresenter(presenter);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        ((BaseActivity) getActivity()).onFragmentCreated();
        FragmentDailyWordListBinding fragmentWordListBinding =
                DataBindingUtil.inflate(inflater, R.layout.fragment_daily_word_list, container,
                        false);
        fragmentWordListBinding.setViewModel((DailyWordListViewModel) mViewModel);
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
    }

    @Override
    public void onDetach() {
        super.onDetach();
        ((BaseActivity) getActivity()).onFragmentDetach();
    }
}
