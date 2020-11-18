package com.cinnamontoast.scavengerhunt.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import com.amplifyframework.core.Amplify;
import com.cinnamontoast.scavengerhunt.R;

public class SignInActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        final SharedPreferences.Editor prefEdit = preferences.edit();

        ((Button) findViewById(R.id.SignInBtn)).setOnClickListener(view -> {
            String username = ((TextView) findViewById(R.id.usernameSignIn)).getText().toString();
            String password = ((TextView) findViewById(R.id.loginPassword)).getText().toString();

            
            Amplify.Auth.signIn(
                    username,
                    password,
                    result -> {
                        Log.i("Amplify.login", result.isSignInComplete() ? "Sign In Succesful" :
                                "Sign in failed");
                        prefEdit.putString("username",username);
                        prefEdit.apply();
                        startActivity(new Intent(SignInActivity.this, MainActivity.class));
                    },
                    error -> Log.e("Amplify.login", error.toString())
            );
        });

    }
}