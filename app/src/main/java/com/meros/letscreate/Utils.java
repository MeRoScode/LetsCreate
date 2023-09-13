package com.meros.letscreate;

import android.content.Context;
import android.nfc.Tag;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

public class Utils {


    public abstract static class LetFragment extends Fragment implements pageOnClicks{

        @Override
        public void Toast(Context c,String text) {
            Toast.makeText(c, text, Toast.LENGTH_SHORT).show();
        }
    }
    public abstract static class LetActivity extends AppCompatActivity {

    }

    public interface pageOnClicks{
        void Toast (Context c,String text);
    }

}

