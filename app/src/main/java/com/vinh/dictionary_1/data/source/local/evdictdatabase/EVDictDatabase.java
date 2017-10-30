package com.vinh.dictionary_1.data.source.local.evdictdatabase;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;
import com.vinh.dictionary_1.data.model.Word;
import com.vinh.dictionary_1.data.source.local.DictTypeConverter;

import static com.vinh.dictionary_1.data.source.local.evdictdatabase.EVDictDatabase.DATABASE_VERSION;

/**
 * Created by VinhTL on 27/10/2017.
 */
@Database(entities = Word.class, version = DATABASE_VERSION)
@TypeConverters({DictTypeConverter.class})
public abstract class EVDictDatabase extends RoomDatabase{
    private static EVDictDatabase sEVDictDatabase;
    static final int DATABASE_VERSION = 3;
    private static final String DATABASE_NAME = "ev";
    
    public abstract EVDictDAO evDictDAO();
    
    public static EVDictDatabase getInstance(Context context){
        if(sEVDictDatabase == null){
            sEVDictDatabase = Room.databaseBuilder(context, EVDictDatabase.class, DATABASE_NAME)
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return sEVDictDatabase;
    }
}
