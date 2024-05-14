package com.example.finalproject.activities;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.finalproject.R;
import com.example.finalproject.adapter.EventAdapter;
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
        myEvents.add(new EventModel("Cycling Event", new Date(), 30.00, 30.00));
        myEvents.add(new EventModel("Running Event", new Date(), 30.00, 30.00));
        myEvents.add(new EventModel("Hiking Event", new Date(), 30.00, 30.00));
        myEvents.add(new EventModel("Cross Fit Event", new Date(), 30.00, 30.00));

        adapter = new EventAdapter(myEvents);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


    }
}