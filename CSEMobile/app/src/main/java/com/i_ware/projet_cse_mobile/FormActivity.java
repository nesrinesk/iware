package com.i_ware.projet_cse_mobile;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.i_ware.projet_cse_mobile.adapters.ProductAdapter;
import com.i_ware.projet_cse_mobile.models.Alert;
import com.i_ware.projet_cse_mobile.models.Product;
import com.i_ware.projet_cse_mobile.rows.Product_row;
import com.i_ware.projet_cse_mobile.utils.DateParse;
import com.i_ware.projet_cse_mobile.utils.ObjectToJson;
import com.i_ware.projet_cse_mobile.utils.UrlParse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class FormActivity extends AppCompatActivity {
    private String TAG = FormActivity.class.getSimpleName();

    EditText editTextQtity;
    TextView startDate;
    TextView endDate;
    TextView date;
    TextView refEmployee;
    TextView refProduct;
    Button submitBtn;

    /**/
    Spinner spinner_alert;
    String path;
    ProjetCSEMobileApplication myvalue;
    ArrayAdapter<String> dataAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);
        myvalue= (ProjetCSEMobileApplication) this.getApplication();
        /***/
        submitBtn = (Button) findViewById(R.id.btnSubmit);
        editTextQtity=(EditText) findViewById(R.id.editText);
      /*
        startDate = (TextView) findViewById(R.id.startDate);
        endDate = (TextView) findViewById(R.id.endDate);
        refEmployee = (TextView) findViewById(R.id.refEmployee);
        refProduct = (TextView) findViewById(R.id.refProduct);
        date = (TextView) findViewById(R.id.date);
        */

        //Create an ArrayAdapter using the string array and a default spinner layout
        spinner_alert = (Spinner) findViewById(R.id.spinner_alert);
        dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, FilesActivity.alertListString);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_alert.setAdapter(dataAdapter);

        /****/

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    addTask();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                finish();
                Intent intent = new Intent(Intent.ACTION_MAIN);
                intent.addCategory(Intent.CATEGORY_HOME);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });


    }

    void addTask() throws JSONException {
        //*** url
        String baseUrl = getString(R.string.baseUrl);
        String refAlert= FilesActivity.alertList.get(spinner_alert.getSelectedItemPosition()).getRef();

        path = baseUrl + "addTask/" + ObjectToJson.variablToJsonStringTask(myvalue.getPosteRef(),
                myvalue.getEmployee().getRef(),myvalue.getEmployee().getLoginDateD(),myvalue.getEmployee().getLogoutDateD()
                ,editTextQtity.getText(),myvalue.getProductRef(),refAlert);


        // public static String variablToJsonStringTask(String refPost, String refEmpl,
       // Date dateLogin, Date dateLogout,
        //float quantity, String product,
          //      String refAlert)
        //********alert dialog ***********//
        final SweetAlertDialog pDialog = new SweetAlertDialog(this, SweetAlertDialog.PROGRESS_TYPE);
        pDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
        pDialog.setTitleText("Loading");
        pDialog.setCancelable(false);
        pDialog.show();
        //******//

        RequestQueue queue =
                Volley.newRequestQueue(FormActivity.this);

        StringRequest strRequest = new StringRequest(Request.Method.GET, path,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        System.out.println("heyyy repppppppppp " + response.toString());
                        pDialog.dismiss();
                        finish();
                    }

                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        pDialog.hide();
                        Toast.makeText(FormActivity.this.getApplicationContext(), error.toString(), Toast.LENGTH_SHORT).show();
                    }
                }) {

        };
        strRequest.setRetryPolicy(new DefaultRetryPolicy(
                7000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        queue.add(strRequest);


        /***** default ***/
    }



}
