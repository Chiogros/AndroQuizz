package com.example.loustics.models;

import android.content.Context;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.Index;

import java.util.List;


@Entity(tableName = "Question",
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
public abstract class Question {

    @NonNull
    @ColumnInfo(name = "subject")
    private String m_s_subject;

    @NonNull
    @ColumnInfo (name = "chapterName")
    private String m_s_chapterName;

    @NonNull
    @ColumnInfo (name = "courseName")
    private String m_s_courseName;

    @ColumnInfo (name = "answers")
    private List<String> m_l_answers;

    @Ignore
    private List<String> m_l_rightAnswers, m_l_wrongAnswers;

    public Question(String m_s_subject, String m_s_chapterName, String m_s_courseName, List<String> m_l_answers) {
        this.m_s_subject = m_s_subject;
        this.m_s_chapterName = m_s_chapterName;
        this.m_s_courseName = m_s_courseName;
        this.m_l_answers = m_l_answers;
    }

    public abstract Object getAlternativeAnswer();

    public abstract Object getAnswer();

    public abstract View getAnswerView(Context context, Object answer);

    public List<String> getM_l_answers() {
        return this.m_l_answers;
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

    public abstract View getSubjectView(Context context);

}
