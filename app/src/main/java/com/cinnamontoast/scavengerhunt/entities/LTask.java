package com.cinnamontoast.scavengerhunt.entities;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.cinnamontoast.scavengerhunt.entities.relations.LLocationWithLTasks;
import com.cinnamontoast.scavengerhunt.entities.relations.LTaskWithLHints;

import java.util.ArrayList;
import java.util.List;

import static androidx.room.ForeignKey.CASCADE;

@Entity
public class LTask {
    @PrimaryKey(autoGenerate = true)
    public int id;
    public String objective;
    public String instruction;
    public Boolean completed;
    public int pointValue;
    public String picture;
    @ForeignKey(entity = LLocation.class, parentColumns = "id", childColumns = "lLocationId", onDelete = CASCADE)
    public int lLocationId;
    @Ignore
    public List<LHint> lHintList = null;

    @Override
    public String toString() {
        return "LTask{" +
                "id=" + id +
                ", objective='" + objective + '\'' +
                ", instruction='" + instruction + '\'' +
                ", completed=" + completed +
                ", pointValue=" + pointValue +
                ", picture='" + picture + '\'' +
                ", lLocationId=" + lLocationId +
                ", lHintList=" + lHintList +
                '}';
    }

    public LTask(String objective, String instruction, Boolean completed, int pointValue, String picture, int lLocationId) {
        this.objective = objective;
        this.instruction = instruction;
        this.completed = completed;
        this.pointValue = pointValue;
        this.picture = picture;
        this.lLocationId = lLocationId;
    }

    public LTask(LTaskWithLHints red){
        this.id = red.lTask.id;
        this.objective = red.lTask.objective;
        this.instruction = red.lTask.instruction;
        this.completed = red.lTask.completed;
        this.pointValue = red.lTask.pointValue;
        this.picture = red.lTask.picture;
        this.lLocationId = red.lTask.lLocationId;
        this.lHintList = red.lHints;

    }

}
