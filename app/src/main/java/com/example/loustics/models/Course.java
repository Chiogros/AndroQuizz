package com.example.loustics.models;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import androidx.room.Entity;

@Entity(tableName = "Course")
public class Course {

    @ColumnInfo(name="logo")
    private int m_i_logo;

    @PrimaryKey @NonNull
    @ColumnInfo(name="name")
    private String m_s_name;

    @Ignore
    public Course(String m_s_name) {
        this.m_i_logo = -1;
        this.m_s_name = m_s_name;
    }

    public Course(String m_s_name, int m_i_logo) {
        this.m_i_logo = m_i_logo;
        this.m_s_name = m_s_name;
    }

    public int getM_i_logo() {
        return this.m_i_logo;
    }

    public String getM_s_name() {
        return this.m_s_name;
    }

}
