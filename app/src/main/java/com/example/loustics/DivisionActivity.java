package com.example.loustics;

import android.os.Bundle;

import com.example.loustics.models.Calcul;
import com.example.loustics.models.Division;

public class DivisionActivity extends CalculActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void setCalculClass() {
        super.c_calculClass = Division.class;
    }
}