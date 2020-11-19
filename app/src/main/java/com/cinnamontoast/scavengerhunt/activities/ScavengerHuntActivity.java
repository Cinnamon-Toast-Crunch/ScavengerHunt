package com.cinnamontoast.scavengerhunt.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

import com.cinnamontoast.scavengerhunt.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.dynamiclinks.FirebaseDynamicLinks;
import com.google.firebase.dynamiclinks.PendingDynamicLinkData;

public class ScavengerHuntActivity extends AppCompatActivity {

    private static final String TAG = "no worky";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scavenger_hunt);

        Log.i("DEEP LINKS", "---- Deep Link Works ----");

        // Handle Hunt DeepLink
        Intent appLinkIntent = getIntent();
        String appLinkAction = appLinkIntent.getAction();
        Uri appLinkData = appLinkIntent.getData();

        // This pulls the unique Quest ID from the query param link
        Intent intent = getIntent();
        String action = intent.getAction();
        Uri data = intent.getData();
        String questId = data.getQueryParameter("questID");

        Log.i("-- FROM LINK ---", "Custom questId is : " + questId);

        // Grab the Quest ID from the Intent
        // Query AWS
        // Save it to Room & run the quest off of Room

    }
}