/**
 * @Module Name/Class		:	PublicationArticleActivity
 * @Author Name            :	Sombir Singh Bisht
 * @Date :	Sept 7, 2015
 * @Purpose :	This page/functionality is used to provide Publication Document.
 */
package com.pantheon.macroandroid.ui;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.pantheon.macroandroid.R;
import com.pantheon.macroandroid.utility.DataBaseHelper;
import com.pantheon.macroandroid.utility.SharedPreferenceManager;

public class PublicationArticleActivity extends AppCompatActivity {
    private RelativeLayout viewfullreport;
    private ImageView ivOffline;
    private TextView tvArticleHeading, tvArticleAuthor, tvArticleBrief, tvCategory, tvDownloadInfo, tvDownloadStatus;
    private Button btnRequest;
    private DataBaseHelper mydatabase;
    private String useremail;
    private String noAccess = "ACCESS DENIED";
    private LinearLayout llOfflineStatus;
    private String article_downloadtoken, article_heading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publication_article);

        viewfullreport = (RelativeLayout) findViewById(R.id.viewfullreport);
        ivOffline = (ImageView) findViewById(R.id.ivOffline);
        tvArticleHeading = (TextView) findViewById(R.id.tvArticleHeading);
        tvArticleAuthor = (TextView) findViewById(R.id.tvArticleAuthor);
        tvArticleBrief = (TextView) findViewById(R.id.tvArticleBrief);
        tvCategory = (TextView) findViewById(R.id.tvCategory);
        tvDownloadInfo = (TextView) findViewById(R.id.tvDownloadInfo);
        tvDownloadStatus = (TextView) findViewById(R.id.tvDownloadStatus);
        btnRequest = (Button) findViewById(R.id.btnRequest);
        llOfflineStatus = (LinearLayout) findViewById(R.id.llOfflineStatus);

        Intent intent = getIntent();
        final String article_heading = intent.getStringExtra("article_heading");
        final String article_author = intent.getStringExtra("article_author");
        final String article_preview = intent.getStringExtra("article_preview");
        final String article_category = intent.getStringExtra("article_category");
        final String article_downloadinfo = intent.getStringExtra("article_downloadinfo");
        final String article_downloadstatus = intent.getStringExtra("article_downloadstatus");
        final String article_downloadtoken = intent.getStringExtra("article_downloadtoken");

        getSupportActionBar().setTitle(article_category);
        tvArticleHeading.setText(Html.fromHtml(article_heading));
        tvArticleAuthor.setText(article_author);
        tvArticleBrief.setText(Html.fromHtml(article_preview));
        tvCategory.setText(article_category);
        tvDownloadInfo.setText(article_downloadinfo);
        tvDownloadStatus.setText(article_downloadstatus);
        tvArticleAuthor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent contactus=new Intent(PublicationArticleActivity.this,ContactusActivity.class);
                startActivity(contactus);
            }
        });

        viewfullreport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (article_downloadstatus.equalsIgnoreCase(noAccess)) {

                    System.out.println("Click download token1...." + article_downloadstatus);

                    llOfflineStatus.setVisibility(View.VISIBLE);
                    btnRequest.setFocusable(true);
                    btnRequest.setFocusableInTouchMode(true);
                    btnRequest.requestFocus();
                    Toast.makeText(PublicationArticleActivity.this, getResources().getString(R.string.request_complimentary_trial), Toast.LENGTH_LONG).show();

                } else {

                    Intent intent = new Intent(PublicationArticleActivity.this, PdfViewerActivity.class);
                    intent.putExtra("articlepdf_token", article_downloadtoken);
                    intent.putExtra("publication_heading", article_heading);
                    intent.putExtra("download_info", article_downloadinfo);
                    intent.putExtra("article_author", article_author);
                    intent.putExtra("article_category", article_category);
                    intent.putExtra("article_preview", article_preview);
                    intent.putExtra("article_downloadstatus", article_downloadstatus);
                    startActivity(intent);
                }
            }
        });

        SharedPreferenceManager preferenceManager = SharedPreferenceManager.getInstance();
        useremail = preferenceManager.getUserEmail(this);

        btnRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_EMAIL, new String[]{getResources().getString(R.string.receiver_email)});
                intent.putExtra(Intent.EXTRA_SUBJECT, getResources().getString(R.string.sender_subject));
                intent.putExtra(Intent.EXTRA_TEXT, getResources().getString(R.string.sender_email) + useremail);
                startActivity(intent);
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_publication_article, menu);
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
        return super.onOptionsItemSelected(item);
    }

}
