package com.example.loustics;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.PorterDuff;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.BackgroundColorSpan;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Space;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.example.loustics.db.AppDatabase;
import com.example.loustics.db.DatabaseClient;
import com.example.loustics.models.User;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;


public class LoginActivity extends AppCompatActivity {

    private List<User> m_l_users;
    private AppDatabase db;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        setDAOs();
        setBarsThemes();
        setFloatingButton();
        setTextColors();

        // lance la récupération de toutes les questions en lien avec ce chapitre
        new UsersAsyncTask().execute();
    }

    private void setTextColors() {
        // surligne le texte
        SpannableString str = new SpannableString(" Bienvenue. ");
        // marron
        str.setSpan(new BackgroundColorSpan(0xFFc89462), 0, str.length(), 0);
        str.setSpan(new ForegroundColorSpan(0xFFFFFFFF), 0, str.length(), 0);

        TextView t = findViewById(R.id.tv_message);
        t.setText(str);
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
                // pour faire le margin entre les LinearLayout
                LinearLayout ll = new LinearLayout(getContext());
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                params.setMargins(0,0, 0, 10);

                // La ligne qui accueille les éléments
                LinearLayout ll_line = new LinearLayout(getContext());
                ll.addView(ll_line, params);
                ll_line.setPadding(50, 50, 100, 50);
                ll_line.setOrientation(LinearLayout.HORIZONTAL);
                ll_line.setGravity(Gravity.CENTER_VERTICAL);

                // met le fond en blanc + sur-élève
                ll_line.setBackgroundResource(R.drawable.card_background);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    ll_line.setElevation(2);
                }

                // Image de l'user
                ImageView iv_photo = new ImageView(getContext());
                try {
                    // parse et récupère l'image dans la gallerie
                    final Uri imageUri = Uri.parse(this.getItem(position).getM_photo());
                    final InputStream imageStream = getContentResolver().openInputStream(imageUri);
                    final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
                    iv_photo.setImageBitmap(Bitmap.createScaledBitmap(selectedImage,
                            (int) getResources().getDisplayMetrics().density * 100,
                            (int) getResources().getDisplayMetrics().density * 100, false));
                } catch (FileNotFoundException | SecurityException e) {
                    // si l'image n'a pas été trouvée, on en met une par défaut
                    iv_photo.setImageResource(getResources().getIdentifier(
                        // à partir du nom du logo
                        "ic_unknown_person", "drawable", getPackageName()));
                    // on met ce logo en marron
                    iv_photo.setColorFilter(getResources().getColor(R.color.brown), PorterDuff.Mode.SRC_IN);
                }

                CardView cv_photo = new CardView(getContext());
                // on met arrondi la cardview pour avoir un cercle
                cv_photo.setRadius(getResources().getDisplayMetrics().density * 38);
                cv_photo.addView(iv_photo);
                ll_line.addView(cv_photo);

                Space space = new Space(getContext());
                space.setLayoutParams(new LinearLayout.LayoutParams(50, 1));
                ll_line.addView(space);

                LinearLayout ll_names = new LinearLayout(getContext());
                ll_names.setOrientation(LinearLayout.VERTICAL);
                ll_line.addView(ll_names);

                TextView tv_firstName = new TextView(getContext());
                tv_firstName.setText(this.getItem(position).getM_s_firstName());
                tv_firstName.setTextSize(20);
                ll_names.addView(tv_firstName);

                TextView tv_lastName = new TextView(getContext());
                tv_lastName.setText(this.getItem(position).getM_s_lastName());
                tv_lastName.setTextSize(16);
                ll_names.addView(tv_lastName);

                // Au clic sur l'utilisateur'
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
                        i.putExtra(HomeActivity.FIRSTNAME, tv_firstName.getText().toString());

                        // on empêche de revenir à la page de login par le bouton retour
                        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                        startActivity(i);
                        finish();
                    }
                });

                return ll;
            }
        });
    }

    private void nothingThere() {

    }

    // afficher la navigationBar en blanc avec les boutons noirs
    public void setBarsThemes() {
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR | View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR);
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