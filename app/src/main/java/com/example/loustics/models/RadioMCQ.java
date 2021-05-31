package com.example.loustics.models;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

public class RadioMCQ extends MCQ {

    public RadioMCQ() {
        super();
    }

    public RadioMCQ(Question question, Context context) {
        super(question, context);
    }

    public View getView() {
        Question q = getQuestion();
        return new View(getContext());
    }

    public boolean isRight() {
        // TODO
        return false;
    }
}
