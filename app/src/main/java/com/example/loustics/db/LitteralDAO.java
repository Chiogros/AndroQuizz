package com.example.loustics.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.loustics.models.Litteral;

import java.util.List;


@Dao
public interface LitteralDAO {

    @Query("SELECT * FROM Litteral WHERE chapterName = :chapterName AND courseName = :courseName")
    List<Litteral> getAllQuestions(String chapterName, String courseName);

    @Insert
    void insert(Litteral litteral);

    @Update
    void update(Litteral litteral);

    @Delete
    void delete(Litteral litteral);
}
