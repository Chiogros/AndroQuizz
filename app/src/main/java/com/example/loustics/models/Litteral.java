package com.example.loustics.models;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import androidx.room.Entity;
import java.util.ArrayList;

@Entity(tableName = "Litteral")
public class Litteral implements Question {

    @PrimaryKey @NonNull
    @ColumnInfo(name="subject")
    private String m_s_subject;

    @Ignore
    private ArrayList<String> m_al_answers;

    @ColumnInfo (name="chapterName")
    private String m_s_chapterName;


    public Litteral(String m_s_subject, String m_s_chapterName) {
        this.m_s_subject = m_s_subject;
        this.m_s_chapterName = m_s_chapterName;
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

    public String getM_s_chapterName() {
        return this.m_s_chapterName;
    }

    public String getM_s_subject() {
        return this.m_s_subject;
    }

    public View getSubjectView(Context context) {
        TextView tv = new TextView(context);
        tv.setText(this.m_s_subject);
        return tv;
    }

}
