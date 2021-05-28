package com.example.loustics.models;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.PrimaryKey;
import androidx.room.Entity;

@Entity public class User{
    @PrimaryKey @NonNull
    @ColumnInfo(name = "nom")
    private String nom;

    @ColumnInfo(name = "prenom")
    private String prenom;


    /* Getters and Setters */

    public String getNom() {return this.nom;}
    public String getPrenom() {return this.prenom;}

    public void setNom(String nom) {this.nom = nom;}
    public void setPrenom(String prenom) {this.prenom = prenom;}


}
