package com.example.loustics;

import android.os.Bundle;

import com.example.loustics.models.Addition;
import com.example.loustics.models.Calcul;

public class AdditionActivity extends CalculActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void setCalculClass() {
        super.c_calculClass = Addition.class;
    }
}