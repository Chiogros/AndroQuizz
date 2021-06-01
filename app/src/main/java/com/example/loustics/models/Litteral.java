package com.example.loustics.models;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import org.json.JSONObject;

import java.util.List;

public class Litteral extends Question {

    public Litteral(String m_s_subject, String m_s_chapterName, String m_s_courseName, JSONObject m_json_answers) {
        super(m_s_subject, m_s_chapterName, m_s_courseName, m_json_answers);
    }

    @Override
    public View getAnswerView(Object answer) {
        TextView tv = new TextView(getM_c_context());
        tv.setText(answer.toString());
        return tv;
    }

    @Override
    public View getSubjectView() {
        TextView tv = new TextView(getM_c_context());
        tv.setText(getM_s_subject());
        return tv;
    }
}
