package com.cinnamontoast.scavengerhunt.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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
    RelativeLayout relativeLayout;
    ConstraintLayout constraintLayout;



    RecyclerView taskRecycler;
    RecyclerView locationRecycler;
    RecyclerView questRecycler;
    Handler taskHandler;
    Handler locationHandler;
    Handler questHandler;

    int pointTotal;
    Quest emptyQuest;

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

                    populateLocations();
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

                popupWindow = new PopupWindow(container, 1000, 1500, true);
                popupWindow.showAtLocation(constraintLayout,Gravity.NO_GRAVITY, 600, 600);

                //--------- Save and Stay button
                Button saveAndStay = container.findViewById(R.id.anotherItem);
                saveAndStay.setOnClickListener(view ->{

                    String item = ((TextView) container.findViewById(R.id.newItemName)).getText().toString();
                    String pointsString = ((TextView) container.findViewById(R.id.newItemPoints)).getText().toString();
                    int points = Integer.parseInt(pointsString);

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
                    int points = Integer.parseInt(pointsString);

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
                    //TODO Location recycler
//                    taskRecycler = findViewById(R.id.locationRecycler);
//                    taskRecycler.setLayoutManager(new LinearLayoutManager(this));
//                    taskRecycler.setAdapter(new LocationAdapter(viewLocation, this));

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

        populateTasks(location);
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
           pointTotal -= task.getPointValue();
       }else{
           selectedTasks.add(task);
           pointTotal += task.getPointValue();
       }
    }

    //=========== Save Quest =================================
    public void saveQuest(){
        Button saveQuest = findViewById(R.id.saveQuest);
        saveQuest.setOnClickListener(v -> {

            String title = ((TextView)findViewById(R.id.inputQuestName)).getText().toString();
            if(title == ""){
                Context context = getApplicationContext();
                CharSequence text = "Please enter a name for this quest.";
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

        LocationInstance instance = LocationInstance.builder()
                .questId(emptyQuest.getId())
                .name(selectedLocation.getName())
                .lat(selectedLocation.getLat())
                .lon(selectedLocation.getLon())
                .totalPoints(pointTotal)
                .build();

        for(Task task : selectedTasks){
            TaskJoiner joiner = TaskJoiner.builder()
                    .locationInstance(instance)
                    .task(task)
                    .build();

            Amplify.API.mutate(ModelMutation.create(joiner),
                    response -> Log.i("MyAmplify", "Joined Task"),
                    error -> Log.e("MyAmplify", "Failed to join"));
        }
        selectedTasks.clear();
        pointTotal = 0;
        questLocations.add(selectedLocation);
        questPreview();
        selectedLocation = null;
        });
    }



    //=====================================================================
//    public void onButtonShowPopupWindowClick(View view) {
//
//        // inflate the layout of the popup window
//        LayoutInflater inflater = (LayoutInflater)
//                getSystemService(LAYOUT_INFLATER_SERVICE);
//        View popupView = inflater.inflate(R.layout.popup_window, null);
//
//        // create the popup window
//        int width = LinearLayout.LayoutParams.WRAP_CONTENT;
//        int height = LinearLayout.LayoutParams.WRAP_CONTENT;
//        boolean focusable = true; // lets taps outside the popup also dismiss it
//        final PopupWindow popupWindow = new PopupWindow(popupView, width, height, focusable);
//
//        // show the popup window
//        // which view you pass in doesn't matter, it is only used for the window tolken
//        popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);
//
//        // dismiss the popup window when touched
//        popupView.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                popupWindow.dismiss();
//                return true;
//            }
//        });
//    }


    //======================================================================
//    class ShowPopUp extends CreateQuestActivity {
//        PopupWindow popUp;
//        boolean click = true;
//
//        @Override
//        public void onCreate(Bundle savedInstanceState) {
//            super.onCreate(savedInstanceState);
//            popUp = new PopupWindow(this);
//            LinearLayout layout = new LinearLayout(this);
//            LinearLayout mainLayout = new LinearLayout(this);
//            TextView tv = new TextView(this);
//            Button but = new Button(this);
//            but.setText("Click Me");
//            but.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    if (click) {
//                        popUp.showAtLocation(layout, Gravity.BOTTOM, 10, 10);
//                        popUp.update(50, 50, 300, 80);
//                        click = false;
//                    } else {
//                        popUp.dismiss();
//                        click = true;
//                    }
//                }
//            });
//
//            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
//                    LinearLayout.LayoutParams.WRAP_CONTENT);
//            layout.setOrientation(LinearLayout.VERTICAL);
//            tv.setText("Hi this is a sample text for popup window");
//            layout.addView(tv, params);
//            popUp.setContentView(layout);
//            // popUp.showAtLocation(layout, Gravity.BOTTOM, 10, 10);
//            mainLayout.addView(but, params);
//            setContentView(mainLayout);
//        }
//    }

}