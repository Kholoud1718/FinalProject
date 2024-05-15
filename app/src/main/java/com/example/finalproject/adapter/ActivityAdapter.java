package com.example.finalproject.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.finalproject.R;
import com.example.finalproject.model.SportCategoryModel;

import java.util.ArrayList;

public class ActivityAdapter extends RecyclerView.Adapter<ActivityAdapter.ViewHolder> {
    private Context context;
    private ArrayList<SportCategoryModel> mData;
    private OnSportCheckedChangeListener listener;

    public ActivityAdapter(ArrayList<SportCategoryModel> data, OnSportCheckedChangeListener listener) {
        mData = data;
        this.listener = listener;
    }
    @NonNull
    @Override
    public ActivityAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_activity, parent, false);
        return new ViewHolder(view, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull ActivityAdapter.ViewHolder holder, int position) {
        holder.sport.setChecked(mData.get(position).isSelected());
        holder.sport.setText(mData.get(position).getName());
        holder.sport.setBackgroundColor(Color.parseColor(mData.get(position).getColor()));
        holder.sport.setCompoundDrawablesWithIntrinsicBounds(null, null, ContextCompat.getDrawable(context,mData.get(position).getDrawable()), null);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        CheckBox sport;

        public ViewHolder(@NonNull View itemView, OnSportCheckedChangeListener listener) {
            super(itemView);
            sport = itemView.findViewById(R.id.checkbox);
            sport.setOnCheckedChangeListener((buttonView, isChecked) -> {
                int adapterPosition = getAdapterPosition();
                if (adapterPosition != RecyclerView.NO_POSITION) {
                    listener.onSportCheckedChanged(adapterPosition, isChecked);
                }
            });

        }
    }

    public interface OnSportCheckedChangeListener {
        void onSportCheckedChanged(int position, boolean isChecked);
    }
}
