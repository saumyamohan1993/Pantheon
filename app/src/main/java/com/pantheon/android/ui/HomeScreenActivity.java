/**
 @Page/Module Name/Class	:	HomeScreenActivity
 @Author Name		:	Mr. Sombir Singh Bisht
 @Date				:	Aug 25,  2015
 @Purpose			:	This page/functionality is used to provide Home Screen.
 */
package com.pantheon.android.ui;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.pantheon.android.R;
import com.pantheon.android.fragments.SelectRegionFragment;
import com.pantheon.android.utility.SharedPreferenceManager;

/**
 * Created by sombirbisht on 25/8/15.
 */
public class HomeScreenActivity extends AppCompatActivity {
    private LinearLayout llContactUs, llLogout, llOfflineMode;
    private FrameLayout frameLayout;
    private ActionBarDrawerToggle drawerToggle;
    private DrawerLayout drawerLayout;
    private LinearLayout left_drawer;
    private View mSearchView;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

        frameLayout = (FrameLayout) findViewById(R.id.content_frame);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        llContactUs = (LinearLayout) findViewById(R.id.llContactUs);
        llLogout = (LinearLayout) findViewById(R.id.llLogout);
        left_drawer = (LinearLayout) findViewById(R.id.left_drawer);
        llOfflineMode = (LinearLayout) findViewById(R.id.llOfflineMode);

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.draweropen, R.string.drawerclose) {
            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }

            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                super.onDrawerSlide(drawerView, slideOffset);
                frameLayout.setTranslationX(slideOffset * drawerView.getWidth());
                drawerLayout.bringChildToFront(drawerView);
                drawerLayout.requestLayout();
            }
        };

        drawerToggle.setDrawerIndicatorEnabled(true);
        drawerLayout.setDrawerListener(drawerToggle);

        llOfflineMode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeScreenActivity.this, OfflinePublicationListActivity.class);
                intent.putExtra("offline",true);
                startActivity(intent);
            }
        });

        llContactUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeScreenActivity.this, ContactusActivity.class);
                startActivity(intent);
            }
        });

        llLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferenceManager preferenceManager = SharedPreferenceManager.getInstance();
                preferenceManager.clearCredentials(HomeScreenActivity.this);
                preferenceManager.setLoginCheck(HomeScreenActivity.this, false);
                Intent intent = new Intent(HomeScreenActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

        addFirstFragment();
    }

        @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        drawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Pass any configuration change to the drawer toggles
        drawerToggle.onConfigurationChanged(newConfig);
    }

    /**
     @Function Name		:	addFirstFragment



     @Author Name		:	Mr. Sombir Singh Bisht
     @Date				:	Aug, 25 2015
     @Param			    :	N/A
     @Purpose			:	To add fragment to the Home Screen Activity.
     */
    private void addFirstFragment() {
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        SelectRegionFragment selectRegionFragment = new SelectRegionFragment();
        fragmentTransaction.add(R.id.content_frame, selectRegionFragment);
        fragmentTransaction.commit();
    }


    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home_screen, menu);
        mSearchView = menu.findItem(R.id.search).getActionView();

        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        return super.onPrepareOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Pass the event to ActionBarDrawerToggle, if it returns
        // true, then it has handled the app icon touch event

        switch (item.getItemId()) {
            case R.id.search:
                //do your work here
                Intent intent = new Intent(HomeScreenActivity.this, SearchActivity.class);
                startActivity(intent);
                return true;
        }
        if (drawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        // Handle your other action bar items...

        return super.onOptionsItemSelected(item);
    }






}
