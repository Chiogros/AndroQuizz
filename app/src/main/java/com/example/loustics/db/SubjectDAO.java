package com.example.loustics.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;
import com.example.loustics.models.Question;


@Dao
public interface SubjectDAO {
    @Query("SELECT * FROM Subject")
    List<Question> getAllSubject();

    @Insert
    void insert(Question question);

    @Delete
    void delete(Question question);

    @Update
    void update(Question question);


}
