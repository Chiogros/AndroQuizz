package com.example.loustics.db;

import androidx.room.Dao;
import androidx.room.Query;

import com.example.loustics.models.Addition;
import com.example.loustics.models.Multiplication;
import com.example.loustics.models.Soustraction;

import java.util.List;

@Dao
public interface CalculationDAO {

    @Query("SELECT * FROM Addition WHERE chapterName = :chapterName AND courseName = :courseName")
    List<Addition> getAddition(String chapterName, String courseName);

    @Query("SELECT * FROM Soustraction WHERE chapterName = :chapterName AND courseName = :courseName")
    List<Soustraction> getSoustraction(String chapterName, String courseName);

    @Query("SELECT * FROM Multiplication WHERE chapterName = :chapterName AND courseName = :courseName")
    List<Multiplication> getMultiplication(String chapterName, String courseName);

}
