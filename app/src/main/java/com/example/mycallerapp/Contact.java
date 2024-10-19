package com.example.mycallerapp;

public class Contact {
    int id;
    String nom;
    String numtel;

    public Contact(int id, String nom, String numtel) {
        this.id = id;
        this.nom = nom;
        this.numtel = numtel;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getNumtel() {
        return numtel;
    }

    public void setNumtel(String numtel) {
        this.numtel = numtel;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", numtel='" + numtel + '\'' +
                '}';
    }
}
