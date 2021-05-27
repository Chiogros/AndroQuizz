package com.example.loustics.models;

import android.content.Context;
import android.view.View;
import android.widget.TextView;
import androidx.room.ColumnInfo;
import androidx.room.PrimaryKey;
import androidx.room.Entity;
import java.util.ArrayList;

@Entity
public class Litteral implements Question {
    @PrimaryKey @ColumnInfo(name="subject")
    private String m_s_sentence;
    @ColumnInfo(name="answers")
    private ArrayList<String> m_al_answers;
    @ColumnInfo (name="chapterName")
    private String m_s_chapterName;

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
