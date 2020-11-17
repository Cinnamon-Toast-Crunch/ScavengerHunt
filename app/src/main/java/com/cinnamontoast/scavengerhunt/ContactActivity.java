package com.cinnamontoast.scavengerhunt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class ContactActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);

        setupButtons();
    }

    public void setupButtons (){
        Button addcontacts = findViewById(R.id.addcontacts);
        addcontacts.setOnClickListener(view -> this.startActivity(new Intent(this, ParentProfileActivity.class)));

    }
}