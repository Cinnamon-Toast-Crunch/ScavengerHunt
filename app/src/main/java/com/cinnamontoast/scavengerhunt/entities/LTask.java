package com.cinnamontoast.scavengerhunt.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class LTask {
    @PrimaryKey(autoGenerate = true)
    public int id;
    public String objective;
    public String instruction;
    public Boolean completed;
    public int pointValue;
    public String picture;
    public int lLocationId;

    public LTask(String objective, String instruction, Boolean completed, int pointValue, String picture, int lLocationId) {
        this.objective = objective;
        this.instruction = instruction;
        this.completed = completed;
        this.pointValue = pointValue;
        this.picture = picture;
        this.lLocationId = lLocationId;
    }

}
