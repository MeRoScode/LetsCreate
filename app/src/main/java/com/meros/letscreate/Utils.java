package com.meros.letscreate;

import static android.app.Activity.RESULT_OK;
import static com.meros.letscreate.Constants.REQ_CODE_SPEECH_INPUT;
import static com.meros.letscreate.Constants.textToSpeech;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.speech.RecognizerIntent;
import android.speech.tts.TextToSpeech;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.Locale;

public class Utils {


    public abstract static class LetFragment extends Fragment implements pageOnClicks {

        @Override
        public void Toast(Context c, String text) {
            Toast.makeText(c, text, Toast.LENGTH_SHORT).show();
        }


        @Override
        public void setProgress(boolean mustShow) {
            setProgressbar((FrameLayout) getView(), getActivity(), mustShow);
        }
    }

    public abstract static class LetActivity extends AppCompatActivity implements pageOnClicks {
        @Override
        public void Toast(Context c, String text) {
            Toast.makeText(c, text, Toast.LENGTH_SHORT).show();
        }

        @Override
        public void setProgress(boolean mustShow) {
            setProgressbar((FrameLayout) getWindow().getDecorView().findViewById(android.R.id.content), getApplicationContext(), mustShow);
        }

    }

    public interface pageOnClicks {
        void Toast(Context c, String text);

        void setProgress(boolean mustShow);
    }

    public static void setProgressbar(FrameLayout root, Context c, boolean mustShow) {
        if (root != null) {
            if (c != null) {
                ConstraintLayout loadingView = root.findViewById(R.id.progressView);
                if (loadingView == null && mustShow) {
                    loadingView = (ConstraintLayout) LayoutInflater.from(c).inflate(R.layout.progress_view, root, false);
                    root.addView(loadingView);
                }
                if (loadingView != null) {
                    if (mustShow)
                        loadingView.setVisibility(View.VISIBLE);
                    else {
                        loadingView.setVisibility(View.GONE);
                    }
                }
            }
        }
    }

    public static void speak(Context c, String text) {
        if (textToSpeech == null) {
            textToSpeech = new TextToSpeech(c,
                    status -> {
                        if (status != TextToSpeech.ERROR) {
                            textToSpeech.setLanguage(Locale.US);
                        }
                    });
        }
        textToSpeech.speak(text, TextToSpeech.QUEUE_FLUSH, null);
    }

    public static void listen(Activity activity, String question) {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, question);
        try {
            activity.startActivityForResult(intent, REQ_CODE_SPEECH_INPUT);
        } catch (ActivityNotFoundException a) {
        }
    }


    public static String handleListenResult(Intent data) {
        String result = "";
        if (data != null) {
            ArrayList<String> resultList = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
            result = resultList.get(0);
        }
        return result;
    }

}

