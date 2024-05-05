package com.example.finalproject;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.finalproject.adapter.ActivityAdapter;
import com.example.finalproject.model.ActivityModel;

import java.util.ArrayList;

public class ChallengeActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ActivityAdapter activityAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_challenge);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        recyclerView = findViewById(R.id.recycler_view);
        ArrayList<ActivityModel> sports = new ArrayList<>();
        sports.add(new ActivityModel("Swimming", "#FFBB33", R.drawable.baseline_pool_24));
        sports.add(new ActivityModel("Hiking", "#FF8800", R.drawable.baseline_hiking_24));
        sports.add(new ActivityModel("Cycling", "#CEFF8A80", R.drawable.baseline_directions_bike_24));
        sports.add(new ActivityModel("Running", "#633B48", R.drawable.baseline_directions_run_24));
        sports.add(new ActivityModel("Team Sports", "#787296", R.drawable.baseline_people_24));
        sports.add(new ActivityModel("Cross Fit", "#CD3A81F8", R.drawable.baseline_downhill_skiing_24));
        sports.add(new ActivityModel("Any", "#B478E67C", R.drawable.baseline_accessibility_24));



        activityAdapter = new ActivityAdapter(sports);
        recyclerView.setAdapter(activityAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        findViewById(R.id.next_button).setOnClickListener( v ->
                startActivity(new Intent(ChallengeActivity.this, EventProximityActivity.class)));
        findViewById(R.id.cancel_button).setOnClickListener( v ->
                startActivity(new Intent(ChallengeActivity.this, MainActivity.class)));
    }
}