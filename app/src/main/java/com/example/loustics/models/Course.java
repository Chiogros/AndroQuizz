package com.example.loustics.models;

import android.graphics.drawable.Drawable;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class Course {

    private ArrayList<Chapter> m_alChapters;
    private int m_dLogo;
    private String m_sName;
    private static ArrayList<Course> s_lCourses = new ArrayList<>();

    public Course(String sName) {
        this.m_alChapters = new ArrayList<>();
        this.m_dLogo = -1;
        this.m_sName = sName;
        s_lCourses.add(this);
    }

    public Course(String sName, int dLogo) {
        this.m_alChapters = new ArrayList<>();
        this.m_dLogo = dLogo;
        this.m_sName = sName;
        s_lCourses.add(this);
    }

    public int getLogo() {
        return this.m_dLogo;
    }

    public String getName() {
        return this.m_sName;
    }

    public static List<Course> getCourses() {
        return s_lCourses;
    }

}
