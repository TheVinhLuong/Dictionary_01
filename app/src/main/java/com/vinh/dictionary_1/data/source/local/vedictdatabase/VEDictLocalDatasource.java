package com.vinh.dictionary_1.data.source.local.vedictdatabase;

import com.vinh.dictionary_1.data.model.Word;
import com.vinh.dictionary_1.data.source.VEDictDatasource;
import com.vinh.dictionary_1.utis.rx.SchedulerProvider;
import io.reactivex.Flowable;
import java.util.List;

/**
 * Created by VinhTL on 27/10/2017.
 */

public class VEDictLocalDatasource implements VEDictDatasource.LocalDataSource {
    private static VEDictLocalDatasource sInstance;
    private VEDictDAO mVEDictDAO;

    public static VEDictLocalDatasource getInstance(VEDictDAO VEDictDAO) {
        if (sInstance == null) {
            sInstance = new VEDictLocalDatasource(VEDictDAO);
        }
        return sInstance;
    }

    private VEDictLocalDatasource(VEDictDAO VEDictDAO) {
        mVEDictDAO = VEDictDAO;
    }

    @Override
    public Flowable<List<Word>> getLocalWordsDetail(String queryWord, int limitCount) {
        return mVEDictDAO.getVEWordsDetail(queryWord, limitCount)
                .observeOn(SchedulerProvider.getInstance().io())
                .map(words -> {
                    for (Word word : words) {
                        if (word.getShortDescription().contains("@")) {
                            Word originalWord = (getLocalWordDetailSync(
                                    word.getShortDescription().replace("@", "").replace("-", " ")));
                            if (originalWord != null) {
                                word.setVEDescription(originalWord.getVEDescription());
                                word.setShortDescription(originalWord.getShortDescription());
                            }
                        }
                    }
                    return words;
                });
    }

    @Override
    public Flowable<Word> getLocalWordDetail(String queryWord) {
        return mVEDictDAO.getVEWordDetail(queryWord)
                .observeOn(SchedulerProvider.getInstance().io())
                .map(word -> {
                    if (word.getShortDescription().contains("@")) {
                        Word originalWord = (getLocalWordDetailSync(
                                word.getShortDescription().replace("@", "").replace("-", " ")));
                        if (originalWord != null) {
                            word.setVEDescription(originalWord.getVEDescription());
                            word.setShortDescription(originalWord.getShortDescription());
                        }
                    }
                    return word;
                });
    }

    @Override
    public Word getLocalWordDetailSync(String queryWord) {
        Word word = mVEDictDAO.getVEWordDetailSync(queryWord);
        if (word == null) {
            return word;
        }
        if (word.getShortDescription().contains("@")) {
            Word originalWord = (getLocalWordDetailSync(
                    word.getShortDescription().replace("@", "").replace("-", " ")));
            if (originalWord != null) {
                word.setVEDescription(originalWord.getVEDescription());
                word.setShortDescription(originalWord.getShortDescription());
            }
        }
        return word;
    }
}

