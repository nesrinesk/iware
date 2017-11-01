package com.i_ware.projet_cse_mobile.models;

import java.util.Date;

/**
 * Created by asus on 19/10/2017.
 */

public class Employee {

    private String ref;
    private String nom;
    private String prenom;
    private String departmentName;
    private String loginDate;
    private Date loginDateD;
    private Date logoutDateD;
    private String logoutDate;
//*** à enlever **//
  //  private Department department;


    public Employee(String ref, String nom, String prenom, String departmentName) {
        this.ref = ref;
        this.nom = nom;
        this.prenom = prenom;
        this.departmentName = departmentName;
    }

    public Employee(String ref, String nom, String prenom, String departmentName, String loginDate, String logoutDate) {
        this.ref = ref;
        this.nom = nom;
        this.prenom = prenom;
        this.departmentName = departmentName;
        this.loginDate = loginDate;
        this.logoutDate = logoutDate;
    }

    public Employee(String ref, String nom, String prenom, String departmentName, Date loginDateD) {
        this.ref = ref;
        this.nom = nom;
        this.prenom = prenom;
        this.departmentName = departmentName;
        this.loginDateD = loginDateD;
    }

    public Employee(String ref, String nom, String prenom, String departmentName, String loginDate) {
        this.ref = ref;
        this.nom = nom;
        this.prenom = prenom;
        this.departmentName = departmentName;
        this.loginDate = loginDate;

    }

    public Date getLogoutDateD() {
        return logoutDateD;
    }

    public void setLogoutDateD(Date logoutDateD) {
        this.logoutDateD = logoutDateD;
    }

    public Date getLoginDateD() {
        return loginDateD;
    }

    public void setLoginDateD(Date loginDateD) {
        this.loginDateD = loginDateD;
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

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }



    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getLoginDate() {
        return loginDate;
    }

    public void setLoginDate(String loginDate) {
        this.loginDate = loginDate;
    }

    public String getLogoutDate() {
        return logoutDate;
    }

    public void setLogoutDate(String logoutDate) {
        this.logoutDate = logoutDate;
    }
}
