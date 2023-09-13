package com.meros.letscreate.Fragments.Development;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.meros.letscreate.Utils.LetFragment;
import com.meros.letscreate.databinding.FragmentCalenderBinding;
import com.meros.letscreate.databinding.FragmentDevelopmentBinding;

public class DevelopmentFragment extends LetFragment {

    FragmentDevelopmentBinding binding;
    private static final String TAG = "DevelopmentFragment";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentDevelopmentBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Toast(getActivity(),"DevelopmentFragment");
    }
}
