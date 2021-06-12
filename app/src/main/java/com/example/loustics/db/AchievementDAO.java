package com.example.loustics.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.loustics.models.Achievement;

import java.util.List;

@Dao
public interface AchievementDAO {

    @Query("SELECT * FROM Achievement WHERE firstName = :firstName AND lastName = :lastName AND courseName = :courseName")
    List<Achievement> getAllAchievementsInACourse(String firstName, String lastName, String courseName);

    @Query("SELECT * FROM Achievement WHERE firstName = :firstName AND lastName = :lastName AND chapterName = :chapterName AND courseName = :courseName")
    List<Achievement> getAchievement(String firstName, String lastName, String chapterName, String courseName);

    @Insert
    void insert(Achievement achievement);

    @Delete
    void delete(Achievement achievement);

}
