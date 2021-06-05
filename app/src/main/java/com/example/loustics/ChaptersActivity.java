package com.example.loustics;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.example.loustics.db.AppDatabase;
import com.example.loustics.db.DatabaseClient;
import com.example.loustics.models.Addition;
import com.example.loustics.models.CheckMCQ;
import com.example.loustics.models.Litteral;
import com.example.loustics.models.Open;
import com.example.loustics.models.Question;
import com.example.loustics.models.QuestionFrame;
import com.example.loustics.models.RadioMCQ;
import com.example.loustics.models.YesNo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ChaptersActivity extends AppCompatActivity {

    private String m_s_courseName;
    private String m_s_chapterName;
    AppDatabase db;
    private Class<? extends QuestionFrame> m_qf_questionType;
    private static final int s_i_nombreQuestions = 15;
    private List<Question> m_l_questions;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chapter);

        getIntentValues();
        setDAOs();
        defineQuestionFrameType();
        setNavigationBarColors();

        // lance la récupération de toutes les questions en lien avec ce chapitre
        new QuestionsAsyncTask().execute();
    }

    public void defineQuestionFrameType() {
        // int rand = (int) (Math.random() * (4));   // 0 -> 3
        int rand = (int) (Math.random() * (4 - 1));   // 0 -> 2

        switch (rand) {
            case 0:
                m_qf_questionType = YesNo.class;
                break;
            case 1:
                m_qf_questionType = Open.class;
                break;
            case 2:
                m_qf_questionType = RadioMCQ.class;
                break;
            case 3:
                m_qf_questionType = CheckMCQ.class;
                break;
            default:    // should never be chosen
                m_qf_questionType = YesNo.class;
                break;
        }
    }

    public void getIntentValues() {
        m_s_courseName = getIntent().getStringExtra("COURSE");
        m_s_chapterName = getIntent().getStringExtra("CHAPTER");
    }

    public void onButtonClick(View view) {
        // TODO
        /* Récupérer le linearlayout dans lequel il y a toutes les QuestionFrame
         * et appeler sur chacune la méthode isRight() en comptant le nombre de fois où ça renvoie false (aka la réponse entrée est mauvaise)
         * Enfin, aller sur l'activité résultat pour afficher le nombre d'erreurs / ou Félicitations
         */
    }

    public void setDAOs() {
        db = DatabaseClient.getInstance(getApplicationContext()).getAppDatabase();
    }

    public void setListView() {
        if (m_l_questions.size() == 0)
            return ;

        // Définition des lignes pour le ListView
        LinearLayout ll_items = findViewById(R.id.ll_items);
        for (Question question : m_l_questions) {
            try {
                // Nouvelle question du type choisis aléatoirement
                QuestionFrame questionFrame = m_qf_questionType.newInstance();
                questionFrame.setContext(getApplicationContext());
                questionFrame.setQuestion(question);  // assigne une réponse aléatoire

                View v = questionFrame.getView();
                // mettre de l'espacement entre les questions
                v.setPadding(100, 50, 100, 50);
                v.setBackgroundResource(R.drawable.card_background);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    v.setElevation(1);
                }

                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                params.setMargins(20,5, 20, 5);

                ll_items.addView(v, params);

                // les catch sont obligatoires pour le newInstance()
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            }
        }
    }

    // afficher la navigationBar en blanc avec les boutons noirs
    public void setNavigationBarColors() {
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR);
        }
    }


    // Classes privées

    private class QuestionsAsyncTask extends android.os.AsyncTask<String, Void, List<Question>> {

        @Override
        // Récupère toutes les questions de toutes les tables issues de Question qui sont en liées au chapitre et au cours
        protected List<Question> doInBackground(String... strings) {
            List<Question> questions = new ArrayList<>();

            // faire ça pour tous les DAO qui contiennent des Questions, récupère toutes les questions en lien avec ce chapitre + cours
            List<Litteral> l = db.litteralDAO().getAllQuestions(m_s_chapterName, m_s_courseName);
            if (l.size() > 0)
                questions.addAll(l);

            // pour les calculs
            for(int i = 0 ; i < s_i_nombreQuestions ; i++) {
                List<Addition> calc = db.calculationDAO().getAddition(m_s_chapterName, m_s_courseName);
                if (calc.size() > 0)
                    questions.add(calc.get(0));
            }

            return questions;
        }

        @Override
        protected void onPostExecute(List<Question> questions) {
            RandomQuestion.questionsFromAllTypes = questions;
            // lance l'affichage des questions en demandant s_i_nombreQuestions à afficher
            m_l_questions = RandomQuestion.getRandomQuestions(s_i_nombreQuestions);

            setListView();
        }
    }

    private static class RandomQuestion {
        public static List<Question> questionsFromAllTypes;

        public static List<Question> getRandomQuestions(int numberOfQuestions) {
            // renvoie le tableau contenant toutes les questions si on demande >= de questions que ce que l'on a
            if (numberOfQuestions >= questionsFromAllTypes.size()) {
                Collections.shuffle(questionsFromAllTypes);
                return questionsFromAllTypes;
            }

            // sinon récupère aléatoirement des questions parmi celles appartenant à ce chapitre
            ArrayList<Question> questions = new ArrayList<>();
            int length = questionsFromAllTypes.size();
            if (length == 0)
                return questions;

            while (questions.size() < numberOfQuestions) {
                int randomValue = (int) (Math.random() * length);
                questions.add(questionsFromAllTypes.get(randomValue));
            }
            return questions;
        }
    }

}
