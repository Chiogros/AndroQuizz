package com.example.loustics.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import androidx.room.Insert;

import com.example.loustics.models.MCQ;

import java.util.List;
import com.example.loustics.models.Subject;


@Dao
public interface LitteralDAO {
    @Query("SELECT * FROM Litteral")
    List<Subject> getAllLitteral();

    @Insert
    void insert(Subject subject);

    @Delete
    void delete(Subject subject);

    @Update
    void update(Subject subject);


}
