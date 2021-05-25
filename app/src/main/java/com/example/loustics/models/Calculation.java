package com.example.loustics.models;

import android.view.View;

public abstract class Calculation implements Subject {

    private Double operand1;
    private Double operand2;

    public Calculation(Double operand1, Double operand2) {
        this.operand1 = operand1;
        this.operand2 = operand2;
    }

    public abstract Double getAlternativeAnswer();

    public abstract Double getAnswer();

    public Double getOperand1() {
        return this.operand1;
    }

    public Double getOperand2() {
        return this.operand2;
    }

    // TODO : définir le paramètre classe ou pas
    // public static Double getRandomOperand(, Double rangeMin, Double rangeMax) {}

    public abstract String getSubject();

    // TODO : problème pour récupérer le contexte

    public void setOperand1(Double operand) {
        this.operand1 = operand;
    }

    public void setOperand2(Double operand) {
        this.operand2 = operand;
    }


}
