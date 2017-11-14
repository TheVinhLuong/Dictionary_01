package com.vinh.dictionary_1.screen.bookmarkedwordlist.wordlist;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.vinh.dictionary_1.R;
import com.vinh.dictionary_1.data.model.Word;
import com.vinh.dictionary_1.data.source.BookmarkedWordRepository;
import com.vinh.dictionary_1.data.source.local.bookmarkerworddatabase.BookmarkedWordDatabase;
import com.vinh.dictionary_1.data.source.local.bookmarkerworddatabase.BookmarkedWordLocalDatasource;
import com.vinh.dictionary_1.databinding.FragmentBookmarkedWordListBinding;
import com.vinh.dictionary_1.screen.BaseFragment;
import com.vinh.dictionary_1.screen.bookmarkedwordlist.BookmarkedWordsActivity;
import java.util.ArrayList;
import java.util.List;

/**
 * WordList Screen.
 */
public class BookmarkedWordListFragment extends BaseFragment{
    private BookmarkedWordListContract.ViewModel mViewModel;
    
    public static BookmarkedWordListFragment newInstance() {
        return new BookmarkedWordListFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        List<Word> words = new ArrayList<>();
        BookmarkedWordListAdapter
                bookmarkedWordListAdapter = new BookmarkedWordListAdapter(getActivity(), words);
        BookmarkedWordRepository bookmarkedWordRepository = new BookmarkedWordRepository(
                BookmarkedWordLocalDatasource.getInstance(
                        BookmarkedWordDatabase.getInstance(getActivity()).bookmarkedWordDAO()));
        mViewModel = new BookmarkedWordListViewModel(bookmarkedWordListAdapter);
        BookmarkedWordListContract.Presenter presenter =
                new BookmarkedWordListPresenter(mViewModel, bookmarkedWordRepository);
        mViewModel.setPresenter(presenter);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        ((BookmarkedWordsActivity) getActivity()).onFragmentCreated();
        FragmentBookmarkedWordListBinding fragmentWordListBinding =
                DataBindingUtil.inflate(inflater, R.layout.fragment_bookmarked_word_list, container, false);
        fragmentWordListBinding.setViewModel((BookmarkedWordListViewModel) mViewModel);
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
        ((BookmarkedWordsActivity) getActivity()).onFragmentDetach();
    }
}
