package com.example.finalproject.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.finalproject.R;
import com.example.finalproject.model.EventModel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.ViewHolder> {
    private Context context;
    private static ArrayList<EventModel> mData = new ArrayList<>();

    public EventAdapter(ArrayList<EventModel> data, Context context) {
        mData.clear();
        mData.addAll(data);
        this.context = context;
    }
    @NonNull
    @Override
    public EventAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_event, parent, false);
        return new ViewHolder(view, context);
    }

    @Override
    public void onBindViewHolder(@NonNull EventAdapter.ViewHolder holder, int position) {
        holder.eventName.setText(mData.get(position).getName());
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        holder.date.setText(dateFormat.format(mData.get(position).getDate()));
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public void setData(ArrayList<EventModel> data) {
        mData.clear();
        mData.addAll(data);
        notifyDataSetChanged();
    }

    private static void openLocationInGoogleMaps(double latitude, double longitude, String locationName, Context context) {
        Uri gmmIntentUri = Uri.parse("geo:" + latitude + "," + longitude + "?q=" + latitude + "," + longitude + "(" + Uri.encode(locationName) + ")");
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
        mapIntent.setPackage("com.google.android.apps.maps");
        if (mapIntent.resolveActivity(context.getPackageManager()) != null) {
            context.startActivity(mapIntent);
        }
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView date, eventName;
        Button goToMap;

        public ViewHolder(@NonNull View itemView, Context context) {
            super(itemView);
            date = itemView.findViewById(R.id.date);
            eventName = itemView.findViewById(R.id.event_name);
            goToMap = itemView.findViewById(R.id.go_to_map);

            goToMap.setOnClickListener(v -> {
                int position = getAdapterPosition();
                EventModel data = mData.get(position);

                // Open Google Maps with the location
                openLocationInGoogleMaps(data.getLatitude(), data.getLongitude(), data.getName(), context);
            });

        }
    }
}
