package com.pantheon.android.ui;

import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Environment;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;
import com.pantheon.android.R;
import com.pantheon.android.utility.DataBaseHelper;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class PdfViewerActivity extends AppCompatActivity {
    private WebView wvPdfShow;
    private String download_token, publication_heading, download_info;
    private DataBaseHelper mydatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdf_viewer);

        Intent intent = getIntent();
        download_token = intent.getStringExtra("articlepdf_token");
        publication_heading = intent.getStringExtra("publication_heading");
        download_info = intent.getStringExtra("download_info");
        System.out.println("download token check...." + download_token);

        wvPdfShow = (WebView) findViewById(R.id.wvPdfShow);
        wvPdfShow.getSettings().setJavaScriptEnabled(true);
        wvPdfShow.setWebViewClient(new Callback());

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

        DownloadManager mgr = (DownloadManager) this.getSystemService(Context.DOWNLOAD_SERVICE);
        Uri downloadUri = Uri.parse(url);
        DownloadManager.Request request = new DownloadManager.Request(downloadUri);

        System.out.println("Request...." + request);

        request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI | DownloadManager.Request.NETWORK_MOBILE)
                .setDescription("Downloading pdf " + article_heading)
                .setDestinationInExternalPublicDir("/" + getString(R.string.app_name), article_heading + ".pdf");

        registerReceiver(onComplete, new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE));

        mgr.enqueue(request);
    }

    BroadcastReceiver onComplete=new BroadcastReceiver() {
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
                mydatabase.insertRecord(publication_heading, download_info);

                downloadurl(download_token, download_info);
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
}
