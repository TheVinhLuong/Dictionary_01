package com.vinh.dictionary_1.data.source.local.sharedpref;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by VinhTL on 26/10/2017.
 */

public class SharedPrefsImplement implements SharedPrefsApi {
    private static final String PREFS_NAME = "SmartDictionarySharedPreferences";
    private static SharedPreferences sSharedPreferences;
    private static SharedPrefsImplement sSharedPrefsImplement;

    private SharedPrefsImplement(Context context) {
        sSharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
    }
    
    public static SharedPrefsImplement getInstance(){
        return sSharedPrefsImplement;
    }
    
    public static void init(Context context){
        sSharedPrefsImplement = new SharedPrefsImplement(context);
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> T get(String key, Class<T> clazz) {
        if (clazz == String.class) {
            return (T) sSharedPreferences.getString(key, "");
        } else if (clazz == Boolean.class) {
            return (T) Boolean.valueOf(sSharedPreferences.getBoolean(key, false));
        } else if (clazz == Float.class) {
            return (T) Float.valueOf(sSharedPreferences.getFloat(key, 0));
        } else if (clazz == Integer.class) {
            return (T) Integer.valueOf(sSharedPreferences.getInt(key, 0));
        } else if (clazz == Long.class) {
            return (T) Long.valueOf(sSharedPreferences.getLong(key, 0));
        }
        return null;
    }

    @Override
    public <T> void put(String key, T data) {
        SharedPreferences.Editor editor = sSharedPreferences.edit();
        if (data instanceof String) {
            editor.putString(key, (String) data);
        } else if (data instanceof Boolean) {
            editor.putBoolean(key, (Boolean) data);
        } else if (data instanceof Float) {
            editor.putFloat(key, (Float) data);
        } else if (data instanceof Integer) {
            editor.putInt(key, (Integer) data);
        } else if (data instanceof Long) {
            editor.putLong(key, (Long) data);
        }
        editor.apply();
    }
}
