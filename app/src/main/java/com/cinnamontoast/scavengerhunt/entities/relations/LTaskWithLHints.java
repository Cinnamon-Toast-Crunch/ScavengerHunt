package com.cinnamontoast.scavengerhunt.entities.relations;

import androidx.room.Embedded;
import androidx.room.Relation;

import com.cinnamontoast.scavengerhunt.entities.LHint;
import com.cinnamontoast.scavengerhunt.entities.LLocation;
import com.cinnamontoast.scavengerhunt.entities.LTask;

import java.util.List;

public class LTaskWithLHints {
    @Embedded
    public LTask lTask;
    @Relation(parentColumn = "id", entityColumn = "lTaskId", entity = LHint.class)
    public List<LHint> lHints;

    @Override
    public String toString() {
        return "LTaskWithLHints{" +
                "lTask=" + lTask +
                ", lHints=" + lHints +
                '}';
    }
}
