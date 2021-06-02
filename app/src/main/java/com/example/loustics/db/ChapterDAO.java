package com.example.loustics.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;
import com.example.loustics.models.Chapter;


@Dao
public interface ChapterDAO {
    @Query("SELECT * FROM Chapter")
    List<Chapter> getAllChapters();

    @Query("SELECT * FROM Chapter WHERE courseName = :courseName")
    List<Chapter> getAllChapters(String courseName);

    @Insert
    void insert(Chapter chapter);

    @Delete
    void delete(Chapter chapter);

    @Update
    void update(Chapter chapter);

}
