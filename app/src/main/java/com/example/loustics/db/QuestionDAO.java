package com.example.loustics.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;
import com.example.loustics.models.QuestionFrame;


@Dao
public interface QuestionDAO {
    @Query("SELECT * FROM Question")
    List<QuestionFrame> getAllQuestions();

    @Insert
    void insert(QuestionFrame questionFrame);

    @Delete
    void delete(QuestionFrame questionFrame);

    @Update
    void update(QuestionFrame questionFrame);
}
