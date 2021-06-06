package com.example.loustics.models;

import android.content.Context;
import android.util.JsonReader;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.room.Ignore;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Random;

public abstract class Calculation extends Question {

    @Ignore
    protected int m_d_operand1;
    @Ignore
    protected int m_d_operand2;

    public Calculation(int rangeMinOperand1, int rangeMaxOperand1, int rangeMinOperand2, int rangeMaxOperand2) {

        this.m_d_operand1 = getRandomOperand(rangeMinOperand1, rangeMaxOperand1);
        this.m_d_operand2 = getRandomOperand(rangeMinOperand2, rangeMaxOperand2);

        // parser le json dans deux array
        m_json_rightAnswers = getM_json_answers().optJSONArray("right");
        m_json_wrongAnswers = getM_json_answers().optJSONArray("wrong");
    }

    private int getAlternativeAnswer() {
        int val, i = 1;
        do {
            int signe = ((Math.random() * 2) - 1) > 0 ? 1 : -1;
            val = (int) ((signe * (Math.random() * getResult())) + signe * Math.random() * i);
            i++;

            // on met une valeur de secours si on tombe sur un nombre qui s'arrondit mal au bout de la 50ème fois
            if (i >= 50) {
                val = (int) (Math.random() * 100);
            }
        } while (val == getResult());
        return val;
    }

    public View getAnswerView(Object answer, Context context) {
        TextView tv = new TextView(context);
        tv.setText(answer.toString());
        return tv;
    }

    public abstract int getM_i_rangeMinOperand1();

    public abstract int getM_i_rangeMaxOperand1();

    public abstract int getM_i_rangeMinOperand2();

    public abstract int getM_i_rangeMaxOperand2();

    public JSONObject getM_json_answers() {
        int numberOfWrongAnswers = 5;
        ArrayList<Integer> alreadyGenerated = new ArrayList<>();

        int tries = 0;

        // Création du json aléatoire
        String jsonString = "{'right' : [" + getResult() + "], 'wrong' : [";
        while (alreadyGenerated.size() < numberOfWrongAnswers) {
            int generated = getAlternativeAnswer();

            // on met une valeur de secours si on tombe sur un nombre qui s'arrondit mal au bout de la 50ème fois
            if (tries > 50)
                generated = (int) (Math.random() * 100);

            if (alreadyGenerated.contains(generated)) {
                tries++;
                continue;
            }

            alreadyGenerated.add(generated);
            jsonString += generated;

            if (alreadyGenerated.size() < numberOfWrongAnswers)
                jsonString += ", ";
        }

        jsonString += "]}";

        try {
            return new JSONObject(jsonString);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return new JSONObject();
    }

    public abstract String getM_s_chapterName();

    public abstract String getM_s_courseName();

    public abstract String getM_s_subject();

    public static int getRandomOperand(int rangeMin, int rangeMax) {
        return (int) (rangeMin + (Math.random() * (rangeMax - rangeMin)));
    }

    public abstract int getResult();

    public final View getSubjectView(Context context) {
        TextView tv = new TextView(context);
        tv.setText(getM_s_subject());
        return tv;
    }
}
