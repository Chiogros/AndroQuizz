package com.example.loustics.models;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import org.json.JSONArray;

public class Open extends QuestionFrame {

    private EditText m_et;

    public Open() {
        super();
    }

    public Open(Question question, Context context) {
        super(question, context);
    }

    public View getView() {
        Context context = getContext();

        LinearLayout ll = new LinearLayout(context);
        ll.setOrientation(LinearLayout.HORIZONTAL);
        ll.setGravity(Gravity.CENTER_HORIZONTAL);

        Question q = getQuestion();
        View v = q.getSubjectView(context);
        ll.addView(v);

        m_et = new EditText(context);
        m_et.setMinWidth(200);
        ll.addView(m_et);

        return ll;
    }

    public boolean isRight() {
        Question q = getQuestion();

        JSONArray jsonRightAnswers = q.getM_json_rightAnswers();

        // TODO : mettre une vérification que ce ne soit pas des ', {, }, [, ], right, wrong, "
        return jsonRightAnswers.toString().contains(m_et.getText());    // si le texte entré est dans le JSON des bonnes réponses, alors on a bien répondu
    }
}
