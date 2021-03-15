package com.pantheon.android.ui;

import android.Manifest;
import android.app.DownloadManager;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Looper;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.github.barteksc.pdfviewer.PDFView;
import com.pantheon.android.R;
import com.pantheon.android.utility.DataBaseHelper;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class PdfViewerActivity extends AppCompatActivity {
    private WebView wvPdfShow, wvPdfShow1;
    private String download_token, publication_heading, download_info, article_heading, article_author, article_preview, article_category, article_downloadinfo, article_downloadstatus,
            article_downloadtoken;
    private DataBaseHelper mydatabase;
    int responseCode;
    PDFView pdfView;
    private static ProgressDialog progressDialog;
    int flag = 0;

    @RequiresApi(api = Build.VERSION_CODES.P)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdf_viewer);

        Intent intent = getIntent();
        download_token = intent.getStringExtra("articlepdf_token");
        publication_heading = intent.getStringExtra("publication_heading");
        download_info = intent.getStringExtra("download_info");

        article_heading = intent.getStringExtra("article_heading");
        article_author = intent.getStringExtra("article_author");
        article_preview = intent.getStringExtra("article_preview");
        article_category = intent.getStringExtra("article_category");
        article_downloadinfo = intent.getStringExtra("article_downloadinfo");
        article_downloadstatus = intent.getStringExtra("article_downloadstatus");
        article_downloadtoken = intent.getStringExtra("article_downloadtoken");

        ;


        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Loading");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setProgress(0);
        progressDialog.show();

        isStoragePermissionGranted();

        String urlEncoded = null;

        try {
            urlEncoded = URLEncoder.encode(download_token, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();

        }


        wvPdfShow1 = (WebView) findViewById(R.id.wvPdfShow1);
        wvPdfShow1.clearCache(true);
        wvPdfShow1.setWebViewClient(new AppWebViewClients());
        WebSettings settings = wvPdfShow1.getSettings();
        wvPdfShow1.setAlwaysDrawnWithCacheEnabled(true);
        settings.setJavaScriptEnabled(true);
        wvPdfShow1.loadUrl("https://docs.google.com/viewer?embedded=false&url=" + urlEncoded);
        settings.setLoadWithOverviewMode(true);
        wvPdfShow1.getSettings().setDomStorageEnabled(true);
        Log.e("123", "onCreate: "+wvPdfShow1 );


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }


    public void downloadurl(String url, String article_heading, String articleinfo) {

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
            request.setDescription(articleinfo);

            request.allowScanningByMediaScanner();
            request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
            String path = direct + "/" + article_heading;
            Log.e("abc", "downloadurl: " + direct + "/" + article_heading);
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
            Toast.makeText(PdfViewerActivity.this, getResources().getString(R.string.article_sucessfully_saved), Toast.LENGTH_LONG).show();
        }
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_pdf_viewer, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

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
                //  Log.e("done", "onOptionsItemSelected: "+download_token );
                //   downloadurl(download_token, download_info);
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
                        downloadurl(download_token, download_info, publication_heading);
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

    @Override
    public void onBackPressed() {
        super.onBackPressed();
      //  Log.e("123", "onBackPressed: ");
        Intent intent = new Intent(PdfViewerActivity.this, PublicationArticleActivity.class);
        intent.putExtra("articlepdf_token", article_downloadtoken);
        intent.putExtra("publication_heading", article_heading);
        intent.putExtra("download_info", article_downloadinfo);
        intent.putExtra("article_author", article_author);
        intent.putExtra("article_category", article_category);
        intent.putExtra("article_preview", article_preview);
        intent.putExtra("article_downloadstatus", article_downloadstatus);
        finish();
    }

    public class AppWebViewClients extends WebViewClient {
        public boolean shouldOverrideUrlLoading(WebView view, String url) {

            view.loadUrl(url);
            return true;

        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
            progressDialog.show();
            flag = 1;

        }

        @Override
        public void onPageFinished(WebView view, String url) {
            // TODO Auto-generated method stub
            super.onPageFinished(view, url);
            if (flag == 1) {
                Log.e("123", "onPageFinished: finish 1" + flag);

                wvPdfShow1.loadUrl("javascript:(function() { " +
                        "document.querySelector('[role=\"toolbar\"]').remove();})()");

                progressDialog.dismiss();

            } else {
                wvPdfShow1.loadUrl(url);
            }


        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && this.wvPdfShow1.canGoBack()) {
            this.wvPdfShow1.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }


}

