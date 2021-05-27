package com.example.loustics.models;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

public class Open extends Question {

    public Open(Subject subject, Context context) {
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
