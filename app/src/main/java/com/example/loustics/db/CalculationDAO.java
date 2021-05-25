package com.example.loustics.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import androidx.room.Insert;

import com.example.loustics.models.MCQ;

import java.util.List;
import com.example.loustics.models.Calculation;

@Dao
public interface CalculationDAO {
    @Query("SELECT * FROM Calculation")
    List<MCQ> getAllCalculation();

    @Insert
    void insert(Calculation cal);

    @Delete
    void delete(Calculation cal);

    @Update
    void update(Calculation cal);


}