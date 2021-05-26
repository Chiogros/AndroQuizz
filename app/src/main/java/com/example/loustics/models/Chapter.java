package com.example.loustics.models;

import java.util.ArrayList;

public class Chapter {

    private String m_s_name;
    private Quizz m_q_quizz;
    private Lesson m_l_lesson;

    public Chapter(String name) {
        this.m_s_name = name;
    }

    public Lesson getLesson() {
        return this.m_l_lesson;
    }

    public String getName() {
        return this.m_s_name;
    }

    public Quizz getQuizz() {
        return this.m_q_quizz;
    }

    public void setLesson(Lesson lesson) {
        this.m_l_lesson = lesson;
    }

    public void setName(String name) {
        this.m_s_name = name;
    }

    public void setQuizz(Quizz quizz) {
        this.m_q_quizz = quizz;
    }

}
