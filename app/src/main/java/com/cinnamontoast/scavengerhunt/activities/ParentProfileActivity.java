package com.cinnamontoast.scavengerhunt.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.cinnamontoast.scavengerhunt.R;
import com.google.firebase.dynamiclinks.DynamicLink;

public class ParentProfileActivity extends AppCompatActivity {

    private static final int MY_PERMISSIONS_REQUEST_SEND_SMS = 1;

    // TODO: figure out how to pass it an array of phone numbers
    // TODO: research how to pass the data for the recycler view. maybe as an intent? w/ putExtra

    String phoneNo = "+12062519102";
    String questId = "0";
    String message = "https://scavengerhuntstart.page.link/start?questID=" + questId;
    Button sendBtn;

    // Quest ID will come from highlighting the Quest in the recycler view
    // contacts will come from highlighting the Contacts on the right side of the recycler
    // TODO: Where is the list data for the task items coming from?
//     - send the Quest ID --> quest ID Querys the data from AWS
    // TODO: How exactly to transport it

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parent_profile);

        setupButtons();

        sendBtn = (Button) findViewById(R.id.startquestBtn);


        sendBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

                setQuestId("687457"); // TODO: pull this from the recycler view
                sendSMSMessage();
                Log.i("----- DEEP LINK SMS ----", "DEEP LINK SENT to " + phoneNo + " message of " + message );
            }
        });
    }

    public void setupButtons (){
        Button createquest = findViewById(R.id.createquest);
        createquest.setOnClickListener(view -> this.startActivity(new Intent(this, CreateQuestActivity.class)));

        Button addplayer = findViewById(R.id.addplayer);
        addplayer.setOnClickListener(view -> this.startActivity(new Intent(this, ContactActivity.class)));

    }


//    ----- SEND SMS METHODS -----
    protected void sendSMSMessage() {

        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.SEND_SMS)
                != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.SEND_SMS)) {
            } else {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.SEND_SMS},
                        MY_PERMISSIONS_REQUEST_SEND_SMS);
            }
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode,String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_SEND_SMS: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    SmsManager smsManager = SmsManager.getDefault();
                    smsManager.sendTextMessage(phoneNo, null, message, null, null);
                    Toast.makeText(getApplicationContext(), "SMS sent.",
                            Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getApplicationContext(),
                            "SMS failed, please try again.", Toast.LENGTH_LONG).show();
                    return;
                }
            }
        }

    }

    public void setQuestId(String questId){
        this.questId = questId;
        this.message = message + questId;
    }

}