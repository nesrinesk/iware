package com.i_ware.projet_cse_mobile.utils;

import android.text.Editable;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;

/**
 * Created by asus on 27/10/2017.
 */

public class ObjectToJson {
    public static String variablToJsonString(String refPost, String refEmpl, Date dateLogin) throws JSONException {
        JSONObject js =new JSONObject();
        js.put("refPost",refPost);
        js.put("refEmpl",refEmpl);

        js.put("dateLogin",DateParse.currentdateToString(dateLogin));
        return js.toString();
    }
    public static String variablToJsonStringTask(String refPost, String refEmpl,
                                                 Date dateLogin, Date dateLogout,
                                                 Editable quantity, String product,
                                                 String refAlert) throws JSONException {
        JSONObject js =new JSONObject();
        js.put("refPost",refPost);
        js.put("refEmpl",refEmpl);
        js.put("quantity",quantity);
        js.put("product",product);
        js.put("refAlert",refAlert);
        js.put("dateLogin",DateParse.currentdateToString(dateLogin));
        js.put("dateLogout",DateParse.currentdateToString(dateLogout));
        return js.toString();
    }
}
