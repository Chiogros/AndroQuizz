package com.example.loustics.models;

import android.content.Context;

import java.util.ArrayList;

public class Quizz {

    private ArrayList<Question> m_al_questions;
    private Context m_c_context;
    private int m_i_resource;

    public Quizz(Context context, int resource) {
        this.m_al_questions = new ArrayList<>();
        this.m_c_context = context;
        this.m_i_resource = resource;
    }

    public void addQuestion(Question question) throws Exception {
        if (question.getContext() != this.m_c_context)
            throw new Exception("This question is not of the same context that this quizz.");
        if (question.getResource() != this.m_i_resource)
            throw new Exception("This question does not aim on the same resource that this quizz.");

        this.m_al_questions.add(question);
    }

    public void display() {
        // TODO
    }

    public Question getQuestion(int position) {
        return this.m_al_questions.get(position);
    }

    public ArrayList<Question> getQuestions() {
        return this.m_al_questions;
    }

    public void insertQuestion(Question question, int position) throws Exception {
        try {
            this.m_al_questions.add(position, question);
        } catch (IndexOutOfBoundsException ex) {
            throw new Exception("Cannot add this question to the " + position + " position while the array is " + this.m_al_questions.size() + " elements long.");
        }
    }

    public boolean removeQuestion(Question question) {
        return this.m_al_questions.remove(question);
    }

    public Question removeQuestion(int position) throws Exception {
        try {
            return this.m_al_questions.remove(position);
        } catch (IndexOutOfBoundsException ex) {
            throw new Exception("Cannot remove the " + position + "th question from the array that is " + this.m_al_questions.size() + " elements long.");
        }
    }
}
