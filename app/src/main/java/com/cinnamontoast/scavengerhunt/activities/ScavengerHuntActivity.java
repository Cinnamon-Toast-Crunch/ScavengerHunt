package com.cinnamontoast.scavengerhunt.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.amplifyframework.api.graphql.model.ModelQuery;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.generated.model.Hint;
import com.amplifyframework.datastore.generated.model.Location;
import com.amplifyframework.datastore.generated.model.LocationInstance;
import com.amplifyframework.datastore.generated.model.Quest;
import com.amplifyframework.datastore.generated.model.Task;
import com.amplifyframework.datastore.generated.model.TaskJoiner;
import com.cinnamontoast.scavengerhunt.R;
import com.cinnamontoast.scavengerhunt.adapters.LLocationAdapter;
import com.cinnamontoast.scavengerhunt.adapters.LTaskAdapter;
import com.cinnamontoast.scavengerhunt.adapters.QuestAdapter;
import com.cinnamontoast.scavengerhunt.database.LDatabase;
import com.cinnamontoast.scavengerhunt.entities.LHint;
import com.cinnamontoast.scavengerhunt.entities.LLocation;
import com.cinnamontoast.scavengerhunt.entities.LQuest;
import com.cinnamontoast.scavengerhunt.entities.LTask;
import com.cinnamontoast.scavengerhunt.entities.relations.LQuestWithLLocations;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.dynamiclinks.FirebaseDynamicLinks;
import com.google.firebase.dynamiclinks.PendingDynamicLinkData;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ScavengerHuntActivity extends AppCompatActivity implements LTaskAdapter.LTaskListFormatter, LLocationAdapter.LLocationListFormatter {

    private static final String TAG = "no worky";
    public Quest retrievedQuest;
    public LDatabase roomDb;
    ArrayList<String> locationList = new ArrayList<>();
    public HashMap<String, Task> tasksByJoiner = new HashMap<>();
    String referenceQuestId;
    RecyclerView taskRecycler;
    Handler taskRecyclerHandler;
    ArrayList<LTask> thisLocationTaskList;

    Spinner spinner;

    RecyclerView locationRecycler;
    Handler locationRecyclerHandler;
    ArrayList<LLocation> questLocations;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scavenger_hunt);
        roomDb = Room.databaseBuilder(getApplicationContext(), LDatabase.class, "scavengerlocal")
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries().build();

        setupTaskRecycler();
        taskRecyclerHandler = createRecyclerHandler(taskRecycler);

        setupLocationRecycler();
        locationRecyclerHandler = createRecyclerHandler(locationRecycler);

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

        // Query AWS and Save it to Room
        referenceQuestId = questId;
        queryTasks();
//        getQuestFromCloud(referenceQuestId);

        // setup recycler view to listen for task updates


        // ------- Run the quest off of Room -------
        // ------- Location Spinner -------

        // ---- moving to method ---
//        LQuest recentQuest = roomDb.lQuestDao().getLastQuest();
//        Integer id = recentQuest.id;
//        LQuest currentQuest = retrieveQuestFromRoom(id);
//        for (LLocation location : currentQuest.lLocationList){
//            locationList.add(location.name);
//        }
//
//        for (String place : locationList) Log.i("LOCAL ARRAY -- ", place);
        // ----

//        locationList.add("one place");
//        locationList.add("another place");
        spinner = findViewById(R.id.locationSpinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, locationList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);



//        spinner.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                String potato = parent.getItemAtPosition(position).toString();
//                Log.i("POTOTA", potato);
//
//            }
//        });

//        Button spinnerButton = findViewById(R.id.spinnerButton);
//        spinnerButton.setOnClickListener(v -> {
//            String selectedLocation = String.valueOf(spinner.getSelectedItem());
//            Log.i("SelectedLocation", selectedLocation);
//        });
    }

    public void setupLocationRecycler() {
        questLocations = new ArrayList<>();
        locationRecycler = findViewById(R.id.lLocationRecycler);
        locationRecycler.setLayoutManager(new LinearLayoutManager(this));
        locationRecycler.setAdapter(new LLocationAdapter(questLocations, this));
    }

    public void setupTaskRecycler() {
        thisLocationTaskList = new ArrayList<>();
        taskRecycler = findViewById(R.id.lTaskRecycler);
        taskRecycler.setLayoutManager(new LinearLayoutManager(this));
        taskRecycler.setAdapter(new LTaskAdapter(thisLocationTaskList, this));
    }

    public void activateQuest() {
        // ------- Run the quest off of Room -------
        LQuest recentQuest = roomDb.lQuestDao().getLastQuest();
        Integer id = recentQuest.id;
        LQuest currentQuest = retrieveQuestFromRoom(id);
        for (LLocation location : currentQuest.lLocationList){
            locationList.add(location.name);
            questLocations.add(location);
        }
        locationRecyclerHandler.sendEmptyMessage(1);

        // ------- Location Spinner -------
        for (String place : locationList) Log.i("LOCAL ARRAY -- ", place);

    }

    public Handler createRecyclerHandler(RecyclerView rView) {
        Handler handler = new Handler(Looper.getMainLooper(), new Handler.Callback() {
            @Override
            public boolean handleMessage(@NonNull Message msg) {
                rView.getAdapter().notifyDataSetChanged();
                return false;
            }
        });
        return handler;
    }


    public void queryTasks() {
        Amplify.API.query(ModelQuery.list(
                Task.class),
                response -> {
                    Log.i("taskjoinerQuery", response.toString());

                    for (Task task : response.getData()) {
                        Log.i("TaskBeforeJoiner", task.toString());
                        tasksByJoiner.put(task.getId(), task);
                    }
                    Log.i("taskjoinerQuery", tasksByJoiner.toString());
                    getQuestFromCloud(referenceQuestId);
                    },
                error -> {
                    Log.e("taskjoinerQuery", "oh no", error);
                }
                );
    }

    public void addFakeRoomData() {
        LQuest quest = new LQuest("forest quest");
        roomDb.lQuestDao().saveLQuest(quest);
        quest = roomDb.lQuestDao().getLastQuest();

        LLocation location1 = new LLocation("forage the lion's mane mushroom", 15, (float)47.8609, (float)129.9348, "", quest.id);
        LLocation location2 = new LLocation("commune with the great horned owl", 50, (float)47.8609, (float)129.9348, "", quest.id);
        roomDb.lLocationDao().saveLLocation(location1);
        roomDb.lLocationDao().saveLLocation(location2);

        LTask task1loc1 = new LTask("you must find the mushroom on a pine tree", "look for needles and duff", false, 15, "img.url", 1);
        LTask task2loc1 = new LTask("find the owl and learn from her", "she is in the trees", false, 50, "img.url", 1);
        roomDb.lTaskDao().saveLTask(task1loc1);
        roomDb.lTaskDao().saveLTask(task2loc1);

        LHint hint1task1 = new LHint("Check that big tree over there", 1);
        LHint hint2task1 = new LHint("Not that tree. The other one", 1);
        roomDb.lHintDao().saveLHint(hint1task1);
        roomDb.lHintDao().saveLHint(hint2task1);

//        List<LQuest> questList = roomDb.lQuestDao().getAll();
//        List<LQuestWithLLocations> questLocationsList = roomDb.lQuestDao().getLQuestsWithLocations();
//        Log.i("room", questLocationsList.toString());

//        LQuest uniqueQuest = retrieveQuestFromRoom(1);
//        Log.i("room", uniqueQuest.toString());
    }

    public void copyQuestToLocal(){
        LQuest quest = new LQuest(retrievedQuest.getTitle());
        roomDb.lQuestDao().saveLQuest(quest);
        quest = roomDb.lQuestDao().getLastQuest();
        Log.i("incomingLocations", retrievedQuest.getLocations().toString());
        for (LocationInstance location : retrievedQuest.getLocations()) {
            copyLocationToLocal(location, quest.id);
        }
    }

    public void copyLocationToLocal(LocationInstance locationInstance, int lastQuestId){
        LLocation location = new LLocation(
                locationInstance.getName(),
                locationInstance.getTotalPoints() != null ? locationInstance.getTotalPoints() : 0,
                locationInstance.getLat() != null ? locationInstance.getLat() : (float) 1,
                locationInstance.getLon() != null ? locationInstance.getLon() : (float) 1,
                locationInstance.getTaskList(),
                lastQuestId);
        roomDb.lLocationDao().saveLLocation(location);
        location = roomDb.lLocationDao().getLastLocation();
        String[] taskIdArray = locationInstance.getTaskList().split(",");
        Log.i("receivedLocationInstance", locationInstance.toString());
        Log.i("newLocation", location.toString());
        for (String taskJoiner : taskIdArray) {
            Log.i("incomingJoiner", taskJoiner.toString());
            Task task = tasksByJoiner.get(taskJoiner);
            copyTaskToLocal(task, location.id);
        }
    }

    public void copyTaskToLocal(Task task, int lastLocationId) {
        Log.i("incomingTask", task.toString());
        LTask lTask = new LTask(task.getObjective(),
                task.getInstruction(),
                task.getCompleted(),
                task.getPointValue() != null ? task.getPointValue() : 1,
                "",
                lastLocationId);
        roomDb.lTaskDao().saveLTask(lTask);
        LTask recentTask = roomDb.lTaskDao().getLastTask();
        for (Hint hint : task.getHints()) {
            copyHintToLocal(hint, recentTask.id);
        }
    }

    public void copyHintToLocal(Hint hint, int lastTaskId) {
        LHint lHint = new LHint(
                hint.getContent(),
                lastTaskId);
    }

    public LQuest retrieveQuestFromRoom(int questId){
        List<LQuestWithLLocations> questFromDatabase = roomDb.lQuestDao().getLQuestsWithLocations();
        LQuest newQuest = null;
        for(LQuestWithLLocations q : questFromDatabase){
            if(q.lQuest.id == questId){
                newQuest = new LQuest(q);
            }
        }
        return newQuest;
    }

    //Grabs the questId
    public void getQuestFromCloud(String questId){
        Amplify.API.query(ModelQuery.get(Quest.class, questId),
                response -> {
                    Log.i("logggg", "got quest");
                    retrievedQuest = response.getData();
                    Log.i("logggg", retrievedQuest.toString());
                    Log.i("logggg", retrievedQuest.getLocations().toString());
                    copyQuestToLocal();
                    LQuest check = roomDb.lQuestDao().getLastQuest();
                    Log.i("logggg", check.toString());
                    activateQuest();
                },
                error -> {Log.e("logggg", "failed to get quest", error);
                });
    }

    @Override
    public void lTaskFormatter(LTask lTask) {
        
    }

    @Override
    public void lTaskHighlighter(View lTaskView, LTask lTask) {

    }

    @Override
    public void lLocationFormatter(LLocation lLocation) {
        thisLocationTaskList.clear();
        for (LTask task : lLocation.lTaskList) thisLocationTaskList.add(task);
        taskRecyclerHandler.sendEmptyMessage(1);
    }

    @Override
    public void lLocationHighlighter(View lLocationView, LLocation lLocation) {

    }
}