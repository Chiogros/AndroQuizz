package com.example.loustics.models;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.Index;

import org.jetbrains.annotations.NotNull;

@Entity(tableName = "Chapter",
foreignKeys = {
    @ForeignKey(entity = Course.class, parentColumns = "name", childColumns = "courseName")
},
primaryKeys = {
    "name", "courseName"
},
indices = {
    @Index("name"),
    @Index("courseName")
})
public class Chapter {

    @NonNull
    @ColumnInfo(name="name")
    private final String m_s_name;

    @NonNull
    @ColumnInfo(name = "courseName")
    private final String m_s_courseName;

    @Ignore
    private Lesson m_l_lesson;


    public Chapter(@NotNull String m_s_name, @NotNull String m_s_courseName) {
        this.m_s_name = m_s_name;
        this.m_s_courseName = m_s_courseName;
    }

    public Lesson getM_l_lesson() {
        return this.m_l_lesson;
    }

    @NotNull
    public String getM_s_courseName() {
        return this.m_s_courseName;
    }

    @NotNull
    public String getM_s_name() {
        return this.m_s_name;
    }

}
