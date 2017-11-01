package com.i_ware.projet_cse_mobile.models;

/**
 * Created by asus on 19/10/2017.
 */

public class Product {
    private String ref;
    private String nom;
    private SubFamily subFamily;

    public Product(String ref, String nom) {
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

    public SubFamily getSubFamily() {
        return subFamily;
    }

    public void setSubFamily(SubFamily subFamily) {
        this.subFamily = subFamily;
    }
}
