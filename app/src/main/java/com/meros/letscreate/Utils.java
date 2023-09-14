package com.meros.letscreate;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

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

}

