package com.i_ware.projet_cse_mobile;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.i_ware.projet_cse_mobile.models.Alert;
import com.i_ware.projet_cse_mobile.models.Employee;
import com.i_ware.projet_cse_mobile.utils.DateParse;
import com.i_ware.projet_cse_mobile.volleyUtils.CustomJSONObjectRequest;
import com.i_ware.projet_cse_mobile.volleyUtils.CustomVolleyRequestQueue;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class LoginActivity extends Activity {
    private String TAG = LoginActivity.class.getSimpleName();
    private RequestQueue mQueueLogin;
    public static final String REQUEST_TAG = "MainVolleyActivity";

    EditText ref;
    Button btn_login;
    private String path1;
    private String path2;
    String url1;
    String url2;

    ProjetCSEMobileApplication myvalue;
    TextView errorText;
    /**********/
    public static ArrayList<Alert> alertList ;
    //private ArrayList<String> alertListString ;
    public static List<String> alertListString = new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
            requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
            requestPermissions(new String[]{Manifest.permission.INTERNET}, 1);
            requestPermissions(new String[]{Manifest.permission.READ_PHONE_STATE}, 1);
            requestPermissions(new String[]{Manifest.permission.ACCESS_NETWORK_STATE}, 1);
        }
        myvalue= (ProjetCSEMobileApplication) this.getApplication();

        //


        //

        ref =  (EditText) findViewById(R.id.editText);
        ref.getText().toString();
        btn_login = (Button) findViewById(R.id.btnLogin);
        //errorText= (TextView) findViewById(R.id.error);

        String baseUrl =  getString(R.string.baseUrl);
        path1 = baseUrl+"ouvrier/";
        //path1 = "http://date.jsontest.com"; //baseUrl+"ouvrier/";

        final SweetAlertDialog pDialog = new SweetAlertDialog(LoginActivity.this, SweetAlertDialog.PROGRESS_TYPE);
        pDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
        pDialog.setTitleText("Loading");
        pDialog.setCancelable(false);
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                pDialog.show();
                url1 = path1+ref.getText();

                mQueueLogin = CustomVolleyRequestQueue.getInstance(LoginActivity.this)
                        .getRequestQueue();

                //execution ws login
                RequestQueue queue =
                        Volley.newRequestQueue(LoginActivity.this);

                StringRequest strRequest = new StringRequest(Request.Method.GET, url1,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                System.out.println("heyyy response "+response.toString());
                                pDialog.dismiss();
                                try {
                                    JSONObject object = new JSONObject(response);

                                    Employee employee = new Employee(object.getString("ref"),
                                            object.getString("nom"), object.getString("prenom")
                                            ,object.getJSONObject("departement_ouvrier").getString("nom"),
                                            Calendar.getInstance().getTime()
                                            );

                                    myvalue.setEmployee(employee);
                                    myvalue.setDepartment(employee.getDepartmentName());
                                    Log.d(TAG,"employee name :"+ employee.getNom()+ "depart name : " + employee.getDepartmentName() );
                                    Log.d(TAG,"employee name :"+ myvalue.getEmployee().getNom());

                                    if(employee.getNom()!= null){
                                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                        startActivity(intent);
                                        finish();
                                    }
                                    else {
                                     //   pDialog.hide();
                                        Toast.makeText(LoginActivity.this.getApplicationContext(), "Ref error !!! ", Toast.LENGTH_SHORT).show();

                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }


                            }

                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                pDialog.dismiss();
                                Toast.makeText(LoginActivity.this.getApplicationContext(), error.toString(), Toast.LENGTH_SHORT).show();
                              //  errorText.setText("Please re-enter your referene");

                            }
                        }) {

                };

                strRequest.setRetryPolicy(new DefaultRetryPolicy(
                        7000,
                        DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                        DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
                pDialog.dismiss();
                queue.add(strRequest);



            }
        });

    }

}
