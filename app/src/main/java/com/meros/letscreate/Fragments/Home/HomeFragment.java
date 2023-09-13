package com.meros.letscreate.Fragments.Home;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.meros.letscreate.Utils.LetFragment;
import com.meros.letscreate.databinding.FragmentCalenderBinding;
import com.meros.letscreate.databinding.FragmentHomeBinding;

public class HomeFragment extends LetFragment {

    FragmentHomeBinding binding;
    private static final String TAG = "HomeFragment";


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(getLayoutInflater());
        return binding.getRoot();

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Toast(getActivity(),"HomeFragment");
    }


}
