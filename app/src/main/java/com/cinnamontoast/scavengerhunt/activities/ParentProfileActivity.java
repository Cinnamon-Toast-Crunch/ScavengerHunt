package com.cinnamontoast.scavengerhunt.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.cinnamontoast.scavengerhunt.R;
import com.cinnamontoast.scavengerhunt.activities.ContactActivity;
import com.cinnamontoast.scavengerhunt.activities.CreateLocationActivity;
import com.cinnamontoast.scavengerhunt.activities.CreateQuestActivity;
import com.cinnamontoast.scavengerhunt.activities.LaunchQuestActivity;

public class ParentProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parent_profile);

        setupButtons();
    }

    public void setupButtons (){
        Button createquest = findViewById(R.id.createquest);
        createquest.setOnClickListener(view -> this.startActivity(new Intent(this, CreateQuestActivity.class)));

        Button addplayer = findViewById(R.id.addplayer);
        addplayer.setOnClickListener(view -> this.startActivity(new Intent(this, ContactActivity.class)));

        Button startquest = findViewById(R.id.startquest);
        startquest.setOnClickListener(view -> this.startActivity(new Intent(this, LaunchQuestActivity.class)));
    }
}