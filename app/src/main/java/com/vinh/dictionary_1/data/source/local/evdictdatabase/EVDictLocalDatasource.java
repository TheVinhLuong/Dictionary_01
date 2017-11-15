package com.vinh.dictionary_1.data.source.local.evdictdatabase;

import com.vinh.dictionary_1.data.model.Word;
import com.vinh.dictionary_1.data.source.EVDictDatasource;
import com.vinh.dictionary_1.utis.Constant;
import com.vinh.dictionary_1.utis.rx.SchedulerProvider;
import io.reactivex.Flowable;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by VinhTL on 27/10/2017.
 */

public class EVDictLocalDatasource implements EVDictDatasource.LocalDataSource {
    private static EVDictLocalDatasource sInstance;
    private EVDictDAO mEVDictDAO;
    private Pattern mPhoneticPattern;

    public static EVDictLocalDatasource getInstance(EVDictDAO EVDictDAO) {
        if (sInstance == null) {
            sInstance = new EVDictLocalDatasource(EVDictDAO);
        }
        return sInstance;
    }

    private EVDictLocalDatasource(EVDictDAO EVDictDAO) {
        mPhoneticPattern = Pattern.compile(Constant.REGEX_WORD_PHONETIC);
        mEVDictDAO = EVDictDAO;
    }

    @Override
    public Flowable<List<Word>> getLocalWordsDetail(String queryWord, int limitCount) {
        return mEVDictDAO.getEVWordsDetail(queryWord, limitCount)
                .observeOn(SchedulerProvider.getInstance().io())
                .map(words -> {
                    boolean derived;
                    for (Word word : words) {
                        derived = false;
                        if (word.getShortDescription().contains("@")) {
                            derived = true;
                            Word originalWord = (getLocalWordDetailSync(
                                    word.getShortDescription().replace("@", "").replace("-", " ")));
                            word.setEVDescription(originalWord.getEVDescription());
                            word.setShortDescription(originalWord.getShortDescription());
                        }
                        Matcher matcher = mPhoneticPattern.matcher(word.getEVDescription());
                        if (!derived && matcher.find()) {
                            word.setPronounce(matcher.group().replace("[", "/").replace("]", "/"));
                        } else {
                            word.setPronounce("");
                        }
                    }
                    return words;
                });
    }

    @Override
    public Flowable<Word> getLocalWordDetail(String queryWord) {
        return mEVDictDAO.getEVWordDetail(queryWord)
                .observeOn(SchedulerProvider.getInstance().io())
                .map(word -> {
                    boolean derived = false;
                    if (word.getShortDescription().contains("@")) {
                        derived = true;
                        Word originalWord = (getLocalWordDetailSync(
                                word.getShortDescription().replace("@", "").replace("-", " ")));
                        word.setEVDescription(originalWord.getEVDescription());
                        word.setShortDescription(originalWord.getShortDescription());
                    }
                    Matcher matcher = mPhoneticPattern.matcher(word.getEVDescription());
                    if (!derived && matcher.find()) {
                        word.setPronounce(matcher.group().replace("[", "/").replace("]", "/"));
                    } else {
                        word.setPronounce("");
                    }
                    return word;
                });
    }

    @Override
    public Word getLocalWordDetailSync(String queryWord) {
        return mEVDictDAO.getEVWordDetailSync(queryWord);
    }

    @Override
    public Flowable<Word> getRandomWord() {
        return mEVDictDAO.getEVRandomWord()
                .observeOn(SchedulerProvider.getInstance().io())
                .map(word -> {
                    boolean derived = false;
                    if (word.getShortDescription().contains("@")) {
                        derived = true;
                        Word originalWord = (getLocalWordDetailSync(
                                word.getShortDescription().replace("@", "").replace("-", " ")));
                        word.setEVDescription(originalWord.getEVDescription());
                        word.setShortDescription(originalWord.getShortDescription());
                    }
                    Matcher matcher = mPhoneticPattern.matcher(word.getEVDescription());
                    if (!derived && matcher.find()) {
                        word.setPronounce(matcher.group().replace("[", "/").replace("]", "/"));
                    } else {
                        word.setPronounce("");
                    }
                    return word;
                });
    }
}
