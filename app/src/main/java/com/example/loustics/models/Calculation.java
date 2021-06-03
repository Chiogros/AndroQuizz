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

    public Calculation(int m_s_difficulty) {
        switch (m_s_difficulty) {
            case 1:
                this.m_d_operand1 = getRandomOperand(0, 10);
                this.m_d_operand2 = getRandomOperand(0, 10);
                break;
            case 2:
                this.m_d_operand1 = getRandomOperand(0, 100);
                this.m_d_operand2 = getRandomOperand(0, 100);
                break;
            case 3:
                this.m_d_operand1 = getRandomOperand(0, 1000);
                this.m_d_operand2 = getRandomOperand(0, 1000);
                break;
            case 4:
                this.m_d_operand1 = getRandomOperand(-100, 100);
                this.m_d_operand2 = getRandomOperand(-100, 100);
                break;
            case 5:
                this.m_d_operand1 = getRandomOperand(-1000000, 1000000);
                this.m_d_operand2 = getRandomOperand(-1000000, 1000000);
                break;
        }

        m_json_rightAnswers = getM_json_answers().optJSONArray("right");
        m_json_wrongAnswers = getM_json_answers().optJSONArray("wrong");
    }

    public abstract boolean isRight(Object answer);

    private int getAlternativeAnswer() {
        int val, i = 1;
        do {
            int signe = ((Math.random() * 2) - 1) > 0 ? 1 : -1;
            val = (int) ((signe * (Math.random() * getResult())) + signe * Math.random() * i);
            i++;
        } while (val == getResult());
        return val;
    }

    public View getAnswerView(Object answer, Context context) {
        TextView tv = new TextView(context);
        tv.setText(answer.toString());
        return tv;
    }

    public abstract int getM_s_difficulty();

    public JSONObject getM_json_answers() {
        int numberOfWrongAnswers = 5;
        ArrayList<Integer> alreadyGenerated = new ArrayList<>();

        // Création du json aléatoire
        String jsonString = "{'right' : [" + getResult() + "], 'wrong' : [";
        while (alreadyGenerated.size() < numberOfWrongAnswers){
            int generated = getAlternativeAnswer();

            if (alreadyGenerated.contains(generated))
                continue;

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
