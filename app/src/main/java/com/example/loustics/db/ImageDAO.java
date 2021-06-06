package com.example.loustics.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.loustics.models.Image;
import com.example.loustics.models.Litteral;

import java.util.List;


@Dao
public interface ImageDAO {

    @Query("SELECT * FROM Image WHERE chapterName = :chapterName AND courseName = :courseName")
    List<Image> getAllQuestions(String chapterName, String courseName);
}
