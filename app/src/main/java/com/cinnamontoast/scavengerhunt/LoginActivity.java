package com.cinnamontoast.scavengerhunt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        setupButtons();
    }

    public void setupButtons (){
        Button signin = findViewById(R.id.signin);
        signin.setOnClickListener(view -> this.startActivity(new Intent(this, ParentProfileActivity.class)));

    }
}