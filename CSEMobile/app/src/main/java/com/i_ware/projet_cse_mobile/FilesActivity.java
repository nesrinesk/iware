package com.i_ware.projet_cse_mobile;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.i_ware.projet_cse_mobile.models.Alert;
import com.i_ware.projet_cse_mobile.models.Product;
import com.i_ware.projet_cse_mobile.service.FileCache;
import com.i_ware.projet_cse_mobile.utils.UrlParse;
import com.i_ware.projet_cse_mobile.volleyUtils.CustomVolleyRequestQueue;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class FilesActivity extends AppCompatActivity {
    private String TAG = FilesActivity.class.getSimpleName();
    private String path;
    ProjetCSEMobileApplication myvalue;

    private Button btn;
    private Button btnLogout;
    private ProgressDialog mProgressDialog;
    public static final int DIALOG_DOWNLOAD_PROGRESS = 0;
   FileCache fileCache;
    /** fichier validé**/
    int verf;
    static String pdfRef;
    /****/
    public static List<Alert> alertList = new ArrayList<Alert>();

   // public static ArrayList<Alert> alertList ;
    //private ArrayList<String> alertListString ;
    public static List<String> alertListString = new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_files);
         fileCache=new FileCache(FilesActivity.this);
        myvalue= (ProjetCSEMobileApplication) this.getApplication();
      /****/

        UrlParse.setPdfName(myvalue.getProductRef()+".pdf");
      /** Intent**/
        Intent intent = getIntent();
        String value = intent.getStringExtra("product");
        System.out.println(value);
        /**** start download *****/
        btn = (Button)findViewById(R.id.btnOpen);
        btnLogout = (Button)findViewById(R.id.btnLogout);
        btn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                if(verf != 0 ){
                    System.out.println("verf"+ verf);
                  //  setVerfPDF();
                    Intent intent = new Intent(FilesActivity.this, FormActivity.class);
                    startActivity(intent);
                 }
                 else {
                    Intent intent = new Intent(FilesActivity.this, ViewPdfActivity.class);
                    startActivity(intent);
                }
            }
        });

        btnLogout.setOnClickListener(new View.OnClickListener() {
         @Override
             public void onClick(View view) {
             myvalue.getEmployee().setLogoutDateD(Calendar.getInstance().getTime());
                getAlerts();
             }
        });

        //*** url
        String baseUrl = getString(R.string.baseUrl);
        path = baseUrl +"pdfChange/"+value+"/"+myvalue.getPosteRef();

        //********alert dialog ***********//
        final SweetAlertDialog pDialog = new SweetAlertDialog(this, SweetAlertDialog.PROGRESS_TYPE);
        pDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
        pDialog.setTitleText("Loading");
        pDialog.setCancelable(false);
        pDialog.show();
        //*** ws ***//

        RequestQueue queue =
                Volley.newRequestQueue(FilesActivity.this);

        StringRequest strRequest = new StringRequest(Request.Method.GET, path,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        System.out.println("heyyy repppppppppp "+response.toString());
                        pDialog.dismiss();
                        try {
                            JSONObject verify = new JSONObject(response);
                            verf= verify.getInt("verf");
                            pdfRef = verify.getString("ref");
                            Log.d(TAG,"value verf :"+ verf);
                        } catch (JSONException e) {
                            pDialog.hide();
                            e.printStackTrace();
                        }


                        // ListView on item selected listener.


                    }

                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        pDialog.hide();
                        Toast.makeText(FilesActivity.this.getApplicationContext(), error.toString(), Toast.LENGTH_SHORT).show();
                    }
                }) {

        };
        strRequest.setRetryPolicy(new DefaultRetryPolicy(
                7000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        queue.add(strRequest);

    }


    void setVerfPDF() {
        final SweetAlertDialog pDialog = new SweetAlertDialog(this, SweetAlertDialog.PROGRESS_TYPE);
        pDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
        pDialog.setTitleText("Loading");
        pDialog.setCancelable(false);
        pDialog.show();
/****/
        RequestQueue mQueueLogin;
        String baseUrl = getString(R.string.baseUrl);
        String path1 = baseUrl + "updateOpenedPdf/" + pdfRef;
       // pDialog.show();

        mQueueLogin = CustomVolleyRequestQueue.getInstance(FilesActivity.this)
                .getRequestQueue();

        //execution ws login
        RequestQueue queue = Volley.newRequestQueue(FilesActivity.this);

        StringRequest strRequest = new StringRequest(Request.Method.GET, path1,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        System.out.println("heyyy response " + response.toString());
                        pDialog.dismiss();
                        fileCache.clearFile(pdfRef+".pdf");

                        Intent intent = new Intent(FilesActivity.this, DownloadAndViewPdfActivity.class);
                        startActivity(intent);
                    }

                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        pDialog.dismiss();
                        Toast.makeText(FilesActivity.this.getApplicationContext(), error.toString(), Toast.LENGTH_SHORT).show();
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


    public void getAlerts(){

        //*** url
        String baseUrl = getString(R.string.baseUrl);
       String path2 = baseUrl +"allAlertsByDept/"+myvalue.getEmployee().getDepartmentName();

        //********alert dialog ***********//
        final SweetAlertDialog pDialog = new SweetAlertDialog(this, SweetAlertDialog.PROGRESS_TYPE);
        pDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
        pDialog.setTitleText("Loading");
        pDialog.setCancelable(false);
        pDialog.show();
        //*** ws ***//

        RequestQueue queue =
                Volley.newRequestQueue(FilesActivity.this);

        StringRequest strRequest = new StringRequest(Request.Method.GET, path2,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        System.out.println("heyyy repppppppppp "+response.toString());
                        pDialog.dismiss();

                        try {
                            JSONArray verify = new JSONArray(response);
                            for (int i = 0; i < verify.length(); i++) {
                                JSONObject jsonobject = verify.getJSONObject(i);
                                alertList.add(new Alert(jsonobject.getString("ref"), jsonobject.getString("nom")));

                            }
                            for( Alert a :alertList){
                                alertListString.add(a.getNom());
                            }

                            Intent intent = new Intent(FilesActivity.this, FormActivity.class);
                            startActivity(intent);
                        } catch (JSONException e) {
                            pDialog.hide();
                            e.printStackTrace();
                        }

                        // ListView on item selected listener.


                    }

                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        pDialog.hide();
                        Toast.makeText(FilesActivity.this.getApplicationContext(), error.toString(), Toast.LENGTH_SHORT).show();
                    }
                }) {

        };
        strRequest.setRetryPolicy(new DefaultRetryPolicy(
                7000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        queue.add(strRequest);

    }

}
