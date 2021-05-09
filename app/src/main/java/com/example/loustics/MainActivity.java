package com.example.loustics;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    // ID REQUETES
    public final static int ACTIVITY_MULTIPLICATION = 3;
    public final static int ACTIVITY_ADDITION = 4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onMultiplication(View view) {

        // Création d'une intention
        Intent TableMultiplication = new Intent(MainActivity.this, ChoixTableMultiplicationActivity.class);

        // Lancement de la demande de changement d'activité
        startActivityForResult(TableMultiplication, ACTIVITY_MULTIPLICATION);
    }

    public void onAddition(View view) {

        // Création d'une intention
        Intent addition = new Intent(MainActivity.this, AdditionActivity.class);

        // Lancement de la demande de changement d'activité
        startActivityForResult(addition, ACTIVITY_ADDITION);
    }
}