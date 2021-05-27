package com.example.loustics.models;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

public class Litteral implements Question {

    private String m_s_subject;
    private ArrayList<String> m_al_answers;
    private String m_s_chapterName;

    public Litteral(String subject) {
        this.m_s_subject = subject;
    }

    public String getAlternativeAnswer() {
        int randomBadAnswer = (int) (1 + Math.random() * 3);
        return m_al_answers.get(randomBadAnswer);
    }

    public String getAnswer() {
        return m_al_answers.get(0);
    }

    @Override
    public View getAnswerView(Context context, Object answer) {
        TextView tv = new TextView(context);
        tv.setText(answer.toString());
        return tv;
    }

    public String getSubject() {
        return this.m_s_subject;
    }

    public View getSubjectView(Context context) {
        TextView tv = new TextView(context);
        tv.setText(this.m_s_subject);
        return tv;
    }

}
