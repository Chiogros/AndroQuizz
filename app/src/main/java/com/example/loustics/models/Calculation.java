package com.example.loustics.models;

import android.content.Context;
import android.util.JsonReader;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.room.Ignore;

import org.json.JSONException;
import org.json.JSONObject;

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
        return (int) (m_d_operand1 + m_d_operand2 + ((new Random().nextInt() - new Random().nextInt()) % (0.2 * (m_d_operand1 + m_d_operand2))));
    }

    public View getAnswerView(Object answer, Context context) {
        TextView tv = new TextView(context);
        tv.setText(answer.toString());
        return tv;
    }

    public JSONObject getM_json_answers() {
        // Création du json
        String jsonString = "{'right' : [" + getResult() + "], 'wrong' : [";
        for(int i = 0 ; i < 10 ; i++) {
            jsonString += getAlternativeAnswer();
            if (i < 9) jsonString += ", ";
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
