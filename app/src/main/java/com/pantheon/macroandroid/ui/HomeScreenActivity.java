package com.pantheon.macroandroid.ui;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import com.pantheon.macroandroid.R;
import com.pantheon.macroandroid.fragments.SelectRegionFragment;
import com.pantheon.macroandroid.utility.SharedPreferenceManager;

public class HomeScreenActivity extends AppCompatActivity {
    private LinearLayout llContactUs, llLogout, llOfflineMode, Settingslayout;
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
        Settingslayout = (LinearLayout) findViewById(R.id.Settingslayout);
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
                intent.putExtra("offline", true);
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
        Settingslayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeScreenActivity.this, SettingsActivity.class);
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
        drawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        drawerToggle.onConfigurationChanged(newConfig);
    }

    private void addFirstFragment() {
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        SelectRegionFragment selectRegionFragment = new SelectRegionFragment();
        fragmentTransaction.add(R.id.content_frame, selectRegionFragment);
        fragmentTransaction.commit();
    }

    public boolean onCreateOptionsMenu(Menu menu) {
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

        switch (item.getItemId()) {
            case R.id.search:
                Intent intent = new Intent(HomeScreenActivity.this, SearchActivity.class);
                startActivity(intent);
                return true;
            case R.id.bell:

                Intent intent1 = new Intent(HomeScreenActivity.this, AlertSettingsActivity.class);
                startActivity(intent1);

        }
        if (drawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
