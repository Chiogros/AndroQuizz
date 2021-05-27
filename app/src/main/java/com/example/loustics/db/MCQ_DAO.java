package com.example.loustics.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;
import com.example.loustics.models.MCQ;


@Dao
public interface MCQ_DAO {
    @Query("SELECT * FROM MCQ")
    List<MCQ> getAllMCQ();

    @Insert
    void insert(MCQ mcq);

    @Delete
    void delete(MCQ mcq);

    @Update
    void update(MCQ mcq);

}
