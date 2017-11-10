package com.vinh.dictionary_1.screen.worddetail;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.vinh.dictionary_1.R;
import com.vinh.dictionary_1.data.source.DictRepository;
import com.vinh.dictionary_1.data.source.EVDictRepository;
import com.vinh.dictionary_1.data.source.VEDictRepository;
import com.vinh.dictionary_1.utis.Constant;
import com.vinh.dictionary_1.utis.rx.SchedulerProvider;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

/**
 * Listens to user actions from the UI ({@link WordDetailFragment}), retrieves the data and updates
 * the UI as required.
 */
final class WordDetailPresenter implements WordDetailContract.Presenter {
    private static final String TAG = WordDetailPresenter.class.getName();

    private final WordDetailContract.ViewModel mViewModel;
    private EVDictRepository mEVDictRepository;
    private VEDictRepository mVEDictRepository;
    private DictRepository mDictRepository;

    WordDetailPresenter(WordDetailContract.ViewModel viewModel,
            EVDictRepository evDictRepository, VEDictRepository veDictRepository) {
        mViewModel = viewModel;
        mEVDictRepository = evDictRepository;
        mVEDictRepository = veDictRepository;
        mDictRepository = new DictRepository(mEVDictRepository);
    }

    @Override
    public void onStart() {
    }

    @Override
    public void onStop() {
    }

    @Override
    public DictRepository getDictRepository() {
        return mDictRepository;
    }

    public static class WordDetailWebViewClient extends WebViewClient {
        private Context context;
        private DictRepository mDictRepository;

        WordDetailWebViewClient(Context context, DictRepository dictRepository) {
            this.context = context;
            this.mDictRepository = dictRepository;
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            WordDetailFragment wordDetailFragment = WordDetailFragment.newInstance();
            Bundle bundle = new Bundle();
            url = url.replace("file:///android_asset/", "");
            try {
                this.mDictRepository.getLocalWordDetail(URLDecoder.decode(url, "UTF-8"))
                        .subscribeOn(SchedulerProvider.getInstance().io())
                        .observeOn(SchedulerProvider.getInstance().ui())
                        .subscribe((word) -> {
                            if (word != null && word.getWord() != null) {
                                bundle.putSerializable(Constant.ARGUMENT_WORD, word);
                                wordDetailFragment.setArguments(bundle);
                                ((AppCompatActivity) context).getSupportFragmentManager()
                                        .beginTransaction()
                                        .replace(R.id.fragment_container, wordDetailFragment)
                                        .addToBackStack(null)
                                        .commit();
                            }
                        });
            } catch (UnsupportedEncodingException e) {
                return true;
            }
            return true;
        }
    }
}
