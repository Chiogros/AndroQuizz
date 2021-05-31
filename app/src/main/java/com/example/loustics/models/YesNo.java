package com.example.loustics.models;

import android.content.Context;
import android.view.View;

public class YesNo extends QuestionFrame {

    public YesNo() {
        super();
    }

    public YesNo(Question question, Context context) {
        super(question, context);
    }

    public View getView() {
        Question q = getQuestion();
        return new View(getContext());
    }

    public boolean isRight() {
        // TODO
        return false;
    }
}
