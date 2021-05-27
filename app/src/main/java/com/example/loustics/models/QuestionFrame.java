package com.example.loustics.models;

import android.content.Context;
import android.view.View;

public abstract class QuestionFrame {

    private Context m_c_context;
    private Question m_s_question;

    public QuestionFrame(Question question, Context context) {
        this.m_s_question = question;
        this.m_c_context = context;
    }

    public Context getContext() {
        return this.m_c_context;
    }

    protected Question getQuestion() {
        return this.m_s_question;
    }

    public abstract View getView(Context context);

    public abstract boolean isRight();

}
