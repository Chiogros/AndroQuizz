
package com.example.loustics.db;


import android.content.Context;

import java.lang.reflect.Array;
import java.util.ArrayList;
import androidx.annotation.NonNull;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.concurrent.Executors;
import com.example.loustics.models.Chapter;

public class DatabaseClient {

    // Instance unique permettant de faire le lien avec la base de données
    private static DatabaseClient instance;

    // Objet représentant la base de données de votre application
    private AppDatabase appDatabase;

    // Constructeur
    private DatabaseClient(final Context context) {

        // Créer l'objet représentant la base de données de votre application
        // à l'aide du "Room database builder"
        appDatabase = Room.databaseBuilder(context, AppDatabase.class, "Loustics")
                .addCallback(roomDatabaseCallback)
                .build();
    }

    // Méthode statique
    // Retourne l'instance de l'objet DatabaseClient
    public static synchronized DatabaseClient getInstance(Context context) {
        if (instance == null) {
            instance = new DatabaseClient(context);
        }
        return instance;
    }

    // Retourne l'objet représentant la base de données de votre application
    public AppDatabase getAppDatabase() {
        return appDatabase;
    }

    // Objet permettant de populate (remplir) la base de données à sa création
    RoomDatabase.Callback roomDatabaseCallback = new RoomDatabase.Callback() {

        // Called when the database is created for the first time.
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);

            //Table Course (Nom des cours + logo)
            {
                db.execSQL("INSERT INTO Course VALUES('Français', 'ic_book');");
                db.execSQL("INSERT INTO Course VALUES('Géographie', 'ic_map');");
                db.execSQL("INSERT INTO Course VALUES('Histoire', 'ic_history');");
                db.execSQL("INSERT INTO Course VALUES('Mathématiques', 'ic_math');");
                db.execSQL("INSERT INTO Course VALUES('Physique', 'ic_fire');");
            }

            //Table Chapters (Noms des chapitres, sous cours)

                // Français
                {
                db.execSQL("INSERT INTO Chapter VALUES('Grammaire - Pronoms', 'Français');");
                db.execSQL("INSERT INTO Chapter VALUES('Grammaire - Adverbes', 'Français');");
                db.execSQL("INSERT INTO Chapter VALUES('Conjugaison - Présent', 'Français');");
                db.execSQL("INSERT INTO Chapter VALUES('Conjugaison - Futur', 'Français');");
                db.execSQL("INSERT INTO Chapter VALUES('Conjugaison - Passé simple', 'Français');");
                }
                // Géographie
                {
                    db.execSQL("INSERT INTO Chapter VALUES('Les départements', 'Géographie');");
                    db.execSQL("INSERT INTO Chapter VALUES('Les régions', 'Géographie');");
                    db.execSQL("INSERT INTO Chapter VALUES('Les villes', 'Géographie');");
                    db.execSQL("INSERT INTO Chapter VALUES(\"L'Europe\", 'Géographie');");
                    db.execSQL("INSERT INTO Chapter VALUES('Le monde', 'Géographie');");
                }
                // Mathématiques
                {
                    db.execSQL("INSERT INTO Chapter VALUES('Additions 1', 'Mathématiques');");
                    db.execSQL("INSERT INTO Chapter VALUES('Additions 2', 'Mathématiques');");
                    db.execSQL("INSERT INTO Chapter VALUES('Additions 3', 'Mathématiques');");
                    db.execSQL("INSERT INTO Chapter VALUES('Additions 4', 'Mathématiques');");
                    db.execSQL("INSERT INTO Chapter VALUES('Additions 5', 'Mathématiques');");
                }

            //Table Litteral (question et réponses possibles)
            {
               db.execSQL("INSERT INTO Litteral VALUES(\"L'assembleur\", 'Grammaire - Pronoms', 'Français', \"{'right' : ['permet de manipuler la mémoire', 'est un langage de programmation', 'est la base de nimporte quel langage informatique'], 'wrong' : ['permet de commander Uber', 'a été inventé par JP', 'offre des réductions dès 3 octets achetés', 'sent bon la menthe']}\");");
            }


            // Table Calculation
            {
                db.execSQL("INSERT INTO Calculation VALUES('Additions 1', 'Mathématiques', 1);");
                db.execSQL("INSERT INTO Calculation VALUES('Additions 2', 'Mathématiques', 2);");
                db.execSQL("INSERT INTO Calculation VALUES('Additions 3', 'Mathématiques', 3);");
                db.execSQL("INSERT INTO Calculation VALUES('Additions 4', 'Mathématiques', 4);");
                db.execSQL("INSERT INTO Calculation VALUES('Additions 5', 'Mathématiques', 5);");
            }
        }
    };
}
