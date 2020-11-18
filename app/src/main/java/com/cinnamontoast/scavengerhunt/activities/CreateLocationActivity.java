package com.cinnamontoast.scavengerhunt.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.cinnamontoast.scavengerhunt.R;
import com.cinnamontoast.scavengerhunt.activities.CreateHuntActivity;

public class CreateLocationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_location);

        setupButtons();
    }

    public void setupButtons (){
        Button createhunt = findViewById(R.id.createhunt);
        createhunt.setOnClickListener(view -> this.startActivity(new Intent(this, CreateHuntActivity.class)));

    }
}