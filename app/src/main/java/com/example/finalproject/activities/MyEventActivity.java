package com.example.finalproject.activities;

import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.finalproject.R;
import com.example.finalproject.adapter.EventAdapter;
import com.example.finalproject.callbacks.EventCallback;
import com.example.finalproject.controllers.UserEventsController;
import com.example.finalproject.model.EventModel;

import java.util.ArrayList;
import java.util.Date;

public class MyEventActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    EventAdapter adapter;
    ArrayList<EventModel> myEvents = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_my_event);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        recyclerView = findViewById(R.id.recycler_view);

        new UserEventsController().getEvents(new EventCallback() {
            @Override
            public void onSuccess(ArrayList<EventModel> events) {
                myEvents.clear();
                myEvents.addAll(events);
                if (adapter == null) {
                    adapter = new EventAdapter(myEvents, MyEventActivity.this);
                    recyclerView.setAdapter(adapter);
                    recyclerView.setLayoutManager(new LinearLayoutManager(MyEventActivity.this));

                } else {
                    adapter.setData(myEvents);
                }

            }

            @Override
            public void onFailure(String message) {
                Toast.makeText(MyEventActivity.this, message, Toast.LENGTH_SHORT).show();
            }
        });
    }

}