package com.cinnamontoast.scavengerhunt.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.ColorSpace;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.amplifyframework.api.graphql.model.ModelMutation;
import com.amplifyframework.api.graphql.model.ModelQuery;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.generated.model.Contact;
import com.amplifyframework.datastore.generated.model.Hint;
import com.amplifyframework.datastore.generated.model.Location;
import com.amplifyframework.datastore.generated.model.LocationInstance;
import com.amplifyframework.datastore.generated.model.Quest;
import com.amplifyframework.datastore.generated.model.Task;
import com.amplifyframework.datastore.generated.model.TaskJoiner;
import com.cinnamontoast.scavengerhunt.R;
import com.cinnamontoast.scavengerhunt.adapters.LocationAdapter;
import com.cinnamontoast.scavengerhunt.adapters.TaskAdapter;

import java.util.ArrayList;

import static androidx.core.content.ContextCompat.getSystemService;

public class CreateQuestActivity extends AppCompatActivity implements LocationAdapter.LocationListFormatter,
        TaskAdapter.TaskListFormatter {

    ArrayList<Task> selectedTasks = new ArrayList<>();
    ArrayList<Task> viewTasks = new ArrayList<>();
    ArrayList<Location> viewLocation = new ArrayList<>();
    ArrayList<Location> questLocations = new ArrayList<>();
    ArrayList<String> hintsArr = new ArrayList<>();

    Location selectedLocation;
    PopupWindow popupWindow;
    LayoutInflater layoutInflater;
    ConstraintLayout constraintLayout;
    RecyclerView taskRecycler;
    RecyclerView locationRecycler;
    RecyclerView questRecycler;
    Handler taskHandler;
    Handler locationHandler;
    Handler questHandler;

    int pointTotal;
    Quest emptyQuest;

    Button itemButton;

    View lastView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_quest);


        setQuestRecycler();
        setTaskRecycler();
        setLocationRecycler();
        taskHandler = createHandler(taskRecycler);
        locationHandler = createHandler(locationRecycler);
        questHandler = createHandler(questRecycler);
        buildEmptyQuest();
        itemButton = findViewById(R.id.makeNewItem);
        itemButton.setVisibility(View.INVISIBLE);

        populateLocations();
        addToQuest();
        saveQuest();
        newItemPopup();
        newLocPopup();

    }

    //============ Handler ===========================
    public Handler createHandler(RecyclerView recyclerView) {
        Handler handler = new Handler(Looper.getMainLooper(),
                new Handler.Callback() {
                    @Override
                    public boolean handleMessage(@NonNull Message msg) {

                        recyclerView.getAdapter().notifyDataSetChanged();
                        return false;
                    }
                });
        return handler;
    }

    //=============== Set up Recyclers ======================
    public void setTaskRecycler(){
        taskRecycler = findViewById(R.id.taskRecycler);
        taskRecycler.setLayoutManager(new LinearLayoutManager(this));
        taskRecycler.setAdapter(new TaskAdapter(viewTasks, this));
    }

    public void setQuestRecycler(){
        questRecycler = findViewById(R.id.questPreview);
        questRecycler.setLayoutManager(new LinearLayoutManager(this));
        questRecycler.setAdapter(new LocationAdapter(questLocations, this));
    }

    public void setLocationRecycler (){
        locationRecycler = findViewById(R.id.locationRecycler);
        locationRecycler.setLayoutManager(new LinearLayoutManager(this));
        locationRecycler.setAdapter(new LocationAdapter(viewLocation, this));
    }

    //=============== Create location popup =================
    public void newLocPopup() {
        Button newLoc = findViewById(R.id.locationPopup);

        constraintLayout = (ConstraintLayout) findViewById(R.id.createquest);

        newLoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layoutInflater = (LayoutInflater) getApplicationContext().getSystemService(LAYOUT_INFLATER_SERVICE);
                ViewGroup container = (ViewGroup) layoutInflater.inflate(R.layout.location_popup, null);

                popupWindow = new PopupWindow(container, 900, 1000, true);
                popupWindow.showAtLocation(constraintLayout, Gravity.NO_GRAVITY, 100, 600);

                //------- Create location button
                Button createloc = container.findViewById(R.id.createNewLoc);
                createloc.setOnClickListener(v2 ->{

                    String name = ((TextView) container.findViewById(R.id.newLocationName)).getText().toString();
                    String lat = ((TextView) container.findViewById(R.id.newLat)).getText().toString();
                    Float latFloat;
                    try {
                        latFloat = Float.parseFloat(lat);
                    }catch(Exception e){
                        latFloat = (float)0.0;
                    }
                    String lon = ((TextView) container.findViewById(R.id.newLon)).getText().toString();
                    Float lonFloat;
                    try {
                        lonFloat = Float.parseFloat(lon);
                    }catch(Exception e){
                        lonFloat = (float)0.0;
                    }


                    Location newLoc = Location.builder()
                            .userId(Amplify.Auth.getCurrentUser().getUserId())
                            .name(name)
                            .lat(latFloat)
                            .lon(lonFloat)
                            .build();

                    Amplify.API.mutate(ModelMutation.create(newLoc),
                            response -> Log.i("MyAmplify", "Location created"),
                            error -> Log.e("MyAmplify", "Failed to create Location"));

                    selectedLocation = null;
                    setLocationRecycler();
                    populateLocations();
                    selectedTasks.clear();
                    pointTotal = 0;
                    viewTasks.clear();
                    setTaskRecycler();
                    itemButton.setVisibility(View.INVISIBLE);
                    popupWindow.dismiss();
                });
            }
        });
    }

    //=============== Create new item popup =================
    public void newItemPopup(){
        Button newItem = findViewById(R.id.makeNewItem);

        constraintLayout = (ConstraintLayout) findViewById(R.id.createquest);

        newItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layoutInflater = (LayoutInflater) getApplicationContext().getSystemService(LAYOUT_INFLATER_SERVICE);
                ViewGroup container = (ViewGroup) layoutInflater.inflate(R.layout.popup_window, null);

                popupWindow = new PopupWindow(container, 900, 1000, true);
                popupWindow.showAtLocation(constraintLayout,Gravity.NO_GRAVITY, 100, 600);

                //--------- Save and Stay button
                Button saveAndStay = container.findViewById(R.id.anotherItem);
                saveAndStay.setOnClickListener(view ->{

                    String item = ((TextView) container.findViewById(R.id.newItemName)).getText().toString();
                    String pointsString = ((TextView) container.findViewById(R.id.newItemPoints)).getText().toString();
//                    int points = Integer.parseInt(pointsString);

                    int points = 2;
                    try {
                        points = Integer.parseInt(pointsString);
                    }catch(Exception e){
                        Log.i("MyAmplify", "Going with the default value.");
                    }

                    Task newTask = Task.builder()
                            .locationId(selectedLocation.getId())
                            .objective(item)
                            .completed(false)
                            .pointValue(points)
                            .build();

                    Amplify.API.mutate(ModelMutation.create(newTask),
                            response -> Log.i("MyAmplify", "Task created"),
                            error -> Log.e("MyAmplify", "Failed to create task"));

                    if(hintsArr.size() > 0){
                        for(String hint : hintsArr){
                            Hint newHint = Hint.builder()
                                    .taskId(newTask.getId())
                                    .content(hint)
                                    .build();

                            Amplify.API.mutate(ModelMutation.create(newHint),
                                    response -> Log.i("MyAmplify", "Hint created"),
                                    error -> Log.e("MyAmplify", "Failed to create hint"));
                        }
                    }

                    String hint = ((TextView) container.findViewById(R.id.hint)).getText().toString();
                    if(hint != ""){
                        Hint newHint = Hint.builder()
                                .taskId(newTask.getId())
                                .content(hint)
                                .build();

                        Amplify.API.mutate(ModelMutation.create(newHint),
                                response -> Log.i("MyAmplify", "Hint created"),
                                error -> Log.e("MyAmplify", "Failed to create hint"));
                    }
                    hintsArr.clear();
                    EditText nameInput = container.findViewById(R.id.newItemName);
                    EditText pointInput = container.findViewById(R.id.newItemPoints);
                    EditText hintInput = container.findViewById(R.id.hint);
                    hintInput.getText().clear();
                    pointInput.getText().clear();
                    nameInput.getText().clear();
                });

                //--------- Save and go button
                Button saveAndGo = container.findViewById(R.id.saveAndReturn);
                saveAndGo.setOnClickListener(view ->{

                    String item = ((TextView) container.findViewById(R.id.newItemName)).getText().toString();
                    String pointsString = ((TextView) container.findViewById(R.id.newItemPoints)).getText().toString();
//                    int points = Integer.parseInt(pointsString);

                    int points = 2;
                    try {
                        points = Integer.parseInt(pointsString);
                    }catch(Exception e){
                        Log.i("MyAmplify", "Going with the default value.");
                    }

                    Task newTask = Task.builder()
                            .locationId(selectedLocation.getId())
                            .objective(item)
                            .completed(false)
                            .pointValue(points)
                            .build();

                    Amplify.API.mutate(ModelMutation.create(newTask),
                            response -> Log.i("MyAmplify", "Task created"),
                            error -> Log.e("MyAmplify", "Failed to create task"));

                    if(hintsArr.size() > 0){
                        for(String hint : hintsArr){
                            Hint newHint = Hint.builder()
                                    .taskId(newTask.getId())
                                    .content(hint)
                                    .build();

                            Amplify.API.mutate(ModelMutation.create(newHint),
                                    response -> Log.i("MyAmplify", "Hint created"),
                                    error -> Log.e("MyAmplify", "Failed to create hint"));
                        }
                    }

                    String hint = ((TextView) container.findViewById(R.id.hint)).getText().toString();
                    if(hint != ""){
                        Hint newHint = Hint.builder()
                                .taskId(newTask.getId())
                                .content(hint)
                                .build();

                        Amplify.API.mutate(ModelMutation.create(newHint),
                                response -> Log.i("MyAmplify", "Hint created"),
                                error -> Log.e("MyAmplify", "Failed to create hint"));
                    }
                    viewTasks.clear();
                    populateTasks(selectedLocation);
                   popupWindow.dismiss();
                });

                //------------- add hint
                Button addhint = container.findViewById(R.id.addHint);
                addhint.setOnClickListener(v1 -> {
                    String hint = ((TextView) container.findViewById(R.id.hint)).getText().toString();
                    hintsArr.add(hint);
                    EditText hintInput = container.findViewById(R.id.hint);
                    hintInput.getText().clear();
                });
            }
        });
    }

    //=============== Populate quest preview ================
    public void questPreview(){
        //TODO remove this
        questHandler.sendEmptyMessage(1);
//        taskRecycler = findViewById(R.id.questPreview);
//        taskRecycler.setLayoutManager(new LinearLayoutManager(this));
//        taskRecycler.setAdapter(new LocationAdapter(questLocations, this));

    }

    //================= Build empty quest ====================
    public void buildEmptyQuest(){
        emptyQuest = Quest.builder()
                .userId(Amplify.Auth.getCurrentUser().getUserId())
                .title("")
                .build();
    }

    //================= Populate Location Recycler ===========
    public void populateLocations(){
        Amplify.API.query(
                ModelQuery.list(Location.class),
                response -> {
                    viewLocation.clear();
                    for(Location location : response.getData()){

                            viewLocation.add(location);
                    }
                    locationHandler.sendEmptyMessage(1);
                    Log.i("MyAmplify", "tasks array created");
                },
                error -> Log.e("MyAmplify", "Failed to load tasks")
        );
    }

    //======== Grab Location from recyclerview ===========
    @Override
    public void locationFormatter(Location location) {

        selectedLocation = location;
        selectedTasks.clear();
        viewTasks.clear();
        pointTotal = 0;

        setTaskRecycler();
        populateTasks(location);
        itemButton.setVisibility(View.VISIBLE);
    }


    @Override
    public void locationHighlighter(View locationView, Location location) {
        if(lastView != null){
            lastView.setBackgroundColor(Color.TRANSPARENT);
        }
        lastView = locationView;
        locationView.setBackgroundColor(Color.LTGRAY);
    }

    //================= Populate Task recycler =============
    public void populateTasks(Location location){
        Amplify.API.query(
                ModelQuery.list(Task.class),
                response -> {
                    for(Task task : response.getData()){
                        if(task.getLocationId().equals(location.getId())){
                            viewTasks.add(task);
                        }
                    }
                    //TODO task recycler
                    taskHandler.sendEmptyMessage(1);

                    Log.i("MyAmplify", "tasks array created");

                },
                error -> Log.e("MyAmplify", "Failed to load tasks")
        );
    }

    //====================== Build Task Arraylist ==========
    @Override
    public void taskFormatter(Task task) {
       if(selectedTasks.contains(task)) {
           selectedTasks.remove(task);
           System.out.println("removed task " + selectedTasks.size());

           pointTotal -= task.getPointValue();
       }else{
           selectedTasks.add(task);
           System.out.println("added task " + selectedTasks.size());
           pointTotal += task.getPointValue();
       }
    }

    @Override
    public void taskHighlighter(View taskView, Task task) {
      if(selectedTasks.contains(task)){
          taskView.setBackgroundColor(Color.TRANSPARENT);
      } else {
          taskView.setBackgroundColor(Color.LTGRAY);
      }
    }

    //=========== Save Quest =================================
    public void saveQuest(){
        Button saveQuest = findViewById(R.id.saveQuest);
        saveQuest.setOnClickListener(v -> {

            String title = ((TextView)findViewById(R.id.inputQuestName)).getText().toString();
            if(title.isEmpty()){
                Context context = getApplicationContext();
                CharSequence text = "Please enter a name for this quest.";
                int duration = Toast.LENGTH_LONG;

                Toast toast = Toast.makeText(context, text, duration);
                toast.show();

            }else if (questLocations.size() == 0) {

                Context context = getApplicationContext();
                CharSequence text = "Your quest is empty, please add a location.";
                int duration = Toast.LENGTH_LONG;

                Toast toast = Toast.makeText(context, text, duration);
                toast.show();

            }else {

                Quest newQuest = Quest.builder()
                        .userId(Amplify.Auth.getCurrentUser().getUserId())
                        .title(title)
                        .id(emptyQuest.getId())
                        .build();

                Amplify.API.mutate(ModelMutation.create(newQuest),
                        response -> Log.i("MyAmplify", "Quest created"),
                        error -> Log.e("MyAmplify", "Failed to create quest"));

                this.startActivity(new Intent(this, ParentProfileActivity.class));
            }
        });

    }

    //=========== Add location/task to quest =================
    public void addToQuest(){

        Button addToQuest = findViewById(R.id.addToQuest);
        addToQuest.setOnClickListener(v -> {

        if(selectedTasks.size() == 0){
            Context context = getApplicationContext();
            CharSequence text = "Please select an item for this location.";
            int duration = Toast.LENGTH_LONG;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }else {
            String taskIds = "";
            int counter = 0;
            for(Task task : selectedTasks){
                if(counter == 0) {
                    taskIds = task.getId();
                    counter++;
                }else {
                    taskIds += "," + task.getId();
                }
            }

        LocationInstance instance = LocationInstance.builder()
                .questId(emptyQuest.getId())
                .name(selectedLocation.getName())
                .lat(selectedLocation.getLat())
                .lon(selectedLocation.getLon())
                .totalPoints(pointTotal)
                .taskList(taskIds)
                .build();

            Amplify.API.mutate(ModelMutation.create(instance),
                    response -> Log.i("MyAmplify", "Instance Task"),
                    error -> Log.e("MyAmplify", "Failed to create instance"));

        for(Task task : selectedTasks){
            TaskJoiner joiner = TaskJoiner.builder()
                    .locationInstance(instance)
                    .task(task)
                    .build();

            Amplify.API.mutate(ModelMutation.create(joiner),
                    response -> Log.i("MyAmplify", "Joined Task"),
                    error -> Log.e("MyAmplify", "Failed to join"));
        }
            Context context = getApplicationContext();
            CharSequence text = selectedTasks.size() + " items have been added to your quest.";
            int duration = Toast.LENGTH_LONG;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();

        selectedTasks.clear();
        pointTotal = 0;
        questLocations.add(selectedLocation);
        questPreview();
        selectedLocation = null;
        itemButton.setVisibility(View.INVISIBLE);
        viewTasks.clear();
        populateLocations();
        setTaskRecycler();
        setLocationRecycler();
        }});
    }
}