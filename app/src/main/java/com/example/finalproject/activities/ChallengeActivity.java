package com.example.finalproject.activities;

import android.content.Intent;
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
import com.example.finalproject.adapter.ActivityAdapter;
import com.example.finalproject.model.SportCategoryModel;
import com.example.finalproject.utils.SharedData;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class ChallengeActivity extends AppCompatActivity implements ActivityAdapter.OnSportCheckedChangeListener {

    private RecyclerView recyclerView;
    private ActivityAdapter activityAdapter;
    ArrayList<SportCategoryModel> sports = new ArrayList<>();

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
        sports.add(new SportCategoryModel(0,"Swimming", "#FFBB33", R.drawable.baseline_pool_24));
        sports.add(new SportCategoryModel(1,"Hiking", "#FF8800", R.drawable.baseline_hiking_24));
        sports.add(new SportCategoryModel(2,"Cycling", "#CEFF8A80", R.drawable.baseline_directions_bike_24));
        sports.add(new SportCategoryModel(3,"Running", "#633B48", R.drawable.baseline_directions_run_24));
        sports.add(new SportCategoryModel(4,"Team Sports", "#787296", R.drawable.baseline_people_24));
        sports.add(new SportCategoryModel(5,"Cross Fit", "#CD3A81F8", R.drawable.baseline_downhill_skiing_24));
        sports.add(new SportCategoryModel(6,"Any", "#B478E67C", R.drawable.baseline_accessibility_24, true));



        activityAdapter = new ActivityAdapter(sports, this);
        recyclerView.setAdapter(activityAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        findViewById(R.id.next_button).setOnClickListener( v -> {
            if (!SharedData.selectedSports.isEmpty())
                startActivity(new Intent(ChallengeActivity.this, EventProximityActivity.class));
            else
                Toast.makeText(this, "Please select at least one challenge or any choice", Toast.LENGTH_SHORT).show();
        });
        findViewById(R.id.cancel_button).setOnClickListener( v -> {
            Intent intent = new Intent(ChallengeActivity.this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        });
    }

    @Override
    public void onSportCheckedChanged(int position, boolean isChecked) {
        sports.get(position).setSelected(isChecked);
        SharedData.selectedSports = new ArrayList<>(sports.stream().filter(SportCategoryModel::isSelected).collect(Collectors.toList()));
        if (!recyclerView.isComputingLayout() ) {
            activityAdapter.notifyDataSetChanged();
        }
    }
}