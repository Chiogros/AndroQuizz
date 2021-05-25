package com.example.loustics.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import androidx.room.Insert;
import com.example.loustics.models.CheckMCQ;
import com.example.loustics.models.MCQ;

import java.util.List;


@Dao
public interface CheckMCQ_DAO {
    @Query("SELECT * FROM CheckMCQ")
    List<CheckMCQ> getAllCheckMCQ();

    @Insert
    void insert(CheckMCQ checkmcq);

    @Delete
    void delete(CheckMCQ checkmcq);

    @Update
    void update(CheckMCQ checkmcq);


}
