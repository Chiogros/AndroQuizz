package com.example.loustics.models;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.loustics.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Random;

public class RadioMCQ extends MCQ {

    public RadioMCQ() {
        super();
    }

    public RadioMCQ(Question question, Context context) {
        super(question, context);
    }

    public View getView() {
        Question q = getQuestion();

        View questionSubjectView = q.getSubjectView(getContext());

        int maxWrongAnswers = q.getM_json_wrongAnswers().length();
        int numberOfAnswers = (int) (Math.random() * 3)+2;    // nombre entre 2 et le nombre de réponses disponibles
        if (numberOfAnswers > maxWrongAnswers) numberOfAnswers = maxWrongAnswers;

        ArrayList<View> answersViews = new ArrayList<>();   // contient toutes les réponses qui seront proposées
        answersViews.add(q.getAnswerView(q.getRightAnswer(), getContext()));    // on y met au moins une réponse juste
        while (answersViews.size() < numberOfAnswers) { // récupère les réponses et les met dans answersViews
            View v = q.getAnswerView(q.getWrongAnswer(), getContext());

            if (!answersViews.contains(v)) {    // vérifie que cette réponse n'a pas déjà été choisie
                answersViews.add(v);
            }
        }
        Collections.shuffle(answersViews);  // mélange les réponses

        // créé les boutons avec les vues
        RadioGroup rg = new RadioGroup(getContext());
        for (View view : answersViews) {
            RadioButton rb = new RadioButton(getContext());

            if (view instanceof ImageView)
                rb.setBackground(view.getBackground());

            if (view instanceof TextView)
                rb.setText(((TextView) view).getText());

            rg.addView(rb);
        }

        LinearLayout ll_question = new LinearLayout(getContext());
        ll_question.setOrientation(LinearLayout.VERTICAL);
        ll_question.addView(questionSubjectView);
        ll_question.addView(rg);
        return ll_question;
    }

    public boolean isRight() {
        // TODO
        return false;
    }

}
