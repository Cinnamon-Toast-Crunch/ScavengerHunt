package com.cinnamontoast.scavengerhunt.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import com.amplifyframework.api.graphql.model.ModelMutation;
import com.amplifyframework.api.graphql.model.ModelQuery;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.generated.model.Contact;
import com.amplifyframework.datastore.generated.model.Location;
import com.amplifyframework.datastore.generated.model.LocationInstance;
import com.amplifyframework.datastore.generated.model.Quest;
import com.amplifyframework.datastore.generated.model.Task;
import com.amplifyframework.datastore.generated.model.TaskJoiner;
import com.cinnamontoast.scavengerhunt.R;
import com.cinnamontoast.scavengerhunt.adapters.LocationAdapter;
import com.cinnamontoast.scavengerhunt.adapters.TaskAdapter;

import java.util.ArrayList;

public class CreateQuestActivity extends AppCompatActivity implements LocationAdapter.LocationListFormatter,
        TaskAdapter.TaskListFormatter {

    Location selectedLocation;
    ArrayList<Task> selectedTask = new ArrayList<>();
    ArrayList<Task> viewTasks = new ArrayList<>();
    ArrayList<Location> viewLocation = new ArrayList<>();

    int pointTotal;
    Quest emptyQuest;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_quest);

        buildEmptyQuest();
        populateLocations();
        addToQuest();

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
                    RecyclerView taskRecycler = findViewById(R.id.locationRecycler);
                    taskRecycler.setLayoutManager(new LinearLayoutManager(this));
                    taskRecycler.setAdapter(new LocationAdapter(viewLocation, this));

                    Log.i("MyAmplify", "tasks array created");

                },
                error -> Log.e("MyAmplify", "Failed to load tasks")
        );

    }
    //================= Build empty quest ====================
    public void buildEmptyQuest(){
        emptyQuest = Quest.builder()
                .userId(Amplify.Auth.getCurrentUser().getUserId())
                .title("")
                .build();
    }
    //================= Grab Location ========================
    @Override
    public void locationFormatter(Location location) {

        selectedLocation = location;
        selectedTask.clear();

        //=================== Populate task recycler ========
        Amplify.API.query(
                ModelQuery.list(Task.class),
                response -> {
                    viewTasks.clear();
                    for(Task task : response.getData()){
                        if(task.getLocationId().equals(location.getId())){
                            viewTasks.add(task);
                        }
                    }
                    RecyclerView taskRecycler = findViewById(R.id.taskRecycler);
                    taskRecycler.setLayoutManager(new LinearLayoutManager(this));
                    taskRecycler.setAdapter(new TaskAdapter(viewTasks, this));

                    Log.i("MyAmplify", "tasks array created");

                },
                error -> Log.e("MyAmplify", "Failed to load tasks")
        );
    }

    //====================== Build Task Arraylist ==========
    @Override
    public void taskFormatter(Task task) {
       if(selectedTask.contains(task)) {
           selectedTask.remove(task);
           pointTotal -= task.getPointValue();
       }else{
           selectedTask.add(task);
           pointTotal += task.getPointValue();
       }
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

        for(Task task : selectedTask){
            TaskJoiner joiner = TaskJoiner.builder()
                    .locationInstance(instance)
                    .task(task)
                    .build();

            Amplify.API.mutate(ModelMutation.create(joiner),
                    response -> Log.i("MyAmplify", "Joined Task"),
                    error -> Log.e("MyAmplify", "Failed to join"));
        }
        selectedTask.clear();
        selectedLocation = null;
        });
    }
}