package com.example.loustics.models;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

public class CheckMCQ extends MCQ {

    public CheckMCQ(Subject subject, Context context) {
        super(subject, context);
    }

    public View getView(Context context) {
        Subject s = getSubject();
        return new View(context);
    }

    public boolean isRight() {
        // TODO
        return false;
    }
}
