package com.i_ware.projet_cse_mobile.models;

/**
 * Created by asus on 19/10/2017.
 */

public class SubFamily {
    private String ref;
    private String nom;
    private Family family;

    public SubFamily(String ref, String nom) {
        this.ref = ref;
        this.nom = nom;
    }

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Family getFamily() {
        return family;
    }

    public void setFamily(Family family) {
        this.family = family;
    }
}
