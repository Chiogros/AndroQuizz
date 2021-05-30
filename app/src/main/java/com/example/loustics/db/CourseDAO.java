package com.example.loustics.db;


import java.util.ArrayList;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import androidx.room.Insert;

import java.util.List;
import com.example.loustics.models.Course;
import com.example.loustics.models.Chapter;

@Dao
public interface CourseDAO {
    @Query("SELECT * FROM Course")
    LiveData<List<Course>> getAllCourses();

    @Insert
    void insert(Course course);

    @Delete
    void delete(Course course);

    @Update
    void update(Course course);

}
