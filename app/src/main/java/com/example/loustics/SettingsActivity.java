package com.example.loustics;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;

import com.example.loustics.db.AppDatabase;
import com.example.loustics.db.DatabaseClient;
import com.example.loustics.models.User;

import java.util.Objects;

public class SettingsActivity extends AppCompatActivity {

    public static final String FIRSTNAME = "firstName";
    private static String m_s_firstName;
    public static final String LASTNAME = "lastName";
    private static String m_s_lastName;
    private static AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        setBackButton();
        setLayoutXML();
        setNavigationBarColors();
        getIntentValues();
        setDAOs();
    }

    private void getIntentValues() {
        // Nom et prénom de l'utilisateur
        m_s_lastName = getIntent().getStringExtra(LASTNAME);
        m_s_firstName = getIntent().getStringExtra(FIRSTNAME);
    }

    // Activé lors du clic sur la flèche de retour de la ActionBar
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //if (item.getItemId() == R.id.home) {    // R.id.home existe déjà, interne à Android
        this.finish();
        /*}
        Log.v("id", String.valueOf(item.getItemId()));
        return super.onOptionsItemSelected(item);*/
        return true;
    }

    // Activer la flèche de retour dans la ActionBar
    private void setBackButton() {
        Objects.requireNonNull(getSupportActionBar()).setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void setDAOs() {
        db = DatabaseClient.getInstance(getApplicationContext()).getAppDatabase();
    }

    private void setLayoutXML() {
        // insère le XML dans le layout
        getSupportFragmentManager().beginTransaction().replace(R.id.fl_settings, new SettingsFragment()).commit();
    }

    // afficher la navigationBar en blanc avec les boutons noirs
    private void setNavigationBarColors() {
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR);
        }
    }


    // Classes privéess

    // Il faut associer le settings_fragment.xml qui sert de layout au FrameLayout qui est dans activity_settings.xml
    public static class SettingsFragment extends PreferenceFragmentCompat {
        @Override
        public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
            // Liaison entre le FrameLayout et le XML
            setPreferencesFromResource(R.xml.settings_fragment, rootKey);

            // Changer la photo
            Preference changePhotoButton = getPreferenceManager().findPreference("changePhotoButton");
            if (changePhotoButton != null) {
                changePhotoButton.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
                    @Override
                    public boolean onPreferenceClick(Preference preference) {
                        Toast.makeText(getContext(), "Oops, cette fonctionnalité n'a pas été implémentée.", Toast.LENGTH_LONG).show();
                        return true;
                    }
                });
            }

            // Se déconnecter
            Preference deconnexionButton = getPreferenceManager().findPreference("deconnexionButton");
            if (deconnexionButton != null) {
                deconnexionButton.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
                    // quand on clique sur "Se déconnecter", on est envoyé sur la page de login
                    @Override
                    public boolean onPreferenceClick(Preference preference) {
                        Intent i = new Intent(getContext(), LoginActivity.class);
                        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(i);
                        return true;
                    }
                });
            }

            // Supprimer le compte
            Preference deleteButton = getPreferenceManager().findPreference("deleteButton");
            if (deleteButton != null) {
                deleteButton.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
                    // quand on clique sur "Supprimé le compte", on est envoyé sur la page de login et on suppprime le compte
                    @Override
                    public boolean onPreferenceClick(Preference preference) {
                        // supprime l'utilisateur
                        new SelectUserForDeletionAsyncTask().execute(m_s_firstName, m_s_lastName);

                        return true;
                    }

                    // classes pour supprimer le user
                    class SelectUserForDeletionAsyncTask extends android.os.AsyncTask<String, Void, User> {

                        @Override
                        // Récupère toutes les questions de toutes les tables issues de Question qui sont en liées au chapitre et au cours
                        protected User doInBackground(String... strings) {
                            return db.userDAO().getUser(strings[0], strings[1]).get(0);
                        }
                        @Override
                        protected void onPostExecute(User user) {
                            new DeleteUsersAsyncTask().execute(user);
                        }
                    }

                    class DeleteUsersAsyncTask extends android.os.AsyncTask<User, Void, String> {

                        @Override
                        // Récupère toutes les questions de toutes les tables issues de Question qui sont en liées au chapitre et au cours
                        protected String doInBackground(User... users) {
                            db.userDAO().delete(users[0]);
                            return "Compte supprimé. À bientôt !";
                        }
                        @Override
                        protected void onPostExecute(String sentence) {

                            // notification de suppression
                            Toast.makeText(getContext(), sentence, Toast.LENGTH_SHORT).show();

                            // renvoie sur la page de login
                            Intent i = new Intent(getContext(), LoginActivity.class);
                            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(i);
                        }
                    }
                });
            }
        }
    }

}