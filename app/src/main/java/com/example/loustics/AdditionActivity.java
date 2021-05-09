package com.example.loustics;

import android.os.Bundle;

public class AdditionActivity extends CalculActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try { setClassType("Addition"); } catch (Exception e) {};
    }
}
