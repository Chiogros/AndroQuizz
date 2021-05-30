package com.example.loustics.models;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;
import androidx.room.Entity;
import java.util.ArrayList;
import java.util.List;

@Entity(tableName = "Litteral",
foreignKeys = {
    @ForeignKey(entity = Chapter.class, parentColumns = { "name", "courseName" }, childColumns = { "chapterName", "courseName" })
},
primaryKeys = {
    "subject", "chapterName", "courseName"
},
indices = {
    @Index("subject"),
    @Index("chapterName"),
    @Index("courseName")
})
public class Litteral implements Question {

    @NonNull
    @ColumnInfo (name = "subject")
    private String m_s_subject;

    @NonNull
    @ColumnInfo (name = "chapterName")
    private String m_s_chapterName;

    @NonNull
    @ColumnInfo (name = "courseName")
    private String m_s_courseName;

    @ColumnInfo (name = "answers")
    private List<String> m_al_answers;


    public Litteral(String m_s_subject, String m_s_chapterName, String m_s_courseName, List<String> m_al_answers) {
        this.m_s_subject = m_s_subject;
        this.m_s_chapterName = m_s_chapterName;
        this.m_s_courseName = m_s_courseName;
        this.m_al_answers = m_al_answers;
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

    public List<String> getM_al_answers() {
        return this.m_al_answers;
    }

    public String getM_s_chapterName() {
        return this.m_s_chapterName;
    }

    public String getM_s_courseName() {
        return this.m_s_courseName;
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
