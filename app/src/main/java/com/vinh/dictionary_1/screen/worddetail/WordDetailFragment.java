package com.vinh.dictionary_1.screen.worddetail;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.vinh.dictionary_1.R;
import com.vinh.dictionary_1.data.model.Word;
import com.vinh.dictionary_1.data.source.BookmarkedWordRepository;
import com.vinh.dictionary_1.data.source.EVDictRepository;
import com.vinh.dictionary_1.data.source.VEDictRepository;
import com.vinh.dictionary_1.data.source.local.bookmarkerworddatabase.BookmarkedWordDatabase;
import com.vinh.dictionary_1.data.source.local.bookmarkerworddatabase.BookmarkedWordLocalDatasource;
import com.vinh.dictionary_1.data.source.local.evdictdatabase.EVDictDatabase;
import com.vinh.dictionary_1.data.source.local.evdictdatabase.EVDictLocalDatasource;
import com.vinh.dictionary_1.data.source.local.vedictdatabase.VEDictDatabase;
import com.vinh.dictionary_1.data.source.local.vedictdatabase.VEDictLocalDatasource;
import com.vinh.dictionary_1.databinding.FragmentWordDetailBinding;
import com.vinh.dictionary_1.screen.BaseFragment;
import com.vinh.dictionary_1.screen.home.HomeActivity;
import com.vinh.dictionary_1.utis.Constant;

/**
 * WordDetail Screen.
 */
public class WordDetailFragment extends BaseFragment {

    private WordDetailContract.ViewModel mViewModel;

    public static WordDetailFragment newInstance() {
        return new WordDetailFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        mViewModel = new WordDetailViewModel(getActivity());
        mViewModel.setWord((Word) bundle.getSerializable(Constant.ARGUMENT_WORD));
        EVDictRepository evDictRepository = new EVDictRepository(EVDictLocalDatasource.getInstance(
                EVDictDatabase.getInstance(getActivity()).evDictDAO()));
        VEDictRepository veDictRepository = new VEDictRepository(VEDictLocalDatasource.getInstance(
                VEDictDatabase.getInstance(getActivity()).veDictDAO()));
        BookmarkedWordRepository bookmarkedWordRepository = new BookmarkedWordRepository(
                BookmarkedWordLocalDatasource.getInstance(
                        BookmarkedWordDatabase.getInstance(getActivity()).bookmarkedWordDAO()));
        WordDetailContract.Presenter presenter =
                new WordDetailPresenter(mViewModel, evDictRepository, veDictRepository,
                        bookmarkedWordRepository);
        mViewModel.setPresenter(presenter);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        ((HomeActivity) getActivity()).onFragmentCreated();
        FragmentWordDetailBinding binding =
                DataBindingUtil.inflate(inflater, R.layout.fragment_word_detail, container, false);
        binding.setViewModel((WordDetailViewModel) mViewModel);
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
    public void onDetach() {
        super.onDetach();
        ((HomeActivity) getActivity()).onFragmentDetach(this);
    }
}
