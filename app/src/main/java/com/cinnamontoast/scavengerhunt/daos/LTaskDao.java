package com.cinnamontoast.scavengerhunt.daos;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;

import com.cinnamontoast.scavengerhunt.entities.LQuest;
import com.cinnamontoast.scavengerhunt.entities.LTask;
import com.cinnamontoast.scavengerhunt.entities.relations.LQuestWithLLocations;
import com.cinnamontoast.scavengerhunt.entities.relations.LTaskWithLHints;

import java.util.List;

@Dao
public interface LTaskDao {
    @Insert public void saveLTask(LTask lTask);

    @Query("SELECT * FROM ltask")
    List<LTask> getAll();

    //In Java ... can be used for variable arguments (varargs) in which zero or many arguments are passed
    @Insert
    void insertAll(LTask ... lTasks);

    @Delete
    void delete(LTask lTask);

    @Transaction
    @Query("SELECT * FROM ltask")
    public List<LTaskWithLHints> getLTaskWithLHints();

    @Query("SELECT * FROM ltask ORDER BY id DESC LIMIT 1")
    public LTask getLastTask();
}
