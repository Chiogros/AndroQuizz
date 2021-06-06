
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

                // Histoire
                {
                    db.execSQL("INSERT INTO Chapter VALUES('La préhistoire', 'Histoire');");
                    db.execSQL("INSERT INTO Chapter VALUES(\"L'antiquité\", 'Histoire');");
                    db.execSQL("INSERT INTO Chapter VALUES('Le Moyen-Âge', 'Histoire');");
                    db.execSQL("INSERT INTO Chapter VALUES('Les temps modernes', 'Histoire');");
                    db.execSQL("INSERT INTO Chapter VALUES(\"L'époque contemporaine\", 'Histoire');");
                    db.execSQL("INSERT INTO Chapter VALUES('Le temps présent', 'Histoire');");
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
                // Français
                {

                }

                // Géographie
                {

                }

                // Histoire
                {
                    // La Préhistoire
                    {
                        db.execSQL("INSERT INTO Litteral VALUES('Un bipède', 'La préhistoire', 'Histoire', \"{'right' : ['est une espèce qui marche sur deux pieds', 'n utilise pas ses mains pour se déplacer', 'signifie bi pour deux et pède pour pieds : une espèce avec deux pieds'], 'wrong' : ['se déplace grâce à ses ailes', 'est un outil permettant de couper du bois']}\");");
                        db.execSQL("INSERT INTO Litteral VALUES('Une espèce', 'La préhistoire', 'Histoire', \"{'right' : ['est un ensemble d êtres vivants qui se ressemblent', 'peut se reproduire'], 'wrong' : ['se mange en hiver pour redonner des forces', 'est une épice', 'englobe tous les animaux']}\");");
                        db.execSQL("INSERT INTO Litteral VALUES(\"L'espèce humaine\", 'La préhistoire', 'Histoire', \"{'right' : ['est probablement apparue en Afrique', 'est apparue il y a environ 7 millions d années', 'est proche du singe', 'est majoritairement composée de bipèdes'], 'wrong' : ['vient d Europe et a été importée aux États-Unis', 'a été créée en laboratoire', 'a été créée par Dieu']}\");");
                        db.execSQL("INSERT INTO Litteral VALUES('Le plus vieux fossile', 'La préhistoire', 'Histoire', \"{'right' : ['a été découvert en 2001', 'a été trouvé au Tchad', 'a été surnommé Le Crâne de Toumaï'], 'wrong' : ['a été découvert avec le squelette de Lucy', 'a été retrouvé en haut du Mont-Blanc', 'a la même forme qu un crâne humain']}\");");
                        db.execSQL("INSERT INTO Litteral VALUES(\"L'Australopithèque\", 'La préhistoire', 'Histoire', \"{'right' : ['est apparu il y a environ 4 millions d années', 'se nourrissait de racines, de fruits et de viande crue', 'pouvait marcher sur des courtes distances'], 'wrong' : ['allait chez l armurier pour s équiper', 'ne se déplaçait qu à genoux']}\");");
                        db.execSQL("INSERT INTO Litteral VALUES(\"L'Homo Habilis\", 'La préhistoire', 'Histoire', \"{'right' : ['mesure 1,40m', 'vivait il y a -2.4 millions d années', 'était un nomade'], 'wrong' : ['se servait des pierres pour faire des ricochets', 'faisait la danse de la pluie quand il avait la flemme d aller chercher de l eau', 'faisait de l agriculture pour se nourir']}\");");
                        db.execSQL("INSERT INTO Litteral VALUES(\"L'Homo Érectus\", 'La préhistoire', 'Histoire', \"{'right' : ['mesure 1.50m', 'vivait il y a -1.8 millions d années', 'était un nomade'], 'wrong' : ['était paresseux', 'chassait chez enfants', 'faisait du troc avec les oiseaux']}\");");
                        db.execSQL("INSERT INTO Litteral VALUES(\"L'Homo Sapiens\", 'La préhistoire', 'Histoire', \"{'right' : ['est aussi appelé Homme de Cro-Magnon', 'est sédentaire', 'vivait dans les villages', 'savait utiliser des outils agricoles'], 'wrong' : ['vivait en solitaire', 'ne savait pas se servir d outils pour l agriculture']}\");");
                        db.execSQL("INSERT INTO Litteral VALUES('Les Hommes préhistoriques', 'La préhistoire', 'Histoire', \"{'right' : ['mangeaient du mammouth', 'et les singes ont un ancêtre commun'], 'wrong' : ['habitaient dans une grotte', 'ont connu les dinosaures', 'descendent du singe', 'étaient de grosses brutes']}\");");
                        db.execSQL("INSERT INTO Litteral VALUES('La période de la Préhistoire', 'La préhistoire', 'Histoire', \"{'right' : ['correspond à l apparition de l Homme sur Terre'], 'wrong' : ['est la période d apparition des dinosaures', 'a commencée il y a 5,5 millions d annees']}\");");
                        db.execSQL("INSERT INTO Litteral VALUES('Le squelette Lucy', 'La préhistoire', 'Histoire', \"{'right' : ['a été découvert en Éthiopie', 'a été trouvé en 1974', 'porte ce nom car les chercheurs qui l ont trouvé écoutaient Lucy in the sky of diamonds des Beatles'], 'wrong' : ['portait un bracelet en défense de mammouth où était écrit son nom', 's appelle comme ça car la personne qui l a trouvé s appelle Lucy']}\");");
                        db.execSQL("INSERT INTO Litteral VALUES('À la préhistoire, on allumait le feu', 'La préhistoire', 'Histoire', \"{'right' : ['avec des pierres', 'en tapotant deux cailloux l un contre l autre', 'en tapant un silex avec du minerai de fer'], 'wrong' : ['avec un briquet', 'en soufflant très fort sur l herbe sèche']}\");");
                        db.execSQL("INSERT INTO Litteral VALUES('Les personnes qui étudient la Préhistoire sont appelés', 'La préhistoire', 'Histoire', \"{'right' : ['préhistoriens'], 'wrong' : ['préhistoriques', 'préhistologues']}\");");
                    }
                }

                // Physique
                {

                }
            }

            // Tables de calculs
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
                db.execSQL("INSERT INTO Image VALUES('Les logos', 'Les départements', 'Géographie', \"{'right' : ['ic_book', 'ic_check', 'ic_fire'], 'wrong' : ['ic_map', 'ic_math', 'ic_settings', 'ic_history']}\");");
                db.execSQL("INSERT INTO Image VALUES('Le drapeau français est', \"L'Europe\", 'Géographie', \"{'right' : ['ic_france'], 'wrong' : ['ic_united_states', 'ic_italy']}\");");
            }
        }
    };
}
