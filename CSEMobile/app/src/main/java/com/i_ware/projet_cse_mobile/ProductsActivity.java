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
import com.i_ware.projet_cse_mobile.adapters.ProductAdapter;
import com.i_ware.projet_cse_mobile.models.Alert;
import com.i_ware.projet_cse_mobile.models.Family;
import com.i_ware.projet_cse_mobile.models.Product;
import com.i_ware.projet_cse_mobile.rows.Family_row;
import com.i_ware.projet_cse_mobile.rows.Product_row;
import com.i_ware.projet_cse_mobile.utils.UrlParse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class ProductsActivity extends Activity {
    private String TAG = ProductsActivity.class.getSimpleName();
    public List<Product> productList = new ArrayList<Product>();
    ListView listView;
    private String path;
    private String path2;
    ProjetCSEMobileApplication myvalue;
    ArrayList<Product_row> dataModels;
    /**********/
    public static ArrayList<Alert> alertList ;
    //private ArrayList<String> alertListString ;
    public static List<String> alertListString = new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products);

        myvalue = (ProjetCSEMobileApplication) this.getApplication();
        listView = (ListView) findViewById(R.id.list);
        listView.setFocusable(false);

        //***** permission internet*****//
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(new String[]{Manifest.permission.INTERNET}, 1);
        }
        //******
        Intent intent = getIntent();
        String value = intent.getStringExtra("subFamily");
        Log.d(TAG, "value intent :" + value);

        System.out.println(value);

        //*** url
        String baseUrl = getString(R.string.baseUrl);
        path = baseUrl + "allProductBySubFamily/" + value;

        //********alert dialog ***********//
        final SweetAlertDialog pDialog = new SweetAlertDialog(this, SweetAlertDialog.PROGRESS_TYPE);
        pDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
        pDialog.setTitleText("Loading");
        pDialog.setCancelable(false);
        pDialog.show();
        //******//

        RequestQueue queue =
                Volley.newRequestQueue(ProductsActivity.this);

        StringRequest strRequest = new StringRequest(Request.Method.GET, path,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        System.out.println("heyyy repppppppppp " + response.toString());
                        pDialog.dismiss();
                        try {
                            JSONArray verify = new JSONArray(response);
                            for (int i = 0; i < verify.length(); i++) {
                                JSONObject jsonobject = verify.getJSONObject(i);
                                productList.add(new Product(jsonobject.getString("ref"), jsonobject.getString("nom")));
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        dataModels = new ArrayList<>();
                        for (int i = 0; i < productList.size(); i++) {
                            dataModels.add(new Product_row(
                                    productList.get(i).getRef(), productList.get(i).getNom()));


                        }
                        final ProductAdapter dataAdapter = new ProductAdapter(getApplicationContext(), R.layout.activity_products, dataModels);
                        // Assign adapter to ListView


                        // ListView on item selected listener.
                        listView.setAdapter(dataAdapter);

                        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            public void onItemClick(AdapterView<?> parent, View view,
                                                    int position, long id) {


                                for (int i = 0; i < productList.size(); i++) {
                                    if (position == i) {
                                        Intent intent = new Intent(ProductsActivity.this, FilesActivity.class);
                                        intent.putExtra("product", String.valueOf(productList.get(i).getRef())); // ref du produit
                                        myvalue.setProduct(String.valueOf(productList.get(i).getNom()));
                                        myvalue.setProductRef(String.valueOf(productList.get(i).getRef()));
                                        UrlParse.setDirName("dept:" + myvalue.getEmployee().getDepartmentName() + "/family:" + myvalue.getFamily() + "/subFamily:" + myvalue.getSubFamily() + "/product:" + String.valueOf(productList.get(i).getRef()));
                                        System.out.println(UrlParse.getDirName());
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
                        Toast.makeText(ProductsActivity.this.getApplicationContext(), error.toString(), Toast.LENGTH_SHORT).show();
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
