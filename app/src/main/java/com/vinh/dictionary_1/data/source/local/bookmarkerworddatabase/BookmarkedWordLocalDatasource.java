package com.vinh.dictionary_1.data.source.local.bookmarkerworddatabase;

import com.vinh.dictionary_1.data.model.Word;
import com.vinh.dictionary_1.data.source.BookmarkedWordDatasource;
import com.vinh.dictionary_1.utis.Constant;
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
    public Word getBookmarkedWordByWord(String queryWord) {
        return mBookmarkedWordDAO.getBookmarkedWordByWord(queryWord);
    }

    @Override
    public Flowable<List<Word>> getBookmarkedWord(int page) {
        return mBookmarkedWordDAO.getAllBookmarkedWord(
                page * Constant.NUM_OF_BOOKMARKED_WORD_PER_PAGE);
    }

    @Override
    public void deleteBookmarkedWord(Word bookmarkedWord) {
        mBookmarkedWordDAO.deleteBookmarkedWord(bookmarkedWord);
    }

    @Override
    public void insertBookmarkedWord(Word bookmarkedWord) {
        mBookmarkedWordDAO.insertBookmarkedWord(bookmarkedWord);
    }

    @Override
    public void updateWord(Word... bookmarkedWord) {
        mBookmarkedWordDAO.updateWord(bookmarkedWord);
    }
}
