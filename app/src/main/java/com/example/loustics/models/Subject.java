package com.example.loustics.models;

import androidx.room.ColumnInfo;
import androidx.room.PrimaryKey;
import androidx.room.Entity;

@Entity public abstract class Subject {

    public Subject() {}

    public abstract Object getAlternativeAnswer();

    public abstract Object getAnswer();

    public abstract Object getSubject();

}
