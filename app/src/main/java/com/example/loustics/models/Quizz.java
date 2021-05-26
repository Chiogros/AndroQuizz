package com.example.loustics.models;

import android.content.Context;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

public class Quizz {

    private ArrayAdapter<Question> m_aa_questions;
    private Context m_c_context;
    private int m_i_resource;

    public Quizz(Context context, int resource) {
        this.m_aa_questions = new ArrayAdapter<Question>(context, resource);
        this.m_c_context = context;
        this.m_i_resource = resource;
    }

    public void addQuestion(Question question) throws Exception {
        if (question.getContext() != this.m_c_context)
            throw new Exception("This question is not of the same context that this quizz.");

        this.m_aa_questions.add(question);
    }

    public void display() {
        // TODO
    }

    public Context getContext() {
        return this.m_c_context;
    }

    public Question getQuestion(int position) {
        return this.m_aa_questions.getItem(position);
    }

    public void insertQuestion(Question question, int position) throws Exception {
        try {
            this.m_aa_questions.insert(question, position);
        } catch (IndexOutOfBoundsException ex) {
            throw new Exception("Cannot add this question to the " + position + " position while the array is " + this.m_aa_questions.getCount() + " elements long.");
        }
    }

    public void removeQuestion(Question question) {
        this.m_aa_questions.remove(question);
    }
}
