package com.vinh.dictionary_1.utis;

import android.app.ProgressDialog;
import android.content.Context;
import android.view.WindowManager;

/**
 * Created by VinhTL on 26/10/2017.
 */

public class DialogManager {
    
    public static ProgressDialog getSpinningDialog(Context context, String message){
        ProgressDialog progressDialog =  new ProgressDialog(context);
        progressDialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setMessage(message);
        progressDialog.setCancelable(false);
        progressDialog.setCanceledOnTouchOutside(false);
        return progressDialog;
    }
}
