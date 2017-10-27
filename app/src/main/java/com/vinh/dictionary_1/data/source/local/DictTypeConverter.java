package com.vinh.dictionary_1.data.source.local;

import android.arch.persistence.room.TypeConverter;
import java.io.UnsupportedEncodingException;

/**
 * Created by VinhTL on 27/10/2017.
 */

public class DictTypeConverter {
    private static final String META = "<META http-equiv=\"Content-Type\" content=\"text/html;"
            + " charset=UTF-8\">\n<link rel=\"Stylesheet\" type=\"text/css\" href=\"dic.css\">";

    @TypeConverter
    public static String fromBlob(byte[] blob) throws UnsupportedEncodingException {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(META).append(new String(blob, "UTF-8"));
        return stringBuilder.toString();
    }

    @TypeConverter
    public static byte[] fromString(String string) throws UnsupportedEncodingException {
        return string.getBytes();
    }
}
