package com.pantheon.macroandroid.ui;

import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NavUtils;

import com.pantheon.macroandroid.R;
import com.pantheon.macroandroid.adapter.OfflinePublicationAdapter;
import com.pantheon.macroandroid.bean.PublicationData;
import com.pantheon.macroandroid.utility.DataBaseHelper;

import java.io.File;
import java.util.ArrayList;

public class OfflinePublicationListActivity extends AppCompatActivity {
    private ListView lvPublication;
    private OfflinePublicationAdapter adapter;
    private ArrayList<PublicationData> publicationList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publications_list);

        lvPublication = (ListView) findViewById(R.id.lvPublications);
        publicationList = new ArrayList<PublicationData>();

        getOfflineList();
        initAdapter();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.home) {
            NavUtils.navigateUpFromSameTask(this);
        }
        if (id == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    public void getOfflineList() {

        publicationList.clear();
        DataBaseHelper mydatabase = new DataBaseHelper(this);
        mydatabase.getAllRecords(publicationList);
        System.out.println("Publication List Size" + publicationList.size());

        if (publicationList.size() == 0) {

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

    public void deletePublication(int id) {

        DataBaseHelper mydatabase = new DataBaseHelper(this);
        int i = mydatabase.deleteRow(id);
        System.out.println("Deleted item :::;" + i);
        getOfflineList();
        adapter.notifyDataSetChanged();
    }

    public void initAdapter() {

        adapter = new OfflinePublicationAdapter(this, publicationList, lvPublication);
        lvPublication.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    public void downloadOfflineurl(String info_download) {

        //  File file = new File(Environment.getExternalStorageDirectory() + "/" + getString(R.string.app_name), info_download);
        File file = new File("/storage/emulated/0/Download" + Environment.getExternalStorageDirectory() + "/" + getString(R.string.app_name), info_download);

        if (file.exists()) {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            Uri uri = Uri.fromFile(file);
            intent.setDataAndType(uri, "application/pdf");
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

            try {
                startActivity(intent);
            } catch (ActivityNotFoundException e) {

                Toast.makeText(this, "No Application available to view pdf", Toast.LENGTH_LONG).show();
            }
        } else {
            Toast.makeText(this, "No Application available to view pdf", Toast.LENGTH_LONG).show();
        }
    }
}


