package com.cinnamontoast.scavengerhunt.entities;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.cinnamontoast.scavengerhunt.entities.relations.LLocationWithLTasks;
import com.cinnamontoast.scavengerhunt.entities.relations.LQuestWithLLocations;
import com.cinnamontoast.scavengerhunt.entities.relations.LTaskWithLHints;

import java.util.ArrayList;
import java.util.List;

import static androidx.room.ForeignKey.CASCADE;

@Entity
public class LLocation {
    @PrimaryKey(autoGenerate = true)
    public int id;
    public String name;
    public int totalPoints;
    public float lat;
    public float lon;
    @ForeignKey(entity = LQuest.class, parentColumns = "id", childColumns = "lQuestId", onDelete = CASCADE)
    public int lQuestId;
    @Ignore
    public List<LTask> lTaskList = null;

    @Override
    public String toString() {
        return "LLocation{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", totalPoints=" + totalPoints +
                ", lat=" + lat +
                ", lon=" + lon +
                ", lQuestId=" + lQuestId +
                ", lTaskList=" + lTaskList +
                '}';
    }

    public LLocation(String name, int totalPoints, float lat, float lon, int lQuestId) {
        this.name = name;
        this.totalPoints = totalPoints;
        this.lat = lat;
        this.lon = lon;
        this.lQuestId = lQuestId;
    }

    public LLocation(LLocationWithLTasks red){
        this.id = red.lLocation.id;
        this.name = red.lLocation.name;
        this.totalPoints = red.lLocation.totalPoints;
        this.lat = red.lLocation.lat;
        this.lon = red.lLocation.lon;
        this.lQuestId = red.lLocation.lQuestId;
        this.lTaskList = this.getTasks(red.lTasks);

    }

    private List<LTask> getTasks(List<LTaskWithLHints> lTasks) {
        List<LTask> tasks = new ArrayList<>();
        for(LTaskWithLHints hint : lTasks){
            LTask task = new LTask(hint);
            tasks.add(task);
        }
        return tasks;
    }
}
