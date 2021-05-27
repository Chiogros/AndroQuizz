package com.example.loustics.models;

import android.content.Context;
import android.view.View;

public class YesNo extends QuestionFrame {

    public YesNo(Question question, Context context) {
        super(question, context);
    }

    public View getView(Context context) {
        Question q = getQuestion();
        return new View(context);
    }

    public boolean isRight() {
        // TODO
        return false;
    }
}
