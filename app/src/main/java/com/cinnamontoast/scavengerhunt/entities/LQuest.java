package com.cinnamontoast.scavengerhunt.entities;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.cinnamontoast.scavengerhunt.entities.relations.LLocationWithLTasks;
import com.cinnamontoast.scavengerhunt.entities.relations.LQuestWithLLocations;

import java.util.ArrayList;
import java.util.List;

@Entity
public class LQuest {
    @PrimaryKey(autoGenerate = true)
    public int id;
    public String title;
    @Ignore
    public List<LLocation> lLocationList = null;

    @Override
    public String toString() {
        return "LQuest{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", lLocationList=" + lLocationList +
                '}';
    }

    public LQuest(String title) {
        this.title = title;
    }

    public LQuest(LQuestWithLLocations red){
        this.id = red.lQuest.id;
        this.title = red.lQuest.title;
        this.lLocationList = this.getLocations(red.lLocations);

    }

    private List<LLocation> getLocations(List<LLocationWithLTasks> lLocations) {
        List<LLocation> locations = new ArrayList<>();
        for(LLocationWithLTasks task : lLocations){
            LLocation location = new LLocation(task);
            locations.add(location);
        }
        return locations;
    }

}
