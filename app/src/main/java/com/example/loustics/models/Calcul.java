package com.example.loustics.models;

import org.jetbrains.annotations.NotNull;

public abstract class Calcul<T> {

    protected T operande1;
    protected T operande2;
    protected String operation;

    Calcul(T operande1, T operande2, String operation) {
        this.operande1 = operande1;
        this.operande2 = operande2;
        this.operation = operation;
    }

    public abstract void display();

    public abstract double getResult();

    public T getOperande1(T operande) {
        return this.operande1;
    }

    public T getOperande2(T operande) {
        return this.operande2;
    }

    public String getOperation(String operation) {
        return this.operation;
    }

    public void setOperande1(T operande) {
        this.operande1 = operande;
    }

    public void setOperande2(T operande) {
        this.operande2 = operande;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    @NotNull
    public abstract String toString();

}
