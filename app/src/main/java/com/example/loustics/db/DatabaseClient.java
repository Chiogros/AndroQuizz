
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
            /*{ArrayList<Chapter> chap = new ArrayList<Chapter>();
            Chapter revo = new Chapter("La révolution", "Histoire");
            Chapter guerre1 = new Chapter("La première guerre Mondiale", "Histoire");
            chap.add(revo);
            chap.add(guerre1);
            db.execSQL("INSERT INTO Course VALUES(\"Histoire\", 70004);");
            chap.clear();
            Chapter villeF = new Chapter("Les villes de France", "Géographie");
            Chapter villeE = new Chapter("Les villes d'Europe", "Géographie");
            chap.add(villeF);
            chap.add(villeE);
            db.execSQL("INSERT INTO Course (chaptersList, logo, name) VALUES(chap, 70004,\"Géographie\");");
            chap.clear();
            Chapter gramm = new Chapter("Grammaire", "Français");
            Chapter conjug = new Chapter("Conjugaison", "Français");
            chap.add(gramm);
            chap.add(conjug);
            db.execSQL("INSERT INTO Course (chaptersList, logo, name) VALUES(chap, 700038,\"Français\");");
            chap.clear();
            Chapter cycle = new Chapter("Le cycle de l'eau", "Sciences");
            Chapter foret = new Chapter("La fôret", "Sciences");
            chap.add(cycle);
            chap.add(foret);
            db.execSQL("INSERT INTO Course (chaptersList, logo, name) VALUES(chap, 70004,\"Sciences\");");
            chap.clear();}*/
            db.execSQL("INSERT INTO Course VALUES('Français', 'ic_french');");
            db.execSQL("INSERT INTO Course VALUES('Géographie', 'ic_earth');");
            db.execSQL("INSERT INTO Course VALUES('Histoire', 'ic_history');");
            db.execSQL("INSERT INTO Course VALUES('Mathématiques', 'ic_math');");
            db.execSQL("INSERT INTO Course VALUES('Physique', 'ic_fire');");

            //Table Chapters (Noms des chapitres, sous cours)
            /*{db.execSQL("INSERT INTO Chapter (chapterName) VALUES(\"Addition\");");
            db.execSQL("INSERT INTO Chapter (chapterName) VALUES(\"Multiplication\");");
            db.execSQL("INSERT INTO Chapter (chapterName) VALUES(\"Division\");");
            db.execSQL("INSERT INTO Chapter (chapterName) VALUES(\"Soustraction\");");
            db.execSQL("INSERT INTO Chapter (chapterName) VALUES(\"Modulo\");");
            db.execSQL("INSERT INTO Chapter (chapterName) VALUES(\"Grammaire\");");
            db.execSQL("INSERT INTO Chapter (chapterName) VALUES(\"Conjugaison\");");
            db.execSQL("INSERT INTO Chapter (chapterName) VALUES(\"La révolution\");");
            db.execSQL("INSERT INTO Chapter (chapterName) VALUES(\"La première guerre Mondiale\");");
            db.execSQL("INSERT INTO Chapter (chapterName) VALUES(\"Les villes de France\");");
            db.execSQL("INSERT INTO Chapter (chapterName) VALUES(\"Les villes d'Europe\");");
            db.execSQL("INSERT INTO Chapter (chapterName) VALUES(\"Le cycle de l'eau\");");
            db.execSQL("INSERT INTO Chapter (chapterName) VALUES(\"La fôret\");");}*/
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

            //Table Litteral (question et réponses possibles)
            /*{ArrayList<String> reponses = new ArrayList<>();
            reponses.add("Antoine");
            reponses.add("Chevallet");
            reponses.add("Rieu");
            reponses.add("Le gif préféré de Line");
            db.execSQL("INSERT INTO Litteral (subject, answers, chapterName) VALUES(\"Bonsoir est\", reponses,\"La révolution\");");
            reponses.clear();
            reponses.add("Antoine");
            reponses.add("Chevallet");
            reponses.add("Rieu");
            reponses.add("Le gif préféré de Line");
            db.execSQL("INSERT INTO Litteral (subject, answers, chapterName) VALUES(\"La réponse toujours juste parce que c'est comme ça est\", reponses,\"La révolution\");");
            reponses.clear();}*/

        }
    };
}
