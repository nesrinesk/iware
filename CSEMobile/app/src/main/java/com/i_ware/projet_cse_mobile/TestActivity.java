package com.i_ware.projet_cse_mobile;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class TestActivity extends AppCompatActivity {
    ProjetCSEMobileApplication myvalue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        myvalue= (ProjetCSEMobileApplication) this.getApplication();

        SharedPreferences prefs= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        String  refPost=prefs.getString("postRef", "").toString();
        // Toast.makeText(getApplicationContext(), refPost, Toast.LENGTH_LONG).show();
        myvalue.setPosteRef(refPost);
        if(myvalue.getPosteRef() == ""){
            Intent intent = new Intent(TestActivity.this,LaunchActivity.class);
            startActivity(intent);
        }
        else {
            Intent intent = new Intent(TestActivity.this,LoginActivity.class);
            startActivity(intent);
        }
    }
}
