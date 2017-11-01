package com.i_ware.projet_cse_mobile;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.widget.TextView;

import com.i_ware.projet_cse_mobile.service.FileCache;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class ViewPdfActivity extends Activity {
    TextView tv_loading;
    String dest_file_path = "test.pdf";
    int downloadedSize = 0, totalsize;
    String download_file_url = "http://192.168.43.45:8086/Controller/download/ref001.pdf";
    float per = 0;
    FileCache fileCache;
    ProjetCSEMobileApplication myvalue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    myvalue= (ProjetCSEMobileApplication) this.getApplication();

    tv_loading = new TextView(this);
    setContentView(tv_loading);
        tv_loading.setGravity(Gravity.CENTER);
        tv_loading.setTypeface(null, Typeface.BOLD);
    fileCache=new FileCache(ViewPdfActivity.this);
    downloadAndOpenPDF();
}

    void downloadAndOpenPDF() {
        new Thread(new Runnable() {
            public void run() {
                //Uri path = Uri.fromFile(downloadFile(download_file_url));
                //Uri path = Uri.fromFile(fileCache.getFile(myvalue.getProductRef()+".pdf"));
                Uri path = Uri.fromFile(fileCache.getFile("ref001.pdf"));

                try {
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setDataAndType(path, "application/pdf");
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                    finish();
                } catch (ActivityNotFoundException e) {
                    tv_loading
                            .setError("PDF Reader application is not installed in your device");
                }
            }
        }).start();

    }

    void setTextError(final String message, final int color) {
        runOnUiThread(new Runnable() {
            public void run() {
                tv_loading.setTextColor(color);
                tv_loading.setText(message);
            }
        });

    }

    void setText(final String txt) {
        runOnUiThread(new Runnable() {
            public void run() {
                tv_loading.setText(txt);
            }
        });

    }
}
