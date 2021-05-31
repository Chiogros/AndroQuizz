package com.example.loustics.models;

import android.content.Context;
import android.view.View;

public class CheckMCQ extends MCQ {

    public CheckMCQ() {
        super();
    }

    public CheckMCQ(Question question, Context context) {
        super(question, context);
    }

    public static CheckMCQ getInstance(Question question, Context context) {
        return new CheckMCQ(question, context);
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
