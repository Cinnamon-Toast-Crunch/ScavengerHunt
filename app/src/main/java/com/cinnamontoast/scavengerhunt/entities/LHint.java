package com.cinnamontoast.scavengerhunt.entities;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import static androidx.room.ForeignKey.CASCADE;

@Entity
public class LHint {
    @PrimaryKey(autoGenerate = true)
    public int id;
    public String content;
    @ForeignKey(entity = LTask.class, parentColumns = "id", childColumns = "lTaskId", onDelete = CASCADE)
    public int lTaskId;

    @Override
    public String toString() {
        return "LHint{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", lTaskId=" + lTaskId +
                '}';
    }

    public LHint(String content, int lTaskId) {
        this.content = content;
        this.lTaskId = lTaskId;
    }
}
