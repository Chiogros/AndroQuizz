package com.example.loustics.models;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import java.util.List;

public class Litteral extends Question {

    public Litteral(String m_s_subject, String m_s_chapterName, String m_s_courseName, List<String> m_al_answers) {
        super(m_s_subject, m_s_chapterName, m_s_courseName, m_al_answers);
    }

    public String getAlternativeAnswer() {
        int randomBadAnswer = (int) (1 + Math.random() * 3);
        return m_l_answers.get(randomBadAnswer);
    }

    public String getAnswer() {
        return ;
    }

    @Override
    public View getAnswerView(Context context, Object answer) {
        TextView tv = new TextView(context);
        tv.setText(answer.toString());
        return tv;
    }

    public View getSubjectView(Context context) {
        TextView tv = new TextView(context);
        tv.setText(getM_s_subject());
        return tv;
    }
}
