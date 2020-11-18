package com.cinnamontoast.scavengerhunt.entities.relations;

import androidx.room.Embedded;
import androidx.room.Relation;

import com.cinnamontoast.scavengerhunt.entities.LLocation;
import com.cinnamontoast.scavengerhunt.entities.LQuest;
import com.cinnamontoast.scavengerhunt.entities.LTask;

import java.util.List;

public class LQuestWithLLocations {
    @Embedded
    public LQuest lQuest;
    @Relation(parentColumn = "id", entityColumn = "lQuestId")
    public List<LLocation> lLocations;
}
