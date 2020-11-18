package com.cinnamontoast.scavengerhunt.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class LLocation {
    @PrimaryKey(autoGenerate = true)
    public int id;
    public String name;
    public int totalPoints;
    public float lat;
    public float lon;
    public int lQuestId;

    public LLocation(String name, int totalPoints, float lat, float lon, int lQuestId) {
        this.name = name;
        this.totalPoints = totalPoints;
        this.lat = lat;
        this.lon = lon;
        this.lQuestId = lQuestId;
    }
}
