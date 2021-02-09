package com.pantheon.android.ui;

import android.Manifest;
import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Looper;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.pantheon.android.R;
import com.pantheon.android.utility.DataBaseHelper;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class PdfViewerActivity extends AppCompatActivity {
    private WebView wvPdfShow;
    private String download_token, publication_heading, download_info;
    private DataBaseHelper mydatabase;
    int responseCode;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdf_viewer);

        Intent intent = getIntent();
        download_token = intent.getStringExtra("articlepdf_token");
        publication_heading = intent.getStringExtra("publication_heading");
        download_info = intent.getStringExtra("download_info");
        Log.e("done", "onCreate: " + publication_heading + "\n" + download_token);

        wvPdfShow = (WebView) findViewById(R.id.wvPdfShow);
        wvPdfShow.getSettings().setJavaScriptEnabled(true);
        wvPdfShow.setWebViewClient(new Callback());
        isStoragePermissionGranted();

        String urlEncoded = null;

        try {
            urlEncoded = URLEncoder.encode(download_token, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        wvPdfShow.loadUrl("https://docs.google.com/viewer?url=" + urlEncoded);
        wvPdfShow.getSettings().setBuiltInZoomControls(true);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public void downloadurl(String url, String article_heading) {

        File direct = new File(Environment.getExternalStorageDirectory() + "/" + getString(R.string.app_name));

        if (!direct.exists()) {
            direct.mkdirs();
        }
        if (isStoragePermissionGranted() == true) {
            Uri uri = Uri.parse(url);

            DownloadManager downloadManager = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
            DownloadManager.Request request = new DownloadManager.Request(uri);
            request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI |
                    DownloadManager.Request.NETWORK_MOBILE);

            request.setTitle(article_heading);
            request.setDescription(article_heading);

            request.allowScanningByMediaScanner();
            request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
            String path = direct + "/" + article_heading;
            request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, path);
            request.setMimeType("*/*");
            registerReceiver(onComplete, new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE));
            downloadManager.enqueue(request);
        } else {
            isStoragePermissionGranted();
        }
    }

    BroadcastReceiver onComplete = new BroadcastReceiver() {
        public void onReceive(Context ctxt, Intent intent) {
            // Do Something
            Toast.makeText(PdfViewerActivity.this, getResources().getString(R.string.article_sucessfully_saved), Toast.LENGTH_LONG).show();
        }
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_pdf_viewer, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        if (id == android.R.id.home) {
            finish();
        }
        if (id == R.id.download) {
            mydatabase = new DataBaseHelper(PdfViewerActivity.this);

            //if (mydatabase.unique(article_heading).equalsIgnoreCase(article_heading))
            if (mydatabase.unique(publication_heading) > 0) {

                Toast.makeText(PdfViewerActivity.this, getResources().getString(R.string.article_alreadysaved), Toast.LENGTH_LONG).show();
            } else {
                isServerReachable(download_token, download_info, publication_heading);
            }
        }

        return super.onOptionsItemSelected(item);
    }

    private class Callback extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            return (false);
        }
    }

    public boolean isStoragePermissionGranted() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED) {
                Log.v("permission", "Permission is granted");
                return true;
            } else {

                Log.v("permission", "Permission is revoked");
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                return false;
            }
        } else { //permission is automatically granted on sdk<23 upon installation
            Log.v("permission", "Permission is granted");
            return true;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            // Log.v("permission", "Permission: " + permissions[0] + "was " + grantResults[0]);
            //resume tasks needing this permission
        }
    }


    public void isServerReachable(final String download_token, final String download_info, final String publication_heading) {

        Thread thread = new Thread() {
            public void run() {
                Looper.prepare();

                try {
                    URL url1 = new URL(download_token);
                    HttpURLConnection huc = (HttpURLConnection) url1.openConnection();
                    huc.setRequestMethod("HEAD");

                    responseCode = huc.getResponseCode();
                    Log.e("done", "isServerReachable1: " + responseCode);
                    if (responseCode == 200) {
                        mydatabase.insertRecord(publication_heading, download_info);
                        downloadurl(download_token, download_info);
                    } else {
                    }


                } catch (Exception e) {
                    e.printStackTrace();
                }
                checkstatus(responseCode);
                Looper.loop();
            }
        };
        thread.start();

    }

    public void checkstatus(int responseCode) {
        Log.e("done", "isServerReachable2:check " + responseCode);

        if (responseCode == 200) {
            //  downloadurl(download_token, download_info);

        } else {
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(PdfViewerActivity.this);
            alertDialogBuilder.setMessage(getString(R.string.filenotfound));
            alertDialogBuilder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface arg0, int arg1) {
                    finish();
                }
            });
            AlertDialog alertDialog = alertDialogBuilder.create();
            alertDialog.show();

        }
    }

}

