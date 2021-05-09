package com.example.loustics;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.LinearLayout;

public class TableMultiplicationActivity extends AppCompatActivity {

    public static final String NUMBER_KEY = "number_key";
    private int number;
    private final int iter = 10;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multiplication);
        this.number = getIntent().getIntExtra(NUMBER_KEY,0);
        afficherTable(this.number);
    }

    public void afficherTable(int number){
        LinearLayout layout = findViewById(R.id.layout);

        for (int i = 0; i<= iter; i++){
            LinearLayout multi = new LinearLayout(this);
            layout.addView(multi);
            multi.setOrientation (LinearLayout.HORIZONTAL);

            TextView txt = new TextView (this);
            txt.setText(String.format("%d x %d = ", number, i));
            multi.addView(txt);

            EditText edit = new EditText(this);
            edit.setId(i);
            multi.addView(edit);
        }
    }

    public void tableMultiValider(View view) {
        int erreurs = 0;
        for (int j = 0; j<=iter; j++){
            EditText edt = findViewById(j);
            if (TextUtils.isEmpty(edt.getText().toString())){
                erreurs++;
                continue;
            }
            if (Integer.parseInt(edt.getText().toString()) != (this.number * j)) {
                erreurs++;
            }
        }

    }
}

