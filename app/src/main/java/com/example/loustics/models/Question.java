package com.example.loustics.models;

import android.content.Context;
import android.view.View;
import android.widget.ArrayAdapter;

import java.util.List;

public abstract class Question {

    protected Question() {}

    public abstract Object getAlternativeAnswer();

    public abstract Object getAnswer();

    public abstract View getAnswerView(Context context, Object answer);

    public abstract Object getM_s_subject();

    public abstract View getSubjectView(Context context);

    public static List<Question> getAllQuestions(String chapterName, String courseName) {

    }

}
