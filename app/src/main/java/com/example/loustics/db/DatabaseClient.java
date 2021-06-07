
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
            {
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
                    db.execSQL("INSERT INTO Chapter VALUES('Les villes', 'Géographie');");
                    db.execSQL("INSERT INTO Chapter VALUES('Les départements', 'Géographie');");
                    db.execSQL("INSERT INTO Chapter VALUES('Les régions', 'Géographie');");
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
            }

            // Table Litteral
            {
                // Français
                {
                    // Grammaire - Pronoms
                    {
                        db.execSQL("INSERT INTO Litteral VALUES('Oh, regarde ces chiots ! Tu as vu comme _____ est beau ?', 'Grammaire - Pronoms', 'Français', \"{'right' : ['celui-ci'], 'wrong' : ['ceux', 'ce', 'celui']}\");");
                        db.execSQL("INSERT INTO Litteral VALUES(\"J'aime beaucoup faire _____\", 'Grammaire - Pronoms', 'Français', \"{'right' : ['cela', 'ceci'], 'wrong' : ['ceux-là', 'ce', 'celle-là']}\");");
                        db.execSQL("INSERT INTO Litteral VALUES(\"J'ai fait mes exercices mais je n'ai pas bien compris _____, ni celui-là d'ailleurs\", 'Grammaire - Pronoms', 'Français', \"{'right' : ['celui-ci'], 'wrong' : ['ceux', 'ce', 'celui', 'celui-là']}\");");
                        db.execSQL("INSERT INTO Litteral VALUES('Je ne comprends pas _____ que vous voulez me dire', 'Grammaire - Pronoms', 'Français', \"{'right' : ['ce'], 'wrong' : ['ceux', 'ceci', 'celui-ci']}\");");
                        db.execSQL("INSERT INTO Litteral VALUES(\"J'ai couru voir le postier, _____ m'a remis une lettre pour vous.\", 'Grammaire - Pronoms', 'Français', \"{'right' : ['lequel'], 'wrong' : ['laquelle', 'auxquels', 'duquel']}\");");
                        db.execSQL("INSERT INTO Litteral VALUES(\"_____ s'est trompée.\", 'Grammaire - Pronoms', 'Français', \"{'right' : ['Elle'], 'wrong' : ['lui', 'on', 'elles']}\");");
                        db.execSQL("INSERT INTO Litteral VALUES('Regarde ces voitures. _____ sont en très mauvais état !', 'Grammaire - Pronoms', 'Français', \"{'right' : ['elles'], 'wrong' : ['ils', 'elle', 'il']}\");");
                        db.execSQL("INSERT INTO Litteral VALUES('Les tonneaux vides sont ceux _____ font le plus de bruit.', 'Grammaire - Pronoms', 'Français', \"{'right' : ['qui'], 'wrong' : ['auxquels', 'que', 'dont']}\");");
                        db.execSQL("INSERT INTO Litteral VALUES('Je vis dans une maison _____ les murs tombent en ruine.', 'Grammaire - Pronoms', 'Français', \"{'right' : ['dont'], 'wrong' : ['qui', 'duquel', 'ou']}\");");
                        db.execSQL("INSERT INTO Litteral VALUES('Nous avons trouvé un trésor _____ nous allons garder précieusement.', 'Grammaire - Pronoms', 'Français', \"{'right' : ['que'], 'wrong' : ['qui', 'auxquels', 'duquel']}\");");
                    }

                    // Grammaire - Adverbes
                    {
                        db.execSQL("INSERT INTO Litteral VALUES(\"Les vacances commencent demain !' L'adverbe est\", 'Grammaire - Adverbes', 'Français', \"{'right' : ['demain'], 'wrong' : ['commencent', 'les', 'vacances']}\");");
                        db.execSQL("INSERT INTO Litteral VALUES(\"Les enfants pourront jouer dehors ce soir !' L'adverbe est\", 'Grammaire - Adverbes', 'Français', \"{'right' : ['dehors'], 'wrong' : ['soir', 'enfants', 'jouer']}\");");
                        db.execSQL("INSERT INTO Litteral VALUES(\"Nous avons mangé des burgers hier soir. L'adverbe est\", 'Grammaire - Adverbes', 'Français', \"{'right' : ['hier'], 'wrong' : ['burgers', 'soir', 'nous']}\");");
                    }

                    // Conjugaison - Présent
                    {
                        db.execSQL("INSERT INTO Litteral VALUES('Je (être) un petit bout de bois.', 'Conjugaison - Présent', 'Français', \"{'right' : ['suis'], 'wrong' : ['suit', 'es', 'sui']}\");");
                        db.execSQL("INSERT INTO Litteral VALUES('Le lion (être) mort ce soir', 'Conjugaison - Présent', 'Français', \"{'right' : ['est'], 'wrong' : ['ait', 'et', 'es']}\");");
                        db.execSQL("INSERT INTO Litteral VALUES('Alexandre (mange) un brownie', 'Conjugaison - Présent', 'Français', \"{'right' : ['mange'], 'wrong' : ['manges', 'mangent', 'mangeais', 'celui-là']}\");");
                        db.execSQL("INSERT INTO Litteral VALUES('Allez Pô, allez tu (venir)', 'Conjugaison - Présent', 'Français', \"{'right' : ['viens'], 'wrong' : ['vient', 'vi1', 'vien']}\");");
                        db.execSQL("INSERT INTO Litteral VALUES('Seules les vacances (être) bonnes pour la santé', 'Conjugaison - Présent', 'Français', \"{'right' : ['sont'], 'wrong' : ['son', 's on', 'sons']}\");");
                        db.execSQL("INSERT INTO Litteral VALUES('Ces cartons (être) lourds', 'Conjugaison - Présent', 'Français', \"{'right' : ['sont'], 'wrong' : ['son', 's on', 'sons']}\");");
                        db.execSQL("INSERT INTO Litteral VALUES('Il (faire) tout noir', 'Conjugaison - Présent', 'Français', \"{'right' : ['fait'], 'wrong' : ['fais', 'fai', 'faie']}\");");
                        db.execSQL("INSERT INTO Litteral VALUES(\"Vous n'(avoir) toujours rien corrigé\", 'Conjugaison - Présent', 'Français', \"{'right' : ['avez'], 'wrong' : ['avait', 'avé', 'avais']}\");");
                        db.execSQL("INSERT INTO Litteral VALUES(\"Elles (avoir) des cours d'informatique\", 'Conjugaison - Présent', 'Français', \"{'right' : ['ont'], 'wrong' : ['on', 'ons', 'hon']}\");");
                    }

                    // Conjugaison - Futur
                    {

                    }

                    // Conjugaison - Passé simple
                    {

                    }
                }

                // Géographie
                {
                    // Les villes
                    {
                        db.execSQL("INSERT INTO Litteral VALUES('Grenoble', 'Les villes', 'Géographie', \"{'right' : ['est appelée la Capitale des Alpes', 'a pour maire Éric Piolle', 'a accueilli les JO d hiver en 1968', 'est la ville la plus plate de France'], 'wrong' : ['accueille la plus grande gare de la région', 'est connu pour le quartier Fourvièvre']}\");");
                        db.execSQL("INSERT INTO Litteral VALUES('La ville de Bordeaux', 'Les villes', 'Géographie', \"{'right' : ['est la capitale mondiale du Vin', 'est traversée par la Garonne', 'est la 9ème ville française en nombre d'habitants'], 'wrong' : ['organise une grande paella chaque année', 'est dans le département du Vaucluse']}\");");
                    }

                    // Les départements
                    {
                        db.execSQL("INSERT INTO Litteral VALUES('Le département du Vaucluse', 'Les départements', 'Géographie', \"{'right' : ['est dans la région PACA', 'culmine à 1912, en haut du Mont Ventoux'], 'wrong' : ['est en région Aquitaine', 'est limitrophe avec la Suisse']}\");");
                        db.execSQL("INSERT INTO Litteral VALUES(\"L'Ain a pour numéro\", 'Les départements', 'Géographie', \"{'right' : ['01'], 'wrong' : ['02', '38', '05']}\");");
                        db.execSQL("INSERT INTO Litteral VALUES(\"Le terme de Département en Outre-mer s'applique pour\", 'Les départements', 'Géographie', \"{'right' : ['la Guadeloupe', 'la Martinique', 'la Guyane', 'la Réunion', 'Mayotte'], 'wrong' : ['l Yonne', 'l Orne', 'l Indre', 'le Puy-de-Dôme', 'la Haute-Corse', 'les Alpes-Maritime']}\");");
                        db.execSQL("INSERT INTO Litteral VALUES('En France, il y a', 'Les départements', 'Géographie', \"{'right' : ['101 départements'], 'wrong' : ['78 départements', '26 départements', '136 départements']}\");");
                        db.execSQL("INSERT INTO Litteral VALUES('Les départements sont numérotés', 'Les départements', 'Géographie', \"{'right' : ['par ordre alphabétique', 'de 01 à 95 et de 971 à 976'], 'wrong' : ['par taille', 'par nombre d habitants', 'par nombre d habitants au km²']}\");");
                        db.execSQL("INSERT INTO Litteral VALUES('Les départements', 'Les départements', 'Géographie', \"{'right' : ['ont été créés en 1789'], 'wrong' : ['ont été créés en 1928', 'ont été découpés puis leurs frontières n ont jamais été changées']}\");");
                        db.execSQL("INSERT INTO Litteral VALUES('Le 41 correspond', 'Les départements', 'Géographie', \"{'right' : ['au Loir-et-Cher'], 'wrong' : ['à la Meuse', 'au Finistère', 'au Morbihan', 'à la Haute-Saône', 'au département des Landes']}\");");
                        db.execSQL("INSERT INTO Litteral VALUES('Le n°55 correspond', 'Les départements', 'Géographie', \"{'right' : ['à la Meuse'], 'wrong' : ['au Loir-et-Cher', 'au Finistère', 'au Morbihan', 'à la Haute-Saône', 'au département des Landes']}\");");
                        db.execSQL("INSERT INTO Litteral VALUES('Le département n°29', 'Les départements', 'Géographie', \"{'right' : ['est celui du Finistère'], 'wrong' : ['est celui de Loir-et-Cher', 'est celui de la Meuse', 'est celui du Morbihan', 'est celui de la Haute-Saône', 'est le département des Landes']}\");");
                        db.execSQL("INSERT INTO Litteral VALUES('La capitale française se situe dans', 'Les départements', 'Géographie', \"{'right' : ['le 75'], 'wrong' : ['le 76', 'le 94', 'le 18', 'le 17', 'le 36']}\");");
                    }

                    // Les régions
                    {
                        db.execSQL("INSERT INTO Litteral VALUES('En 2021 en France, il y a', 'Les régions', 'Géographie', \"{'right' : ['18 régions', '13 régions métropolitaines + 5 régions d Outre-mer'], 'wrong' : ['9 régions', '13 régions + les 5 départements d Outre-mer', '15 régions', '23 régions']}\");");
                        db.execSQL("INSERT INTO Litteral VALUES('La plus grande région est celle', 'Les régions', 'Géographie', \"{'right' : ['de Nouvelle Aquitaine'], 'wrong' : ['d Occitanie', 'de Rhône-Alpes', 'de Savoie', 'du Centre-Val de l Oire']}\");");
                        db.execSQL("INSERT INTO Litteral VALUES('La plus région avec le moins d habitants au km² est', 'Les régions', 'Géographie', \"{'right' : ['la Corse'], 'wrong' : ['le Centre-Val de l Oire', 'l Ardèche', 'le Grand Est', 'la Bretagne']}\");");
                        db.execSQL("INSERT INTO Litteral VALUES('La région la plus peuplée', 'Les régions', 'Géographie', \"{'right' : ['est l Île de France', 'compte plus de 12 millions d habitants'], 'wrong' : ['est la région Provence-Alpes-Côte d Azur', 'est la région qui a la plus grande superficie']}\");");
                        db.execSQL("INSERT INTO Litteral VALUES('La région Auvergne-Rhône-Alpes accueille la grande ville', 'Les régions', 'Géographie', \"{'right' : ['de Lyon'], 'wrong' : ['de Poitiers', 'de Bordeaux', 'du Touquet', 'de Besançon']}\");");
                    }

                    // L'Europe
                    {
                        db.execSQL("INSERT INTO Litteral VALUES('En Europe, il y a', \"L'Europe\", 'Géographie', \"{'right' : ['50 pays en Europe', '5 grandes régions européennes', 'Venise'], 'wrong' : ['aucun monument classé au patrimoine mondial de l UNESCO', 'la plus grande montagne au monde', 'la plus haute tour du monde']}\");");
                        db.execSQL("INSERT INTO Litteral VALUES('La BCE', \"L'Europe\", 'Géographie', \"{'right' : ['signifie Banque Centrale Européenne', 'a été créée en 1998', 'se situe à Francfort, en Belgique', 'gère l émission des billets de la zone Euro'], 'wrong' : ['veut dire Bruyère Cantal Eybens', 'a son siège social à Liège', 'ne publie jamais de bulletin économique', 'limite le nombre d emprunts accordés aux banques']}\");");
                        db.execSQL("INSERT INTO Litteral VALUES('Lisbonne est la capitale', \"L'Europe\", 'Géographie', \"{'right' : ['du Portugal'], 'wrong' : ['de l Espagne', 'de la Suède', 'de l Autriche']}\");");
                        db.execSQL("INSERT INTO Litteral VALUES(\"L'espace Schengen\", \"L'Europe\", 'Géographie', \"{'right' : ['comprend 26 états européens', 'compte plus de 400 millions d habitants'], 'wrong' : ['est la même chose que l Union Européenne', 'a été mis en place en 1950', 'oblige les pays à avoir un contrôle aux frontières entre les pays membres']}\");");
                    }

                    // Le monde
                    {
                        db.execSQL("INSERT INTO Litteral VALUES('En mars 2020, il y avait', 'Le monde', 'Géographie', \"{'right' : ['7,8 milliards de personnes sur Terre'], 'wrong' : ['5,5 milliards de personnes sur Terre', '8.3 milliards de personnes sur Terre']}\");");
                        db.execSQL("INSERT INTO Litteral VALUES(\"L'océan pacifique\", 'Le monde', 'Géographie', \"{'right' : ['est entre la Chine et les États-Unis', 'est le plus grand au monde'], 'wrong' : ['le deuxième plus grand océan après l océan Atlantique', 'entre les États-Unis et l Europe']}\");");
                        db.execSQL("INSERT INTO Litteral VALUES('La capitale des États-Unis', 'Le monde', 'Géographie', \"{'right' : ['est Washigton D.C.', 'accueille la Maison Blanche'], 'wrong' : ['est New York', 'est Brooklyn']}\");");
                        db.execSQL("INSERT INTO Litteral VALUES('La capitale de la Chine', 'Le monde', 'Géographie', \"{'right' : ['est Beijing', 'fait 16 000 km²'], 'wrong' : ['est Tokyo', 'est Shanghaï']}\");");
                        db.execSQL("INSERT INTO Litteral VALUES('La muraille de Chine', 'Le monde', 'Géographie', \"{'right' : ['traverse le pays d Est en Ouest', 'mesure 21,196 mètres', 'fait de 6 à 7 mètres de haut'], 'wrong' : ['servait à facilement traverser le pays', 'était utilisée pour le commerce sur de longues distances']}\");");
                        db.execSQL("INSERT INTO Litteral VALUES('Moscou', 'Le monde', 'Géographie', \"{'right' : ['est la ville la plus peuplée d Europe', 'produit 25% du PIB de la Russie', 'est la capitale Russe'], 'wrong' : ['est la capitale de la Mongolie', 'accueille chaque année le G20']}\");");
                        db.execSQL("INSERT INTO Litteral VALUES('Le Rwanda', 'Le monde', 'Géographie', \"{'right' : ['est en Afrique', 'est membre de l ONU', 'est appelé le Pays des milles collines'], 'wrong' : ['compte beaucoup de déserts', 'la majorité de la population sont protestants', 'accueillera en 2025 les championnats du monde de cyclisme sur route pour la seconde fois']}\");");
                    }
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

                    // L'Antiquité
                    {

                    }

                    // Le Moyen-Âge
                    {

                    }

                    // Les temps modernes
                    {
                        db.execSQL("INSERT INTO Litteral VALUES(\"Sous l'Ancien Régime, la France est divisée en trois ordres : le Clergé, la Noblesse et\", 'Les temps modernes', 'Histoire', \"{'right' : ['le Tier État'], 'wrong' : ['les paysans', 'le Tier pauvre', 'la classe moyenne']}\");");
                        db.execSQL("INSERT INTO Litteral VALUES('Lors de la révolution, _____ était le roi à la tête de la France', 'Les temps modernes', 'Histoire', \"{'right' : ['Louis XVI'], 'wrong' : ['Louis XIV', 'Napoléon', 'Henri IV']}\");");
                        db.execSQL("INSERT INTO Litteral VALUES(\"Le serment proclamé à l'assemblée nationale s'appelle le serment\", 'Les temps modernes', 'Histoire', \"{'right' : ['du jeu de paume'], 'wrong' : ['des députés', 'de l assemblée', 'de Versailles']}\");");
                        db.execSQL("INSERT INTO Litteral VALUES('La révolution éclate le ', 'Les temps modernes', 'Histoire', \"{'right' : ['14 juillet 1789'], 'wrong' : ['14 juillet 1785', '30 août 1789', '30 août 1785']}\");");
                        db.execSQL("INSERT INTO Litteral VALUES('_____ est envahit par les parisiens le jour où la révolution française éclate', 'Les temps modernes', 'Histoire', \"{'right' : ['La prison de la Bastille'], 'wrong' : ['L hôpital militaire', 'La tour Eiffel', 'Versailles']}\");");
                        db.execSQL("INSERT INTO Litteral VALUES(\"Le texte qui met fin à l'ancier régime est\", 'Les temps modernes', 'Histoire', \"{'right' : ['lé délcaration des droits de l Homme'], 'wrong' : ['la république', 'la constitution', 'la monarchie']}\");");
                        db.execSQL("INSERT INTO Litteral VALUES('Après le renversement de la royauté, la république est proclamée en', 'Les temps modernes', 'Histoire', \"{'right' : ['1792'], 'wrong' : ['1789', '1790', '1791']}\");");
                        db.execSQL("INSERT INTO Litteral VALUES('_____ mène la politique de la terreur avec les montagnards et les sans-culottes', 'Les temps modernes', 'Histoire', \"{'right' : ['Robespierre'], 'wrong' : ['Jean Valjean', 'Les députés', 'Jean-Pierre Chevallet']}\");");
                        db.execSQL("INSERT INTO Litteral VALUES('Après sa fuite, le roi a été guillotiné en', 'Les temps modernes', 'Histoire', \"{'right' : ['1793'], 'wrong' : ['1791', '1790', '1792']}\");");
                    }

                    // L'époque contemporaine
                    {
                        db.execSQL("INSERT INTO Litteral VALUES('Adolph Hitler devient Führer en Allemagne en', \"L'époque contemporaine\", 'Histoire', \"{'right' : ['1933'], 'wrong' : ['1935', '1938', '1940']}\");");
                        db.execSQL("INSERT INTO Litteral VALUES(\"La seconde guerre mondiale s'est déroulée de\", \"L'époque contemporaine\", 'Histoire', \"{'right' : ['1939 à 1945'], 'wrong' : ['1914 à 1918', '1920 à 1930', '1945 à 1970']}\");");
                        db.execSQL("INSERT INTO Litteral VALUES(\"L'événement lors duquel la France a été envahie s'appelle\", \"L'époque contemporaine\", 'Histoire', \"{'right' : ['la guerre éclair'], 'wrong' : ['la bataille éclair', 'la guerre lumière', 'la guerre totale']}\");");
                        db.execSQL("INSERT INTO Litteral VALUES(\"_____ a pris le titre de chef de l'État Français lors de l'occupation\", \"L'époque contemporaine\", 'Histoire', \"{'right' : ['Le maréchal Pétain'], 'wrong' : ['Le Général de Gaulle', 'Le général Joffre', 'Le général Vichy']}\");");
                        db.execSQL("INSERT INTO Litteral VALUES('Pour être distingués, les juifs devaient porter', \"L'époque contemporaine\", 'Histoire', \"{'right' : ['une étoile jaune'], 'wrong' : ['une étoile rouge', 'un carré jaune', 'un carré rouge']}\");");
                        db.execSQL("INSERT INTO Litteral VALUES('Les juifs étaient déportés dans', \"L'époque contemporaine\", 'Histoire', \"{'right' : ['des camps d extermination'], 'wrong' : ['des camps de concentration', 'des camps d héradication', 'des goulags']}\");");
                        db.execSQL("INSERT INTO Litteral VALUES(\"_____ était allié de l'Allemagne\", \"L'époque contemporaine\", 'Histoire', \"{'right' : ['Le Japon'], 'wrong' : ['La Chine', 'L Angleterre', 'Le Brésil']}\");");
                        db.execSQL("INSERT INTO Litteral VALUES(\"Les États-Unis ont déclaré la guerre à l'Allemagne et ses alliés lors de l'attaque de\", \"L'époque contemporaine\", 'Histoire', \"{'right' : ['Pearl Harbor en 1941'], 'wrong' : ['Pearl Harbor en 1940', 'New York en 1941', 'New York en 1940']}\");");
                        db.execSQL("INSERT INTO Litteral VALUES('On surnomme la seconde guerre mondiale comme étant la', \"L'époque contemporaine\", 'Histoire', \"{'right' : ['guerre totale'], 'wrong' : ['guerre éclair', 'bataille éclair', 'guerre lumière']}\");");
                        db.execSQL("INSERT INTO Litteral VALUES('La première guerre mondiale dure de', \"L'époque contemporaine\", 'Histoire', \"{'right' : ['1914 à 1918'], 'wrong' : ['1915 à 1918', '1914 à 1919', '1915 à 1919']}\");");
                        db.execSQL("INSERT INTO Litteral VALUES(\"L'armistice est signé à Rethondes le'\", \"L'époque contemporaine\", 'Histoire', \"{'right' : ['11 novembre 1918'], 'wrong' : ['11 novembre 1919', '11 décembre 1918', '11 décembre 1919']}\");");
                        db.execSQL("INSERT INTO Litteral VALUES(\"L'archiduc François-Ferdinand a eu lieu le\", \"L'époque contemporaine\", 'Histoire', \"{'right' : ['28 juin 1914 à Sarajevo'], 'wrong' : ['28 juin 1916 à Sarajevo', '28 juin 1914 à Vienne', '28 juin 1916 à Vienne']}\");");
                        db.execSQL("INSERT INTO Litteral VALUES('Pendant la guerre, deux blocs se forment. La France fait partie', \"L'époque contemporaine\", 'Histoire', \"{'right' : ['de la Triple Alliance'], 'wrong' : ['de la Triple Entente', 'de la Triple Armée', 'du bloc de l Ouest']}\");");
                        db.execSQL("INSERT INTO Litteral VALUES(\"L'Allemagne s'allie à l'Autriche-Hongrie et\", \"L'époque contemporaine\", 'Histoire', \"{'right' : ['à l Italie'], 'wrong' : ['aux Pays-Bas', 'à la Russie', 'à la Suisse']}\");");
                        db.execSQL("INSERT INTO Litteral VALUES('_____ commandant la bataille de Verdun', \"L'époque contemporaine\", 'Histoire', \"{'right' : ['Le général Pétain'], 'wrong' : ['Le général Clémenceau', 'Le général Joffre', 'Le général Nivel']}\");");
                        db.execSQL("INSERT INTO Litteral VALUES(\"Pour instaurer la paix, les Alliés et l'Allemagne ont signé le\", \"L'époque contemporaine\", 'Histoire', \"{'right' : ['Traité de Versailles'], 'wrong' : ['Traité de Neuilly', 'Traité de Berlin', 'Traité de Paris']}\");");
                        db.execSQL("INSERT INTO Litteral VALUES('Pour régler les désaccords entre pays, fut signée', \"L'époque contemporaine\", 'Histoire', \"{'right' : ['la Société des Nations'], 'wrong' : ['l Union Européenne', 'la Société de la Paix', 'l ONU']}\");");
                        db.execSQL("INSERT INTO Litteral VALUES('La bataille la plus meurtrière fut', \"L'époque contemporaine\", 'Histoire', \"{'right' : ['la bataille de la Somme'], 'wrong' : ['la bataille de Verdun', 'la bataille de la Marne', 'la bataille des Frontières']}\");");
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
                // Géographie
                {
                    // Les villes
                    {

                    }

                    // Les départements
                    {

                    }

                    // Les régions
                    {

                    }

                    // L'Europe
                    {
                        db.execSQL("INSERT INTO Image VALUES('Le drapeau de la France est', \"L'Europe\", 'Géographie', \"{'right' : ['ic_france'], 'wrong' : ['ic_united_states', 'ic_italy', 'ic_canada', 'ic_europe', 'ic_mexico', 'ic_poland', 'ic_russia', 'ic_spain', 'ic_sweden']}\");");
                        db.execSQL("INSERT INTO Image VALUES(\"Le drapeau de l'Italie est\", \"L'Europe\", 'Géographie', \"{'right' : ['ic_italy'], 'wrong' : ['ic_united_states', 'ic_canada', 'ic_europe', 'ic_mexico', 'ic_poland', 'ic_russia', 'ic_spain', 'ic_sweden', 'ic_france']}\");");
                        db.execSQL("INSERT INTO Image VALUES(\"Le drapeau de l'Union Européenne est\", \"L'Europe\", 'Géographie', \"{'right' : ['ic_europe'], 'wrong' : ['ic_united_states', 'ic_italy', 'ic_canada', 'ic_mexico', 'ic_poland', 'ic_russia', 'ic_spain', 'ic_sweden', 'ic_france']}\");");
                        db.execSQL("INSERT INTO Image VALUES('Le drapeau de la Pologne est', \"L'Europe\", 'Géographie', \"{'right' : ['ic_poland'], 'wrong' : ['ic_united_states', 'ic_italy', 'ic_canada', 'ic_europe', 'ic_mexico', 'ic_russia', 'ic_spain', 'ic_sweden', 'ic_france']}\");");
                        db.execSQL("INSERT INTO Image VALUES('Le drapeau de la Suède est', \"L'Europe\", 'Géographie', \"{'right' : ['ic_sweden'], 'wrong' : ['ic_united_states', 'ic_italy', 'ic_canada', 'ic_europe', 'ic_mexico', 'ic_poland', 'ic_russia', 'ic_spain', 'ic_france']}\");");
                        db.execSQL("INSERT INTO Image VALUES(\"Le drapeau de l'Espagne est\", \"L'Europe\", 'Géographie', \"{'right' : ['ic_spain'], 'wrong' : ['ic_united_states', 'ic_italy', 'ic_canada', 'ic_europe', 'ic_mexico', 'ic_poland', 'ic_russia', 'ic_sweden', 'ic_france']}\");");
                        db.execSQL("INSERT INTO Image VALUES(\"Ce pays fait partie de l'Union Européenne : \", \"L'Europe\", 'Géographie', \"{'right' : ['ic_france', 'ic_italy', 'ic_spain', 'ic_sweden', 'ic_poland'], 'wrong' : ['ic_united_states', 'ic_canada', 'ic_mexico', 'ic_russia']}\");");
                    }

                    // Le monde
                    {
                        db.execSQL("INSERT INTO Image VALUES('Le drapeau du Canada est', 'Le monde', 'Géographie', \"{'right' : ['ic_canada'], 'wrong' : ['ic_united_states', 'ic_italy', 'ic_europe', 'ic_mexico', 'ic_poland', 'ic_russia', 'ic_spain', 'ic_sweden', 'ic_france']}\");");
                        db.execSQL("INSERT INTO Image VALUES('Le drapeau de la Russie est', 'Le monde', 'Géographie', \"{'right' : ['ic_russia'], 'wrong' : ['ic_united_states', 'ic_italy', 'ic_canada', 'ic_europe', 'ic_mexico', 'ic_poland', 'ic_spain', 'ic_sweden', 'ic_france']}\");");
                        db.execSQL("INSERT INTO Image VALUES('Le drapeau des États-Unis est', 'Le monde', 'Géographie', \"{'right' : ['ic_united_states'], 'wrong' : ['ic_italy', 'ic_canada', 'ic_europe', 'ic_mexico', 'ic_poland', 'ic_russia', 'ic_spain', 'ic_sweden', 'ic_france']}\");");
                        db.execSQL("INSERT INTO Image VALUES('Le drapeau du Mexique est', 'Le monde', 'Géographie', \"{'right' : ['ic_mexico'], 'wrong' : ['ic_united_states', 'ic_italy', 'ic_canada', 'ic_europe', 'ic_poland', 'ic_russia', 'ic_spain', 'ic_sweden', 'ic_france']}\");");
                    }
                }

                // Histoire
                {
                    // La Préhistoire
                    {

                    }

                    // L'Antiquité
                    {

                    }

                    // Le Moyen-Âge
                    {

                    }

                    // Les temps modernes
                    {

                    }

                    // L'époque contemporaine
                    {

                    }
                }

            }
        }
    };
}
