package com.cinnamontoast.scavengerhunt.database;

import androidx.room.RoomDatabase;

import com.cinnamontoast.scavengerhunt.daos.LQuestDao;
import com.cinnamontoast.scavengerhunt.entities.LQuest;

@androidx.room.Database(entities = {LQuest.class}, version=1)
public abstract class LDatabase extends RoomDatabase {
    public abstract LQuestDao lQuestDao();
}
