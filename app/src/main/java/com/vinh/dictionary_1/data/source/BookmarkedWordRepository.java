package com.vinh.dictionary_1.data.source;

import com.vinh.dictionary_1.data.model.Word;
import io.reactivex.Flowable;
import java.util.List;

/**
 * Created by VinhTL on 05/11/2017.
 */

public class BookmarkedWordRepository implements BookmarkedWordDatasource{
    private static BookmarkedWordRepository sInstance;
    private BookmarkedWordDatasource mLocalDatasource;

    public static BookmarkedWordRepository getInstance(BookmarkedWordDatasource localDatasource) {
        if (sInstance == null) {
            sInstance = new BookmarkedWordRepository(localDatasource);
        }
        return sInstance;
    }

    public BookmarkedWordRepository(BookmarkedWordDatasource localDatasource) {
        mLocalDatasource = localDatasource;
    }

    @Override
    public Word getBookmarkedWordByWord(String queryWord) {
        return mLocalDatasource.getBookmarkedWordByWord(queryWord);
    }

    @Override
    public Flowable<List<Word>> getBookmarkedWord(int page) {
        return mLocalDatasource.getBookmarkedWord(page);
    }

    @Override
    public void deleteBookmarkedWord(Word bookmarkedWord) {
        mLocalDatasource.deleteBookmarkedWord(bookmarkedWord);
    }

    @Override
    public void insertBookmarkedWord(Word bookmarkedWord) {
        mLocalDatasource.insertBookmarkedWord(bookmarkedWord);
    }

    @Override
    public void updateWord(Word... bookmarkedWord) {
        mLocalDatasource.updateWord(bookmarkedWord);
    }
}
