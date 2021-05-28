package com.example.loustics.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import androidx.room.Insert;

import com.example.loustics.models.Litteral;
import com.example.loustics.models.MCQ;

import java.util.ArrayList;
import java.util.List;
import com.example.loustics.models.Question;


@Dao
public interface LitteralDAO {
    @Query("SELECT * FROM Litteral")
    List<Litteral> getAllLitterals();

    @Query("SELECT * FROM Litteral WHERE chapterName = :chapterName")
    List<Litteral> getAllLitteralsFromChapter(String chapterName);

    @Insert
    void insert(Litteral litteral);

    @Delete
    void delete(Litteral litteral);

    @Update
    void update(Litteral litteral);


}
