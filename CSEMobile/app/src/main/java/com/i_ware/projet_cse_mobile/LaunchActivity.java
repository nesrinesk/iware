package com.i_ware.projet_cse_mobile;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LaunchActivity extends Activity {
    Button btn;
    Button btnlgn;
    EditText editText;
    TextView textView;

    ProjetCSEMobileApplication myvalue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch);
        myvalue= (ProjetCSEMobileApplication) this.getApplication();
        btnlgn = (Button) findViewById(R.id.btnlogin);
        final Button storeinformation = (Button) findViewById(R.id.storeinformation);
        Button showinformation = (Button) findViewById(R.id.showinformation);
       // storeinformation.setVisibility(View.INVISIBLE);
        textView = (TextView) findViewById(R.id.txtPrefs);

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.storeinformation:
                        Intent intent = new Intent(LaunchActivity.this,MyPreferencesActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.showinformation:
                      //  storeinformation.setVisibility(View.VISIBLE);
                        displaySharedPreferences();
                        break;
                    default:
                        break;
                }
            }
        };
        storeinformation.setOnClickListener(listener);
        showinformation.setOnClickListener(listener);

        /*******/

            btnlgn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(LaunchActivity.this,LoginActivity.class);
                    startActivity(intent);
                }
            });
           //


    }


    private void displaySharedPreferences() {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(LaunchActivity.this);
        String postRef = prefs.getString("postRef", "");

        StringBuilder builder = new StringBuilder();
        builder.append("Post Ref: " + postRef + "\n");

        myvalue.setPosteRef(postRef);

        textView.setText(builder.toString());

    }

}
