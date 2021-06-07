package com.example.loustics;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.loustics.db.AppDatabase;
import com.example.loustics.db.DatabaseClient;
import com.example.loustics.models.Addition;
import com.example.loustics.models.Calculation;
import com.example.loustics.models.CheckMCQ;
import com.example.loustics.models.Image;
import com.example.loustics.models.Litteral;
import com.example.loustics.models.Multiplication;
import com.example.loustics.models.Open;
import com.example.loustics.models.Question;
import com.example.loustics.models.QuestionFrame;
import com.example.loustics.models.RadioMCQ;
import com.example.loustics.models.Soustraction;
import com.example.loustics.models.YesNo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ChaptersActivity extends AppCompatActivity {

    public static final String CHAPTER = "chapterName";
    private String m_s_chapterName;
    public static final String COURSE = "courseName";
    private String m_s_courseName;
    public static final String LASTNAME = "lastName";
    private String m_s_lastName;
    public static final String FIRSTNAME = "firstName";
    private String m_s_firstName;
    private AppDatabase db;
    private Class<? extends QuestionFrame> m_qf_questionType;
    private static final int s_i_nombreQuestions = 15;
    private List<Question> m_l_questions;
    private List<QuestionFrame> m_l_questionsFrames;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chapter);

        getIntentValues();
        setDAOs();
        defineQuestionFrameType();
        setNavigationBarColors();
        setHeader();
        m_l_questionsFrames = new ArrayList<>();

        // lance la récupération de toutes les questions en lien avec ce chapitre
        new QuestionsAsyncTask().execute();
    }

    public void defineQuestionFrameType() {
        int rand = (int) (Math.random() * (4));   // 0 -> 3

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
        m_s_courseName = getIntent().getStringExtra(COURSE);
        m_s_chapterName = getIntent().getStringExtra(CHAPTER);
        m_s_firstName = getIntent().getStringExtra(FIRSTNAME);
        m_s_lastName = getIntent().getStringExtra(LASTNAME);
    }

    public void onButtonClick(View view) {
        int errors = 0;
        for(QuestionFrame qf : m_l_questionsFrames) {
            if (!qf.isRight())
                errors++;
        }

        Intent i = new Intent(this, ResultActivity.class);
        i.putExtra(ResultActivity.COURSE, m_s_courseName);
        i.putExtra(ResultActivity.CHAPTER, m_s_chapterName);
        i.putExtra(ResultActivity.FIRSTNAME, m_s_firstName);
        i.putExtra(ResultActivity.LASTNAME, m_s_lastName);
        i.putExtra(ResultActivity.ERRORS, errors);
        i.putExtra(ResultActivity.NUMBEROFQUESTIONS, m_l_questionsFrames.size());

        startActivity(i);
    }

    public void setDAOs() {
        db = DatabaseClient.getInstance(getApplicationContext()).getAppDatabase();
    }

    // Nom de la matière dans l'en-tête
    public void setHeader() {

        // on applique le texte au TextView afin qu'il soit affiché
        TextView t = (TextView) findViewById(R.id.tv_chapter);
        t.setText(m_s_chapterName);

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
                m_l_questionsFrames.add(questionFrame);

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

            // LitteralDAO
            List<Litteral> l_litteral = db.litteralDAO().getAllQuestions(m_s_chapterName, m_s_courseName);
            if (l_litteral.size() > 0)
                questions.addAll(l_litteral);

            // ImageDAO
            List<Image> l_image = db.imageDAO().getAllQuestions(m_s_chapterName, m_s_courseName);
            if (l_image.size() > 0)
                questions.addAll(l_image);

            // CalculationDAO
            List<Addition> addition = db.calculationDAO().getAddition(m_s_chapterName, m_s_courseName);
            if (addition.size() > 0) {
                for(int i = 0 ; i < s_i_nombreQuestions ; i++) {
                    addition = db.calculationDAO().getAddition(m_s_chapterName, m_s_courseName);
                    questions.add(addition.get(0));
                }
            }
            List<Soustraction> soustraction = db.calculationDAO().getSoustraction(m_s_chapterName, m_s_courseName);
            if (soustraction.size() > 0) {
                for(int i = 0 ; i < s_i_nombreQuestions ; i++) {
                    soustraction = db.calculationDAO().getSoustraction(m_s_chapterName, m_s_courseName);
                    questions.add(soustraction.get(0));
                }
            }
            List<Multiplication> multiplications = db.calculationDAO().getMultiplication(m_s_chapterName, m_s_courseName);
            if (multiplications.size() > 0) {
                for(int i = 0 ; i < s_i_nombreQuestions ; i++) {
                    multiplications = db.calculationDAO().getMultiplication(m_s_chapterName, m_s_courseName);
                    questions.add(multiplications.get(0));
                }
            }


            return questions;
        }

        @Override
        protected void onPostExecute(List<Question> questions) {
            // Sélectionne le nombre de questions max
            Collections.shuffle(questions);
            int maxQuestions = questions.size() > s_i_nombreQuestions ? s_i_nombreQuestions-1 : questions.size();
            questions = questions.subList(0, maxQuestions);

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
