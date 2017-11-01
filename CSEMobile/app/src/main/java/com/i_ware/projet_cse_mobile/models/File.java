package com.i_ware.projet_cse_mobile.models;

/**
 * Created by asus on 19/10/2017.
 */

public class File {
    private String ref;
    private String contentType;
    private Integer verf;
    private String urlPdf;

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public Integer getVerf() {
        return verf;
    }

    public void setVerf(Integer verf) {
        this.verf = verf;
    }

    public String getUrlPdf() {
        return urlPdf;
    }

    public void setUrlPdf(String urlPdf) {
        this.urlPdf = urlPdf;
    }
}
