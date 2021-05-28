package com.example.loustics.models;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import androidx.room.Entity;

@Entity(tableName = "Chapter")
public class Chapter {

    @PrimaryKey @NonNull
    @ColumnInfo(name="chapter")
    private String m_s_name;

    @ColumnInfo(name = "courseName")
    private String m_s_courseName;

    @Ignore
    private Quizz m_q_quizz;

    @Ignore
    private Lesson m_l_lesson;


    public Chapter(String m_s_name, String m_s_courseName) {
        this.m_s_name = m_s_name;
        this.m_s_courseName = m_s_courseName;
    }

    public Lesson getLesson() {
        return this.m_l_lesson;
    }

    public String getM_s_courseName() {
        return this.m_s_courseName;
    }

    public String getM_s_name() {
        return this.m_s_name;
    }

    public Quizz getQuizz() {
        return this.m_q_quizz;
    }

    public void setLesson(Lesson lesson) {
        this.m_l_lesson = lesson;
    }

    public void setM_s_name(String name) {
        this.m_s_name = name;
    }

    public void setQuizz(Quizz quizz) {
        this.m_q_quizz = quizz;
    }

}
