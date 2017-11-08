package com.vinh.dictionary_1.data.source.local.bookmarkerworddatabase;

import com.vinh.dictionary_1.data.model.BookmarkedWord;
import com.vinh.dictionary_1.data.source.BookmarkedWordDatasource;
import io.reactivex.Flowable;
import java.util.List;

/**
 * Created by VinhTL on 05/11/2017.
 */

public class BookmarkedWordLocalDatasource implements BookmarkedWordDatasource {
    private static BookmarkedWordLocalDatasource sInstance;
    private BookmarkedWordDAO mBookmarkedWordDAO;

    public static BookmarkedWordLocalDatasource getInstance(BookmarkedWordDAO bookmarkedWordDAO) {
        if (sInstance == null) {
            sInstance = new BookmarkedWordLocalDatasource(bookmarkedWordDAO);
        }
        return sInstance;
    }

    public BookmarkedWordLocalDatasource(BookmarkedWordDAO bookmarkedWordDAO) {
        mBookmarkedWordDAO = bookmarkedWordDAO;
    }

    @Override
    public BookmarkedWord getBookmarkedWordByWord(String queryWord) {
        return mBookmarkedWordDAO.getBookmarkedWordByWord(queryWord);
    }

    @Override
    public Flowable<List<BookmarkedWord>> getAllBookmarkedWord() {
        return mBookmarkedWordDAO.getAllBookmarkedWord();
    }

    @Override
    public void deleteBookmarkedWord(BookmarkedWord bookmarkedWord) {
        mBookmarkedWordDAO.deleteBookmarkedWord(bookmarkedWord);
    }

    @Override
    public void insertBookmarkedWord(BookmarkedWord bookmarkedWord) {
        mBookmarkedWordDAO.insertBookmarkedWord(bookmarkedWord);
    }

    @Override
    public void updateWord(BookmarkedWord... bookmarkedWord) {
        mBookmarkedWordDAO.updateWord(bookmarkedWord);
    }
}
