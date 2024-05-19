package com.example.finalproject.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.finalproject.R;

public class CommunityActivity extends AppCompatActivity {

    Button groupBtn;
    Button storyBtn;
    Button challengeBtn;
    Button eventBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_community);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        groupBtn = findViewById(R.id.groupBtn);
        storyBtn = findViewById(R.id.storyBtn);
        challengeBtn = findViewById(R.id.challengeBtn);
        eventBtn = findViewById(R.id.eventBtn);

        groupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Intent intent = new Intent(CommunityActivity.this, JoinGroupActivity.class);
                // startActivity(intent);
            }
        });

        storyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CommunityActivity.this, MyStoryActivity.class);
                startActivity(intent);
            }
        });

        challengeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Intent intent = new Intent(CommunityActivity.this, ChallengeActivity.class);
                // startActivity(intent);
            }
        });

        eventBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CommunityActivity.this, VirtualEventActivity.class);
                startActivity(intent);
            }
        });
    }
}