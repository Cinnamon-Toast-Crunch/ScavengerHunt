package com.cinnamontoast.scavengerhunt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class CreateHuntActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_hunt);

        setupButtons();
    }

    public void setupButtons (){
        Button makehunt = findViewById(R.id.makehunt);
        makehunt.setOnClickListener(view -> this.startActivity(new Intent(this, ParentProfileActivity.class)));

        Button createitem = findViewById(R.id.createitem);
//        createitem.setOnClickListener(view -> this.startActivity(new Intent(this, CreateLocationActivity.class)));

    }
}