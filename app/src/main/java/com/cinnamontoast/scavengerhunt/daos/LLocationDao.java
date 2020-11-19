package com.cinnamontoast.scavengerhunt.daos;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;

import com.cinnamontoast.scavengerhunt.entities.LLocation;
import com.cinnamontoast.scavengerhunt.entities.LQuest;
import com.cinnamontoast.scavengerhunt.entities.relations.LLocationWithLTasks;
import com.cinnamontoast.scavengerhunt.entities.relations.LQuestWithLLocations;

import java.util.List;

@Dao
public interface LLocationDao {
    @Insert public void saveLLocation(LLocation lLocation);

    @Query("SELECT * FROM llocation")
    List<LLocation> getAll();

    //In Java ... can be used for variable arguments (varargs) in which zero or many arguments are passed
    @Insert
    void insertAll(LLocation ... lLocations);

    @Delete
    void delete(LLocation lLocation);

    @Transaction
    @Query("SELECT * FROM llocation")
    public List<LLocationWithLTasks> getLLocationsWithLTasks();
}
