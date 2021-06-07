package com.example.loustics;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.loustics.db.AppDatabase;
import com.example.loustics.db.DatabaseClient;
import com.example.loustics.models.User;

public class AddUserActivity extends AppCompatActivity {

    private AppDatabase db;
    private EditText et_firstName;
    private EditText et_lastName;
    private Button b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);

        setAttributes();
        setDAOs();
        setNavigationBarColors();
        setListeners();
    }

    private void setAttributes() {
        et_firstName = findViewById(R.id.et_firstName);
        et_lastName = findViewById(R.id.et_lastName);
        b = findViewById(R.id.b_add_user);
    }

    public void onClickButton(View view) {
        User newUser = new User(et_firstName.getText().toString(),
                                et_lastName.getText().toString(),
                        "photo"
        );

        new UserAddAsyncTask().execute(newUser);
    }

    private void setListeners() {

        // prénom entré change, vérifie si disponible
        et_firstName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (et_firstName.getText().length() >= 3 && et_lastName.getText().length() >= 3) {
                    new UserAvailableAsyncTask().execute(et_firstName.getText().toString(), et_lastName.getText().toString());
                } else if (b.isEnabled()){
                    b.setEnabled(false);
                }
            }
        });

        // nom entré change, vérifie si disponible
        et_lastName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (et_firstName.getText().length() >= 3 && et_lastName.getText().length() >= 3) {
                    new UserAvailableAsyncTask().execute(et_firstName.getText().toString(), et_lastName.getText().toString());
                } else if (b.isEnabled()){
                    b.setEnabled(false);
                }
            }
        });

    }

    public void setDAOs() {
        db = DatabaseClient.getInstance(getApplicationContext()).getAppDatabase();
    }

    // afficher la navigationBar en blanc avec les boutons noirs
    public void setNavigationBarColors() {
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR);
        }
    }


    // Classes privées

    private class UserAddAsyncTask extends AsyncTask<User, Void, String> {

        @Override
        // Récupère tous les utilisateurs
        protected String doInBackground(User... users) {
            db.userDAO().insert(users[0]);
            return new String("user properly added");
        }

        @Override
        protected void onPostExecute(String sentence) {
            // on retourne la phrase confirmant qu'un utilisateur a été ajouté
            Intent returnIntent = new Intent();
            returnIntent.putExtra("result", sentence);
            setResult(LoginActivity.RESULT_OK, returnIntent);
            finish();
        }
    }

    private class UserAvailableAsyncTask extends AsyncTask<String, Void, Boolean> {

        @Override
        // Check si un utilisateur avec ce nom et prénom existe déjà
        protected Boolean doInBackground(String... strings) {
            return db.userDAO().getUser(strings[0], strings[1]).size() == 0;
        }

        @Override
        protected void onPostExecute(Boolean available) {

            if (available) {
                b.setEnabled(true);
            } else {
                b.setEnabled(false);
            }
        }
    }

}
