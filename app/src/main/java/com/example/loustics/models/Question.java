package com.example.loustics.models;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

public abstract class Question extends ArrayAdapter<Question> {

    private int m_i_resource;
    private Subject m_s_subject;

    public Question(Subject subject, Context context, int resource) {
        super(context, resource);
        this.m_s_subject = subject;
        this.m_i_resource = resource;
    }

    public int getResource() {
        return this.m_i_resource;
    }

    public Subject getSubject() {
        return this.m_s_subject;
    }

    @Override
    public abstract View getView(int position, View convertView, ViewGroup parent);

    public abstract boolean isRight();

}
