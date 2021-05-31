package com.example.loustics.models;

import android.content.Context;
import android.view.View;

public abstract class QuestionFrame {

    private Context m_c_context;
    private Question m_s_question;

    protected QuestionFrame() {}

    protected QuestionFrame(Question question, Context context) {
        this.m_s_question = question;
        this.m_c_context = context;
    }

    public Context getContext() {
        return this.m_c_context;
    }

    public Question getQuestion() {
        return this.m_s_question;
    }

    public abstract View getView();

    public abstract boolean isRight();

    public void setContext(Context context) {
        m_c_context = context;
    }

    public void setQuestion(Question question) {
        m_s_question = question;
    }

}
