/**
 @Module Name/Class		:	PublicationsListActivity
 @Author Name			:	Sombir Singh Bisht
 @Date					:	Sept 4, 2015
 @Purpose				:	This page/functionality is used to provide Publication List.
 */

package com.pantheon.android.ui;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NavUtils;

import com.pantheon.android.R;
import com.pantheon.android.adapter.PublicationAdapter;
import com.pantheon.android.bean.BaseBean;
import com.pantheon.android.bean.Login;
import com.pantheon.android.bean.PublicationData;
import com.pantheon.android.bean.PublicationRegion;
import com.pantheon.android.http.BaseListener;
import com.pantheon.android.http.HttpConnectionUtil;
import com.pantheon.android.http.HttpConstant;
import com.pantheon.android.http.WebserviceType;
import com.pantheon.android.utility.SharedPreferenceManager;
import com.google.gson.Gson;
import java.util.ArrayList;

public class PublicationsListActivity extends AppCompatActivity implements BaseListener {
    private ListView lvPublication;
    private PublicationAdapter adapter;
    private ArrayList <PublicationData> publicationList;

    private final String APPTOKEN="J50pjO0d6rH3wzY3";
    public final boolean MODPUBLIST=true;
    private final String SOURCE="Android";
    private String UNAME=null;
    private String UID=null;
    private String UTOKEN=null;
    public String CATID, mQuery;
    private final String DATE= null;
    private final String SRCH= null;
    private final String TAGS= null;
    private final String CUSTOM1= null;

    public final String US = "1174";
    public final String EURO = "1172";
    public final String LATAM = "1170";
    public final String UK = "1561";
    private final String ASIA_MONITOR="18631";
    private final String EMERGE_ASIA="3604";
    private final String CHINA="1863";
    private final String GLOBAL="3379";

    private String search;
    private MenuItem mSearch;
    private String filterText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publications_list);

        SharedPreferenceManager preferenceManager = SharedPreferenceManager.getInstance();
        UNAME = preferenceManager.getUserName(this);
        UID = preferenceManager.getUserId(this);
        UTOKEN = preferenceManager.getUserToken(this);

        CATID=getIntent().getStringExtra("catID");

        mQuery=getIntent().getStringExtra("query");

        filterText=getIntent().getStringExtra("filter");


        try{
            if(CATID.equals(US)){
                getSupportActionBar().setTitle("U.S. Publications");
            } else if(CATID.equals(EURO)){
                getSupportActionBar().setTitle("EuroZone Publications");
            }else if(CATID.equals(LATAM)){
                getSupportActionBar().setTitle("Latin America Publications");
            }else if(CATID.equals(UK)){
                getSupportActionBar().setTitle("U.K. Publications");
            }
            else if(CATID.equals(ASIA_MONITOR)){
                getSupportActionBar().setTitle("Asia Monitor Publications");
            }
            else if(CATID.equals(EMERGE_ASIA)){
                getSupportActionBar().setTitle("Emerging Asia");
            }
            else if(CATID.equals(CHINA)){
                getSupportActionBar().setTitle("China+");
            }
            else if(CATID.equals(GLOBAL)){
                getSupportActionBar().setTitle("Global");
            }
        }catch (NullPointerException e){
        }


        lvPublication=(ListView)findViewById(R.id.lvPublications);

        lvPublication.setTextFilterEnabled(true);

        lvPublication.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {

                System.out.println(arg2 + " --postion");
            }
        });

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getPublicationList();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_publications_list, menu);
        mSearch = menu.findItem(R.id.search);

        return true;
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
        if (id == R.id.search) {

            Intent intent = new Intent(PublicationsListActivity.this, SearchActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     @Function Name		:	getPublicationList
     @Author Name		:	Mr. Sombir Singh Bisht
     @Date				:	Sept, 04 2015
     @Param			    :	NA
     @Purpose			:	To see publication list of selected category.
     */
    private void getPublicationList(){

        PublicationRegion USbean = new PublicationRegion(HttpConstant.USMONITOR_URL);
        Log.e("login", "publication url: "+HttpConstant.USMONITOR_URL );

        USbean.setAppToken(APPTOKEN);
        USbean.setModPubList(MODPUBLIST);
        USbean.setSource(SOURCE);
        USbean.setUName(UNAME);
        USbean.setUId(UID);
        USbean.setUToken(UTOKEN);
        if(CATID!=null) {
            USbean.setCatID(CATID);
        }
        USbean.setDate(DATE);
        System.out.println("mQuery"+mQuery);
        if(mQuery!=null) {
            USbean.setSrch(mQuery);
        }
        if(SRCH!=null){
            USbean.setSrch(search);
        }
        USbean.setTags(TAGS);
        USbean.setCustom1(CUSTOM1);
        USbean.setProgressEnable(true);
//        Log.e("response", "AppToken "+USbean.getAppToken());
//        Log.e("response", "source "+USbean.getSource());
//        Log.e("response", "UNAME "+USbean.getUName());
//        Log.e("response", "UID "+USbean.getUId());
//        Log.e("response", "UTOKEN "+USbean.getUToken());
//        Log.e("response", "catID "+USbean.getCatID());
//        Log.e("response", "ModPubList "+MODPUBLIST);
        HttpConnectionUtil.callWebService(USbean, this, WebserviceType.USMONITOR,onWebServiceCompleteListener);
    }

    BaseListener.OnWebServiceCompleteListener onWebServiceCompleteListener=new BaseListener.OnWebServiceCompleteListener() {
        @Override
        public void onWebServiceComplete(Object baseObject) {

            refreshAdapter((BaseBean) baseObject);
        }
    };

    private void refreshAdapter(BaseBean baseObject){
      //  Login loginBean=(Login)baseObject;

        PublicationRegion USbean=(PublicationRegion)baseObject;
        publicationList=((PublicationRegion) baseObject).getPublicationDataArrayList();

        //Log.e("login1", "publication: "+USbean.result );


       // Log.e("on responselogin :", new Gson().toJson(USbean.toString())+"");
//        Log.e("login3", "publicat: "+((PublicationRegion) baseObject).URL );


        if (publicationList!=null) {
            if (USbean.result == true) {

                adapter = new PublicationAdapter(this, publicationList);
                lvPublication.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }
        }
        else{
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(PublicationsListActivity.this);
            alertDialogBuilder.setMessage("No publication found based on search criteria.");
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
