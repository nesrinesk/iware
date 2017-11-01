package com.i_ware.projet_cse_mobile;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.i_ware.projet_cse_mobile.adapters.FamilyAdapter;
import com.i_ware.projet_cse_mobile.models.Family;
import com.i_ware.projet_cse_mobile.rows.Family_row;
import com.i_ware.projet_cse_mobile.utils.ObjectToJson;
import com.i_ware.projet_cse_mobile.volleyUtils.CustomJSONObjectRequest;
import com.i_ware.projet_cse_mobile.volleyUtils.CustomVolleyRequestQueue;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.pedant.SweetAlert.SweetAlertDialog;

import static com.i_ware.projet_cse_mobile.LoginActivity.REQUEST_TAG;

public class MainActivity extends Activity {
    private String TAG = MainActivity.class.getSimpleName();
    public List<Family> familyList = new ArrayList<Family>();
    ListView listView;
    private String path;
    ProjetCSEMobileApplication myvalue;
    ArrayList<Family_row> dataModels;
    private RequestQueue mQueueLogin;
    CustomJSONObjectRequest jsonRequestLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myvalue = (ProjetCSEMobileApplication) this.getApplication();
        listView = (ListView) findViewById(R.id.list);
        listView.setFocusable(false);

        //***** permission internet*****//
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(new String[]{Manifest.permission.INTERNET}, 1);
        }
        try {
            addAffectation();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        // listFamily();
    }
    void listFamily(){
        //*** url
        String baseUrl = getString(R.string.baseUrl);
        path = baseUrl + "allfamiliesByDeprt/" + myvalue.getEmployee().getDepartmentName();

        //********alert dialog ***********//
        final SweetAlertDialog pDialog = new SweetAlertDialog(this, SweetAlertDialog.PROGRESS_TYPE);
        pDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
        pDialog.setTitleText("Loading");
        pDialog.setCancelable(false);
        pDialog.show();

        //******//
//


        RequestQueue queue =
                Volley.newRequestQueue(MainActivity.this);

        StringRequest strRequest = new StringRequest(Request.Method.GET, path,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        System.out.println("heyyy repppppppppp "+response.toString());

                        try {
                            JSONArray verify = new JSONArray(response);
                            for (int i = 0; i < verify.length(); i++) {
                                JSONObject jsonobject = verify.getJSONObject(i);
                                familyList.add(new Family(jsonobject.getString("ref"),jsonobject.getString("nom")));
                            }

                        } catch (JSONException e) {
                            pDialog.hide();
                            e.printStackTrace();
                        }

                        dataModels= new ArrayList<>();
                        for(int i =0 ; i< familyList.size() ; i++){
                                    dataModels.add(new Family_row(
                                    familyList.get(i).getRef(),familyList.get(i).getNom()));


                        }
                        final FamilyAdapter dataAdapter = new FamilyAdapter(getApplicationContext(), R.layout.activity_main, dataModels);
                        // Assign adapter to ListView

                        pDialog.hide();

                        // ListView on item selected listener.

                        listView.setAdapter(dataAdapter);

                        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            public void onItemClick(AdapterView<?> parent, View view,
                                                    int position, long id) {


                                for (int i=0;i<familyList.size();i++){
                                    if (position==i){
                                        Intent intent = new Intent(MainActivity.this, SubFamilyMainActivity.class);
                                        intent.putExtra("family",String.valueOf(familyList.get(i).getRef()));
                                        myvalue.setFamily(String.valueOf(familyList.get(i).getRef()));
                                        startActivity(intent);
                                    }
                                }

                            }

                        });
                    }

                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        pDialog.hide();
                        Toast.makeText(MainActivity.this.getApplicationContext(), error.toString(), Toast.LENGTH_SHORT).show();
                    }
                }) {

        };
        strRequest.setRetryPolicy(new DefaultRetryPolicy(
                7000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        queue.add(strRequest);

    }

    void addAffectation() throws JSONException {


        String baseUrl = getString(R.string.baseUrl);
        String path2 = baseUrl + "saveAffectation/"+ ObjectToJson.variablToJsonString(myvalue.getPosteRef(),myvalue.getEmployee().getRef(),myvalue.getEmployee().getLoginDateD());
        //********alert dialog ***********//
        final SweetAlertDialog pDialog = new SweetAlertDialog(this, SweetAlertDialog.PROGRESS_TYPE);
        pDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
        pDialog.setTitleText("Loading");
        pDialog.setCancelable(false);
        pDialog.show();

        //******//
//


        RequestQueue queue =
                Volley.newRequestQueue(MainActivity.this);

        StringRequest strRequest = new StringRequest(Request.Method.GET, path2,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        System.out.println("heyyy repppppppppp "+response.toString());
                        listFamily();
                        pDialog.dismiss();
                             //
                        // listFamily();
                    }

                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        pDialog.hide();
                        Toast.makeText(MainActivity.this.getApplicationContext(), error.toString(), Toast.LENGTH_SHORT).show();
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
