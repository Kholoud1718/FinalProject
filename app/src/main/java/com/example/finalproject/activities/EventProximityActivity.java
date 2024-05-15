package com.example.finalproject.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Toast;

import com.example.finalproject.R;
import com.example.finalproject.utils.SharedData;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class EventProximityActivity extends AppCompatActivity {

    private CheckBox kilos5, kilos10, anyDistance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_event_proximity);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        kilos5 = findViewById(R.id.kilos5);
        kilos10 = findViewById(R.id.kilos10);
        anyDistance = findViewById(R.id.AnyDistance);

        kilos5.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                kilos10.setChecked(false);
                anyDistance.setChecked(false);
                SharedData.min_radius = 0;
                SharedData.max_radius = 5;
            }
        });

        kilos10.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                kilos5.setChecked(false);
                anyDistance.setChecked(false);
                SharedData.min_radius = 10;
                SharedData.max_radius = 20;
            }
        });

        anyDistance.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                kilos5.setChecked(false);
                kilos10.setChecked(false);
                SharedData.min_radius = 0;
                SharedData.max_radius = Double.MAX_VALUE;
            }
        });

        findViewById(R.id.next_button).setOnClickListener( v -> {
            if (kilos5.isChecked() || kilos10.isChecked() || anyDistance.isChecked()) {
                startActivity(new Intent(EventProximityActivity.this, SetTimeActivity.class));
            } else {
                Toast.makeText(this, "Please select one option", Toast.LENGTH_SHORT).show();
            }
        });
        findViewById(R.id.cancel_button).setOnClickListener( v -> {
            Intent intent = new Intent(EventProximityActivity.this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        });

    }
}