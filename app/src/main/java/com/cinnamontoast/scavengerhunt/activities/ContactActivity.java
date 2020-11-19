package com.cinnamontoast.scavengerhunt.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.generated.model.Contact;
import com.cinnamontoast.scavengerhunt.R;

public class ContactActivity extends AppCompatActivity {

//    SharedPreferences preferences;
//    SharedPreferences.Editor preferencesEditor;
    Toast toast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);


//        preferences = PreferenceManager.getDefaultSharedPreferences(this);
//        preferencesEditor = preferences.edit();

        setupButtons();
    }

    public void setupButtons (){
        Button addcontacts = findViewById(R.id.addcontacts);
        addcontacts.setOnClickListener(view -> {

            String userName = ((TextView) findViewById(R.id.playername)).getText().toString();
            String phoneNumber = ((TextView) findViewById(R.id.phonenumber)).getText().toString();

            Contact newContact = Contact.builder()
                    .userId(Amplify.Auth.getCurrentUser().getUserId())
                    .name(userName)
                    .phoneNumber(phoneNumber)
                    .build();

            toast.show();
            this.startActivity(new Intent(this, ParentProfileActivity.class));
        });
    }

    public void makeToast (){
        Context context = getApplicationContext();
        CharSequence text = "Contact Added.";
        int duration = Toast.LENGTH_SHORT;

        toast = Toast.makeText(context, text, duration);
    }

}