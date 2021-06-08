package com.example.loustics.models;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;

import static androidx.room.ForeignKey.CASCADE;

@Entity(tableName = "Achievement",
foreignKeys = {
    @ForeignKey(onDelete = CASCADE, entity = Chapter.class, parentColumns = { "name", "courseName" }, childColumns = { "chapterName", "courseName" }),
    @ForeignKey(onDelete = CASCADE, entity = User.class, parentColumns = { "firstName", "lastName" }, childColumns = { "firstName", "lastName" })
},
primaryKeys = {
    "chapterName", "courseName", "lastName", "firstName"
},
indices = {
    @Index("chapterName"),
    @Index("courseName"),
    @Index("firstName"),
    @Index("lastName")
})
public class Achievement{

    @NonNull
    @ColumnInfo (name = "chapterName")
    private String m_s_chapterName;

    @NonNull
    @ColumnInfo (name = "courseName")
    private String m_s_courseName;

    @NonNull
    @ColumnInfo(name = "firstName")
    private String m_s_firstName;

    @NonNull
    @ColumnInfo(name = "lastName")
    private String m_s_lastName;


    public Achievement(String m_s_chapterName, String m_s_courseName, String m_s_firstName, String m_s_lastName) {
        this.m_s_chapterName = m_s_chapterName;
        this.m_s_courseName = m_s_courseName;
        this.m_s_firstName = m_s_firstName;
        this.m_s_lastName = m_s_lastName;
    }

    public String getM_s_chapterName() {
        return m_s_chapterName;
    }

    public String getM_s_courseName() {
        return m_s_courseName;
    }

    public String getM_s_firstName() {
        return m_s_firstName;
    }

    public String getM_s_lastName() {
        return m_s_lastName;
    }
}
