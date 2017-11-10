package com.vinh.dictionary_1.data.model;

import android.content.Context;
import android.speech.tts.TextToSpeech;
import java.util.Locale;

/**
 * Created by VinhTL on 05/11/2017.
 */

public class WordSpeaker {
    private static WordSpeaker sWordSpeaker;
    
    private TextToSpeech mUKTextToSpeech, mUSTextToSpeech;

    public WordSpeaker(Context context) {
        mUKTextToSpeech = new TextToSpeech(context, status -> {
            if (status != TextToSpeech.ERROR) {
                mUKTextToSpeech.setLanguage(Locale.UK);
            }
        });
        mUSTextToSpeech = new TextToSpeech(context, status -> {
            if (status != TextToSpeech.ERROR) {
                mUSTextToSpeech.setLanguage(Locale.US);
            }
        });
    }
    
    public static WordSpeaker getInstance() {
        return sWordSpeaker;
    }

    public static void init(Context context) {
        sWordSpeaker = new WordSpeaker(context);
    }

    public void speakUK(String word) {
        mUKTextToSpeech.speak(word, TextToSpeech.QUEUE_FLUSH, null);
    }

    public void speakUS(String word) {
        mUSTextToSpeech.speak(word, TextToSpeech.QUEUE_FLUSH, null);
    }
}
