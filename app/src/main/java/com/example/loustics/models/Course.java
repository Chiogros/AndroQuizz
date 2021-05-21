package com.example.loustics.models;

import java.util.ArrayList;
import java.util.List;

public class Course {

    private ArrayList<Chapter> m_al_Chapters;
    private int m_i_Logo;
    private String m_s_Name;
    private static ArrayList<Course> s_al_Courses = new ArrayList<>();

    public Course(String sName) {
        this.m_al_Chapters = new ArrayList<>();
        this.m_i_Logo = -1;
        this.m_s_Name = sName;
        s_al_Courses.add(this);
    }

    public Course(String sName, int iLogo) {
        this.m_al_Chapters = new ArrayList<>();
        this.m_i_Logo = iLogo;
        this.m_s_Name = sName;
        s_al_Courses.add(this);
    }

    public int getLogo() {
        return this.m_i_Logo;
    }

    public String getName() {
        return this.m_s_Name;
    }

    public static List<Course> getCourses() {
        return s_al_Courses;
    }

}
