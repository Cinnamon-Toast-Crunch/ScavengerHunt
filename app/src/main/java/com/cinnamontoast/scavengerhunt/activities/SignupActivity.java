package com.cinnamontoast.scavengerhunt.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.TextView;

import com.amplifyframework.auth.AuthUserAttributeKey;
import com.amplifyframework.auth.options.AuthSignUpOptions;
import com.amplifyframework.core.Amplify;
import com.cinnamontoast.scavengerhunt.R;

public class SignupActivity extends AppCompatActivity {

    SharedPreferences preferences;
    SharedPreferences.Editor prefEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        prefEdit = preferences.edit();

        signUpUser();
    }

    public void signUpUser() {
        findViewById(R.id.signUpBtn).setOnClickListener(view -> {
            // TODO: Input Verification

            String username = ((TextView) findViewById(R.id.usernameSignUp)).getText().toString();
            String password = ((TextView) findViewById(R.id.passwordSignUp)).getText().toString();
            String familyId = ((TextView) findViewById(R.id.familyIdSignUp)).getText().toString();
            String dob = ((TextView) findViewById(R.id.birthdaySignUp)).getText().toString();
            String email = ((TextView) findViewById(R.id.emailSignUp)).getText().toString();

            // TODO: Profile Pic upload + make default pic image clickable to upload / update img
            // TODO: Storing family ID Somewhere for later use?
            // TODO: get rid of Birthday field? is it necessary?

            Amplify.Auth.signUp(
                    username,
                    password,
                    AuthSignUpOptions.builder().userAttribute(AuthUserAttributeKey.email(), email).build(),
                    result -> {
                        Log.i("Amplify.signup", "Result : " + result.toString());
                        prefEdit.putString("username",username);
                        prefEdit.putString("password",password);
                        prefEdit.apply();
                        startActivity(new Intent(SignupActivity.this,
                                SignupConfirmationActivity.class));
                    },
                    error -> Log.e("Amplify.signup", "Sign Up Failed", error)
            );
        });
    }

}

