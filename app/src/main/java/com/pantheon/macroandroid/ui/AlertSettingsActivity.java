package com.pantheon.macroandroid.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.pantheon.macroandroid.R;
import com.pantheon.macroandroid.adapter.AlertSettingsadapter;
import com.pantheon.macroandroid.adapter.SettinglistAdapter;
import com.pantheon.macroandroid.model.Region;

import java.util.ArrayList;
import java.util.List;

public class AlertSettingsActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private AlertSettingsadapter adapter;
    private List<Region> regionList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alert_settings);
        recyclerView = findViewById(R.id.recycler_view);
        regionList = new ArrayList<>();
        adapter = new AlertSettingsadapter(AlertSettingsActivity.this, regionList);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setTitle("Alert");
        prepareImages();
    }

    private void prepareImages() {

            Region a = new Region("All","gcjdgjcgdjgcjdgcjgdj");
            regionList.add(a);
            a = new Region("Daily Monitor","chjdhcjdhcjdhjchdjh");
            regionList.add(a);

            a = new Region(" Weekly Monitor","xjbjcbxjcbdbjhdbdbcbn");
            regionList.add(a);
            a = new Region("Datanotes","hxcjcjhdcjhdjchdjhc");
            regionList.add(a);

            a = new Region(" Chartbook","hdjhcjdhcjdhjchj");
            regionList.add(a);

            adapter.notifyDataSetChanged();
        }






    }

