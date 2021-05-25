package com.example.loustics.models;

import androidx.room.ColumnInfo;
import androidx.room.PrimaryKey;
import androidx.room.Entity;

@Entity public abstract class Question {

    private Subject m_s_subject;

    public Question() {}

    public Question(Subject subject) {
        this.m_s_subject = subject;
    }

    public Subject getSubject() {
        return this.m_s_subject;
    }

    public abstract boolean isRight();

    public void setSubject(Subject subject) {
        this.m_s_subject = subject;
    }

}
