package com.meros.letscreate.Fragments.Innovation;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.meros.letscreate.Utils.LetFragment;
import com.meros.letscreate.databinding.FragmentCalenderBinding;
import com.meros.letscreate.databinding.FragmentInnovationBinding;

public class InnovationFragment extends LetFragment {

    FragmentInnovationBinding binding;
    private static final String TAG = "InnovationFragment";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentInnovationBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Toast(getActivity(),"InnovationFragment");
    }
}
