package com.example.loustics;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.loustics.models.Calcul;

import java.util.LinkedHashMap;
import java.util.Random;

public abstract class CalculActivity extends AppCompatActivity {
    private final int minIter = 0;
    private final int maxIter = 10;
    protected Class<? extends Calcul> c_calculClass;

    // paires calcul-View, View qui peut être EditText ou TextView (lorsque la réponse est bonne)
    private LinkedHashMap<Calcul, View> lhm_calculs = new LinkedHashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calcul);

        setCalculClass();
        if (lhm_calculs.isEmpty()) {
            try { creerTable(); } catch (Exception e) {
                Log.e("creerTable()", e.getMessage()); };
        }
        afficherLayout();
    }

    public void afficherLayout()
    {
        LinearLayout layout = findViewById(R.id.layoutcalcul);
        layout.removeAllViewsInLayout();

        for (Calcul c_k : this.lhm_calculs.keySet()) {
            LinearLayout ll_calcul = new LinearLayout(this);
            layout.addView(ll_calcul);
            ll_calcul.setOrientation(LinearLayout.HORIZONTAL);
            ll_calcul.setGravity(Gravity.CENTER);
            ll_calcul.removeAllViewsInLayout();

            TextView tv_calcul = new TextView(this);
            tv_calcul.setText(c_k.toString());
            ll_calcul.addView(tv_calcul);

            View v_calcul = this.lhm_calculs.get(c_k);
            try { ((ViewGroup) v_calcul.getParent()).removeView(v_calcul); } catch (Exception ignored) {}
            ll_calcul.addView(v_calcul);
        }
    }

    public void creerTable() throws InstantiationException, IllegalAccessException {

        for (int i = minIter; i <= maxIter; i++) {
            int nbr1 = new Random().nextInt(10 + 1);
            int nbr2 = new Random().nextInt(10 + 1);

            Calcul c_calcul = this.c_calculClass.newInstance();
            c_calcul.setOperande1(nbr1);
            c_calcul.setOperande2(nbr2);
            EditText et_calcul = new EditText(this);
            this.lhm_calculs.put(c_calcul, et_calcul);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void validerAnswers(View view) {

        int erreurs = 0;

        for (Calcul c_k : this.lhm_calculs.keySet()) {

            View v_answer = lhm_calculs.get(c_k);

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
            if (Integer.parseInt(et_answer.getText().toString()) == c_k.getResultAsInt()) {

                TextView t = new TextView(this);
                t.setText(et_answer.getText());
                t.setTextColor(Color.parseColor("#28E515"));
                lhm_calculs.replace(c_k, t);

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

    public abstract void setCalculClass();
}
