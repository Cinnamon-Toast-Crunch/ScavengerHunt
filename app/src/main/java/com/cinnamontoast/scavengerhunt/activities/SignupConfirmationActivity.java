package com.cinnamontoast.scavengerhunt.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import com.amplifyframework.api.graphql.model.ModelMutation;
import com.amplifyframework.auth.AuthUser;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.generated.model.User;
import com.cinnamontoast.scavengerhunt.R;

public class SignupConfirmationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_confirmation);

        ((Button) findViewById(R.id.confirmBtn)).setOnClickListener(view -> {
            String username = ((TextView)findViewById(R.id.confirmUser)).getText().toString();
            String code = ((TextView)findViewById(R.id.confirmCode)).getText().toString();

            // TODO: Confirm that this will Auto-Login the user
            // TODO: Change confirmation to redirect to profile
            Amplify.Auth.confirmSignUp(
                    username,
                    code,
                    result -> {
                        Log.i("Amplify.confirm", result.isSignUpComplete() ? "Confirm Signup Succeeded" :
                                "Confirm SignUp Not Complete");

                        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
                        String pass = preferences.getString("password", "none");
                        autoSignInOnConfirmation(username, pass);

                        // clear pass from preferences
                        SharedPreferences.Editor prefEdit = preferences.edit();
                        prefEdit.putString("password","");
                        prefEdit.apply();

                        startActivity(new Intent(SignupConfirmationActivity.this, ParentProfileActivity.class));
                    },
                    error -> Log.e("Amplify.confirm", error.toString())
            );
        });
    }

    public void autoSignInOnConfirmation(String name, String pass) {
        Amplify.Auth.signIn(
                name,
                pass,
                result -> {
                    Log.i("Amplify.login", result.isSignInComplete() ? "Sign In Succesful" :
                            "Sign in failed");
                    copySignedInUserToModel();
                },
                error -> Log.e("Amplify.login", error.toString())
        );
    }

    public void copySignedInUserToModel() {
        User newUser = User.builder()
                .userName(Amplify.Auth.getCurrentUser().getUsername())
                .email("placeholder")
                .id(Amplify.Auth.getCurrentUser().getUserId())
                .build();

        Amplify.API.mutate(ModelMutation.create(newUser),
                r -> Log.i("MyAmplify", "User Model Created"),
                e -> Log.e("MyAmplify", "Failed to duplicate User model", e));

    }
}