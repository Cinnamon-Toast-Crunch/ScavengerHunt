package com.cinnamontoast.scavengerhunt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class SignupActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        setupButtons();
    }

    public void setupButtons (){
        Button register = findViewById(R.id.register);
        register.setOnClickListener(view -> this.startActivity(new Intent(this, ParentProfileActivity.class)));

    }
}