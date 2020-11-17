package com.cinnamontoast.scavengerhunt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import com.amazonaws.mobile.auth.ui.SignInActivity;
import com.amplifyframework.core.Amplify;

public class SignupConfirmationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_confirmation);

        ((Button) findViewById(R.id.confirmBtn)).setOnClickListener(view -> {
            String username = ((TextView)findViewById(R.id.confirmUser)).getText().toString();
            String code = ((TextView)findViewById(R.id.confirmCode)).getText().toString();

            Amplify.Auth.confirmSignUp(
                    username,
                    code,
                    result -> {
                        Log.i("Amplify.confirm", result.isSignUpComplete() ? "Confirm Signup Succeeded" :
                                "Confirm SignUp Not Complete");
                        startActivity(new Intent(SignupConfirmationActivity.this, SignInActivity.class));
                    },
                    error -> Log.e("Amplify.confirm", error.toString())
            );
        });
    }
}