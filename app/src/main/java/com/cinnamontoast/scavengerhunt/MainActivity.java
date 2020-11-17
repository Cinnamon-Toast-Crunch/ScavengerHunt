package com.cinnamontoast.scavengerhunt;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;

import com.amplifyframework.AmplifyException;
import com.amplifyframework.api.aws.AWSApiPlugin;
import com.amplifyframework.auth.cognito.AWSCognitoAuthPlugin;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.generated.model.Quest;
import com.amplifyframework.datastore.generated.model.User;
import com.amplifyframework.storage.s3.AWSS3StoragePlugin;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements QuestAdapter.QuestListFormatter {

    ArrayList<Quest> quests = new ArrayList<>();


    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        configAWS();
        User tempUser = addFakeUser("fakeUserBob", "bob@bob.gov");
        quests.add(addFakeQuest("walmart", tempUser));
        quests.add(addFakeQuest("zoo", tempUser));
        quests.add(addFakeQuest("space station", tempUser));

        RecyclerView questRecycler = findViewById(R.id.questRecycler);
        questRecycler.setLayoutManager(new LinearLayoutManager(this));
        questRecycler.setAdapter(new QuestAdapter(quests,this));

        }
    public Quest addFakeQuest(String title, User user){
        Quest quest = Quest.builder().userId(user.getId()).title(title).build();
        return quest;
    }

    public User addFakeUser(String username, String email){
        User user = User.builder().userName(username).email(email).build();
        return user;
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
}