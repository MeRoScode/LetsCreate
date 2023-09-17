package com.meros.letscreate.Fragments.Calender;

import static com.meros.letscreate.Constants.TACK_TB;

import android.app.Activity;
import android.app.AlertDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.CalendarView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.google.android.material.chip.Chip;
import com.meros.letscreate.Constants;
import com.meros.letscreate.MainActivity;
import com.meros.letscreate.MyDb;
import com.meros.letscreate.R;
import com.meros.letscreate.Utils;
import com.meros.letscreate.Utils.LetFragment;
import com.meros.letscreate.Utils.LetActivity;
import com.meros.letscreate.databinding.FragmentCalenderBinding;
import com.meros.letscreate.databinding.NewTackDialogBinding;
import com.meros.letscreate.databinding.PickTimerDialogBinding;
import com.meros.letscreate.databinding.ScheduleDialogBinding;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class CalenderFragment extends LetFragment {

    FragmentCalenderBinding binding;
    private static final String TAG = "CalenderFragment";
    TackAdapter adapter;
    ArrayList<TackModel> tacks;
    String date = "", tackDate = "";
    Chip chip;
    TackModel tackModel = new TackModel();
    TackUrgency tackUrgency = TackUrgency.WHITE;

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
            tacks = Constants.getMangeTackTable(getActivity()).getTacks(generateQuery(), new String[]{date});
        });
        tacks = Constants.getMangeTackTable(getActivity()).getTacks(generateQuery(), new String[]{date});
        adapter = new TackAdapter(tacks);
        binding.recTacks.setLayoutManager(new LinearLayoutManager(getActivity()));
        binding.recTacks.setAdapter(adapter);

        binding.btnAddTack.setOnClickListener((view -> {
            openCreateTackDialog();
        }));
    }

    private String generateQuery() {
        return " SELECT * FROM " + TACK_TB + " WHERE " + TackModel.DATE + " = ?";
    }

    public void openCreateTackDialog() {
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
            tackModel.setFinished(false);

            if (!dialogBinding.edtTitle.getText().toString().isEmpty()) {
                tackModel.setTitle(dialogBinding.edtTitle.getText().toString());
            } else {
                Toast(getActivity(), "Enter Tack Title");
                return;
            }

            if (!dialogBinding.edtDescription.getText().toString().isEmpty()) {
                tackModel.setDescription(dialogBinding.edtDescription.getText().toString());
            }

            v.setVisibility(View.GONE);
            dialog.dismiss();
        }));
        dialogBinding.schedule.setOnClickListener((view -> {
            openCalendarDialog();
        }));
        dialogBinding.btnCancel.setOnClickListener((view -> {
            v.setVisibility(View.GONE);
            dialog.dismiss();
        }));

        ScaleAnimation scaleAnimation1 = new ScaleAnimation(1, 1.5f, 1, 1.5f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        scaleAnimation1.setFillAfter(true);
        scaleAnimation1.setDuration(700);
        dialogBinding.white.startAnimation(scaleAnimation1);


        dialogBinding.white.setOnClickListener((view -> {
            if (tackUrgency != TackUrgency.WHITE) {
                changeUrgency(dialogBinding, tackUrgency);
                ScaleAnimation scaleAnimation = new ScaleAnimation(1, 1.5f, 1, 1.5f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                scaleAnimation.setFillAfter(true);
                scaleAnimation.setDuration(700);
                dialogBinding.white.startAnimation(scaleAnimation);
                tackUrgency = TackUrgency.WHITE;
            }
        }));

        dialogBinding.yellow.setOnClickListener((view -> {

            if (tackUrgency != TackUrgency.YELLOW) {
                changeUrgency(dialogBinding, tackUrgency);
                ScaleAnimation scaleAnimation = new ScaleAnimation(1, 1.5f, 1, 1.5f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                scaleAnimation.setFillAfter(true);
                scaleAnimation.setDuration(700);
                dialogBinding.yellow.startAnimation(scaleAnimation);
                tackUrgency = TackUrgency.YELLOW;
            }
        }));

        dialogBinding.green.setOnClickListener((view -> {

            if (tackUrgency != TackUrgency.GREEN) {
                changeUrgency(dialogBinding, tackUrgency);
                ScaleAnimation scaleAnimation = new ScaleAnimation(1, 1.5f, 1, 1.5f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                scaleAnimation.setFillAfter(true);
                scaleAnimation.setDuration(700);
                dialogBinding.green.startAnimation(scaleAnimation);
                tackUrgency = TackUrgency.GREEN;
            }
        }));

        dialogBinding.orange.setOnClickListener((view -> {
            if (tackUrgency != TackUrgency.ORANGE) {
                changeUrgency(dialogBinding, tackUrgency);
                ScaleAnimation scaleAnimation = new ScaleAnimation(1, 1.5f, 1, 1.5f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                scaleAnimation.setFillAfter(true);
                scaleAnimation.setDuration(700);
                dialogBinding.orange.startAnimation(scaleAnimation);
                tackUrgency = TackUrgency.ORANGE;
            }
        }));

        dialogBinding.red.setOnClickListener((view -> {

            if (tackUrgency != TackUrgency.RED) {
                changeUrgency(dialogBinding, tackUrgency);
                ScaleAnimation scaleAnimation = new ScaleAnimation(1, 1.5f, 1, 1.5f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                scaleAnimation.setFillAfter(true);
                scaleAnimation.setDuration(700);
                dialogBinding.red.startAnimation(scaleAnimation);
                tackUrgency = TackUrgency.RED;
            }
        }));


    }

    public void changeUrgency(NewTackDialogBinding dialogBinding, TackUrgency tackUrgency) {
        ScaleAnimation onscaleAnimation = new ScaleAnimation(1.5f, 1, 1.5f, 1, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        onscaleAnimation.setFillAfter(true);
        onscaleAnimation.setDuration(700);
        if (tackUrgency == TackUrgency.WHITE) {
            dialogBinding.white.startAnimation(onscaleAnimation);
        } else if (tackUrgency == TackUrgency.YELLOW) {
            dialogBinding.yellow.startAnimation(onscaleAnimation);
        } else if (tackUrgency == TackUrgency.GREEN) {
            dialogBinding.green.startAnimation(onscaleAnimation);
        } else if (tackUrgency == TackUrgency.ORANGE) {
            dialogBinding.orange.startAnimation(onscaleAnimation);
        } else if (tackUrgency == TackUrgency.RED) {
            dialogBinding.red.startAnimation(onscaleAnimation);
        }
    }

    public void openCalendarDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        ScheduleDialogBinding dialogBinding = ScheduleDialogBinding.inflate(LayoutInflater.from(getActivity()));
        builder.setView(dialogBinding.getRoot());
        AlertDialog dialog = builder.show();
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);

        dialog.setCancelable(false);
        dialogBinding.tvDone.setOnClickListener((view -> {
            Toast(getActivity(), "Hello");
            dialog.dismiss();
        }));
        dialogBinding.tvCancel.setOnClickListener((view -> {
            dialog.dismiss();
        }));

        dialogBinding.tvTimer.setOnClickListener((view -> {
            openTimerDialog();
        }));

        dialogBinding.calender.setOnDateChangeListener((calendarView, year, month, dayOfMonth) -> {
            tackDate = String.valueOf(year) + String.valueOf(month + 1) + String.valueOf(dayOfMonth);
            dialogBinding.calenderDisable.setVisibility(View.GONE);

            Calendar TodayCalendar = Calendar.getInstance();
            Calendar SelectedCalendar = Calendar.getInstance();
            Calendar TomorrowCalendar = Calendar.getInstance();
            TomorrowCalendar.add(Calendar.DAY_OF_MONTH, 1);
            Calendar TreeDayLaterCalendar = Calendar.getInstance();
            TreeDayLaterCalendar.add(Calendar.DAY_OF_MONTH, 3);
            SelectedCalendar.set(Calendar.YEAR, year);
            SelectedCalendar.set(Calendar.MONTH, month);
            SelectedCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

            long TodayDateMillis = TodayCalendar.getTimeInMillis();
            long TomorrowDateMillis = TomorrowCalendar.getTimeInMillis();
            long TreeDayLaterDateMillis = TreeDayLaterCalendar.getTimeInMillis();
            long selectedDateMillis = SelectedCalendar.getTimeInMillis();

            Date TodayDate = new Date(TodayDateMillis);
            Date TomorrowDate = new Date(TomorrowDateMillis);
            Date TreeDayLaterDate = new Date(TreeDayLaterDateMillis);
            Date selectedDate = new Date(selectedDateMillis);

            String TodayDay = DateFormat.format("dd", TodayDate).toString();
            String TomorrowDay = DateFormat.format("dd", TomorrowDate).toString();
            String TreeDayLaterDay = DateFormat.format("dd", TreeDayLaterDate).toString();
            String selectedDay = DateFormat.format("dd", selectedDate).toString();

            String selectedDayOfWeek = DateFormat.format("EEEE", selectedDate).toString();

            Log.i(TAG, "selectedDay: " + selectedDay);
            Log.i(TAG, "TodayDay: " + TodayDay);
            Log.i(TAG, "TomorrowDay: " + TomorrowDay);
            Log.i(TAG, "TreeDayLaterDay: " + TreeDayLaterDay);


            if (TodayDay.equals(selectedDay)) {
                if (chip != dialogBinding.today) {
                    changeChip(chip, dialogBinding.today);
                    chip = dialogBinding.today;
                }
                Log.i(TAG, "today: ");
            } else if (selectedDay.equals(TomorrowDay)) {
                if (chip != dialogBinding.tomorrow) {
                    changeChip(chip, dialogBinding.tomorrow);
                    chip = dialogBinding.tomorrow;
                }
                Log.i(TAG, "tomorrow: ");
            } else if (selectedDay.equals(TreeDayLaterDay)) {
                if (chip != dialogBinding.treeDaysLater) {
                    changeChip(chip, dialogBinding.treeDaysLater);
                    chip = dialogBinding.treeDaysLater;
                }
                Log.i(TAG, "treeDaysLater: ");
            } else if (selectedDayOfWeek.equals("Sunday")) {
                if (chip != dialogBinding.thisSunday) {
                    changeChip(chip, dialogBinding.thisSunday);
                    chip = dialogBinding.thisSunday;
                }
                Log.i(TAG, "thisSunday: ");
            } else {
                chip = dialogBinding.noDate;
                changeChip(chip, dialogBinding.noDate);
                Log.i(TAG, "noDate: ");
            }
        });


        chip = dialogBinding.today;
        changeChip(dialogBinding.noDate, dialogBinding.today);

        dialogBinding.noDate.setOnClickListener((view -> {
            if (chip != dialogBinding.today) {
                changeChip(chip, dialogBinding.noDate);
                chip = dialogBinding.noDate;
                tackDate = "";
                dialogBinding.calenderDisable.setVisibility(View.VISIBLE);
            }
        }));

        dialogBinding.today.setOnClickListener((view -> {
            if (chip != dialogBinding.today) {
                changeChip(chip, dialogBinding.today);
                chip = dialogBinding.today;
                Calendar calendar = Calendar.getInstance();
                long dateMillis = calendar.getTimeInMillis();
                Date date1 = new Date(dateMillis);

                String selectedDay = DateFormat.format("dd", date1).toString(); // 05
                String selectedMonthNumber = DateFormat.format("MM", date1).toString(); // 6 --> Month Code as Jan = 0 till Dec = 11.
                String selectedYear = DateFormat.format("yyyy", date1).toString(); // 2021
                tackDate = selectedYear + selectedMonthNumber + selectedDay;

                dialogBinding.calender.setDate(dateMillis);
                dialogBinding.calenderDisable.setVisibility(View.GONE);
            }
        }));

        dialogBinding.tomorrow.setOnClickListener((view -> {
            if (chip != dialogBinding.tomorrow) {
                changeChip(chip, dialogBinding.tomorrow);
                chip = dialogBinding.tomorrow;

                Calendar calendar = Calendar.getInstance();
                long dateMillis = calendar.getTimeInMillis();
                long aDay = 1000 * 60 * 60 * 24;
                dateMillis += aDay;
                Date date1 = new Date(dateMillis);

                String selectedDay = DateFormat.format("dd", date1).toString(); // 05
                String selectedMonthNumber = DateFormat.format("MM", date1).toString(); // 6 --> Month Code as Jan = 0 till Dec = 11.
                String selectedYear = DateFormat.format("yyyy", date1).toString(); // 2021
                tackDate = selectedYear + selectedMonthNumber + selectedDay;

                dialogBinding.calender.setDate(dateMillis);
                dialogBinding.calenderDisable.setVisibility(View.GONE);
            }
        }));

        dialogBinding.treeDaysLater.setOnClickListener((view -> {
            if (chip != dialogBinding.treeDaysLater) {
                changeChip(chip, dialogBinding.treeDaysLater);
                chip = dialogBinding.treeDaysLater;

                Calendar calendar = Calendar.getInstance();
                long dateMillis = calendar.getTimeInMillis();
                long aDay = 1000 * 60 * 60 * 24;
                dateMillis += (aDay * 3);
                Date date1 = new Date(dateMillis);

                String selectedDay = DateFormat.format("dd", date1).toString(); // 05
                String selectedMonthNumber = DateFormat.format("MM", date1).toString(); // 6 --> Month Code as Jan = 0 till Dec = 11.
                String selectedYear = DateFormat.format("yyyy", date1).toString(); // 2021
                tackDate = selectedYear + selectedMonthNumber + selectedDay;

                dialogBinding.calender.setDate(dateMillis);
                dialogBinding.calenderDisable.setVisibility(View.GONE);
            }
        }));

        dialogBinding.thisSunday.setOnClickListener((view -> {
            if (chip != dialogBinding.thisSunday) {
                changeChip(chip, dialogBinding.thisSunday);
                chip = dialogBinding.thisSunday;

                Calendar calendar = Calendar.getInstance();
                long dateMillis = calendar.getTimeInMillis();
                long aDay = 1000 * 60 * 60 * 24;
                Date date1 = new Date(dateMillis);

                String selectedDayOfWeek = DateFormat.format("EEEE", date1).toString(); // Monday
                while (!selectedDayOfWeek.equals("Sunday")) {
                    dateMillis += aDay;
                    date1 = new Date(dateMillis);
                    selectedDayOfWeek = DateFormat.format("EEEE", date1).toString();
                }
                String selectedDay = DateFormat.format("dd", date1).toString(); // 05
                String selectedMonthNumber = DateFormat.format("MM", date1).toString(); // 6 --> Month Code as Jan = 0 till Dec = 11.
                String selectedYear = DateFormat.format("yyyy", date1).toString(); // 2021
                tackDate = selectedYear + selectedMonthNumber + selectedDay;

                dialogBinding.calender.setDate(dateMillis);
                dialogBinding.calenderDisable.setVisibility(View.GONE);
            }
        }));
    }

    public void changeChip(Chip oldChip, Chip newChip) {
        newChip.setChecked(true);
        oldChip.setChecked(false);
    }

    public void openTimerDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        PickTimerDialogBinding dialogBinding = PickTimerDialogBinding.inflate(LayoutInflater.from(getActivity()));
        builder.setView(dialogBinding.getRoot());
        AlertDialog dialog = builder.show();
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);

        dialog.setCancelable(false);
        dialogBinding.tvDone.setOnClickListener((view -> {
            Toast(getActivity(), "Hello");
            dialog.dismiss();
        }));
        dialogBinding.tvCancel.setOnClickListener((view -> {
            dialog.dismiss();
        }));

    }
}
