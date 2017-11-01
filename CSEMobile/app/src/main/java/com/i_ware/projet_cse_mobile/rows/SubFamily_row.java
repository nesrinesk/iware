package com.i_ware.projet_cse_mobile.rows;

import android.graphics.drawable.Drawable;

/**
 * Created by asus on 19/10/2017.
 */

public class SubFamily_row {
    private Drawable color;
    private String ref;
    private String name;

    public SubFamily_row(Drawable color, String ref, String name) {
        this.color = color;
        this.ref = ref;
        this.name = name;
    }

    public SubFamily_row(String ref, String name) {
        this.ref = ref;
        this.name = name;
    }

    public Drawable getColor() {
        return color;
    }

    public void setColor(Drawable color) {
        this.color = color;
    }

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
