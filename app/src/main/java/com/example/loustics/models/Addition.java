package com.example.loustics.models;

public class Addition extends Calculation {

    public Addition(Double operand1, Double operand2) {
        super(operand1, operand2);
    }

    @Override
    public Double getAlternativeAnswer() {
        Double alternative;
        do {
            // réponse à +/- 20% d'erreur
            alternative = getAnswer() * (Math.random() * 0.2);

            // Verifie que la réponse alternative ne soit pas la bonne réponse
        } while (alternative == getAnswer());

        return alternative;
    }

    @Override
    public Double getAnswer() {
        return getOperand1() + getOperand2();
    }

    @Override
    public String getSubject() {
        return getOperand1() + " + " + getOperand2() + " = ";
    }

}
