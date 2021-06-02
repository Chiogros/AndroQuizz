package com.example.loustics.db;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;
import com.example.loustics.models.Course;

@Dao
public interface CourseDAO {
    @Query("SELECT * FROM Course")
    List<Course> getAllCourses();

    @Query("SELECT logo FROM Course WHERE name = :name")
    String getLogo(String name);

    @Insert
    void insert(Course course);

    @Delete
    void delete(Course course);

    @Update
    void update(Course course);

}
