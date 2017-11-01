package com.i_ware.projet_cse_mobile;

import android.app.Application;

import com.i_ware.projet_cse_mobile.models.Alert;
import com.i_ware.projet_cse_mobile.models.Employee;

import java.util.ArrayList;

/**
 * Created by asus on 17/10/2017.
 */

public class ProjetCSEMobileApplication extends Application {

    private Employee employee;
    private String department;
    private String family;
    private String subFamily;
    private String product;
    private String productRef;
    //private final String posteRef ="post003";
    private String posteRef;
    private ArrayList<Alert> alertList ;

    public ArrayList<Alert> getAlertList() {
        return alertList;
    }

    public String getPosteRef() {
        return posteRef;
    }

    public void setPosteRef(String posteRef) {
        this.posteRef = posteRef;
    }
/* public void setPosteRef(String posteRef) {
        this.posteRef = posteRef;
    }
    */

    public void setAlertList(ArrayList<Alert> alertList) {
        this.alertList = alertList;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getFamily() {
        return family;
    }

    public void setFamily(String family) {
        this.family = family;
    }

    public String getSubFamily() {
        return subFamily;
    }

    public void setSubFamily(String subFamily) {
        this.subFamily = subFamily;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getProductRef() {
        return productRef;
    }

    public void setProductRef(String productRef) {
        this.productRef = productRef;
    }
}
