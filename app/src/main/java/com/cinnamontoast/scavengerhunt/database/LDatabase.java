package com.cinnamontoast.scavengerhunt.database;

import androidx.room.RoomDatabase;

import com.cinnamontoast.scavengerhunt.daos.LHintDao;
import com.cinnamontoast.scavengerhunt.daos.LLocationDao;
import com.cinnamontoast.scavengerhunt.daos.LQuestDao;
import com.cinnamontoast.scavengerhunt.daos.LTaskDao;
import com.cinnamontoast.scavengerhunt.entities.LHint;
import com.cinnamontoast.scavengerhunt.entities.LLocation;
import com.cinnamontoast.scavengerhunt.entities.LQuest;
import com.cinnamontoast.scavengerhunt.entities.LTask;

@androidx.room.Database(entities = {LQuest.class, LLocation.class, LTask.class, LHint.class}, version=3)
public abstract class LDatabase extends RoomDatabase {
    public abstract LQuestDao lQuestDao();
    public abstract LLocationDao lLocationDao();
    public abstract LTaskDao lTaskDao();
    public abstract LHintDao lHintDao();
}
