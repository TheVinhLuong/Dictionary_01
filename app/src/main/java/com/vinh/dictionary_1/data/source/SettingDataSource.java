package com.vinh.dictionary_1.data.source;

import com.vinh.dictionary_1.data.model.annotation.DictTypeName;
import io.reactivex.Observable;

/**
 * Created by VinhTL on 03/11/2017.
 */

public interface SettingDataSource {
    Observable<String> getCurrentDictType();
    void setCurrentDictType(@DictTypeName String dictType);
    Observable<Boolean> isDatabaseCopied();
    void setDatabaseState(boolean isCopied);
}
