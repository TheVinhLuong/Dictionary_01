package com.vinh.dictionary_1.utis;

/**
 * Created by VinhTL on 26/10/2017.
 */

public class Constant {
    public static final String END_POINT_URL = "https://translate.googleapis.com";

    public static final String PREF_DICT_DB_COPIED = "PREF_DICT_DB_COPIED";
    public static final String PREF_CHOSEN_DICT_TYPE = "PREF_CHOSEN_DICT_TYPE";
    public static final String PREF_USER_LAST_TIME_USAGE = "PREF_USER_LAST_TIME_USAGE";

    public static final String INTENT_ACTION_CHANGE_DICT =
            "com.vinh.dictionary_1.action.change_dict";
    public static final String INTENT_ACTION_UPDATE_SEARCHED_WORDS =
            "com.vinh.dictionary_1.action.update_searched_word";

    public static final String ARGUMENT_WORD = "ARGUMENT_WORD";

    public static final String REGEX_WORD_PHONETIC = "(\\/[^></�]+\\/)|(\\[[^></�]+\\])";

    public static final int DB_QUERY_MAX_RESULT_COUNT = 30;

    public static final int DRAWER_HOME_OPEN_CONTENT_DESC_RES = 1;
    public static final int DRAWER_HOME_CLOSE_CONTENT_DESC_RES = 0;

    public static final String FRAGMENT_TAG_WORD_DETAIL = "FRAGMENT_TAG_WORD_DETAIL";
    public static final String FRAGMENT_TAG_WORD_SEARCH_LIST = "FRAGMENT_TAG_WORD_SEARCH_LIST";
}

