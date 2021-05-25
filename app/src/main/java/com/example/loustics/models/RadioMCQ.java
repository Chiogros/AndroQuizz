package com.example.loustics.models;

import androidx.room.ColumnInfo;
import androidx.room.PrimaryKey;
import androidx.room.Entity;

@Entity public class RadioMCQ extends MCQ {

    public RadioMCQ() {
        super();
    }

    @Override
    public boolean isRight() {
        return true;
    }

}
