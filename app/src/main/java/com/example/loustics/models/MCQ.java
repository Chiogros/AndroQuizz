package com.example.loustics.models;

import android.content.Context;
import android.view.View;

public abstract class MCQ extends QuestionFrame {

    public MCQ() {
        super();
    }

    public MCQ(Question question, Context context) {
        super(question, context);
    }

    public abstract View getView();

    public abstract boolean isRight();
}
