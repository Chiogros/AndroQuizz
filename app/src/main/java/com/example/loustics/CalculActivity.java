package com.example.loustics;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.loustics.models.Addition;

import java.util.LinkedHashMap;
import java.util.Random;

public abstract class CalculActivity extends AppCompatActivity {
    private final int minIter = 0;
    private final int maxIter = 10;
    private Class c_numbersClass;

    // paires calcul-View, View qui peut être EditText ou TextView (lorsque la réponse est bonne)
    private LinkedHashMap<Addition, View> lhm_calculs = new LinkedHashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calcul);

        if (lhm_calculs.isEmpty()) {
            creerTable();
        }
        afficherLayout();
    }

    protected void setClassType(String class_name) throws ClassNotFoundException{
        this.c_numbersClass = Class.forName(class_name);
    }

    public void afficherLayout()
    {
        LinearLayout layout = findViewById(R.id.layoutcalcul);
        layout.removeAllViewsInLayout();

        for (Addition a_k : this.lhm_calculs.keySet()) {
            LinearLayout ll_calcul = new LinearLayout(this);
            layout.addView(ll_calcul);
            ll_calcul.setOrientation(LinearLayout.HORIZONTAL);
            ll_calcul.setGravity(Gravity.CENTER);
            ll_calcul.removeAllViewsInLayout();

            TextView tv_calcul = new TextView(this);
            tv_calcul.setText(a_k.toString());
            ll_calcul.addView(tv_calcul);

            View v_calcul = this.lhm_calculs.get(a_k);
            try { ((ViewGroup) v_calcul.getParent()).removeView(v_calcul); } catch (Exception e) {}
            ll_calcul.addView(v_calcul);
        }
    }

    public void creerTable() {

        for (int i = minIter; i <= maxIter; i++) {
            int nbr1 = new Random().nextInt(10 + 1);
            int nbr2 = new Random().nextInt(10 + 1);

            Addition<Integer> a_calcul = new Addition<>(nbr1, nbr2);
            EditText et_calcul = new EditText(this);
            this.lhm_calculs.put(a_calcul, et_calcul);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void validerAnswers(View view) {

        int erreurs = 0;

        for (Addition a_k : this.lhm_calculs.keySet()) {

            View v_answer = lhm_calculs.get(a_k);

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
                lhm_calculs.replace(a_k, t);

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
