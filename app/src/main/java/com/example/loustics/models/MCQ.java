package com.example.loustics.models;

import androidx.room.ColumnInfo;
import androidx.room.PrimaryKey;
import androidx.room.Entity;

@Entity public abstract class MCQ extends Question {

    public MCQ() {
        super();
    }

    @Override
    public abstract boolean isRight();

}
