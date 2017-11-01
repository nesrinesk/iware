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
import com.i_ware.projet_cse_mobile.adapters.SubFamilyAdapter;
import com.i_ware.projet_cse_mobile.models.Family;
import com.i_ware.projet_cse_mobile.models.SubFamily;
import com.i_ware.projet_cse_mobile.rows.Family_row;
import com.i_ware.projet_cse_mobile.rows.SubFamily_row;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class SubFamilyMainActivity extends Activity {
    private String TAG = SubFamilyMainActivity.class.getSimpleName();
    public List<SubFamily> familyList = new ArrayList<SubFamily>();
    ListView listView;
    private String path;
    ProjetCSEMobileApplication myvalue;
    ArrayList<SubFamily_row> dataModels;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_family_main);
        //******
        myvalue= (ProjetCSEMobileApplication) this.getApplication();
        listView=(ListView)findViewById(R.id.list);
        listView.setFocusable(false);

        //***** permission internet*****//
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(new String[]{Manifest.permission.INTERNET}, 1);
        }

        //******
        Intent intent = getIntent();
        String value = intent.getStringExtra("family");
        Log.d(TAG,"value intent :"+ value);
        System.out.println(value);

        //*** url
        String baseUrl = getString(R.string.baseUrl);
        path = baseUrl +"allSfamiliesByFamily/"+ value;

        //********alert dialog ***********//
        final SweetAlertDialog pDialog = new SweetAlertDialog(this, SweetAlertDialog.PROGRESS_TYPE);
        pDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
        pDialog.setTitleText("Loading");
        pDialog.setCancelable(false);
        pDialog.show();

        //******//
        RequestQueue queue =
                Volley.newRequestQueue(SubFamilyMainActivity.this);

        StringRequest strRequest = new StringRequest(Request.Method.GET, path,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        System.out.println("heyyy repppppppppp "+response.toString());

                        try {
                            JSONArray verify = new JSONArray(response);
                            for (int i = 0; i < verify.length(); i++) {
                                JSONObject jsonobject = verify.getJSONObject(i);
                                familyList.add(new SubFamily(jsonobject.getString("ref"),jsonobject.getString("nom")));
                            }

                        } catch (JSONException e) {
                            pDialog.hide();
                            e.printStackTrace();
                        }

                        dataModels= new ArrayList<>();
                        for(int i =0 ; i< familyList.size() ; i++){
                            dataModels.add(new SubFamily_row(
                                    familyList.get(i).getRef(),familyList.get(i).getNom()));


                        }
                        final SubFamilyAdapter dataAdapter = new SubFamilyAdapter(getApplicationContext(), R.layout.activity_sub_family_main, dataModels);
                        // Assign adapter to ListView

                        pDialog.hide();

                        // ListView on item selected listener.

                        listView.setAdapter(dataAdapter);


                        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            public void onItemClick(AdapterView<?> parent, View view,
                                                    int position, long id) {


                                for (int i=0;i<familyList.size();i++){
                                    if (position==i){
                                        Intent intent = new Intent(SubFamilyMainActivity.this, ProductsActivity.class);
                                        intent.putExtra("subFamily",String.valueOf(familyList.get(i).getRef()));
                                        myvalue.setSubFamily(String.valueOf(familyList.get(i).getRef()));
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
                        Toast.makeText(SubFamilyMainActivity.this.getApplicationContext(), error.toString(), Toast.LENGTH_SHORT).show();
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
