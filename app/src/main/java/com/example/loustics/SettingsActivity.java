package com.example.loustics;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toolbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;

import com.example.loustics.ui.login.LoginActivity;

import java.util.Objects;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        setBackButton();
        setLayoutXML();

        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setNavigationBarColor(getResources().getColor(R.color.defaultWhite));
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR);
        }

    }

    // Activer la flèche de retour dans la ActionBar
    private void setBackButton() {
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    // Activé lors du clic sur la flèche de retour de la ActionBar
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.home) {    // R.id.home existe déjà, interne à Android
            this.finish();
        }
        Log.v("id", String.valueOf(item.getItemId()));
        return super.onOptionsItemSelected(item);
    }


    //
    // Tout ce qui est en dessous est pour l'application du layout
    //

    private void setLayoutXML() {
        // insère le XML dans le layout
        getSupportFragmentManager().beginTransaction().replace(R.id.fl_settings, new SettingsFragment()).commit();
    }

    // Il faut associer le settings_fragment.xml qui sert de layout au FrameLayout qui est dans activity_settings.xml
    public static class SettingsFragment extends PreferenceFragmentCompat {
        @Override
        public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
            // Liaison entre le FrameLayout et le XML
            setPreferencesFromResource(R.xml.settings_fragment, rootKey);

            // Actions sur les éléments du layout
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
        }
    }
}