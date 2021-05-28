package com.example.loustics.models;

import android.content.Context;
import android.view.View;
import android.widget.ArrayAdapter;

public interface Question {

    Object getAlternativeAnswer();

    Object getAnswer();

    View getAnswerView(Context context, Object answer);

    Object getM_s_subject();

    View getSubjectView(Context context);

}
