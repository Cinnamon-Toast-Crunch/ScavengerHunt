package com.cinnamontoast.scavengerhunt.activities;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Query;
import androidx.room.Room;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;


import com.amplifyframework.AmplifyException;
import com.amplifyframework.api.aws.AWSApiPlugin;
import com.amplifyframework.api.graphql.model.ModelMutation;
import com.amplifyframework.auth.AuthUser;
import com.amplifyframework.auth.cognito.AWSCognitoAuthPlugin;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.generated.model.Contact;
import com.amplifyframework.datastore.generated.model.Location;
import com.amplifyframework.datastore.generated.model.LocationInstance;
import com.amplifyframework.datastore.generated.model.Quest;
import com.amplifyframework.datastore.generated.model.Task;
import com.amplifyframework.datastore.generated.model.TaskJoiner;
import com.amplifyframework.datastore.generated.model.User;
import com.amplifyframework.storage.s3.AWSS3StoragePlugin;
import com.cinnamontoast.scavengerhunt.adapters.LocationAdapter;
import com.cinnamontoast.scavengerhunt.adapters.QuestAdapter;
import com.cinnamontoast.scavengerhunt.R;
import com.cinnamontoast.scavengerhunt.database.LDatabase;
import com.cinnamontoast.scavengerhunt.entities.LHint;
import com.cinnamontoast.scavengerhunt.entities.LLocation;
import com.cinnamontoast.scavengerhunt.entities.LQuest;
import com.cinnamontoast.scavengerhunt.entities.LTask;
import com.cinnamontoast.scavengerhunt.entities.relations.LLocationWithLTasks;
import com.cinnamontoast.scavengerhunt.entities.relations.LQuestWithLLocations;
import com.cinnamontoast.scavengerhunt.entities.relations.LTaskWithLHints;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements QuestAdapter.QuestListFormatter, LocationAdapter.LocationListFormatter {

    ArrayList<Quest> quests = new ArrayList<>();
    ArrayList<Location> locations = new ArrayList<>();
//    LDatabase roomDb;


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

        //still working in onCreate for testing. Dummy data below
//        roomDb = Room.databaseBuilder(getApplicationContext(), LDatabase.class, "scavengerlocal")
//        .fallbackToDestructiveMigration()
//                .allowMainThreadQueries().build();
//        LQuest quest = new LQuest("forest quest");
//        roomDb.lQuestDao().saveLQuest(quest);
//
//        LLocation location1 = new LLocation("forage the lion's mane mushroom", 15, (float)47.8609, (float)129.9348, 1);
//        LLocation location2 = new LLocation("commune with the great horned owl", 50, (float)47.8609, (float)129.9348, 1);
//        roomDb.lLocationDao().saveLLocation(location1);
//        roomDb.lLocationDao().saveLLocation(location2);
//
//        LTask task1loc1 = new LTask("you must find the mushroom on a pine tree", "look for needles and duff", false, 15, "img.url", 1);
//        LTask task2loc1 = new LTask("find the owl and learn from her", "she is in the trees", false, 50, "img.url", 1);
//        roomDb.lTaskDao().saveLTask(task1loc1);
//        roomDb.lTaskDao().saveLTask(task2loc1);
//
//        LHint hint1task1 = new LHint("Check that big tree over there", 1);
//        LHint hint2task1 = new LHint("Not that tree. The other one", 1);
//        roomDb.lHintDao().saveLHint(hint1task1);
//        roomDb.lHintDao().saveLHint(hint2task1);
//
//        List<LQuest> questList = roomDb.lQuestDao().getAll();
//        List<LQuestWithLLocations> questLocationsList = roomDb.lQuestDao().getLQuestsWithLocations();
//        Log.i("room", questLocationsList.toString());

//        LQuest uniqueQuest = retrieveQuestFromRoom(1);
//        Log.i("room", uniqueQuest.toString());

        // ------ adding test data to dynamoDB ---------
//        Quest quest1 = Quest.builder().userId(Amplify.Auth.getCurrentUser().getUserId())
//                .title("space quest").build();
//        Quest quest2 = Quest.builder().userId(Amplify.Auth.getCurrentUser().getUserId())
//                .title("forest quest").build();
//
//        LocationInstance location1 = LocationInstance.builder()
//                .questId(quest1.getId())
//                .name("moon").build();
//        LocationInstance location2 = LocationInstance.builder()
//                .questId(quest1.getId())
//                .name("mars").build();
//        LocationInstance location3 = LocationInstance.builder()
//                .questId(quest2.getId())
//                .name("rain forest").build();
//
//        Amplify.API.mutate(ModelMutation.create(quest1), r -> {}, e -> {});
//        Amplify.API.mutate(ModelMutation.create(quest2), r -> {}, e -> {});
//        Amplify.API.mutate(ModelMutation.create(location1), r -> {}, e -> {});
//        Amplify.API.mutate(ModelMutation.create(location2), r -> {}, e -> {});
//        Amplify.API.mutate(ModelMutation.create(location3), r -> {}, e -> {});
//
//
//        Location location = Location.builder()
//                .userId(Amplify.Auth.getCurrentUser().getUserId()).name("snoqualmie").build();
//
//        Task task = Task.builder()
//                .locationId(location.getId()).objective("go snowshoeing").completed(false).build();
//
//        Task task2 = Task.builder()
//                .locationId(location.getId()).objective("find a snowy owl").completed(true).build();
//
//        Amplify.API.mutate(ModelMutation.create(location), r -> {}, e -> {});
//        Amplify.API.mutate(ModelMutation.create(task), r -> {}, e -> {});
//        Amplify.API.mutate(ModelMutation.create(task2), r -> {}, e -> {});
//        TaskJoiner join1 = TaskJoiner.builder().locationInstance(location1).task(task).build();
//        TaskJoiner join2 = TaskJoiner.builder().locationInstance(location1).task(task2).build();
//        Amplify.API.mutate(ModelMutation.create(join1), r -> {}, e -> {});
//        Amplify.API.mutate(ModelMutation.create(join2), r -> {}, e -> {});



        checkIfUserSignedIn();

    }

    public void checkIfUserSignedIn() {
        if (Amplify.Auth.getCurrentUser() != null) {
            startActivity(new Intent(MainActivity.this, ParentProfileActivity.class));
        }
    }

//    public LQuest retrieveQuestFromRoom(int questId){
//        List<LQuestWithLLocations> questFromDatabase = roomDb.lQuestDao().getLQuestsWithLocations();
//        LQuest newQuest = null;
//        for(LQuestWithLLocations q : questFromDatabase){
//            if(q.lQuest.id == questId){
//                newQuest = new LQuest(q);
//            }
//        }
//        return newQuest;
//    }

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
    public void questHighlighter(View questView, Quest quest) {

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