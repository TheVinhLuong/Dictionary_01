package com.vinh.dictionary_1.screen.splash;

import android.content.Context;
import com.vinh.dictionary_1.MainApplication;
import com.vinh.dictionary_1.R;
import com.vinh.dictionary_1.data.model.DailyWord;
import com.vinh.dictionary_1.data.model.Word;
import com.vinh.dictionary_1.data.source.DailyWordRepository;
import com.vinh.dictionary_1.data.source.EVDictRepository;
import com.vinh.dictionary_1.data.source.SettingRepository;
import com.vinh.dictionary_1.data.source.UserInfoRepository;
import com.vinh.dictionary_1.data.source.local.sharedpref.SettingLocalDataSource;
import com.vinh.dictionary_1.data.source.local.sharedpref.UserInfoLocalDatasource;
import com.vinh.dictionary_1.utis.rx.SchedulerProvider;
import io.reactivex.Observable;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import static com.vinh.dictionary_1.data.model.annotation.DictTypeName.DB_NAME_ENGLISH_VIETNAMESE;

/**
 * Listens to user actions from the UI ({@link SplashActivity}), retrieves the data and updates
 * the UI as required.
 */
final class SplashPresenter implements SplashContract.Presenter {
    private static final String TAG = SplashPresenter.class.getSimpleName();

    private SplashContract.ViewModel mViewModel;
    private SettingRepository mSettingRepository;
    private UserInfoRepository mUserInfoRepository;
    private DailyWordRepository mDailyWordRepository;
    private EVDictRepository mEVDictRepository;
    private DateFormat mDateFormat;

    SplashPresenter(SplashContract.ViewModel viewModel, EVDictRepository evDictRepository,
            DailyWordRepository dailyWordRepository) {
        mViewModel = viewModel;
        mEVDictRepository = evDictRepository;
        mDailyWordRepository = dailyWordRepository;
        mSettingRepository = new SettingRepository(SettingLocalDataSource.getInstance());
        mUserInfoRepository = new UserInfoRepository(UserInfoLocalDatasource.getInstance());
        mDateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.US);
        assetHandler();
    }

    @Override
    public void onStart() {
    }

    @Override
    public void onStop() {
    }

    private void assetHandler() {
        mViewModel.showLoadingDialog(
                MainApplication.getInstance().getString(R.string.msg_loading_please_wait));
        if (mSettingRepository.getCurrentDictType().blockingSingle().equals("")) {
            mSettingRepository.setCurrentDictType(DB_NAME_ENGLISH_VIETNAMESE);
        }
        if (mSettingRepository.isDatabaseCopied().blockingSingle()) {
            dailyWordHandler();
        } else {
            Observable observable = unzipDatabase(MainApplication.getInstance()).subscribeOn(
                    SchedulerProvider.getInstance().io())
                    .observeOn(SchedulerProvider.getInstance().ui())
                    .doOnError(throwable -> {
                    })
                    .doOnComplete(() -> {
                        mSettingRepository.setDatabaseState(true);
                        dailyWordHandler();
                    });
            observable.subscribe();
        }
    }

    private Observable<Void> unzipDatabase(Context context) {
        return Observable.create(emitter -> {
            try {
                String path = "/data/data/" + context.getPackageName() + "/";
                String dbZipName = "databases.zip";
                byte[] buffer;
                int bufferLength = 0;
                File f = new File(path);
                f.mkdirs();
                InputStream mInput = context.getAssets().open(dbZipName);
                ZipInputStream zipIn = new ZipInputStream(new BufferedInputStream(mInput));
                ZipEntry entry = zipIn.getNextEntry();
                while (entry != null) {
                    String filePath = path + entry.getName();
                    if (!entry.isDirectory()) {
                        FileOutputStream fos = new FileOutputStream(filePath, false);
                        BufferedOutputStream bos = new BufferedOutputStream(fos);
                        buffer = new byte[4096];
                        while ((bufferLength = zipIn.read(buffer, 0, buffer.length)) != -1) {
                            bos.write(buffer, 0, bufferLength);
                        }
                        bos.close();
                        fos.close();
                    } else {
                        File dir = new File(filePath);
                        dir.mkdir();
                    }
                    entry = zipIn.getNextEntry();
                }
                emitter.onComplete();
            } catch (FileNotFoundException e1) {
                emitter.onError(e1);
            } catch (Exception e) {
                emitter.onError(e);
            }
        });
    }

    private void dailyWordHandler() {
        Date currentDate = new Date();
        if (mUserInfoRepository.getLastTimeUsageInfo() != null
                && !mUserInfoRepository.getLastTimeUsageInfo().equals("")) {
            Date lastDate = new Date(mUserInfoRepository.getLastTimeUsageInfo());
            long elapsedDays = TimeUnit.DAYS.convert(currentDate.getTime() - lastDate.getTime(),
                    TimeUnit.MILLISECONDS);
            if (elapsedDays > 0 && elapsedDays < 365) {
                generateDailyWord(1, elapsedDays);
            } else if (elapsedDays > 365) {
                generateDailyWord(1, 1);
            } else {
                mViewModel.dismissLoadingDialog();
                mViewModel.switchToHomeActivity();
            }
        } else {
            generateDailyWord(1, 1);
        }
    }

    private void generateDailyWord(long startNum, long endNum) {
        long[] start = new long[1];
        start[0] = startNum;
        Observable.create(e -> {
            while (start[0] <= endNum) {
                Word word = mEVDictRepository.getRandomWord();
                DailyWord dailyWord = mDailyWordRepository.getWord(word.getWord());
                if (dailyWord == null) {
                    dailyWord = new DailyWord(word);
                    Calendar calendar = Calendar.getInstance();
                    calendar.add(Calendar.DATE, (int) (start[0] - endNum));
                    dailyWord.setDate(mDateFormat.format(calendar.getTime()));
                    mDailyWordRepository.insertDailyWord(dailyWord);
                    ++start[0];
                }
            }
            mUserInfoRepository.setLastTimeUsageInfo(mDateFormat.format(new Date()));
            e.onComplete();
        })
                .subscribeOn(SchedulerProvider.getInstance().io())
                .observeOn(SchedulerProvider.getInstance().ui())
                .doOnComplete(() -> {
                    mViewModel.dismissLoadingDialog();
                    mViewModel.switchToHomeActivity();
                })
                .subscribe();
    }
}
