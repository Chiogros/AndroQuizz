package com.example.loustics.models;

import android.content.Context;
import android.view.View;

public abstract class MCQ extends QuestionFrame {

    private int m_i_numberOfChoices;

    public MCQ() {
        super();
    }

    public MCQ(Question question, Context context) {
        super(question, context);
    }

    public int getNumberOfChoices() {
        return m_i_numberOfChoices;
    }

    public abstract View getView();

    public abstract boolean isRight();

    public void setNumberOfChoices(int numberOfChoices) {
        m_i_numberOfChoices = numberOfChoices;
    }
}
