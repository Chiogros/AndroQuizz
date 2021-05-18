package com.example.loustics.models;

import java.util.ArrayList;

public class Chapter {

    private String m_s_name;
    private Quizz m_q_quizz;
    private Lesson m_l_lesson;
    private static ArrayList<Chapter> s_al_Chapters = new ArrayList<>();

    public Chapter(String name) {
        this.m_s_name = name;
        s_al_Chapters.add(this);
    }

    public String getName() {
        return this.m_s_name;
    }

    public Quizz getQuizz() {
        return this.m_q_quizz;
    }

    public Lesson getLesson() {
        return this.m_l_lesson;
    }

    public static ArrayList<Chapter> getChapters() {
        return s_al_Chapters;
    }

}
