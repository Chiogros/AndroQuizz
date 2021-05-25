package com.example.loustics.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import androidx.room.Insert;

import com.example.loustics.models.Chapter;

import java.util.List;
import com.example.loustics.models.Quizz;


@Dao
public interface QuizzDAO {
    @Query("SELECT * FROM Quizz")
    List<Quizz> getAllQuizz();

    @Insert
    void insert(Quizz quizz);

    @Delete
    void delete(Quizz quizz);

    @Update
    void update(Quizz quizz);


}
