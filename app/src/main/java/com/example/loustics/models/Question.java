package com.example.loustics.models;

public abstract class Question {

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
