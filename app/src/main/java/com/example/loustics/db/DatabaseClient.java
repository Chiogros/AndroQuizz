
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
                    db.execSQL("INSERT INTO Chapter VALUES('Additions de 0 à 10', 'Mathématiques');");
                    db.execSQL("INSERT INTO Chapter VALUES('Additions de 0 à 50', 'Mathématiques');");
                    db.execSQL("INSERT INTO Chapter VALUES('Additions de 0 à 100', 'Mathématiques');");
                    db.execSQL("INSERT INTO Chapter VALUES('Additions de -200 à 200', 'Mathématiques');");
                    db.execSQL("INSERT INTO Chapter VALUES('Additions pour les pros', 'Mathématiques');");
                    db.execSQL("INSERT INTO Chapter VALUES('Soustractions de 0 à 10', 'Mathématiques');");
                    db.execSQL("INSERT INTO Chapter VALUES('Soustractions de 0 à 50', 'Mathématiques');");
                    db.execSQL("INSERT INTO Chapter VALUES('Soustractions de 0 à 100', 'Mathématiques');");
                    db.execSQL("INSERT INTO Chapter VALUES('Soustractions de -200 à 200', 'Mathématiques');");
                    db.execSQL("INSERT INTO Chapter VALUES('Soustractions pour les pros', 'Mathématiques');");
                    db.execSQL("INSERT INTO Chapter VALUES('Multiplications - Table de 0', 'Mathématiques');");
                    db.execSQL("INSERT INTO Chapter VALUES('Multiplications - Table de 1', 'Mathématiques');");
                    db.execSQL("INSERT INTO Chapter VALUES('Multiplications - Table de 2', 'Mathématiques');");
                    db.execSQL("INSERT INTO Chapter VALUES('Multiplications - Table de 3', 'Mathématiques');");
                    db.execSQL("INSERT INTO Chapter VALUES('Multiplications - Table de 4', 'Mathématiques');");
                    db.execSQL("INSERT INTO Chapter VALUES('Multiplications - Table de 5', 'Mathématiques');");
                    db.execSQL("INSERT INTO Chapter VALUES('Multiplications - Table de 6', 'Mathématiques');");
                    db.execSQL("INSERT INTO Chapter VALUES('Multiplications - Table de 7', 'Mathématiques');");
                    db.execSQL("INSERT INTO Chapter VALUES('Multiplications - Table de 8', 'Mathématiques');");
                    db.execSQL("INSERT INTO Chapter VALUES('Multiplications - Table de 9', 'Mathématiques');");
                    db.execSQL("INSERT INTO Chapter VALUES('Multiplications - Table de 10', 'Mathématiques');");
                    db.execSQL("INSERT INTO Chapter VALUES('Multiplications de 0 à 10', 'Mathématiques');");
                }

            // Table Litteral (questions et réponses possibles)
            {
               db.execSQL("INSERT INTO Litteral VALUES(\"L'assembleur\", 'Grammaire - Pronoms', 'Français', \"{'right' : ['permet de manipuler la mémoire', 'est un langage de programmation', 'est la base de nimporte quel langage informatique'], 'wrong' : ['permet de commander Uber', 'a été inventé par JP', 'offre des réductions dès 3 octets achetés', 'sent bon la menthe']}\");");
            }

            // Table Calculation
            {
                // Addition
                {
                    db.execSQL("INSERT INTO Addition VALUES('Additions de 0 à 10', 'Mathématiques', 0, 10, 0, 10);");
                    db.execSQL("INSERT INTO Addition VALUES('Additions de 0 à 50', 'Mathématiques', 0, 50, 0, 50);");
                    db.execSQL("INSERT INTO Addition VALUES('Additions de 0 à 100', 'Mathématiques', 0, 100, 0, 100);");
                    db.execSQL("INSERT INTO Addition VALUES('Additions de -200 à 200', 'Mathématiques', -200, 200, -200, 200);");
                    db.execSQL("INSERT INTO Addition VALUES('Additions pour les pros', 'Mathématiques', -10000, 10000, -10000, 10000);");
                }

                // Soustraction
                {
                    db.execSQL("INSERT INTO Soustraction VALUES('Soustractions de 0 à 10', 'Mathématiques', 0, 10, 0, 10);");
                    db.execSQL("INSERT INTO Soustraction VALUES('Soustractions de 0 à 50', 'Mathématiques', 0, 50, 0, 50);");
                    db.execSQL("INSERT INTO Soustraction VALUES('Soustractions de 0 à 100', 'Mathématiques', 0, 100, 0, 100);");
                    db.execSQL("INSERT INTO Soustraction VALUES('Soustractions de -200 à 200', 'Mathématiques', -200, 200, -200, 200);");
                    db.execSQL("INSERT INTO Soustraction VALUES('Soustractions pour les pros', 'Mathématiques', -10000, 10000, -10000, 10000);");
                }

                // Multiplication
                {
                    db.execSQL("INSERT INTO Multiplication VALUES('Multiplications - Table de 0', 'Mathématiques', 0, 0, 0, 10);");
                    db.execSQL("INSERT INTO Multiplication VALUES('Multiplications - Table de 1', 'Mathématiques', 1, 1, 0, 10);");
                    db.execSQL("INSERT INTO Multiplication VALUES('Multiplications - Table de 2', 'Mathématiques', 2, 2, 0, 10);");
                    db.execSQL("INSERT INTO Multiplication VALUES('Multiplications - Table de 3', 'Mathématiques', 3, 3, 0, 10);");
                    db.execSQL("INSERT INTO Multiplication VALUES('Multiplications - Table de 4', 'Mathématiques', 4, 4, 0, 10);");
                    db.execSQL("INSERT INTO Multiplication VALUES('Multiplications - Table de 5', 'Mathématiques', 5, 5, 0, 10);");
                    db.execSQL("INSERT INTO Multiplication VALUES('Multiplications - Table de 6', 'Mathématiques', 6, 6, 0, 10);");
                    db.execSQL("INSERT INTO Multiplication VALUES('Multiplications - Table de 7', 'Mathématiques', 7, 7, 0, 10);");
                    db.execSQL("INSERT INTO Multiplication VALUES('Multiplications - Table de 8', 'Mathématiques', 8, 8, 0, 10);");
                    db.execSQL("INSERT INTO Multiplication VALUES('Multiplications - Table de 9', 'Mathématiques', 9, 9, 0, 10);");
                    db.execSQL("INSERT INTO Multiplication VALUES('Multiplications - Table de 10', 'Mathématiques', 10, 10, 0, 10);");
                    db.execSQL("INSERT INTO Multiplication VALUES('Multiplications de 0 à 10', 'Mathématiques', 0, 10, 0, 10);");
                }
            }

            // Table Image
            {
                db.execSQL("INSERT INTO Image VALUES('ic_check', 'Les départements', 'Géographie', \"{'right' : ['ic_book', 'ic_check', 'ic_fire'], 'wrong' : ['ic_map', 'ic_math', 'ic_settings', 'ic_history']}\");");
            }
        }
    };
}
