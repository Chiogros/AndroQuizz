package com.example.loustics;


import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import com.example.loustics.db.DatabaseClient;
import com.example.loustics.db.User;

public class ListUserActivity extends AppCompatActivity {

    //
    private static final int REQUEST_CODE_ADD = 0;

    // DATA
    private DatabaseClient mDb;

    // VIEW
    private Button buttonAdd;
    private ListView listUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_user);

        // Récupération du DatabaseClient
        mDb = DatabaseClient.getInstance(getApplicationContext());

        // Récupérer les vues
        listUser = findViewById(R.id.listUser);
        buttonAdd = findViewById(R.id.button_add);
    }

    listUser.setOnItemClickListener(new OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position,
        long id) {
            Intent intent = new Intent(context, SendMessage.class);
            String message = "abc";
            intent.putExtra(EXTRA_MESSAGE, message);
            startActivity(intent);
        }
    });

}
