package com.example.loustics.models;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

public class Litteral implements Subject {
    private String m_s_sentence;

    public Litteral(String sentence) {
        this.m_s_sentence = sentence;
    }

    public String getAlternativeAnswer() {
        // Renvoie une réponse aléatoire
        return null;
    }

    public String getAnswer() {
        return null;
    }

    public String getSentence() {
        return this.m_s_sentence;
    }

    public String getSubject() {
        return this.m_s_sentence;
    }

    @Override
    public View getView(Context context) {
        TextView tv = new TextView(context);
        tv.setText(this.m_s_sentence);
        return tv;
    }

}
