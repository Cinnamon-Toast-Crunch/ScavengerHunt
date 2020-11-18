package com.cinnamontoast.scavengerhunt.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class LHint {
    @PrimaryKey(autoGenerate = true)
    public int id;
    public String content;
    public int lTaskId;

    public LHint(String content, int lTaskId) {
        this.content = content;
        this.lTaskId = lTaskId;
    }
}
