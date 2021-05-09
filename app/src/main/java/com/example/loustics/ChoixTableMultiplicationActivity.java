package com.example.loustics;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.NumberPicker;

public class ChoixTableMultiplicationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // On charge le XML pour créer l'arbre graphique
        setContentView(R.layout.choix_table_multiplication);

        NumberPicker np = (NumberPicker) findViewById(R.id.np);
        np.setMinValue(0);
        np.setMaxValue(10);
        np.setWrapSelectorWheel(true);
    }

    public void button(View view) {
        Intent intent = new Intent(this, TableMultiplicationActivity.class);
        NumberPicker np = findViewById(R.id.np);
        intent.putExtra(TableMultiplicationActivity.NUMBER_KEY, np.getValue());
        startActivity(intent);
    }
}
