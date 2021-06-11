package com.example.loustics.models;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;

import org.jetbrains.annotations.NotNull;

@Entity(tableName = "User",
primaryKeys = {
        "firstName", "lastName"
},
indices = {
    @Index("firstName"),
    @Index("lastName")
})
public class User {

    @NonNull
    @ColumnInfo(name = "firstName")
    private final String m_s_firstName;

    @NonNull
    @ColumnInfo(name = "lastName")
    private final String m_s_lastName;

    @ColumnInfo(name = "photo")
    private String m_photo;


    public User(@NotNull String m_s_firstName, @NotNull String m_s_lastName, String m_photo) {
        this.m_s_firstName = m_s_firstName;
        this.m_s_lastName = m_s_lastName;
        this.m_photo = m_photo;
    }

    @NotNull
    public String getM_s_lastName() {
        return this.m_s_lastName;
    }

    @NotNull
    public String getM_s_firstName() {
        return this.m_s_firstName;
    }

    public String getM_photo() {
        return this.m_photo;
    }

}
