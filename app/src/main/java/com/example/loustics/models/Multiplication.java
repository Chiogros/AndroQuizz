package com.example.loustics.models;

import org.jetbrains.annotations.NotNull;

public class Multiplication<T> extends Calcul {

    public Multiplication(T operande1, T operande2) {
        super(operande1, operande2, "*");
    }

    @Override
    public void display() {
        System.out.print(this.operande1 + this.operation + this.operande2);
    }

    @Override
    public double getResult() {
        if (this.operande1.getClass().toString().equals("Byte"))
            return (byte) this.operande1 * (byte) this.operande2;
        else if (this.operande1.getClass().toString().equals("Double"))
            return (double) this.operande1 * (double) this.operande2;
        else if (this.operande1.getClass().toString().equals("Float"))
            return (float) this.operande1 * (float) this.operande2;
        else if (this.operande1.getClass().toString().equals("Integer"))
            return (int) this.operande1 * (int) this.operande2;
        else if (this.operande1.getClass().toString().equals("Long"))
            return (long) this.operande1 * (long) this.operande2;
        else if (this.operande1.getClass().toString().equals("Short"))
            return (short) this.operande1 * (short) this.operande2;
        else
            return Double.MIN_VALUE;
    }

    @NotNull
    public String toString() {
        return (this.operande1 + this.operation + this.operande2);
    }
}
