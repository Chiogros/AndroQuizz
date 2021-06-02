package com.example.loustics.models;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.Collections;
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

        int maxAnswers = q.getM_json_rightAnswers().length() + q.getM_json_wrongAnswers().length();
        int numberOfAnswers = (int) (((Math.random() * 10) % (maxAnswers-2))+2);    // nombre entre 2 et le nombre de réponses disponibles

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
            // rg.addView(new CustomRadioButton(getContext(), view));
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

    /*private class CustomRadioButton extends LinearLayout {   // on ne peut pas mettre des images dans des radio boutons, du coup je créé ma classe
        private RadioButton m_rb;
        private View m_view;
        private Context m_context;

        public CustomRadioButton(Context context, View view) {
            super(context);
            m_context = context;
            m_rb = new RadioButton(context);
            setOrientation(HORIZONTAL);
            addView(m_rb);
            addView(view);
        }
    }

    private class CustomRadioGroup extends RadioGroup {   // on ne peut pas mettre des images dans des radio boutons, du coup je créé ma classe

        public CustomRadioGroup(Context context, View view) {
            super(context);
        }
    }*/
}
