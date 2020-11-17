package com.cinnamontoast.scavengerhunt;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;

import com.amazonaws.amplify.generated.graphql.GetUserQuery;
import com.amplifyframework.AmplifyException;
import com.amplifyframework.api.aws.AWSApiPlugin;
import com.amplifyframework.api.graphql.model.ModelMutation;
import com.amplifyframework.auth.cognito.AWSCognitoAuthPlugin;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.generated.model.User;
import com.amplifyframework.storage.s3.AWSS3StoragePlugin;
import com.apollographql.apollo.ApolloCall;
import com.apollographql.apollo.ApolloClient;
import com.apollographql.apollo.api.Response;
import com.apollographql.apollo.exception.ApolloException;

import org.jetbrains.annotations.NotNull;

public class MainActivity extends AppCompatActivity {


    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        configAWS();

        User userTest = User.builder()
                .userName("Bob")
                .email("test@test.com")
                .build();

//        Amplify.API.mutate(ModelMutation.create(userTest),
//                response -> Log.i("MyAmplify", "user created"),
//                error -> Log.e("MyAmplify", "failed to create user"));

        ApolloClient apolloClient = ApolloClient.builder()
                .serverUrl("https://sjb5rvbnzvdfxjzab3fa3zu23i.appsync-api.us-west-2.amazonaws.com/graphql")
                .c
                .build();
//

        apolloClient.query(new GetUserQuery("7d283af0-16c0-4951-a2aa-6eb55691f3cc"))
                .enqueue(new ApolloCall.Callback<GetUserQuery.Data>() {
                    @Override
                    public void onResponse(@NotNull Response<GetUserQuery.Data> response) {
                        Log.e("Apollo", "Launch site: " + response.getData().getUser().email());
                    }

                    @Override
                    public void onFailure(@NotNull ApolloException e) {
                        Log.e("Apollo", "Error", e);
                    }
                });
    }




    public void configAWS() {
        //configAWS();
        try {
            Amplify.addPlugin(new AWSS3StoragePlugin());
            Amplify.addPlugin(new AWSCognitoAuthPlugin());
            Amplify.addPlugin(new AWSApiPlugin());
            Amplify.configure(getApplicationContext());
            Log.i("MyAmplifyApp", "Initialized Amplify");
        } catch (AmplifyException error) {
            Log.e("MyAmplifyApp", "Could not initialize Amplify", error);
        }


    }
}