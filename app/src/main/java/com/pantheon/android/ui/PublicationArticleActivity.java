/**
 @Module Name/Class		:	PublicationArticleActivity
 @Author Name			:	Sombir Singh Bisht
 @Date					:	Sept 7, 2015
 @Purpose				:	This page/functionality is used to provide Publication Document.
 */

package com.pantheon.android.ui;

import android.app.Activity;
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

import com.pantheon.android.utility.DataBaseHelper;
import com.pantheon.android.R;
import com.pantheon.android.utility.SharedPreferenceManager;


public class PublicationArticleActivity extends AppCompatActivity {
    private RelativeLayout viewfullreport;
    private ImageView ivOffline;
    private TextView tvArticleHeading, tvArticleAuthor, tvArticleBrief, tvCategory, tvDownloadInfo, tvDownloadStatus;
    private Button btnRequest;
    private DataBaseHelper mydatabase;
    private String useremail;
    private String noAccess = "No Access";
    private LinearLayout llOfflineStatus;
    private String article_downloadtoken, article_heading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publication_article);

        viewfullreport=(RelativeLayout)findViewById(R.id.viewfullreport);
        ivOffline=(ImageView)findViewById(R.id.ivOffline);
        tvArticleHeading=(TextView)findViewById(R.id.tvArticleHeading);
        tvArticleAuthor=(TextView)findViewById(R.id.tvArticleAuthor);
        tvArticleBrief=(TextView)findViewById(R.id.tvArticleBrief);
        tvCategory=(TextView)findViewById(R.id.tvCategory);
        tvDownloadInfo=(TextView)findViewById(R.id.tvDownloadInfo);
        tvDownloadStatus=(TextView)findViewById(R.id.tvDownloadStatus);
        btnRequest=(Button)findViewById(R.id.btnRequest);
        llOfflineStatus=(LinearLayout)findViewById(R.id.llOfflineStatus);

        Intent intent = getIntent();
        final String article_heading = intent.getStringExtra("article_heading");
        String article_author = intent.getStringExtra("article_author");
        String article_preview = intent.getStringExtra("article_preview");
        String article_category = intent.getStringExtra("article_category");
        final String article_downloadinfo = intent.getStringExtra("article_downloadinfo");
        final String article_downloadstatus = intent.getStringExtra("article_downloadstatus");
        System.out.println("Status check......"+article_downloadstatus);
        final String article_downloadtoken = intent.getStringExtra("article_downloadtoken");

        System.out.println("Article Download Token..."+article_downloadtoken);
        System.out.println("Article Download Status..." + article_downloadstatus);


        getSupportActionBar().setTitle(article_category);
        tvArticleHeading.setText(Html.fromHtml(article_heading));
        System.out.println("1...." + article_heading);
        tvArticleAuthor.setText(article_author);
        tvArticleBrief.setText(Html.fromHtml(article_preview));
        tvCategory.setText(article_category);
        tvDownloadInfo.setText(article_downloadinfo);
        tvDownloadStatus.setText(article_downloadstatus);


//        addtoOffline.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                if (access != article_downloadtoken) {
//
//                    mydatabase = new DataBaseHelper(PublicationArticleActivity.this);
//
//                    //if (mydatabase.unique(article_heading).equalsIgnoreCase(article_heading))
//                    if (mydatabase.unique(article_heading) > 0) {
//
//                        Toast.makeText(PublicationArticleActivity.this, getResources().getString(R.string.article_alreadysaved), Toast.LENGTH_LONG).show();
//                    } else {
//
//                        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(PublicationArticleActivity.this);
//                        alertDialogBuilder.setMessage(getResources().getString(R.string.to_save_article));
//                        alertDialogBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
//
//                            @Override
//                            public void onClick(DialogInterface arg0, int arg1) {
//
//                                Log.v("data inserting", "data");
//                                String heading = tvArticleHeading.getText().toString();
//                                System.out.println("2...." + article_heading);
//                                String articleauthor = tvArticleAuthor.getText().toString();
//                                String articlepreview = tvArticleBrief.getText().toString();
//                                String articlecategory = tvCategory.getText().toString();
//                                String articledownloadinfo = tvDownloadInfo.getText().toString();
//                                String articledownloadstatus = tvDownloadStatus.getText().toString();
//
//                                //String article_heading = Html.fromHtml(heading).toString();
//
//                                mydatabase = new DataBaseHelper(PublicationArticleActivity.this);
//
//                                String article_heading = Html.fromHtml(heading).toString();
//
//                                mydatabase.insertRecord(article_heading, articleauthor, articlepreview, articlecategory, articledownloadinfo, articledownloadstatus);
//
//                                Toast.makeText(PublicationArticleActivity.this, getResources().getString(R.string.article_sucessfully_saved), Toast.LENGTH_LONG).show();
//
//                                System.out.println("Download url...." + article_heading);
//                            }
//                        });
//                        alertDialogBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
//
//                            @Override
//                            public void onClick(DialogInterface arg0, int arg1) {
//
//                            }
//                        });
//                        AlertDialog alertDialog = alertDialogBuilder.create();
//                        alertDialog.show();
//                    }
//                } else {
//                    llOfflineStatus.setVisibility(View.VISIBLE);
//                    Toast.makeText(PublicationArticleActivity.this, getResources().getString(R.string.request_complimentary_trial), Toast.LENGTH_LONG).show();
//                }
//            }
//        });

        viewfullreport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(article_downloadtoken.equals(noAccess)) {

                    System.out.println("Click download token...." + article_downloadtoken);

                    llOfflineStatus.setVisibility(View.VISIBLE);
                    btnRequest.setFocusable(true);
                    btnRequest.setFocusableInTouchMode(true);
                    btnRequest.requestFocus();
                    Toast.makeText(PublicationArticleActivity.this, getResources().getString(R.string.request_complimentary_trial), Toast.LENGTH_LONG).show();

                }else {

                    Intent intent = new Intent(PublicationArticleActivity.this, PdfViewerActivity.class);
                    intent.putExtra("articlepdf_token", article_downloadtoken);
                    intent.putExtra("publication_heading",article_heading);
                    intent.putExtra("download_info",article_downloadinfo);
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
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_publication_article, menu);
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
        if(id == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

}
