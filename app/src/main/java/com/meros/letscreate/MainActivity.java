package com.meros.letscreate;

import static com.meros.letscreate.Constants.REQ_CODE_SPEECH_INPUT;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.bumptech.glide.util.Util;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.meros.letscreate.Fragments.Calender.CalenderFragment;
import com.meros.letscreate.Fragments.Development.DevelopmentFragment;
import com.meros.letscreate.Fragments.Habits.HabitsFragment;
import com.meros.letscreate.Fragments.Home.HomeFragment;
import com.meros.letscreate.Fragments.Innovation.InnovationFragment;
import com.meros.letscreate.databinding.ActivityMainBinding;
import com.meros.letscreate.Utils.LetActivity;

public class MainActivity extends LetActivity implements NavigationView.OnNavigationItemSelectedListener, BottomNavigationView.OnNavigationItemSelectedListener {


    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.navigationBar.setOnNavigationItemSelectedListener(this);

        binding.navigationBar.setSelectedItemId(R.id.home);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        changeBottomNavigationPosition(item.getItemId());
        return true;
    }


    public void changeBottomNavigationPosition(int id){
        if (id == R.id.home) {
            getSupportFragmentManager().clearBackStack(null);
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.fragmentContainer,new HomeFragment());
            transaction.addToBackStack(null);
            transaction.commit();
            Log.i("dgdsg", "changeBottomNavigationPosition: Home");
        } else if (id == R.id.habits) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.fragmentContainer,new HabitsFragment());
            transaction.commit();
            Log.i("dgdsg", "changeBottomNavigationPosition: habits");
        } else if (id == R.id.calender) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.fragmentContainer,new CalenderFragment());
            transaction.commit();
            Log.i("dgdsg", "changeBottomNavigationPosition: calender");

        } else if (id == R.id.development) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.fragmentContainer,new DevelopmentFragment());
            transaction.commit();
            Log.i("dgdsg", "changeBottomNavigationPosition: development");


        } else if (id == R.id.innovation) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.fragmentContainer,new InnovationFragment());
            transaction.commit();
            Log.i("dgdsg", "changeBottomNavigationPosition: innovation");


        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQ_CODE_SPEECH_INPUT && resultCode == RESULT_OK){
            String res = Utils.handleListenResult(data);
        }
    }
}