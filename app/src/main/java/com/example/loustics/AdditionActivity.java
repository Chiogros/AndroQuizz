package com.example.loustics;

import android.os.Bundle;

import com.example.loustics.models.Addition;


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