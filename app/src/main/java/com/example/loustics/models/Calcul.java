package com.example.loustics.models;

import org.jetbrains.annotations.NotNull;

public abstract class Calcul<T> {

    protected T operande1;
    protected T operande2;

    protected Calcul() {}

    protected Calcul(T operande1, T operande2) {
        this.operande1 = operande1;
        this.operande2 = operande2;
    }

    public abstract double getResult();

    public int getResultAsInt() {
        return (int) getResult();
    }

    public T getOperande1(T operande) {
        return this.operande1;
    }

    public T getOperande2(T operande) {
        return this.operande2;
    }

    public abstract String getOperation();

    public void setOperande1(T operande) {
        this.operande1 = operande;
    }

    public void setOperande2(T operande) {
        this.operande2 = operande;
    }

    @NotNull
    public abstract String toString();

}
