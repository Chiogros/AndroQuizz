package com.example.loustics.models;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Space;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.example.loustics.R;

import org.w3c.dom.Text;

import java.util.Random;

public class YesNo extends QuestionFrame {

    private ToggleButton m_tb;
    private boolean m_b_isRight;

    public YesNo() {
        super();
    }

    public YesNo(Question question, Context context) {
        super(question, context);
    }

    public View getView() {
        Question question = getQuestion();

        View questionSubjectView = question.getSubjectView(getContext());
        View questionAnswerView;

        if (new Random().nextBoolean()) {   // choisis aléatoirement si la sujet sera une réponse juste ou fausse impossible
            questionAnswerView = question.getAnswerView(question.getRightAnswer(), getContext());
            m_b_isRight = true;
        } else {
            questionAnswerView = question.getAnswerView(question.getWrongAnswer(), getContext());
            m_b_isRight = false;
        }

        if (questionSubjectView instanceof TextView && questionAnswerView instanceof TextView) {
            TextView tv_subject = ((TextView) questionSubjectView);
            TextView tv_answer = ((TextView) questionAnswerView);

            // insère le mot à la place s'il y a ces 5 underscores
            if (tv_subject.getText().toString().contains("_____")) {
                tv_subject.getText().toString().replace("_____", tv_answer.getText().toString());
            } else {
                tv_subject.append(" " + ((TextView) questionAnswerView).getText());
            }

            tv_answer.setText("");
        }

        // Pour séparer le texte du sujet que la réponse
        TextView space = new TextView(getContext());
        space.setText(" ");

        LinearLayout ll_subject = new LinearLayout(getContext());
        // ll_subject.setOrientation(LinearLayout.HORIZONTAL);
        ll_subject.addView(questionSubjectView);
        ll_subject.addView(space);
        ll_subject.addView(questionAnswerView);

        m_tb = new ToggleButton(getContext());
        m_tb.setTextOff("Faux");
        m_tb.setTextOn("Vrai");
        m_tb.toggle();

        LinearLayout ll_question = new LinearLayout(getContext());
        ll_question.setOrientation(LinearLayout.VERTICAL);
        ll_question.addView(ll_subject);
        ll_question.addView(m_tb);
        return ll_question;
    }

    public boolean isRight() {
        return m_tb.isChecked() == m_b_isRight;
    }
}
