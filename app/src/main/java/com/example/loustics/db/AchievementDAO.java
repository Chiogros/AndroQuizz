package com.example.loustics.db;

import androidx.room.Dao;
import androidx.room.Query;

import java.util.List;

@Dao
public interface AchievementDAO {

    @Query("SELECT * FROM Achievement WHERE userName = :userName")
    List<AchievementDAO> getAllAchievements(String userName);

    @Query("SELECT * FROM Achievement WHERE userName = :userName AND chapterName = :chapterName AND courseName = :courseName")
    AchievementDAO getAchievements(String userName, String chapterName, String courseName);

}
