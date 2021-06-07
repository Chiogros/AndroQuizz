package com.example.loustics.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.loustics.models.Achievement;
import com.example.loustics.models.Litteral;

import java.util.List;

@Dao
public interface AchievementDAO {

    @Query("SELECT * FROM Achievement WHERE firstName = :firstName AND lastName = :lastName")
    List<Achievement> getAllAchievements(String firstName, String lastName);

    @Query("SELECT * FROM Achievement WHERE firstName = :firstName AND lastName = :lastName AND courseName = :courseName")
    List<Achievement> getAllAchievementsInACourse(String firstName, String lastName, String courseName);

    @Query("SELECT * FROM Achievement WHERE chapterName = :chapterName AND courseName = :courseName AND firstName = :firstName AND lastName = :lastName")
    List<Achievement> getAchievement(String chapterName, String courseName, String firstName, String lastName);

    @Insert
    void insert(Achievement achievement);

    @Delete
    void delete(Achievement achievement);

}
