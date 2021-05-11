package com.example.loustics;

import android.os.Bundle;

import com.example.loustics.models.Soustraction;
import com.example.loustics.models.Calcul;

public class SoustractionActivity extends CalculActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void setCalculClass() {
        super.c_calculClass = Soustraction.class;
    }
}