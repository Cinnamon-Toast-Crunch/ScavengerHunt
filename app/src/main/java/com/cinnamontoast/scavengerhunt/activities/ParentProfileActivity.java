package com.cinnamontoast.scavengerhunt.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.cinnamontoast.scavengerhunt.R;

public class ParentProfileActivity extends AppCompatActivity {

    private static final int MY_PERMISSIONS_REQUEST_SEND_SMS = 1;

    // TODO: figure out how to pass it an array of phone numbers
    // TODO: research how to pass the data for the recycler view. maybe as an intent? w/ putExtra

    String phoneNo = "+12062519102";
    String message = "https://scavengerhuntstart.page.link/start";
    Button sendBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parent_profile);

        setupButtons();

        sendBtn = (Button) findViewById(R.id.startquestBtn);

        sendBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
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

}