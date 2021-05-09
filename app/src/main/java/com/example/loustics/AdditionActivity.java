package com.example.loustics;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Random;

import com.example.loustics.models.Addition;

public class AdditionActivity extends AppCompatActivity {

    private final int minIter = 0;
    private final int maxIter = 10;
    //private Class c_numbersClass;

    // paires Addition-View, View qui peut être EditText ou TextView (lorsque la réponse est bonne)
    private LinkedHashMap<Addition, View> lhm_additions = new LinkedHashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addition);

        if (lhm_additions.isEmpty()) {
            creerTable();
        }
        afficherLayout();
    }

    /*public void afficherTable()
    {
        LinearLayout layout = findViewById(R.id.layoutAddition);

        if (layout.getChildCount() > 0) {
            layout.removeAllViews();
        }

        for (int i = 0; i <= this.iter; i++) {
            LinearLayout addition = new LinearLayout(this);
            layout.addView(addition);
            addition.setOrientation(LinearLayout.HORIZONTAL);

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
    }*/

    public void afficherLayout()
    {
        LinearLayout layout = findViewById(R.id.layoutAddition);
        layout.removeAllViewsInLayout();

        for (Addition a_k : this.lhm_additions.keySet()) {
            LinearLayout ll_addition = new LinearLayout(this);
            layout.addView(ll_addition);
            ll_addition.setOrientation(LinearLayout.HORIZONTAL);
            ll_addition.setGravity(Gravity.CENTER);
            ll_addition.removeAllViewsInLayout();

            TextView tv_addition = new TextView(this);
            tv_addition.setText(a_k.toString());
            ll_addition.addView(tv_addition);

            View v_addition = this.lhm_additions.get(a_k);
            try { ((ViewGroup) v_addition.getParent()).removeView(v_addition); } catch (Exception e) {}
            ll_addition.addView(v_addition);
        }
    }

    public void creerTable() {

        for (int i = minIter; i <= maxIter; i++) {
            int nbr1 = new Random().nextInt(10 + 1);
            int nbr2 = new Random().nextInt(10 + 1);

            Addition<Integer> a_addition = new Addition<>(nbr1, nbr2);
            EditText et_addition = new EditText(this);
            this.lhm_additions.put(a_addition, et_addition);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void validerAnswers(View view) {

        int erreurs = 0;

        for (Addition a_k : this.lhm_additions.keySet()) {

            View v_answer = lhm_additions.get(a_k);

            // c'est déjà juste
            if (v_answer.getClass() == TextView.class) {
                continue;
            }

            // c'est donc à corriger
            EditText et_answer = (EditText) v_answer;

            // si rien n'a été rentré
            if (et_answer.getText().length() == 0) {
                erreurs++;
                continue;
            }

            // une valeur a été rentrée
            if (Integer.parseInt(et_answer.getText().toString()) == a_k.getResultAsInt()) {

                TextView t = new TextView(this);
                t.setText(et_answer.getText());
                t.setTextColor(Color.parseColor("#28E515"));
                lhm_additions.replace(a_k, t);

            } else {
                erreurs++;
            }
        }

        // Notification du nombre d'erreurs
        String s_nb_errors = "Vous avez fait " + erreurs + " erreur";
        if (erreurs > 1) {
            s_nb_errors += "s";
        }
        Toast t_display_number_of_errors = Toast.makeText(getApplicationContext(), s_nb_errors, Toast.LENGTH_LONG);
        t_display_number_of_errors.show();

        afficherLayout();
    }
}
