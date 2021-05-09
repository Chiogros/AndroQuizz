package com.example.loustics;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Button;

public class ResultatActivity extends AppCompatActivity {
    public static final String ERREURS = "erreurs";
    private int erreurs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultat);
        this.erreurs = getIntent().getIntExtra(ERREURS,0);

        SetTitre();
        SetBouton();
    }

    private void SetBouton() {
        if (this.erreurs != 0){
            Button boutonC = findViewById(R.id.Correction);
            boutonC.setText("Corriger");

        }
    }


    private void SetTitre() {
        if (this.erreurs != 0){
            TextView message = findViewById(R.id.message_titre);
            message.setText("NOMBRE D'ERREUR(S) : " + this.erreurs);
        }
    }


    public void CorrectionValider(View view) {
        if (this.erreurs!=0) {
            Intent intent = new Intent(this, TableMultiplicationActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        }
        else {
            Intent intent = new Intent(this, ChoixTableMultiplicationActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        }
    }

    public void ExoTableValider(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}
