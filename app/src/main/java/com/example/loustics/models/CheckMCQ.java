package com.example.loustics.models;

import android.content.Context;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class CheckMCQ extends MCQ {

    private ArrayList<CheckBox> m_al_checkboxesToNotCheck, m_al_checkboxesToCheck;
    private ArrayList<View> m_al_rightViews;

    public CheckMCQ() {
        super();
    }

    public CheckMCQ(Question question, Context context) {
        super(question, context);
    }

    public static CheckMCQ getInstance(Question question, Context context) {
        return new CheckMCQ(question, context);
    }

    public View getView() {
        Question q = getQuestion();

        View questionSubjectView = q.getSubjectView(getContext());

        int maxWrongAnswers = q.getM_json_wrongAnswers().length();
        int numberOfAnswers = (int) (Math.random() * 4)+2;    // nombre entre 2 et le nombre de réponses disponibles
        if (numberOfAnswers > maxWrongAnswers) numberOfAnswers = maxWrongAnswers;

        ArrayList<View> answersViews = new ArrayList<>();   // contient toutes les réponses qui seront proposées

        View v_minimalRightAnswer = q.getAnswerView(q.getRightAnswer(), getContext()); // on isole la vue de la bonne réponse pour ensuite pouvoir récupérer l'ID du RadioBouton qui est juste
        answersViews.add(v_minimalRightAnswer);    // on y met au moins une réponse juste
        m_al_rightViews.add(v_minimalRightAnswer);

        while (answersViews.size() < numberOfAnswers) { // récupère les réponses et les met dans answersViews
            View v;
            boolean rightAnswer;

            if (new Random().nextBoolean()) {
                v = q.getAnswerView(q.getRightAnswer(), getContext());
                rightAnswer = true;
            } else {
                v = q.getAnswerView(q.getWrongAnswer(), getContext());
                rightAnswer = false;
            }

            if (answersViews.contains(v))   // vérifie que cette réponse n'a pas déjà été choisie
                continue;

            answersViews.add(v);
            m_al_rightViews.add(v);
        }
        Collections.shuffle(answersViews);  // mélange les réponses

        // on créé la ligne qui accueille toute la question
        LinearLayout ll_question = new LinearLayout(getContext());
        ll_question.setOrientation(LinearLayout.VERTICAL);
        ll_question.addView(questionSubjectView);

        // créé les boutons avec les vues
        for (View view : answersViews) {

            CheckBox cb = new CheckBox(getContext());

            if (view instanceof ImageView)  // met l'image en fond si on récupère une ImageView de la question
                cb.setBackground(view.getBackground());

            if (view instanceof TextView)   // change le texte si on récupère un TextView de la question
                cb.setText(((TextView) view).getText());

            if (m_al_rightViews.contains(view)) {  // si c'est une vue qui est juste, on garde ce bouton pour la vérification
                m_al_checkboxesToCheck.add(cb);
            } else {
                m_al_checkboxesToNotCheck.add(cb);    // garde toutes les checkbox fausses pour la vérification
            }

            ll_question.addView(cb);
        }

        return ll_question;
    }

    public boolean isRight() {
        for (CheckBox cb : m_al_checkboxesToCheck) {    // vérifie que toutes les CheckBox qui doivent être sélectionnées le soient
            if (!cb.isChecked())
                return false;
        }

        for (CheckBox cb : m_al_checkboxesToNotCheck) { // vérifie que toutes les ChecBox qui ne doivent pas être sélectionnées ne le sont pas
            if (cb.isChecked())
                return false;
        }

        return true;    // tout s'est bien passé, seules les CheckBox justes ont été sélectionnées
    }
}
