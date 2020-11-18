package com.cinnamontoast.scavengerhunt.daos;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.cinnamontoast.scavengerhunt.entities.LQuest;

import java.util.List;

@Dao
public interface LQuestDao {
    @Insert public void saveLQuest(LQuest lQuest);

    @Query("SELECT * FROM lquest")
    List<LQuest> getAll();

    //In Java, ... can be used for variable arguments (varargs) in which zero or many arguments are passed
    @Insert
    void insertAll(LQuest ... lQuests);

    @Delete
    void delete(LQuest lQuest);
}
