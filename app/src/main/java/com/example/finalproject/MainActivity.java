package com.example.finalproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    TextView appName;
    TextView nextEvent;
    Button exploreEvents;
    Button myEvents;
    Button challengeMeCommunity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); // Make sure this layout file name matches your XML file

        // Initialize UI components
        appName = findViewById(R.id.appName);
        nextEvent = findViewById(R.id.NextEvent); // Make sure ID matches your XML
        exploreEvents = findViewById(R.id.ExploreEvents); // Make sure ID matches your XML
        myEvents = findViewById(R.id.MyEvents); // Make sure ID matches your XML
        challengeMeCommunity = findViewById(R.id.ChallengeMeCommunity); // Make sure ID matches your XML

        // Set click listeners for buttons
        exploreEvents.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create an Intent to start the SecondActivity
                Intent intent = new Intent(MainActivity.this, Explor.class);
                startActivity(intent); // Start the SecondActivity
            }
        });


    }
}
