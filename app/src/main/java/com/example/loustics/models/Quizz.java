package com.example.loustics.models;

import java.util.ArrayList;

public class Quizz {

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
