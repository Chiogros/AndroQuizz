package com.example.loustics;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.LinearLayout;
import java.util.Random;
import java.util.List;
import java.util.ArrayList;
import android.graphics.Color;

import static java.lang.String.*;

public class AdditionActivity extends AppCompatActivity {

    private final int iter = 10;
    private final List<Integer> tab1 = new ArrayList<>();
    private final List<Integer> tab2 = new ArrayList<>();
    private final List<String> correction = new ArrayList<>();
    private final List<Integer> resultat_entree = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addition);
        afficherTable();
    }

    public void afficherTable()
    {
        LinearLayout layout = findViewById(R.id.layoutAddition);

        if(((LinearLayout) layout).getChildCount() > 0) {
            ((LinearLayout) layout).removeAllViews();
        }

        for (int i = 0; i<= this.iter; i++){
            LinearLayout addition = new LinearLayout(this);
            layout.addView(addition);
            addition.setOrientation (LinearLayout.HORIZONTAL);

            Random rand1 = new Random();
            int nbr1 = rand1.nextInt(10 + 1);
            this.tab1.add(nbr1);
            Random rand2 = new Random();
            int nbr2 = rand2.nextInt(10 + 1);
            this.tab2.add(nbr2);

            TextView txt = new TextView (this);
            txt.setText(format("%d + %d = ", nbr1, nbr2));
            addition.addView(txt);

            if (this.resultat_entree.isEmpty()) {
                EditText edit = new EditText(this);
                edit.setId(i);
                addition.addView(edit);
            }
            else {
                if (this.correction.get(i).equals("OK")){
                    TextView editv = new TextView (this);
                    editv.setTextColor(Color.parseColor("#28e515"));     //Vert
                    editv.setText(format("%d", this.resultat_entree.get(i)));
                    addition.addView(editv);
                }
                else{
                    EditText edit = new EditText(this);
                    edit.setId(i);
                    addition.addView(edit);
                }
            }


            TextView reponse = new TextView (this);
            if (this.correction.isEmpty()) {
                for (int j = 0; j<=this.iter; j++) {
                    this.correction.add(" ");
                }
            }
            else {
                if (this.correction.get(i).equals("OK")) {
                    reponse.setTextColor(Color.parseColor("#28e515"));     //Vert
                } else {
                    reponse.setTextColor(Color.parseColor("#e51515"));     //Rouge
                }
            }
            reponse.setText(format("    %s", this.correction.get(i)));
            addition.addView(reponse);
        }
    }


    public void tableAddValider(View view) {
        int erreurs = 0;
        for (int j = 0; j<=iter; j++){
            EditText edt = findViewById(j);
            this.resultat_entree.add(Integer.parseInt(edt.getText().toString()));
            if (TextUtils.isEmpty(edt.getText().toString())){
                erreurs++;
                this.correction.set(j, "FAUX");
                continue;
            }
            else {
                this.correction.set(j, "OK");
            }
            if (Integer.parseInt(edt.getText().toString()) != (this.tab1.get(j) + this.tab2.get(j))) {
                erreurs++;
                this.correction.set(j, "FAUX");
            }
        }
        afficherTable();
    }
}
