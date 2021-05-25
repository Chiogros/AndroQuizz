package com.example.loustics.models;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

public abstract class MCQ extends Question {

    public MCQ(Subject subject, Context context, int resource) {
        super(subject, context, resource);
    }

    public abstract View getView(int position, View convertView, ViewGroup parent);

    public abstract boolean isRight();
}
