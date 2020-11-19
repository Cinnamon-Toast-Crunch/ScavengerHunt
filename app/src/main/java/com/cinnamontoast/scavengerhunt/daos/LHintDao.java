package com.cinnamontoast.scavengerhunt.daos;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;

import com.cinnamontoast.scavengerhunt.entities.LHint;
import com.cinnamontoast.scavengerhunt.entities.LQuest;
import com.cinnamontoast.scavengerhunt.entities.relations.LQuestWithLLocations;

import java.util.List;

@Dao
public interface LHintDao {
    @Insert public void saveLHint(LHint lHint);

    @Query("SELECT * FROM lhint")
    List<LHint> getAll();

    //In Java ... can be used for variable arguments (varargs) in which zero or many arguments are passed
    @Insert
    void insertAll(LHint ... lHints);

    @Delete
    void delete(LHint lHint);
}
