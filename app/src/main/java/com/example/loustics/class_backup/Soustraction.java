package com.example.loustics.class_backup;

import org.jetbrains.annotations.NotNull;

public class Soustraction<T> extends Calcul {

    private final String operation = "-";

    public Soustraction() { super(); }

    public Soustraction(T operande1, T operande2) {
        super(operande1, operande2);
    }

    @Override
    public String getOperation() {
        return operation;
    }

    @Override
    public double getResult() {
        if (this.operande1 instanceof Byte)
            return (byte) this.operande1 - (byte) this.operande2;
        else if (this.operande1 instanceof Double)
            return (double) this.operande1 - (double) this.operande2;
        else if (this.operande1 instanceof Float)
            return (float) this.operande1 - (float) this.operande2;
        else if (this.operande1 instanceof Integer)
            return (int) this.operande1 - (int) this.operande2;
        else if (this.operande1 instanceof Long)
            return (long) this.operande1 - (long) this.operande2;
        else if (this.operande1 instanceof Short)
            return (short) this.operande1 - (short) this.operande2;
        else
            return Double.MIN_VALUE;
    }

    @NotNull
    public String toString() {
        return (this.operande1 + operation + this.operande2 + " = ");
    }

}
