package com.cinnamontoast.scavengerhunt.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.generated.model.Contact;
import com.cinnamontoast.scavengerhunt.R;

public class CreateQuestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_quest);


    }

    //===================== Create Quest =====================
    public void createQuest() {
        Button createQuest = findViewById(R.id.saveQuest); //change button id as needed
        createQuest.setOnClickListener(view -> {

            String userName = ((TextView) findViewById(R.id.playername)).getText().toString();
            String phoneNumber = ((TextView) findViewById(R.id.phonenumber)).getText().toString();
            String email = ((TextView) findViewById(R.id.email)).getText().toString();

            Contact newContact = Contact.builder()
                    .userId(Amplify.Auth.getCurrentUser().getUserId())
                    .name(userName)
                    .phoneNumber(phoneNumber)
                    .email(email)
                    .build();

            //toast.show();
            this.startActivity(new Intent(this, ParentProfileActivity.class));
        });

    }


}