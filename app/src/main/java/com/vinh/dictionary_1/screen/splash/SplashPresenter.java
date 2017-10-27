package com.vinh.dictionary_1.screen.splash;

import android.content.Context;
import android.util.Log;
import com.vinh.dictionary_1.MainApplication;
import com.vinh.dictionary_1.R;
import com.vinh.dictionary_1.data.source.local.sharedpref.SharedPrefsImplement;
import com.vinh.dictionary_1.utis.Constant;
import com.vinh.dictionary_1.utis.rx.SchedulerProvider;
import io.reactivex.Observable;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/**
 * Listens to user actions from the UI ({@link SplashActivity}), retrieves the data and updates
 * the UI as required.
 */
final class SplashPresenter implements SplashContract.Presenter {
    private static final String TAG = SplashPresenter.class.getSimpleName();

    private final SplashContract.ViewModel mViewModel;

    public SplashPresenter(SplashContract.ViewModel viewModel) {
        mViewModel = viewModel;
        assetHandler();
    }

    @Override
    public void onStart() {
    }

    @Override
    public void onStop() {
    }

    private void assetHandler() {
        SharedPrefsImplement sharedPrefs = SharedPrefsImplement.getInstance();
        if (sharedPrefs.get(Constant.PREF_DICT_DB_COPIED, Boolean.class)) {
            Log.d(TAG, "DB COPIED");
        } else {
            mViewModel.showLoadingDialog(MainApplication.getInstance()
                    .getString(R.string.msg_loading_please_wait));
            Log.d(TAG, "DB NOT COPIED");
            Observable observable = unzipDatabase(MainApplication.getInstance()).subscribeOn(
                    SchedulerProvider.getInstance().io())
                    .observeOn(SchedulerProvider.getInstance().ui())
                    .doOnError(throwable -> Log.e(TAG, throwable.getMessage()))
                    .doOnComplete(() -> {
                        Log.d(TAG, "onComplete, thread: " + Thread.currentThread().getName());
                        sharedPrefs.put(Constant.PREF_DICT_DB_COPIED, true);
                        mViewModel.dismissLoadingDialog();
                    });
            observable.subscribe();
        }
    }

    private Observable<Void> unzipDatabase(Context context) {
        return Observable.create(emitter -> {
            try {
                Log.d(TAG, "begin unzip, thread: " + Thread.currentThread().getName());
                String path = "/data/data/" + context.getPackageName() + "/";
                String dbZipName = "dicts.zip";
                byte[] buffer;
                int bufferLength = 0;
                File f = new File(path);
                f.mkdirs();
                InputStream mInput = context.getAssets().open(dbZipName);
                ZipInputStream zipIn = new ZipInputStream(new BufferedInputStream(mInput));
                ZipEntry entry = zipIn.getNextEntry();
                while (entry != null) {
                    String filePath = path + entry.getName();
                    Log.d(TAG, filePath);
                    if (!entry.isDirectory()) {
                        FileOutputStream fos = new FileOutputStream(filePath, false);
                        BufferedOutputStream bos = new BufferedOutputStream(fos);
                        buffer = new byte[4096];
                        while ((bufferLength = zipIn.read(buffer, 0, buffer.length)) != -1) {
                            bos.write(buffer, 0, bufferLength);
                        }
                        bos.close();
                        fos.close();
                    }else {
                        File dir = new File(filePath);
                        dir.mkdir();
                    }
                    entry = zipIn.getNextEntry();
                }
                Log.d(TAG, "unzip success");
                emitter.onComplete();
            } catch (FileNotFoundException e1) {
                emitter.onError(e1);
                Log.e(TAG, e1.getMessage());
            } catch (Exception e) {
                emitter.onError(e);
                Log.e(TAG, e.getMessage());
            }
        });
    }
}
