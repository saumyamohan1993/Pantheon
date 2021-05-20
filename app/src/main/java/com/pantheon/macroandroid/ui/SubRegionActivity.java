package com.pantheon.macroandroid.ui;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
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
    private final String chartbook = "3484";
    private final String Euroweekly = "238";
    private final String ukeekly = "281";
    private final String asiaweekly = "546";
    private final String chinaweekly = "478";
    private final String latamweekly = "624";

    public String CATID, mQuery;
    TextView viewall;
    RelativeLayout rlIanshe, rlclaus, rlsamuel, rlandres, rlfreya, rlmiguel;
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
        rlIanshe = findViewById(R.id.ianlayout);
        rlsamuel = findViewById(R.id.samuellayout);
        rlclaus = findViewById(R.id.clauslayout);
        rlandres = findViewById(R.id.andreslayout);
        rlfreya = findViewById(R.id.freyalayout);
        rlmiguel = findViewById(R.id.miguellayout);


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
            rlIanshe.setVisibility(View.VISIBLE);
            rlsamuel.setVisibility(View.GONE);
            rlandres.setVisibility(View.GONE);
            rlclaus.setVisibility(View.GONE);
            rlmiguel.setVisibility(View.GONE);
            rlfreya.setVisibility(View.GONE);
            rlIanshe.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ianShepherdson();
                }
            });
        }

        if (CATID.equalsIgnoreCase(EURO)) {
            Region a = new Region("Eurozone Monitor", covers[0], EURO + ";" + Monitor);
            regionList.add(a);

            a = new Region("Eurozone Weekly Monitor", covers[3], EURO + ";" + Euroweekly);
            regionList.add(a);

            a = new Region("Eurozone Datanotes", covers[1], EURO + ";" + datanotes);
            regionList.add(a);

            a = new Region("Eurozone Chartbook", covers[2], EURO + ";" + chartbook);
            regionList.add(a);

            adapter.notifyDataSetChanged();
            rlIanshe.setVisibility(View.GONE);
            rlsamuel.setVisibility(View.GONE);
            rlandres.setVisibility(View.GONE);
            rlclaus.setVisibility(View.VISIBLE);
            rlmiguel.setVisibility(View.GONE);
            rlfreya.setVisibility(View.GONE);
            rlclaus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clausVistesen();
                }
            });
        }
        if (CATID.equalsIgnoreCase(LATAM)) {
            Region a = new Region("LatAm Monitor", covers[0], LATAM + ";" + Monitor);
            regionList.add(a);
            a = new Region("LatAm Weekly Monitor", covers[3], LATAM + ";" + latamweekly);
            regionList.add(a);

            a = new Region("LatAm Datanotes", covers[1], LATAM + ";" + datanotes);
            regionList.add(a);

            a = new Region("LatAm Chartbook", covers[2], LATAM + ";" + chartbook);
            regionList.add(a);
            adapter.notifyDataSetChanged();
            rlIanshe.setVisibility(View.GONE);
            rlsamuel.setVisibility(View.GONE);
            rlandres.setVisibility(View.VISIBLE);
            rlclaus.setVisibility(View.GONE);
            rlmiguel.setVisibility(View.GONE);
            rlfreya.setVisibility(View.GONE);
            rlandres.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    andresAbadia();
                }
            });
        }
        if (CATID.equalsIgnoreCase(UK)) {
            Region a = new Region("U.K. Monitor", covers[0], UK + ";" + Monitor);
            regionList.add(a);

            a = new Region("U.K. Weekly Monitor", covers[3], UK + ";" + ukeekly);
            regionList.add(a);

            a = new Region("U.K. Datanotes", covers[1], UK + ";" + datanotes);
            regionList.add(a);

            a = new Region("U.K. Chartbook", covers[2], UK + ";" + chartbook);
            regionList.add(a);
            adapter.notifyDataSetChanged();
            rlIanshe.setVisibility(View.GONE);
            rlsamuel.setVisibility(View.VISIBLE);
            rlandres.setVisibility(View.GONE);
            rlclaus.setVisibility(View.GONE);
            rlmiguel.setVisibility(View.GONE);
            rlfreya.setVisibility(View.GONE);
            rlsamuel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    samuelTombs();
                }
            });

        }
        if (CATID.equalsIgnoreCase(CHINA)) {
            Region a = new Region("China+  Monitor", covers[0], CHINA + ";" + Monitor);
            regionList.add(a);

            a = new Region("China+  Weekly Monitor", covers[3], CHINA + ";" + chinaweekly);
            regionList.add(a);

            a = new Region("China+  Datanotes", covers[1], CHINA + ";" + datanotes);
            regionList.add(a);

            a = new Region("China+  Chartbook", covers[2], CHINA + ";" + chartbook);
            regionList.add(a);

            adapter.notifyDataSetChanged();
            rlIanshe.setVisibility(View.GONE);
            rlsamuel.setVisibility(View.GONE);
            rlandres.setVisibility(View.GONE);
            rlclaus.setVisibility(View.GONE);
            rlmiguel.setVisibility(View.GONE);
            rlfreya.setVisibility(View.VISIBLE);
            rlfreya.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    freyaBemish();
                }
            });
        }
        if (CATID.equalsIgnoreCase(EMERGE_ASIA)) {
            Region a = new Region("Emerging Asia Monitor", covers[0], EMERGE_ASIA + ";" + Monitor);
            regionList.add(a);

            a = new Region("Emerging Asia Weekly Monitor", covers[3], EMERGE_ASIA + ";" + asiaweekly);
            regionList.add(a);

            a = new Region("Emerging Asia Datanotes", covers[1], EMERGE_ASIA + ";" + datanotes);
            regionList.add(a);

            a = new Region("Emerging Asia Chartbook", covers[2], EMERGE_ASIA + ";" + chartbook);
            regionList.add(a);

            adapter.notifyDataSetChanged();
            rlIanshe.setVisibility(View.GONE);
            rlsamuel.setVisibility(View.GONE);
            rlandres.setVisibility(View.GONE);
            rlclaus.setVisibility(View.GONE);
            rlmiguel.setVisibility(View.VISIBLE);
            rlfreya.setVisibility(View.GONE);
            rlmiguel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    MiguelChanco();
                }
            });
        }
        if (CATID.equalsIgnoreCase(GLOBAL)) {
            Region a = new Region("Global Monitor", covers[0], GLOBAL + ";" + "514");
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

    public void ianShepherdson() {

        final Dialog dialog = new Dialog(SubRegionActivity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.contactus_custom_dialog);
        dialog.setTitle(null);

        TextView tvHeading = (TextView) dialog.findViewById(R.id.tvHeading);
        TextView tvSubHeading = (TextView) dialog.findViewById(R.id.tvSubHeading);
        TextView tvPhoneno = (TextView) dialog.findViewById(R.id.tvPhoneno);
        TextView tvMailid = (TextView) dialog.findViewById(R.id.tvMailid);
        TextView tvTweet = (TextView) dialog.findViewById(R.id.tvTweet);

        tvHeading.setText("IAN SHEPHERDSON");
        tvSubHeading.setText("(Chief U.S. Economist)");
        tvPhoneno.setText("+19146103835");
        tvMailid.setText("ian@pantheonmacro.com");
        tvTweet.setText("@IanShepherdson");

        RelativeLayout rlPhoneno = (RelativeLayout) dialog.findViewById(R.id.rlPhoneno);
        RelativeLayout rlMessage = (RelativeLayout) dialog.findViewById(R.id.rlMessage);
        RelativeLayout rlTweet = (RelativeLayout) dialog.findViewById(R.id.rlTweet);

        rlPhoneno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:+19146103835"));
                startActivity(intent);
                dialog.dismiss();
            }
        });

        rlMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_EMAIL, new String[]{getString(R.string.tvIan_mail)});
                startActivity(intent);
                dialog.dismiss();
            }
        });

        rlTweet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = null;
                try {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://twitter.com" + "/ianShepherdson")));
                    dialog.dismiss();
                } catch (Exception e) {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://twitter.com" + "/ianShepherdson")));
                    dialog.dismiss();
                }
            }
        });

        dialog.show();
    }


    public void clausVistesen() {
        final Dialog dialog = new Dialog(SubRegionActivity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.contactus_custom_dialog);
        dialog.setTitle(null);

        TextView tvHeading = (TextView) dialog.findViewById(R.id.tvHeading);
        TextView tvSubHeading = (TextView) dialog.findViewById(R.id.tvSubHeading);
        TextView tvPhoneno = (TextView) dialog.findViewById(R.id.tvPhoneno);
        TextView tvMailid = (TextView) dialog.findViewById(R.id.tvMailid);
        TextView tvTweet = (TextView) dialog.findViewById(R.id.tvTweet);

        tvHeading.setText("CLAUS VISTESEN");
        tvSubHeading.setText("(Chief Eurozone Economist)");
        tvPhoneno.setText("+441912600308");
        tvMailid.setText("claus@pantheonmacro.com");
        tvTweet.setText("@ClausVistesen");

        RelativeLayout rlPhoneno = (RelativeLayout) dialog.findViewById(R.id.rlPhoneno);
        RelativeLayout rlMessage = (RelativeLayout) dialog.findViewById(R.id.rlMessage);
        RelativeLayout rlTweet = (RelativeLayout) dialog.findViewById(R.id.rlTweet);

        rlPhoneno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:+441912600308"));
                startActivity(intent);
                dialog.dismiss();
            }
        });

        rlMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_EMAIL, new String[]{getString(R.string.tvClaus_mail)});
                startActivity(intent);
                dialog.dismiss();
            }
        });

        rlTweet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = null;
                try {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://twitter.com" + "/clausVistesen")));
                    dialog.dismiss();
                } catch (Exception e) {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://twitter.com" + "/clausVistesen")));
                    dialog.dismiss();
                }
            }
        });

        dialog.show();
    }

    public void andresAbadia() {

        final Dialog dialog = new Dialog(SubRegionActivity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.contactus_custom_dialog);
        dialog.setTitle(null);

        TextView tvHeading = (TextView) dialog.findViewById(R.id.tvHeading);
        TextView tvSubHeading = (TextView) dialog.findViewById(R.id.tvSubHeading);
        TextView tvPhoneno = (TextView) dialog.findViewById(R.id.tvPhoneno);
        TextView tvMailid = (TextView) dialog.findViewById(R.id.tvMailid);
        TextView tvTweet = (TextView) dialog.findViewById(R.id.tvTweet);

        tvHeading.setText("ANDRES ABADIA");
        tvSubHeading.setText("(Senior International Economist)");
        tvPhoneno.setText("+441912600309");
        tvMailid.setText("andres@pantheonmacro.com");
        tvTweet.setText("@Andres__Abadia");

        RelativeLayout rlPhoneno = (RelativeLayout) dialog.findViewById(R.id.rlPhoneno);
        RelativeLayout rlMessage = (RelativeLayout) dialog.findViewById(R.id.rlMessage);
        RelativeLayout rlTweet = (RelativeLayout) dialog.findViewById(R.id.rlTweet);

        rlPhoneno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:+441912600309"));
                startActivity(intent);
                dialog.dismiss();
            }
        });

        rlMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_EMAIL, new String[]{getString(R.string.tvAndres_mail)});
                startActivity(intent);
                dialog.dismiss();
            }
        });

        rlTweet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = null;
                try {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://twitter.com" + "/andres__abadia")));
                    dialog.dismiss();
                } catch (Exception e) {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://twitter.com" + "/andres__abadia")));
                    dialog.dismiss();
                }
            }
        });

        dialog.show();
    }

    public void samuelTombs() {

        final Dialog dialog = new Dialog(SubRegionActivity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.contactus_custom_dialog);
        dialog.setTitle(null);

        TextView tvHeading = (TextView) dialog.findViewById(R.id.tvHeading);
        TextView tvSubHeading = (TextView) dialog.findViewById(R.id.tvSubHeading);
        TextView tvPhoneno = (TextView) dialog.findViewById(R.id.tvPhoneno);
        TextView tvMailid = (TextView) dialog.findViewById(R.id.tvMailid);
        TextView tvTweet = (TextView) dialog.findViewById(R.id.tvTweet);

        tvHeading.setText("Samuel Tombs");
        tvSubHeading.setText("(Senior U.K. Economist)");
        tvPhoneno.setText("+442037447430");
        tvMailid.setText("samuel@pantheonmacro.com");
        tvTweet.setText("@SamuelTombs");

        RelativeLayout rlPhoneno = (RelativeLayout) dialog.findViewById(R.id.rlPhoneno);
        RelativeLayout rlMessage = (RelativeLayout) dialog.findViewById(R.id.rlMessage);
        RelativeLayout rlTweet = (RelativeLayout) dialog.findViewById(R.id.rlTweet);

        rlPhoneno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:+442037447430"));
                startActivity(intent);
                dialog.dismiss();
            }
        });

        rlMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_EMAIL, new String[]{getString(R.string.tvSamuel_mail)});
                startActivity(intent);
                dialog.dismiss();
            }
        });

        rlTweet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = null;
                try {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://twitter.com" + "/samueltombs")));
                    dialog.dismiss();
                } catch (Exception e) {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://twitter.com" + "/samueltombs")));
                    dialog.dismiss();
                }
            }
        });

        dialog.show();
    }


    public void freyaBemish() {

        final Dialog dialog = new Dialog(SubRegionActivity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.contactus_custom_dialog);
        dialog.setTitle(null);

        TextView tvHeading = (TextView) dialog.findViewById(R.id.tvHeading);
        TextView tvSubHeading = (TextView) dialog.findViewById(R.id.tvSubHeading);
        TextView tvPhoneno = (TextView) dialog.findViewById(R.id.tvPhoneno);
        TextView tvMailid = (TextView) dialog.findViewById(R.id.tvMailid);
        TextView tvTweet = (TextView) dialog.findViewById(R.id.tvTweet);

        tvHeading.setText("Freya Beamish");
        tvSubHeading.setText("(Chief Asia Economist)");
        tvPhoneno.setText("+441912499209");
        tvMailid.setText("freya@pantheonmacro.com");
        tvTweet.setText("@freyabeamish");

        RelativeLayout rlPhoneno = (RelativeLayout) dialog.findViewById(R.id.rlPhoneno);
        RelativeLayout rlMessage = (RelativeLayout) dialog.findViewById(R.id.rlMessage);
        RelativeLayout rlTweet = (RelativeLayout) dialog.findViewById(R.id.rlTweet);

        rlPhoneno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:+441912499209"));
                startActivity(intent);
                dialog.dismiss();
            }
        });

        rlMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_EMAIL, new String[]{getString(R.string.tvFreya_mail)});
                startActivity(intent);
                dialog.dismiss();
            }
        });

        rlTweet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = null;
                try {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://twitter.com" + "/freyabeamish")));
                    dialog.dismiss();
                } catch (Exception e) {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://twitter.com" + "/freyabeamish")));
                    dialog.dismiss();
                }
            }
        });

        dialog.show();
    }

    public void MiguelChanco() {

        final Dialog dialog = new Dialog(SubRegionActivity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.contactus_custom_dialog);
        dialog.setTitle(null);

        TextView tvHeading = (TextView) dialog.findViewById(R.id.tvHeading);
        TextView tvSubHeading = (TextView) dialog.findViewById(R.id.tvSubHeading);
        TextView tvPhoneno = (TextView) dialog.findViewById(R.id.tvPhoneno);
        TextView tvMailid = (TextView) dialog.findViewById(R.id.tvMailid);
        TextView tvTweet = (TextView) dialog.findViewById(R.id.tvTweet);

        tvHeading.setText("Miguel Chanco");
        tvSubHeading.setText("(Senior Asia Economist)");
        tvPhoneno.setText("+441912499274");
        tvMailid.setText("miguel@pantheonmacro.com");
        tvTweet.setText("@mc_economist");

        RelativeLayout rlPhoneno = (RelativeLayout) dialog.findViewById(R.id.rlPhoneno);
        RelativeLayout rlMessage = (RelativeLayout) dialog.findViewById(R.id.rlMessage);
        RelativeLayout rlTweet = (RelativeLayout) dialog.findViewById(R.id.rlTweet);

        rlPhoneno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:+441912499274"));
                startActivity(intent);
                dialog.dismiss();
            }
        });

        rlMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_EMAIL, new String[]{getString(R.string.tvMiguel_mail)});
                startActivity(intent);
                dialog.dismiss();
            }
        });

        rlTweet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = null;
                try {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://twitter.com" + "/miguelchanco")));
                    dialog.dismiss();
                } catch (Exception e) {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://twitter.com" + "/miguelchanco")));
                    dialog.dismiss();
                }
            }
        });

        dialog.show();
    }
}