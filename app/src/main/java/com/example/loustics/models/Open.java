package com.example.loustics.models;

import android.content.Context;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

public class Open extends QuestionFrame {

    public Open() {
        super();
    }

    public Open(Question question, Context context) {
        super(question, context);
    }

    public View getView() {
        Context context = getContext();
        LinearLayout ll = new LinearLayout(context);
        ll.setOrientation(LinearLayout.VERTICAL);

        Question q = getQuestion();
        View v = q.getSubjectView();
        ll.addView(v);

        EditText et = new EditText(context);
        ll.addView(et);

        return ll;
    }

    public boolean isRight() {
        return false;
    }
}
