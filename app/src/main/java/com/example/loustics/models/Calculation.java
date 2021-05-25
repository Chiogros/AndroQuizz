package com.example.loustics.models;

import androidx.room.ColumnInfo;
import androidx.room.PrimaryKey;
import androidx.room.Entity;

@Entity public abstract class Calculation extends Subject{

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

    public abstract String getSubject();


}
