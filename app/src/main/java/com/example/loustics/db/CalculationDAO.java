package com.example.loustics.db;

import androidx.room.Dao;
import androidx.room.Query;

import com.example.loustics.models.Addition;

import java.util.List;

@Dao
public interface CalculationDAO {

    // ne pas utiliser, passer par getAllAdditions
    @Query("SELECT * FROM Calculation WHERE chapterName = :chapterName AND courseName = :courseName")
    List<Addition> getAllAdditions(String chapterName, String courseName);

}
