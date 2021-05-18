package com.example.loustics.models;

public class Addition extends Calculation {

    public Addition(Double operand1, Double operand2) {
        super(operand1, operand2);
    }

    @Override
    public Double getAlternativeAnswer() {
        return null;
    }

    @Override
    public Double getAnswer() {
        return getOperand1() + getOperand2();
    }

    @Override
    public String getSubject() {
        return null;
    }

}
