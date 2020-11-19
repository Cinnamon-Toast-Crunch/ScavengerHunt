package com.cinnamontoast.scavengerhunt.daos;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;

import com.cinnamontoast.scavengerhunt.entities.LQuest;
import com.cinnamontoast.scavengerhunt.entities.relations.LQuestWithLLocations;

import java.util.List;

@Dao
public interface LQuestDao {
    @Insert public void saveLQuest(LQuest lQuest);

    @Query("SELECT * FROM lquest")
    List<LQuest> getAll();

    //In Java ... can be used for variable arguments (varargs) in which zero or many arguments are passed
    @Insert
    void insertAll(LQuest ... lQuests);

    @Delete
    void delete(LQuest lQuest);

    @Transaction
    @Query("SELECT * FROM lquest")
    public List<LQuestWithLLocations> getLQuestsWithLocations();

    @Query("SELECT * FROM lquest WHERE id = 1")
    public LQuest getOneQuest();
}
