package com.meros.letscreate.Fragments.Calender;

import static com.meros.letscreate.Constants.TACK_TB;

import android.app.Activity;
import android.app.AlertDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.CalendarView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.meros.letscreate.Constants;
import com.meros.letscreate.MainActivity;
import com.meros.letscreate.MyDb;
import com.meros.letscreate.R;
import com.meros.letscreate.Utils;
import com.meros.letscreate.Utils.LetFragment;
import com.meros.letscreate.Utils.LetActivity;
import com.meros.letscreate.databinding.FragmentCalenderBinding;
import com.meros.letscreate.databinding.NewTackDialogBinding;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class CalenderFragment extends LetFragment {

    FragmentCalenderBinding binding;
    private static final String TAG = "CalenderFragment";
    TackAdapter adapter;
    ArrayList<TackModel> tacks;
    String date = "";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentCalenderBinding.inflate(getLayoutInflater());
        return binding.getRoot();

    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Calendar calendar = Calendar.getInstance();
        date = String.valueOf(calendar.get(Calendar.YEAR)) +
               String.valueOf(calendar.get(Calendar.MONTH)) +
               String.valueOf(calendar.get(Calendar.DAY_OF_MONTH));
        initViews();
    }

    private void initViews() {
        binding.calender.setOnDateChangeListener((calendarView, year, month, dayOfMonth) -> {

            date = String.valueOf(year) +
                    String.valueOf(month) +
                    String.valueOf(dayOfMonth);
            tacks = Constants.getMangeTackTable(getActivity()).getTacks(generateQuery(),new String[]{date});
        });
        tacks = Constants.getMangeTackTable(getActivity()).getTacks(generateQuery(),new String[]{date});
        adapter = new TackAdapter(tacks);
        binding.recTacks.setLayoutManager(new LinearLayoutManager(getActivity()));
        binding.recTacks.setAdapter(adapter);

        binding.btnAddTack.setOnClickListener((view -> {
            openCreateTackDialog();
        }));
    }

    private String generateQuery(){
        return " SELECT * FROM " + TACK_TB + " WHERE " + TackModel.DATE + " = ?";
    }

    public void openCreateTackDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        NewTackDialogBinding dialogBinding = NewTackDialogBinding.inflate(LayoutInflater.from(getActivity()));
        builder.setView(dialogBinding.getRoot());
        AlertDialog dialog = builder.show();
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);

        LetActivity activity = ((MainActivity) getActivity());
        ConstraintLayout v = activity.showBackgroundView(true);
        dialog.setCancelable(false);
        dialogBinding.btnOk.setOnClickListener((view -> {
            Toast(getActivity(),dialogBinding.edtTitle.getText().toString());
            v.setVisibility(View.GONE);
            dialog.dismiss();
        }));
        dialogBinding.btnCancel.setOnClickListener((view -> {
            v.setVisibility(View.GONE);
            dialog.dismiss();
        }));

    }
}
