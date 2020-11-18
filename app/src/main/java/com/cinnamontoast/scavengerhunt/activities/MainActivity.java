package com.cinnamontoast.scavengerhunt.activities;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;

import android.widget.Button;
import android.widget.TextView;


import com.amplifyframework.AmplifyException;
import com.amplifyframework.api.aws.AWSApiPlugin;
import com.amplifyframework.auth.cognito.AWSCognitoAuthPlugin;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.generated.model.Contact;
import com.amplifyframework.datastore.generated.model.Location;
import com.amplifyframework.datastore.generated.model.Quest;
import com.amplifyframework.datastore.generated.model.User;
import com.amplifyframework.storage.s3.AWSS3StoragePlugin;
import com.cinnamontoast.scavengerhunt.adapters.LocationAdapter;
import com.cinnamontoast.scavengerhunt.adapters.QuestAdapter;
import com.cinnamontoast.scavengerhunt.R;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements QuestAdapter.QuestListFormatter, LocationAdapter.LocationListFormatter {

    ArrayList<Quest> quests = new ArrayList<>();
    ArrayList<Location> locations = new ArrayList<>();


    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        configAWS();

//        ---- Recycler View Stuff ----
//        User tempUser = addFakeUser("fakeUserBob", "bob@bob.gov");
//        quests.add(addFakeQuest("walmart", tempUser));
//        quests.add(addFakeQuest("zoo", tempUser));
//        quests.add(addFakeQuest("space station", tempUser));
//
//        RecyclerView questRecycler = findViewById(R.id.questRecycler);
//        questRecycler.setLayoutManager(new LinearLayoutManager(this));
//        questRecycler.setAdapter(new QuestAdapter(quests,this));
//
//        Location tempLocation = addFakeLocation("space station", tempUser);
//        locations.add(addFakeLocation("space station", tempUser));
//        locations.add(addFakeLocation("the moon", tempUser));
//        locations.add(addFakeLocation("low earth orbit", tempUser));
//
//        RecyclerView locationRecycler = findViewById(R.id.locationRecycler);
//        locationRecycler.setLayoutManager(new LinearLayoutManager(this));
//        locationRecycler.setAdapter(new LocationAdapter(locations, this));
//
//        Hunt tempHunt = addFakeHunt("find the freeze dried ice cream", 10, tempLocation);
//        hunts.add(addFakeHunt("pilot space station", 15, tempLocation));
//        hunts.add(addFakeHunt("visit the moon", 25, tempLocation));
//        hunts.add(addFakeHunt("enter low earth orbit", 50,tempLocation));
//
//        RecyclerView huntRecycler = findViewById(R.id.huntRecycler);
//        huntRecycler.setLayoutManager(new LinearLayoutManager(this));
//        huntRecycler.setAdapter(new HuntAdapter(hunts, this));

        setupButtons();

    }

    public Quest addFakeQuest(String title, User user) {
        Quest quest = Quest.builder().userId(user.getId()).title(title).build();
        return quest;
    }

    public User addFakeUser(String username, String email) {
        User user = User.builder().userName(username).email(email).build();
        return user;
    }

    public Location addFakeLocation(String name, User user) {
        Location location = Location.builder().userId(user.getId()).name(name).build();
        return location;
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


    @Override
    public void questFormatter(Quest quest) {

    }

    @Override
    public void locationFormatter(Location location) {

    }

    public void setupButtons() {
        Button login = findViewById(R.id.login);
        login.setOnClickListener(view -> this.startActivity(new Intent(this, SignInActivity.class)));

        Button signup = findViewById(R.id.signup);
        signup.setOnClickListener(view -> this.startActivity(new Intent(this, SignupActivity.class)));


    }




}// End of on create