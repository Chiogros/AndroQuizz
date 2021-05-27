package com.example.loustics.models;

import android.content.Context;
import android.view.View;

public abstract class MCQ extends QuestionFrame {

    private int m_i_numberOfChoices;

    public MCQ(Question question, Context context) {
        super(question, context);
    }

    public int getNumberOfChoices() {
        return m_i_numberOfChoices;
    }

    public abstract View getView(Context context);

    public abstract boolean isRight();
}
