package com.vinh.dictionary_1.screen.translate;

import com.vinh.dictionary_1.data.source.TranslateRepository;
import com.vinh.dictionary_1.data.source.remote.api.error.BaseException;
import com.vinh.dictionary_1.data.source.remote.api.error.RequestError;
import com.vinh.dictionary_1.utis.rx.SchedulerProvider;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * Listens to user actions from the UI ({@link TranslateActivity}), retrieves the data and updates
 * the UI as required.
 */
final class TranslatePresenter implements TranslateContract.Presenter {
    private static final String TAG = TranslatePresenter.class.getName();

    private final TranslateContract.ViewModel mViewModel;
    private TranslateRepository mTranslateRepository;

    public TranslatePresenter(TranslateContract.ViewModel viewModel,
            TranslateRepository translateRepository) {
        mViewModel = viewModel;
        mTranslateRepository = translateRepository;
    }

    @Override
    public void onStart() {
    }

    @Override
    public void onStop() {
    }

    @Override
    public void onTranslateButtonTouch(String sourceText, String sourceLangCode,
            String targetLangCode) {
        mViewModel.showLoadingDialog();
        try {
            mTranslateRepository.translate(targetLangCode, sourceLangCode, URLEncoder.encode(sourceText, "utf-8"))
                    .subscribeOn(SchedulerProvider.getInstance().io())
                    .observeOn(SchedulerProvider.getInstance().ui())
                    .subscribe(s -> {
                        mViewModel.setTranslatedText(s);
                        mViewModel.dismissLoadingDialog();
                    }, new RequestError() {
                        @Override
                        public void onRequestError(BaseException error) {
                            mViewModel.dismissLoadingDialog();
                            mViewModel.showErrorToast();
                        }
                    });
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}
