package com.example.loustics.models;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;

@Entity(tableName = "Calculation",
foreignKeys = {
    @ForeignKey(entity = Chapter.class, parentColumns = { "name", "courseName" }, childColumns = { "chapterName", "courseName" })
},
primaryKeys = {
    "chapterName", "courseName", "difficulty"
},
indices = {
    @Index("chapterName"),
    @Index("courseName"),
    @Index("difficulty")
})
public class Addition extends Calculation {

    @NonNull
    @ColumnInfo(name = "chapterName")
    protected String m_s_chapterName;

    @NonNull
    @ColumnInfo (name = "courseName")
    protected String m_s_courseName;

    @NonNull
    @ColumnInfo (name = "difficulty")
    protected int m_s_difficulty;


    public Addition(String m_s_chapterName, String m_s_courseName, int m_s_difficulty) {
        super(m_s_difficulty);
        this.m_s_chapterName = m_s_chapterName;
        this.m_s_courseName = m_s_courseName;
        this.m_s_difficulty = m_s_difficulty;
    }

    public boolean isRight(Object answer) {
        return m_d_operand1 + m_d_operand2 == Integer.parseInt(answer.toString());
    }

    public int getM_s_difficulty() {
        return m_s_difficulty;
    }

    public String getM_s_chapterName() {
        return m_s_chapterName;
    }

    public String getM_s_courseName() {
        return m_s_courseName;
    }

    public String getM_s_subject() {
        return m_d_operand1 + " + " + m_d_operand2 + " =";
    }

    public int getResult() {
        return m_d_operand1 + m_d_operand2;
    }
}