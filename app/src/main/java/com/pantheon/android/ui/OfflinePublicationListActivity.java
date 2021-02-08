/**
 @Module Name/Class		:	OfflinePublicationListActivity
 @Author Name			:	Sombir Singh Bisht
 @Date					:	July 15, 2015
 @Purpose				:	This page/functionality is used to provide Offline Publication List.
 */
package com.pantheon.android.ui;

import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.ListView;
import com.pantheon.android.utility.DataBaseHelper;
import com.pantheon.android.R;
import com.pantheon.android.adapter.OfflinePublicationAdapter;
import com.pantheon.android.bean.PublicationData;
import java.io.File;
import java.util.ArrayList;

/**
 * Created by sombirbisht on 17/9/15.
 */
public class OfflinePublicationListActivity extends AppCompatActivity {
    private ListView lvPublication;
    private OfflinePublicationAdapter adapter;
    private ArrayList <PublicationData> publicationList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publications_list);

        lvPublication=(ListView)findViewById(R.id.lvPublications);
        publicationList=new ArrayList<PublicationData>();

        getOfflineList();
        initAdapter();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if(id == R.id.home){
            NavUtils.navigateUpFromSameTask(this);
        }
        if(id == android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    public void getOfflineList(){

        publicationList.clear();
        DataBaseHelper mydatabase = new DataBaseHelper(this);
        mydatabase.getAllRecords(publicationList);
        System.out.println("Publication List Size"+publicationList.size());

        if(publicationList.size() == 0){

            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(OfflinePublicationListActivity.this);
            alertDialogBuilder.setMessage(getString(R.string.articlelist_empty));
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

    public void deletePublication(int id){

        DataBaseHelper mydatabase = new DataBaseHelper(this);
        int i=mydatabase.deleteRow(id);
        System.out.println("Deleted item :::;" + i);
        getOfflineList();
        adapter.notifyDataSetChanged();
    }

    public void initAdapter(){

        adapter = new OfflinePublicationAdapter(this, publicationList, lvPublication);
        lvPublication.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    public void downloadOfflineurl(String info_download){

        File file = new File(Environment.getExternalStorageDirectory() +"/"+ getString(R.string.app_name), info_download+".pdf");

        System.out.println("Downloaded File........." + file);

        Intent target = new Intent(Intent.ACTION_VIEW);
        target.setDataAndType(Uri.fromFile(file),"application/pdf");
        target.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);

        Intent intent = Intent.createChooser(target, "Open File");
        try {
            startActivity(intent);
        } catch (ActivityNotFoundException e) {
            // Instruct the user to install a PDF reader here, or something
        }
    }
}
