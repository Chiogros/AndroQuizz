package com.example.loustics.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import org.jetbrains.annotations.NotNull;

@Entity(tableName = "Course")
public class Course {

    @PrimaryKey @NotNull
    @ColumnInfo(name="name")
    private final String m_s_name;

    @NotNull
    @ColumnInfo(name="logo")
    private final String m_s_logo;


    @Ignore
    public Course(@NotNull String m_s_name) {
        this.m_s_logo = "";
        this.m_s_name = m_s_name;
    }

    public Course(@NotNull String m_s_name, @NotNull String m_s_logo) {
        this.m_s_logo = m_s_logo;
        this.m_s_name = m_s_name;
    }

    @NotNull
    public String getM_s_logo() {
        return this.m_s_logo;
    }

    @NotNull
    public String getM_s_name() {
        return this.m_s_name;
    }

}
