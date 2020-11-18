package com.cinnamontoast.scavengerhunt.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.generated.model.Contact;
import com.amplifyframework.datastore.generated.model.Location;
import com.amplifyframework.datastore.generated.model.Task;
import com.cinnamontoast.scavengerhunt.R;
import com.cinnamontoast.scavengerhunt.adapters.LocationAdapter;

import java.util.ArrayList;

public class CreateQuestActivity extends AppCompatActivity implements LocationAdapter.LocationListFormatter {

    Location selectedLocation;
    ArrayList<Task> selectedTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_quest);


    }

    //================= Grab Location ========================
    @Override
    public void locationFormatter(Location location) {
        selectedLocation = location;
    }


    //===================== Create Quest =====================
//    public void createQuest() {
//        Button createQuest = findViewById(R.id.saveQuest); //change button id as needed
//        createQuest.setOnClickListener(view -> {
//
//            String userName = ((TextView) findViewById(R.id.playername)).getText().toString();
//            String phoneNumber = ((TextView) findViewById(R.id.phonenumber)).getText().toString();
//            String email = ((TextView) findViewById(R.id.email)).getText().toString();
//
//            Contact newContact = Contact.builder()
//                    .userId(Amplify.Auth.getCurrentUser().getUserId())
//                    .name(userName)
//                    .phoneNumber(phoneNumber)
//                    .email(email)
//                    .build();
//
//            //toast.show();
//            this.startActivity(new Intent(this, ParentProfileActivity.class));
//        });

    //}



}