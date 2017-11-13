package com.vinh.dictionary_1.screen.translate;

import android.app.ProgressDialog;
import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;
import com.google.gson.Gson;
import com.vinh.dictionary_1.BR;
import com.vinh.dictionary_1.R;
import com.vinh.dictionary_1.databinding.ActivityTranslateBinding;
import com.vinh.dictionary_1.utis.Constant;
import com.vinh.dictionary_1.utis.DialogManager;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/**
 * Exposes the data to be used in the Translate screen.
 */
@SuppressWarnings("unchecked")
public class TranslateViewModel extends BaseObservable implements TranslateContract.ViewModel {

    private TranslateContract.Presenter mPresenter;
    private Context mContext;
    private HashMap<String, String> mMapLangList;
    private ProgressDialog mLoadingDialog;
    private List<String> mLangNaturalNameList;
    private String mTranslatedText;

    TranslateViewModel(Context context) {
        mContext = context;
        try {
            Gson gson = new Gson();
            InputStreamReader inputStreamReader = new InputStreamReader(
                    context.getAssets().open(Constant.FILE_JSON_LANG_LIST_NAME));
            mMapLangList = gson.fromJson(inputStreamReader, HashMap.class);
            inputStreamReader.close();
            mLangNaturalNameList = new ArrayList<>(mMapLangList.keySet());
            Collections.sort(mLangNaturalNameList, String.CASE_INSENSITIVE_ORDER);
            mLangNaturalNameList.add(0, context.getString(R.string.hint_choose_language));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onStart() {
        mPresenter.onStart();
    }

    @Override
    public void onStop() {
        mPresenter.onStop();
    }

    @Override
    public void setPresenter(TranslateContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void showLoadingDialog() {
        mLoadingDialog = DialogManager.getSpinningDialog(mContext,
                mContext.getString(R.string.msg_loading_please_wait));
        mLoadingDialog.show();
    }

    @Override
    public void dismissLoadingDialog() {
        if (mLoadingDialog != null && mLoadingDialog.isShowing()) {
            mLoadingDialog.dismiss();
        }
    }

    @Override
    public void setTranslatedText(String translatedText) {
        mTranslatedText = translatedText;
        notifyPropertyChanged(BR.translatedText);
    }

    @Override
    public void showErrorToast() {
        Toast.makeText(mContext, mContext.getString(R.string.msg_translate_network_error),
                Toast.LENGTH_LONG).show();
    }

    @Bindable
    public String getTranslatedText() {
        return mTranslatedText;
    }

    public List<String> getLangNaturalNameList() {
        return mLangNaturalNameList;
    }

    public void setLangNaturalNameList(List<String> langNaturalNameList) {
        mLangNaturalNameList = langNaturalNameList;
    }

    public void onSwapLanguageTouched() {
        ActivityTranslateBinding mBinding =
                ((TranslateActivity) mContext).getActivityTranslateBinding();
        int sourceLangItemPos = mBinding.spinnerSourceLang.getSelectedItemPosition();
        int targetLangItemPos = mBinding.spinnerTargetLang.getSelectedItemPosition();
        if (sourceLangItemPos == 0 || targetLangItemPos == 0) {
            return;
        }
        mBinding.spinnerSourceLang.setSelection(targetLangItemPos);
        mBinding.spinnerTargetLang.setSelection(sourceLangItemPos);
    }

    public void onTranslateButtonTouch() {
        ActivityTranslateBinding mBinding =
                ((TranslateActivity) mContext).getActivityTranslateBinding();
        int sourceLangItemPos = mBinding.spinnerSourceLang.getSelectedItemPosition();
        int targetLangItemPos = mBinding.spinnerTargetLang.getSelectedItemPosition();
        if (sourceLangItemPos == 0 || targetLangItemPos == 0) {
            return;
        }
        String sourceLangNaturalName = mBinding.spinnerSourceLang.getSelectedItem().toString();
        String targetLangNaturalName = mBinding.spinnerTargetLang.getSelectedItem().toString();
        mPresenter.onTranslateButtonTouch(mBinding.editTextInput.getText().toString(),
                mMapLangList.get(sourceLangNaturalName), mMapLangList.get(targetLangNaturalName));
    }
    
    public void onBackArrowTouched(){
        ((AppCompatActivity)mContext).finish();
    }
}
