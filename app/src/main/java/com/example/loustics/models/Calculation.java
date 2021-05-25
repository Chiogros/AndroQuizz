package com.example.loustics.models;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

public abstract class Calculation implements Subject {

    private Double m_d_operand1;
    private Double m_d_operand2;

    public Calculation(Double operand1, Double operand2) {
        this.m_d_operand1 = operand1;
        this.m_d_operand2 = operand2;
    }

    public abstract Double getAlternativeAnswer();

    public abstract Double getAnswer();

    public Double getOperand1() {
        return this.m_d_operand1;
    }

    public Double getOperand2() {
        return this.m_d_operand2;
    }

    // TODO : définir le paramètre classe ou pas
    // public static Double getRandomOperand(, Double rangeMin, Double rangeMax) {}

    public abstract String getSubject();

    public final View getView(Context context) {
        TextView tv = new TextView(context);
        tv.setText(getSubject());
        return tv;
    }
}
