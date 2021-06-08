package com.example.loustics.models;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;
import androidx.room.Entity;

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
    private String m_s_firstName;

    @NonNull
    @ColumnInfo(name = "lastName")
    private String m_s_lastName;

    @ColumnInfo(name = "photo")
    private String m_photo;


    public User(String m_s_firstName, String m_s_lastName, String m_photo) {
        this.m_s_firstName = m_s_firstName;
        this.m_s_lastName = m_s_lastName;
        this.m_photo = m_photo;
    }

    public String getM_s_lastName() {
        return this.m_s_lastName;
    }

    public String getM_s_firstName() {
        return this.m_s_firstName;
    }

    public String getM_photo() {
        return this.m_photo;
    }

    public void setM_photo(String photo) {
        this.m_photo = photo;
    }
}
