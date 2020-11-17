package com.cinnamontoast.scavengerhunt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class CreateQuestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_quest);

        setupButtons();
    }

    public void setupButtons (){
        Button savequest = findViewById(R.id.savequest);
        savequest.setOnClickListener(view -> this.startActivity(new Intent(this, ParentProfileActivity.class)));

    }
}