package com.example.loustics.models;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

public abstract class MCQ extends Question {

    public MCQ(Subject subject, Context context) {
        super(subject, context);
    }

    public abstract View getView(Context context);

    public abstract boolean isRight();
}
