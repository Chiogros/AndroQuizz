package com.example.loustics.models;

import android.content.Context;
import android.view.View;

import androidx.room.ColumnInfo;
import androidx.room.PrimaryKey;
import androidx.room.Entity;

public class Addition extends Calculation {

    public Addition(Double operand1, Double operand2) {
        super(operand1, operand2);
    }

    public Double getAlternativeAnswer() {
        Double alternative;
        do {
            // réponse à +/- 20% d'erreur
            alternative = getAnswer() * (Math.random() * 0.2);

            // Verifie que la réponse alternative ne soit pas la bonne réponse
        } while (alternative == getAnswer());

        return alternative;
    }

    public Double getAnswer() {
        return getOperand1() + getOperand2();
    }

    public View getAnswerView(Context context, Object answer) {
        return null;
    }

    public String getM_s_subject() {
        return getOperand1() + " + " + getOperand2() + " = ";
    }

}
