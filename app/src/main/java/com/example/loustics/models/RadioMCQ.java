package com.example.loustics.models;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ScaleDrawable;
import android.os.Build;
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

    private RadioGroup m_rg_buttons;
    private RadioButton m_rb_rightOneToCheck;

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
        int numberOfAnswers = (int) (Math.random() * 4)+2;    // nombre entre 2 et le nombre de réponses disponibles
        if (numberOfAnswers > maxWrongAnswers+1) numberOfAnswers = maxWrongAnswers+1;   // +1 pour la réponse juste

        ArrayList<View> answersViews = new ArrayList<>();   // contient toutes les réponses qui seront proposées
        ArrayList<String> al_answers = new ArrayList<>();   // contient toutes les réponses String pour la vérification dans la boucle

        ArrayList<String> al_answersStrings = new ArrayList<>();    // contient tous les sujets en String, afin de vérifier qu'elle ne soit pas déjà prise
        String firstRightAnswer = q.getRightAnswer();   // récupère une bonne réponse
        al_answersStrings.add(firstRightAnswer);

        View v_minimalRightAnswer = q.getAnswerView(firstRightAnswer, getContext()); // on isole la vue de la bonne réponse pour ensuite pouvoir récupérer l'ID du RadioBouton qui est juste
        answersViews.add(v_minimalRightAnswer);    // on y met au moins la réponse juste

        while (answersViews.size() < numberOfAnswers) { // récupère les réponses et les met dans answersViews
            String s;
            s = q.getWrongAnswer();

            if (al_answersStrings.contains(s)) // vérifie que cette réponse n'a pas déjà été choisie
                continue;

            al_answersStrings.add(s);

            View v = q.getAnswerView(s, getContext());  // génère la vue de la réponse
            answersViews.add(v);    // ajoute la vue avec les autres pour l'afficher
        }
        Collections.shuffle(answersViews);  // mélange les réponses

        // créé les boutons avec les vues
        m_rg_buttons = new RadioGroup(getContext());
        for (View view : answersViews) {

            RadioButton rb = new RadioButton(getContext());

            if (view instanceof ImageView)  // met l'image en fond si on récupère une ImageView de la question
                rb.setCompoundDrawablesWithIntrinsicBounds(null, null, ((ImageView) view).getDrawable(), null);

            if (view instanceof TextView)   // change le texte si on récupère un TextView de la question
                rb.setText(((TextView) view).getText());

            if (view == v_minimalRightAnswer)  // si c'est la vue qui est juste, on garde ce bouton pour la vérification
                m_rb_rightOneToCheck = rb;

            m_rg_buttons.addView(rb);
        }

        LinearLayout ll_question = new LinearLayout(getContext());
        ll_question.setOrientation(LinearLayout.VERTICAL);
        ll_question.addView(questionSubjectView);
        ll_question.addView(m_rg_buttons);

        return ll_question;
    }

    public boolean isRight() {
        return m_rg_buttons.getCheckedRadioButtonId() == m_rb_rightOneToCheck.getId();  // vérifie si le bouton sélectionné est bien celui qui est juste
    }

}
