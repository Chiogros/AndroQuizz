package com.example.loustics;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.ParcelFileDescriptor;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.loustics.db.AppDatabase;
import com.example.loustics.db.DatabaseClient;
import com.example.loustics.models.User;

import java.io.FileDescriptor;
import java.io.FileNotFoundException;

public class AddUserActivity extends AppCompatActivity {

    private AppDatabase db;
    private EditText et_firstName;
    private EditText et_lastName;
    private Button b;
    private String m_photo = "";
    private final int RESULT_LOAD_IMG = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);

        setAttributes();
        setDAOs();
        setNavigationBarColors();
        setListeners();
    }

    private Bitmap getThumbnail(Uri uri, int size) throws FileNotFoundException {
        // ouvre l'image depuis les fichiers
        ParcelFileDescriptor parcelFD = getContentResolver().openFileDescriptor(uri, "r");
        FileDescriptor imageStream = parcelFD.getFileDescriptor();

        // décoder juste les dimensions
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFileDescriptor(imageStream, null, options);

        // calculer le ratio de rétrécissement par rapport à la taille max de l'image
        int biggestSize = Math.max(options.outHeight, options.outWidth);
        options.inSampleSize = biggestSize / size;

        // cette fois-ci on lit l'image entière
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeFileDescriptor(imageStream, null, options);
    }

    @Override
    protected void onActivityResult(int reqCode, int resultCode, Intent data) {
        super.onActivityResult(reqCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            try {
                // récupère le chemin vers l'image
                final Uri imageUri = data.getData();
                m_photo = data.getDataString();

                // récupère l'image
                Bitmap ImageThumbnail = getThumbnail(imageUri, 300);

                // affiche l'image
                ImageView iv_photo = findViewById(R.id.iv_photopicker);
                iv_photo.setImageBitmap(ImageThumbnail);
            } catch (FileNotFoundException | NullPointerException e) {
                Toast.makeText(getApplicationContext(), "Oops, une erreur s'est glissée ici...", Toast.LENGTH_SHORT).show();
            }
        }
    }


    public void onAddPhoto(View view) {
        Intent photoPicker = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        photoPicker.setType("image/*");
        startActivityForResult(photoPicker, RESULT_LOAD_IMG);
    }

    public void onValidateButton(View view) {
        User newUser = new User(et_firstName.getText().toString(),
                                et_lastName.getText().toString(),
                                m_photo
        );

        new UserAddAsyncTask().execute(newUser);
    }

    private void setAttributes() {
        et_firstName = findViewById(R.id.et_firstName);
        et_lastName = findViewById(R.id.et_lastName);
        b = findViewById(R.id.b_add_user);
    }

    private void setDAOs() {
        db = DatabaseClient.getInstance(getApplicationContext()).getAppDatabase();
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

            // vérifie que le prénom et nom soient dispo et qu'il y ait plus de 3 caractères entrés
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

            // vérifie que le prénom et nom soient dispo et qu'il y ait plus de 3 caractères entrés
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

    // afficher la navigationBar en blanc avec les boutons noirs
    private void setNavigationBarColors() {
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR | View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR);
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
            b.setEnabled(available);
        }
    }

}
