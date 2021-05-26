package com.example.loustics.models;

import android.content.Context;
import android.view.View;
import android.widget.ArrayAdapter;

public interface Subject {

    Object getAlternativeAnswer();

    Object getAnswer();

    Object getSubject();

    View getView(Context context);

}
