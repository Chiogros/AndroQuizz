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

    public CheckMCQ(Question question, Context context) {
        super(question, context);
    }

    public static CheckMCQ getInstance(Question question, Context context) {
        return new CheckMCQ(question, context);
    }

    public View getView() {
        m_al_checkboxesToNotCheck = new ArrayList<>();
        m_al_checkboxesToCheck = new ArrayList<>();
        ArrayList<View> al_rightViews = new ArrayList<>();

        Question q = getQuestion();

        View questionSubjectView = q.getSubjectView(getContext());

        int maxWrongAnswers = q.getM_json_wrongAnswers().length();
        int numberOfAnswers = (int) (Math.random() * 4)+2;    // nombre entre 2 et le nombre de réponses disponibles
        if (numberOfAnswers > maxWrongAnswers) numberOfAnswers = maxWrongAnswers;

        ArrayList<View> answersViews = new ArrayList<>();   // contient toutes les réponses qui seront proposées

        ArrayList<String> al_answersStrings = new ArrayList<>();    // contient tous les sujets en String, afin de vérifier qu'elle ne soit pas déjà prise
        String firstRightAnswer = q.getRightAnswer();   // récupère une bonne réponse
        al_answersStrings.add(firstRightAnswer);

        View v_minimalRightAnswer = q.getAnswerView(firstRightAnswer, getContext()); // on isole la vue de la bonne réponse pour ensuite pouvoir récupérer l'ID du CheckBouton qui est juste
        answersViews.add(v_minimalRightAnswer);    // on y met au moins la réponse juste
        al_rightViews.add(v_minimalRightAnswer);

        while (answersViews.size() < numberOfAnswers) { // récupère les réponses et les met dans answersViews
            String s;
            boolean isRight;

            if (new Random().nextBoolean()) {
                s = q.getRightAnswer();
                isRight = true; // indique que cette réponse est bonne et qu'on doit ajouter sa vue en tant que réponse juste
            } else {
                s = q.getWrongAnswer();
                isRight = false;    // indique que cette réponse est fausse et qu'on ne doit pas ajouter sa vue en tant que réponse juste
            }

            if (al_answersStrings.contains(s)) // vérifie que cette réponse n'a pas déjà été choisie
                continue;

            al_answersStrings.add(s);

            View v = q.getAnswerView(s, getContext());  // génère la vue de la réponse
            answersViews.add(v);    // ajoute la vue avec les autres pour l'afficher

            if (isRight)    // la réponse est juste, on ajoute sa vue avec les autres qui sont justes
                al_rightViews.add(v);
        }
        Collections.shuffle(answersViews);  // mélange les réponses

        // on créé la ligne qui accueille toute la question
        LinearLayout ll_question = new LinearLayout(getContext());
        ll_question.setOrientation(LinearLayout.VERTICAL);
        ll_question.addView(questionSubjectView);

        // créé les boutons avec les vues
        for (View view : answersViews) {

            CheckBox cb = new CheckBox(getContext());

            if (view instanceof ImageView) {  // met l'image en fond si on récupère une ImageView de la question
                cb.setText("");
                cb.setCompoundDrawablesWithIntrinsicBounds(((ImageView) view).getDrawable(), null, null, null);
            }

            if (view instanceof TextView)   // change le texte si on récupère un TextView de la question
                cb.setText(((TextView) view).getText());

            if (al_rightViews.contains(view)) {  // si c'est une vue qui est juste, on garde ce bouton pour la vérification
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
