package com.example.loustics;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    // ID REQUETES
    public final static int ACTIVITY_MULTIPLICATION = 3;
    public final static int ACTIVITY_ADDITION = 1;
    public final static int ACTIVITY_DIVISION = 4;
    public final static int ACTIVITY_SOUSTRACTION = 2;
    public final static int ACTIVITY_MODULO = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onAddition(View view) {

        // Création d'une intention
        Intent addition = new Intent(MainActivity.this, AdditionActivity.class);

        // Lancement de la demande de changement d'activité
        startActivity(addition);
    }

    public void onSoustraction(View view) {

        // Création d'une intention
        Intent Soustraction = new Intent(MainActivity.this, SoustractionActivity.class);

        // Lancement de la demande de changement d'activité
        startActivity(Soustraction);
    }

    public void onMultiplication(View view) {

        // Création d'une intention
        Intent TableMultiplication = new Intent(MainActivity.this, MultiplicationActivity.class);

        // Lancement de la demande de changement d'activité
        startActivity(TableMultiplication);
    }

    public void onDivision(View view) {

        // Création d'une intention
        Intent Division = new Intent(MainActivity.this, DivisionActivity.class);

        // Lancement de la demande de changement d'activité
        startActivity(Division);
    }

    public void onModulo(View view) {

        // Création d'une intention
        Intent Modulo = new Intent(MainActivity.this, ModuloActivity.class);

        // Lancement de la demande de changement d'activité
        startActivity(Modulo);
    }
}