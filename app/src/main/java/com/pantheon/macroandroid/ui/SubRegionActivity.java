package com.pantheon.macroandroid.ui;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NavUtils;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.pantheon.macroandroid.R;
import com.pantheon.macroandroid.adapter.SubRegionAdapter;
import com.pantheon.macroandroid.model.Region;

import java.util.ArrayList;
import java.util.List;

public class SubRegionActivity extends AppCompatActivity {
    private final String US = "1174";
    private final String EURO = "1172";
    private final String LATAM = "1170";
    private final String UK = "1561";
    private final String ASIA_MONITOR = "1863";
    private final String EMERGE_ASIA = "3604";
    private final String CHINA = "1863";
    private final String GLOBAL = "3379";

    private final String Monitor = "3482";
    private final String datanotes = "3488";
    private final String weekly = "237";
    private final String chartbook = "3483";

    public String CATID, mQuery;
    TextView viewall;
    private RecyclerView recyclerView;
    private SubRegionAdapter adapter;
    private MenuItem mSearch;
    private List<Region> regionList;
    private String filterText;
    private LinearLayout llUsMonitor, llEurozone, llLatinAmerica, llUk, llAsiaMonitor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_region);
        CATID = getIntent().getStringExtra("catID");
        mQuery = getIntent().getStringExtra("query");
        filterText = getIntent().getStringExtra("filter");
        viewall = findViewById(R.id.viewalltxt);
        viewall.setPaintFlags(viewall.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        recyclerView = findViewById(R.id.recycler_view);
        regionList = new ArrayList<>();
        adapter = new SubRegionAdapter(SubRegionActivity.this, regionList);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setTitle("Select Category");
        prepareImages();
    }

    private void prepareImages() {
        int[] covers = new int[]{
                R.drawable.dailymonitor,
                R.drawable.datanotes,
                R.drawable.chartbook,
                R.drawable.weekly,
        };
        if (CATID.equalsIgnoreCase(US)) {
            Region a = new Region("U.S. Monitor", covers[0], US + ";" + Monitor);
            regionList.add(a);

            a = new Region("U.S. Weekly Monitor", covers[3], US + ";" + weekly);
            regionList.add(a);

            a = new Region("U.S. Datanotes", covers[1], US + ";" + datanotes);
            regionList.add(a);

            a = new Region("U.S. Chartbook", covers[2], US + ";" + chartbook);
            regionList.add(a);

            adapter.notifyDataSetChanged();

        }

        if (CATID.equalsIgnoreCase(EURO)) {
            Region a = new Region("Eurozone Monitor", covers[0], EURO + ";" + Monitor);
            regionList.add(a);

            a = new Region("Eurozone Weekly Monitor", covers[3], EURO + ";" + weekly);
            regionList.add(a);

            a = new Region("Eurozone Datanotes", covers[1], EURO + ";" + datanotes);
            regionList.add(a);

            a = new Region("Eurozone Chartbook", covers[2], EURO + ";" + chartbook);
            regionList.add(a);

            adapter.notifyDataSetChanged();
        }
        if (CATID.equalsIgnoreCase(LATAM)) {
            Region a = new Region("LatAm Monitor", covers[0], LATAM + ";" + Monitor);
            regionList.add(a);

            a = new Region("LatAm Datanotes", covers[1], LATAM + ";" + datanotes);
            regionList.add(a);

            a = new Region("LatAm Chartbook", covers[2], LATAM + ";" + chartbook);
            regionList.add(a);
            adapter.notifyDataSetChanged();
        }
        if (CATID.equalsIgnoreCase(UK)) {
            Region a = new Region("U.K. Monitor", covers[0], UK + ";" + Monitor);
            regionList.add(a);

            a = new Region("U.K. Weekly Monitor", covers[3], UK + ";" + weekly);
            regionList.add(a);

            a = new Region("U.K. Datanotes", covers[1], UK + ";" + datanotes);
            regionList.add(a);

            a = new Region("U.K. Chartbook", covers[2], UK + ";" + chartbook);
            regionList.add(a);
            adapter.notifyDataSetChanged();

        }
        if (CATID.equalsIgnoreCase(CHINA)) {
            Region a = new Region("China+  Monitor", covers[0], CHINA + ";" + Monitor);
            regionList.add(a);

            a = new Region("China+  Weekly Monitor", covers[3], CHINA + ";" + weekly);
            regionList.add(a);

            a = new Region("China+  Datanotes", covers[1], CHINA + ";" + datanotes);
            regionList.add(a);

            a = new Region("China+  Chartbook", covers[2], CHINA + ";" + chartbook);
            regionList.add(a);

            adapter.notifyDataSetChanged();

        }
        if (CATID.equalsIgnoreCase(EMERGE_ASIA)) {
            Region a = new Region("Emerging Asia Monitor", covers[0], EMERGE_ASIA + ";" + Monitor);
            regionList.add(a);

            a = new Region("Emerging Asia Weekly Monitor", covers[3], EMERGE_ASIA + ";" + weekly);
            regionList.add(a);

            a = new Region("Emerging Asia Datanotes", covers[1], EMERGE_ASIA + ";" + datanotes);
            regionList.add(a);

            a = new Region("Emerging Asia Chartbook", covers[2], EMERGE_ASIA + ";" + chartbook);
            regionList.add(a);

            adapter.notifyDataSetChanged();

        }
        if (CATID.equalsIgnoreCase(GLOBAL)) {
            Region a = new Region("Global Monitor", covers[0], GLOBAL + ";" + Monitor);
            regionList.add(a);

            a = new Region("Global  Datanotes", covers[1], GLOBAL + ";" + datanotes);
            regionList.add(a);

            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_publications_list, menu);
        mSearch = menu.findItem(R.id.search);
        return true;
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
        if (id == R.id.search) {

            Intent intent = new Intent(SubRegionActivity.this, SearchActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}