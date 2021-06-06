package com.example.loustics.models;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;

@Entity(tableName = "Achievement",
foreignKeys = {
    @ForeignKey(entity = Chapter.class, parentColumns = { "name", "courseName" }, childColumns = { "chapterName", "courseName" }),
    // TODO : mettre la clé étrangère de la classe
    // @ForeignKey(entity = User.class, parentColumns = { "name", "courseName" }, childColumns = { "chapterName", "courseName" })
},
primaryKeys = {
    "chapterName", "courseName", "userName"
},
indices = {
    @Index("chapterName"),
    @Index("courseName"),
    @Index("userName")
})
public class Achievement{

    @NonNull
    @ColumnInfo (name = "chapterName")
    private String m_s_chapterName;

    @NonNull
    @ColumnInfo (name = "courseName")
    private String m_s_courseName;

    @NonNull
    @ColumnInfo (name = "userName")
    private String m_s_userName;

    public Achievement(String m_s_chapterName, String m_s_courseName, String m_s_userName) {
        this.m_s_chapterName = m_s_chapterName;
        this.m_s_courseName = m_s_courseName;
        this.m_s_userName = m_s_userName;
    }

    public String getM_s_chapterName() {
        return m_s_chapterName;
    }

    public String getM_s_courseName() {
        return m_s_courseName;
    }

    public String getM_s_userName() {
        return m_s_userName;
    }
}
