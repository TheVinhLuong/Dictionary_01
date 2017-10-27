package com.vinh.dictionary_1.data.source.local.vedictdatabase;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;
import com.vinh.dictionary_1.data.model.Word;
import com.vinh.dictionary_1.data.source.local.DictTypeConverter;

import static com.vinh.dictionary_1.data.source.local.vedictdatabase.VEDictDatabase
        .DATABASE_VERSION;

/**
 * Created by VinhTL on 27/10/2017.
 */
@Database(entities = Word.class, version = DATABASE_VERSION)
@TypeConverters({DictTypeConverter.class})
public abstract class VEDictDatabase extends RoomDatabase{
    private static VEDictDatabase sVEDictDatabase;
    static final int DATABASE_VERSION = 3;
    private static final String DATABASE_NAME = "ve";
    
    public abstract VEDictDAO veDictDAO();
    
    public static VEDictDatabase getInstance(Context context){
        if(sVEDictDatabase == null){
            sVEDictDatabase = Room.databaseBuilder(context, VEDictDatabase.class, DATABASE_NAME)
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return sVEDictDatabase;
    }
}
