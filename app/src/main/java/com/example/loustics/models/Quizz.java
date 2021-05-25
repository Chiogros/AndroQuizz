package com.example.loustics.models;

import androidx.room.ColumnInfo;
import androidx.room.PrimaryKey;
import androidx.room.Entity;

import java.util.ArrayList;

@Entity public class Quizz {

    private ArrayList<Question> m_al_questions;

    public Quizz() {
        this.m_al_questions = new ArrayList<>();
    }

    public void addQuestion(Question question) {
        this.m_al_questions.add(question);
    }

    public Question getQuestion(int position) {
        return this.m_al_questions.get(position);
    }



}
