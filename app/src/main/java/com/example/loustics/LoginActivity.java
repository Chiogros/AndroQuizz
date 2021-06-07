package com.example.loustics;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Space;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.loustics.db.AppDatabase;
import com.example.loustics.db.DatabaseClient;
import com.example.loustics.models.User;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;


public class LoginActivity extends AppCompatActivity {

    private List<User> m_l_users;
    private AppDatabase db;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        setDAOs();
        setNavigationBarColors();
        setFloatingButton();

        // lance la récupération de toutes les questions en lien avec ce chapitre
        m_l_users = new ArrayList<>();
        new UsersAsyncTask().execute();
    }

    // au retour de l'activité AddUser, si on a ajouté un utilisateur on actualise la liste, sinon rien
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // == 1 car on a donné 1 au startActivityForResult()
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                new UsersAsyncTask().execute();
            }
        }
    }

    public void setDAOs() {
        db = DatabaseClient.getInstance(getApplicationContext()).getAppDatabase();
    }

    public void setFloatingButton() {
        FloatingActionButton fab = findViewById(R.id.fab_add_user);
        fab.setImageResource(R.drawable.ic_user_add);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), AddUserActivity.class);
                startActivityForResult(i, 1);
            }
        });
    }

    public void setListView() {

        if (m_l_users.size() == 0)
            nothingThere();

        ListView user_list = findViewById(R.id.user_list);
        user_list.setAdapter(new ArrayAdapter<User>(getApplicationContext(), R.id.lv_items, m_l_users) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                LinearLayout ll_line = new LinearLayout(getContext());
                ll_line.setPadding(100, 50, 100, 50);
                ll_line.setOrientation(LinearLayout.HORIZONTAL);
                ll_line.setGravity(Gravity.CENTER_VERTICAL);

                // Image de l'user
                ImageView iv_logo = new ImageView(getContext());
                // TODO : afficher l'image de l'user

                Space space = new Space(getContext());
                space.setLayoutParams(new LinearLayout.LayoutParams(25, 1));
                ll_line.addView(space);

                LinearLayout ll_names = new LinearLayout(getContext());
                ll_names.setOrientation(LinearLayout.VERTICAL);
                ll_line.addView(ll_names);

                TextView tv_firstName = new TextView(getContext());
                tv_firstName.setText(this.getItem(position).getM_s_firstName());
                ll_names.addView(tv_firstName);

                TextView tv_lastName = new TextView(getContext());
                tv_lastName.setText(this.getItem(position).getM_s_lastName());
                ll_names.addView(tv_lastName);

                // Au clic sur la matière
                ll_line.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        // on récupère le LinearLayout qui contient les noms
                        LinearLayout ll_line = (LinearLayout) view;
                        LinearLayout ll_names = (LinearLayout) ll_line.getChildAt(
                        ll_line.getChildCount()-1);

                        // Récupère les deux TextView qui contiennent les noms et prénoms
                        TextView tv_lastName = (TextView) ll_names.getChildAt(ll_names.getChildCount()-1);
                        TextView tv_firstName = (TextView) ll_names.getChildAt(ll_names.getChildCount()-2);


                        Intent i = new Intent(getContext(), HomeActivity.class);

                        // on passe le nom et prénom de l'utilisateur à la nouvelle activité
                        i.putExtra(HomeActivity.LASTNAME, tv_lastName.getText().toString());
                        i.putExtra(HomeActivity.FIRSTNAME, tv_lastName.getText().toString());

                        // on empêche de revenir à la page de login par le bouton retour
                        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                        startActivity(i);
                    }
                });

                return ll_line;
            }
        });
    }

    private void nothingThere() {

    }

    // afficher la navigationBar en blanc avec les boutons noirs
    public void setNavigationBarColors() {
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR);
        }
    }


    // Classes privées

    private class UsersAsyncTask extends android.os.AsyncTask<Void, Void, List<User>> {

        @Override
        // Récupère toutes les questions de toutes les tables issues de Question qui sont en liées au chapitre et au cours
        protected List<User> doInBackground(Void... voids) {
            return db.userDAO().getAllUsers();
        }

        @Override
        protected void onPostExecute(List<User> users) {
            m_l_users = users;
            setListView();
        }
    }

}