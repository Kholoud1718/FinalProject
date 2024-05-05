package com.example.finalproject.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.finalproject.R;
import com.example.finalproject.model.ActivityModel;

import java.util.ArrayList;

public class ActivityAdapter extends RecyclerView.Adapter<ActivityAdapter.ViewHolder> {
    private Context context;
    private ArrayList<ActivityModel> mData = new ArrayList<>();

    public ActivityAdapter(ArrayList<ActivityModel> data) {
        mData.clear();
        this.mData.addAll(data);
    }
    @NonNull
    @Override
    public ActivityAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_activity, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ActivityAdapter.ViewHolder holder, int position) {
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

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            sport = itemView.findViewById(R.id.checkbox);
        }
    }
}
