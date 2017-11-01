package com.i_ware.projet_cse_mobile.utils;

/**
 * Created by asus on 20/10/2017.
 */

public class UrlParse {
    private static final String url="http://192.168.43.45:8086/Controller/download/";
  //  private static final String url="http://192.168.1.2:8086/Controller/download/";
    private static String pdfName;
    private static String dirName;
    public static String parseUrlPdf(String ref){
        return url+ref;
    }

    public static String getPdfName() {
        return pdfName;
    }

    public static void setPdfName(String pdfName) {
        UrlParse.pdfName = pdfName;
    }

    public static String getUrl() {
        return url;
    }

    public static String getDirName() {
        return dirName;
    }

    public static void setDirName(String dirName) {
        UrlParse.dirName = dirName;
    }
}
