package com.example.loustics.models;

import android.widget.ArrayAdapter;

public interface Subject extends ArrayAdapter<> {

    Object getAlternativeAnswer();

    Object getAnswer();

    Object getSubject();

}
