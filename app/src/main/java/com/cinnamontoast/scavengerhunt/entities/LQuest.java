package com.cinnamontoast.scavengerhunt.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class LQuest {
    @PrimaryKey(autoGenerate = true)
    public int id;
    public String title;

    public LQuest(String title) {
        this.title = title;
    }
}
