package com.meros.letscreate.Fragments.Habits;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.meros.letscreate.Utils.LetFragment;
import com.meros.letscreate.databinding.FragmentCalenderBinding;
import com.meros.letscreate.databinding.FragmentHabitsBinding;

public class HabitsFragment extends LetFragment {

    FragmentHabitsBinding binding;
    private static final String TAG = "HabitsFragment";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentHabitsBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Toast(getActivity(),"HabitsFragment");
    }
}
