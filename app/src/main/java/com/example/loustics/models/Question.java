package com.example.loustics.models;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

public abstract class Question {

    private Context m_c_context;
    private Subject m_s_subject;

    public Question(Subject subject, Context context) {
        this.m_s_subject = subject;
        this.m_c_context = context;
    }

    public Context getContext() {
        return this.m_c_context;
    }

    protected Subject getSubject() {
        return this.m_s_subject;
    }

    public abstract View getView(Context context);

    public abstract boolean isRight();

}
