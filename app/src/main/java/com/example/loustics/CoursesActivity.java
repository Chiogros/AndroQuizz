package com.example.loustics;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.loustics.models.Chapter;

public class CoursesActivity extends AppCompatActivity {

    public static final String COURSE = "";
    private String m_s_CourseName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_courses);

        Chapter c = new Chapter("Additions");
        c = new Chapter("Soustractions");
        c = new Chapter("Multiplications");
        c = new Chapter("Divisions");

        // Nom de la matière
        this.m_s_CourseName = getIntent().getStringExtra(COURSE);

        // Nom de la matière dans l'en-tête
        TextView t = (TextView) findViewById(R.id.tv_course);
        t.setText(this.m_s_CourseName);

        ImageView iv = (ImageView) findViewById(R.id.iv_course);
        iv.setImageResource(R.drawable.ic_math);
    }

    @Override
    protected void onResume() {
        super.onResume();

        TextView t = (TextView) findViewById(R.id.tv_course);
        // t.setBackgroundResource(R.drawable.half_circle);

        // Afficher les chapitres
        setListView();
    }

    public void setListView() {
        // n'affiche rien si la liste est vide
        if (Chapter.getChapters().size() == 0) {
            return ;
        }

        // Définition des lignes pour le ListView + l'action du click sur chaque ligne qui renvoit sur une nouvelle activité
        ListView lv_items = findViewById(R.id.lv_items);
        lv_items.setAdapter(new ArrayAdapter<Chapter>(getApplicationContext(), R.id.lv_items, Chapter.getChapters()) {
            /*
            méthode utilisée à la génération de la ListView, renvoie une View qui est affichée dans la liste
             */
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                LinearLayout ll_line = new LinearLayout(getContext());
                ll_line.setPadding(0, 30, 0, 30);
                ll_line.setOrientation(LinearLayout.HORIZONTAL);
                ll_line.setGravity(Gravity.CENTER_VERTICAL);

                // Le dernier enfant doit forcément être le nom de la matière
                // Nom de la matière
                TextView tv_chapterName = new TextView(getContext());
                tv_chapterName.setText(this.getItem(position).getName());
                tv_chapterName.setTextSize(25);
                ll_line.addView(tv_chapterName);
                
                // Au clic sur le chapitre
                ll_line.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                    }
                });

                return ll_line;
            }
        });
    }

}
