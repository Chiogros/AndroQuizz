package com.example.loustics.models;

public class TableMultiplication<T> {

    private final T table;

    TableMultiplication(T table) {
        this.table = table;
    }

    public Multiplication multiple(int produit) {
        return new Multiplication(this.table, produit);
    }


}
