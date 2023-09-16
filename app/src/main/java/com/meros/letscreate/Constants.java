package com.meros.letscreate;

import android.content.Context;
import android.speech.tts.TextToSpeech;

import com.meros.letscreate.Fragments.Calender.MangeTackTable;

public class Constants {

    public static TextToSpeech textToSpeech;
    public static int REQ_CODE_SPEECH_INPUT = 1;
    public static MangeTackTable mangeTackTable;
    public static final String TACK_TB = "tack_tb";

    public static MangeTackTable getMangeTackTable(Context c) {
        if (mangeTackTable == null) mangeTackTable = new MangeTackTable(c);
        return mangeTackTable;
    }
}
