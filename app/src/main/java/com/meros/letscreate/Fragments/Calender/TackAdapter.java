package com.meros.letscreate.Fragments.Calender;

import android.annotation.SuppressLint;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.meros.letscreate.R;

import java.util.ArrayList;

public class TackAdapter extends RecyclerView.Adapter<TackAdapter.Viewholder> {

    ArrayList<TackModel> tacks;
    private static int openPosition = -1;
    public TackAdapter(ArrayList<TackModel> tacks) {
        this.tacks = tacks;
    }

    @NonNull
    @Override
    public TackAdapter.Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new Viewholder(LayoutInflater.from(parent.getContext()).inflate(R.layout.tack_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull TackAdapter.Viewholder holder, @SuppressLint("RecyclerView") int position) {
//        holder.bind(tacks.get(position));


        if (openPosition == position){
            holder.tvDescription.setVisibility(View.VISIBLE);
        }else {
            holder.tvDescription.setVisibility(View.GONE);
        }

        holder.itemView.setOnClickListener((view -> {
            if (openPosition == position){
                openPosition = -1;
                notifyItemChanged(position);
            }else if (openPosition == -1){
                openPosition = position;
                notifyItemChanged(position);
            }else{
                int pos = openPosition;
                openPosition = -1;
                notifyItemChanged(pos);
                openPosition = position;
                notifyItemChanged(openPosition);
            }
        }));

    }

    @Override
    public int getItemCount() {
        return 10;
    }

    public static class Viewholder extends RecyclerView.ViewHolder {

        private TextView tvTitle, tvDescription;
        private View urgency;
        private CheckBox isFinished;

        public Viewholder(@NonNull View itemView) {
            super(itemView);

            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvDescription = itemView.findViewById(R.id.tvDescription);
            urgency = itemView.findViewById(R.id.urgency);
            isFinished = itemView.findViewById(R.id.isFinished);
        }

        public void bind(TackModel tackModel) {
            tvTitle.setText(tackModel.getTitle());
            tvDescription.setText(tackModel.getDescription());
            isFinished.setChecked(tackModel.isFinished());
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (tackModel.getUrgency() == TackUrgency.GREEN.getValue()) {
                    urgency.setBackgroundColor(itemView.getContext().getColor(R.color.green));
                } else if (tackModel.getUrgency() == TackUrgency.WHITE.getValue()) {
                    urgency.setBackgroundColor(itemView.getContext().getColor(R.color.white));
                } else if (tackModel.getUrgency() == TackUrgency.YELLOW.getValue()) {
                    urgency.setBackgroundColor(itemView.getContext().getColor(R.color.yellow));
                } else if (tackModel.getUrgency() == TackUrgency.ORANGE.getValue()) {
                    urgency.setBackgroundColor(itemView.getContext().getColor(R.color.orange));
                } else if (tackModel.getUrgency() == TackUrgency.RED.getValue()) {
                    urgency.setBackgroundColor(itemView.getContext().getColor(R.color.red));
                }
            }
        }
    }
}
