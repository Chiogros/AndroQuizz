package com.example.loustics.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import androidx.room.Insert;

import com.example.loustics.models.Litteral;
import com.example.loustics.models.MCQ;

import java.util.ArrayList;
import java.util.List;
import com.example.loustics.models.Question;

import org.json.JSONException;


@Dao
public interface QuestionDAO extends DAO {

    @Query("SELECT * FROM Question WHERE chapterName = :chapterName AND courseName = :courseName")
    List<Question> getAllQuestions(String chapterName, String courseName);

    @Insert
    void insert(Question question);

    @Update
    void update(Question question);

    @Delete
    void delete(Question question);
}
