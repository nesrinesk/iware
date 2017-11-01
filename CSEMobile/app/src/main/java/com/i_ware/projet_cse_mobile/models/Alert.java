package com.i_ware.projet_cse_mobile.models;

/**
 * Created by asus on 26/10/2017.
 */

public class Alert {
    private String ref;
    private String nom;
    private Department department;

    public Alert(String ref, String nom) {
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

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }
}
