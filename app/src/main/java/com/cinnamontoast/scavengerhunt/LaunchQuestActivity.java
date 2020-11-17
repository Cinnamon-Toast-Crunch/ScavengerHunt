package com.cinnamontoast.scavengerhunt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class LaunchQuestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_task);

        setupButtons();
    }

    public void setupButtons (){
        Button launchquest = findViewById(R.id.launchquest);
        launchquest.setOnClickListener(view -> this.startActivity(new Intent(this, ParentProfileActivity.class)));

    }
}