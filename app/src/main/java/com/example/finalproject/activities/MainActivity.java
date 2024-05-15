package com.example.finalproject.activities;

import android.content.Intent;
import android.view.Window;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import android.view.WindowManager;
import android.widget.Toast;

import com.example.finalproject.R;
import com.example.finalproject.callbacks.EventCallback;
import com.example.finalproject.controllers.EventController;
import com.example.finalproject.model.EventModel;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    TextView appName;
    TextView nextEvent;
    Button exploreEvents;
    Button myEvents;
    Button challengeMeCommunity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (android.os.Build.VERSION.SDK_INT >= 21) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(ContextCompat.getColor(this, R.color.colorPrimaryDark)); // Set your color here
        }

        // Initialize UI components
        appName = findViewById(R.id.appName);
        nextEvent = findViewById(R.id.NextEvent); //
        exploreEvents = findViewById(R.id.ExploreEvents);
        myEvents = findViewById(R.id.MyEvents); //
        challengeMeCommunity = findViewById(R.id.ChallengeMeCommunity); // Make sure ID matches your XML

        // Set click listeners for buttons
        exploreEvents.setOnClickListener(v -> {
            // Create an Intent to start the SecondActivity
            Intent intent = new Intent(MainActivity.this, ChallengeActivity.class);
            startActivity(intent); // Start the SecondActivity
        });
        myEvents .setOnClickListener(v -> {
            // Create an Intent to start the SecondActivity
            Intent intent = new Intent(MainActivity.this, MyEventActivity.class);
            startActivity(intent); // Start the SecondActivity
        });
        challengeMeCommunity.setOnClickListener(v -> {
            // Create an Intent to start the SecondActivity
            Intent intent = new Intent(MainActivity.this,CommunityActivity.class);
            startActivity(intent); // Start the SecondActivity
        });
    }
}
