package com.example.loustics.models;

import java.util.ArrayList;

public class Course {

    private ArrayList<Chapter> m_al_chapters;
    private int m_i_logo;
    private String m_s_name;

    public Course(String name) {
        this.m_al_chapters = new ArrayList<>();
        this.m_i_logo = -1;
        this.m_s_name = name;
    }

    public Course(String name, int logo) {
        this.m_al_chapters = new ArrayList<>();
        this.m_i_logo = logo;
        this.m_s_name = name;
    }

    public void addChapter(Chapter chapter) {
        this.m_al_chapters.add(chapter);
    }

    public Chapter getChapter(int position) {
        return this.m_al_chapters.get(position);
    }

    public ArrayList<Chapter> getChapters() {
        return this.m_al_chapters;
    }

    public int getLogo() {
        return this.m_i_logo;
    }

    public String getName() {
        return this.m_s_name;
    }

    public void insertChapter(Chapter chapter, int position) throws Exception {
        try {
            this.m_al_chapters.add(position, chapter);
        } catch (IndexOutOfBoundsException ex) {
            throw new Exception("Cannot add this chapter at the " + position + " position while the array is " + this.m_al_chapters.size() + " elements long.");
        }
    }

    public boolean removeChapter(Chapter chapter) {
        return this.m_al_chapters.remove(chapter);
    }

    public Chapter removeChapter(int position) throws Exception {
        try {
            return this.m_al_chapters.remove(position);
        } catch (IndexOutOfBoundsException ex) {
            throw new Exception("Cannot remove the " + position + "th chapter from the array that is " + this.m_al_chapters.size() + " elements long.");
        }
    }

    public void setName(String name) {
        this.m_s_name = name;
    }
}
