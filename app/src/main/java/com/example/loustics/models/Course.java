package com.example.loustics.models;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import androidx.room.Entity;

@Entity(tableName = "Course")
public class Course {

    @PrimaryKey @NonNull
    @ColumnInfo(name="name")
    private String m_s_name;

    @ColumnInfo(name="logo")
    private String m_s_logo;


    @Ignore
    public Course(String m_s_name) {
        this.m_s_logo = "";
        this.m_s_name = m_s_name;
    }

    public Course(String m_s_name, String m_s_logo) {
        this.m_s_logo = m_s_logo;
        this.m_s_name = m_s_name;
    }

    public String getM_s_logo() {
        return this.m_s_logo;
    }

    public String getM_s_name() {
        return this.m_s_name;
    }

}
