package com.pantheon.macroandroid.ui;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.pantheon.macroandroid.R;

public class SettingsActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    static Switch Ussw1, Ussw2, Ussw3, Ussw4, Ussw5, eusw1, eusw2, eusw3, eusw4,
            eusw5, lasw1, lasw2, lasw3, lasw4, lasw5, emsw1, emsw2, emsw3, emsw4, emsw5,
            gosw1, gosw2;
    static String usall, usdaily, usweekly, usdata, uschartbook;
    static String euall, eudaily, euweekly, eudata, euchartbook;
    static String emall, emdaily, emweekly, emdata, emchartbook;
    static String laall, ladaily, laweekly, ladata, lachartbook;
    static String godaily, godata;
    LinearLayout uslayout, eurolayout, globallayout, latamlayout, emergelayout;
    private Spinner spinner1, spinner2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        spinner1 = (Spinner) findViewById(R.id.spinner1);
        spinner1.setOnItemSelectedListener(this);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setTitle("Setting");

        uslayout = findViewById(R.id.USrelative);
        eurolayout = findViewById(R.id.Eurorelative);
        emergelayout = findViewById(R.id.Emergerelative);
        latamlayout = findViewById(R.id.Latamrelative);
        globallayout = findViewById(R.id.Globalrelative);

        Ussw1 = findViewById(R.id.switch1);
        Ussw2 = findViewById(R.id.switch2);
        Ussw3 = findViewById(R.id.switch3);
        Ussw4 = findViewById(R.id.switch4);
        Ussw5 = findViewById(R.id.switch5);

        eusw1 = findViewById(R.id.switcheu1);
        eusw2 = findViewById(R.id.switcheu2);
        eusw3 = findViewById(R.id.switcheu3);
        eusw4 = findViewById(R.id.switcheu4);
        eusw5 = findViewById(R.id.switcheu5);

        emsw1 = findViewById(R.id.switchem1);
        emsw2 = findViewById(R.id.switchem2);
        emsw3 = findViewById(R.id.switchem3);
        emsw4 = findViewById(R.id.switchem4);
        emsw5 = findViewById(R.id.switchem5);

        lasw1 = findViewById(R.id.switchla1);
        lasw2 = findViewById(R.id.switchla2);
        lasw3 = findViewById(R.id.switchla3);
        lasw4 = findViewById(R.id.switchla4);
        lasw5 = findViewById(R.id.switchla5);

        gosw1 = findViewById(R.id.switchgo1);
        gosw2 = findViewById(R.id.switchgo2);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_setting, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }


    public void setuplayout(String val) {
        if (val.equalsIgnoreCase("U.S")) {

            eurolayout.setVisibility(View.GONE);
            emergelayout.setVisibility(View.GONE);
            latamlayout.setVisibility(View.GONE);
            globallayout.setVisibility(View.GONE);
            uslayout.setVisibility(View.VISIBLE);

            Ussw1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked) {
                        usall = Ussw1.getTextOn().toString();
                    } else {
                        usall = Ussw1.getTextOff().toString();
                    }
                }
            });
            Ussw2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked) {
                        usdaily = Ussw2.getTextOn().toString();
                    } else {
                        usdaily = Ussw2.getTextOff().toString();
                    }
                }
            });
            Ussw3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked) {
                        usweekly = Ussw3.getTextOn().toString();
                    } else {
                        usweekly = Ussw3.getTextOff().toString();

                    }
                }
            });

            Ussw4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked) {
                        usdata = Ussw4.getTextOn().toString();

                    } else {
                        usdata = Ussw4.getTextOff().toString();
                    }
                }
            });

            Ussw5.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked) {
                        uschartbook = Ussw5.getTextOn().toString();
                    } else {
                        uschartbook = Ussw5.getTextOff().toString();

                    }
                }
            });

        } else if (val.equalsIgnoreCase("Eurozone")) {
            eurolayout.setVisibility(View.VISIBLE);
            emergelayout.setVisibility(View.GONE);
            latamlayout.setVisibility(View.GONE);
            globallayout.setVisibility(View.GONE);
            uslayout.setVisibility(View.GONE);

            eusw1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked) {
                        euall = eusw1.getTextOn().toString();

                    } else {
                        euall = eusw1.getTextOff().toString();

                    }
                }
            });
            eusw2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked) {
                        eudaily = eusw2.getTextOn().toString();

                    } else {
                        eudaily = eusw2.getTextOff().toString();

                    }
                }
            });
            eusw3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked) {
                        euweekly = eusw3.getTextOn().toString();
                    } else {
                        euweekly = eusw3.getTextOff().toString();
                    }
                }
            });
            eusw4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked) {
                        eudata = eusw4.getTextOn().toString();
                    } else {
                        eudata = eusw4.getTextOff().toString();
                    }
                }
            });
            eusw5.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked) {
                        euchartbook = eusw5.getTextOn().toString();

                    } else {
                        euchartbook = eusw5.getTextOff().toString();
                    }
                }
            });


        } else if (val.equalsIgnoreCase("LatAm")) {
            eurolayout.setVisibility(View.GONE);
            emergelayout.setVisibility(View.GONE);
            latamlayout.setVisibility(View.VISIBLE);
            globallayout.setVisibility(View.GONE);
            uslayout.setVisibility(View.GONE);


            lasw1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked) {
                        laall = lasw1.getTextOn().toString();
                    } else {
                        laall = lasw1.getTextOff().toString();
                    }
                }
            });
            lasw2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked) {
                        ladaily = lasw2.getTextOn().toString();
                    } else {
                        ladaily = lasw2.getTextOff().toString();
                    }
                }
            });
            lasw3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked) {
                        laweekly = lasw3.getTextOn().toString();
                    } else {
                        laweekly = lasw3.getTextOff().toString();
                    }
                }
            });
            lasw4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked) {
                        ladata = lasw4.getTextOn().toString();
                    } else {
                        ladata = lasw4.getTextOff().toString();
                    }
                }
            });
            lasw5.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked) {
                        lachartbook = lasw5.getTextOn().toString();
                    } else {
                        lachartbook = lasw5.getTextOff().toString();
                    }
                }
            });


        } else if (val.equalsIgnoreCase("Emerging Asia")) {
            eurolayout.setVisibility(View.GONE);
            emergelayout.setVisibility(View.VISIBLE);
            latamlayout.setVisibility(View.GONE);
            globallayout.setVisibility(View.GONE);
            uslayout.setVisibility(View.GONE);


            emsw1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked) {
                        emall = emsw1.getTextOn().toString();
                    } else {
                        emall = emsw1.getTextOff().toString();
                    }
                }
            });
            emsw2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked) {
                        emdaily = emsw2.getTextOn().toString();
                    } else {
                        emdaily = emsw2.getTextOff().toString();
                    }
                }
            });
            emsw3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked) {
                        emweekly = emsw3.getTextOn().toString();
                    } else {
                        emweekly = emsw3.getTextOff().toString();
                    }
                }
            });
            emsw4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked) {
                        emdata = emsw4.getTextOn().toString();
                    } else {
                        emdata = emsw4.getTextOff().toString();
                    }
                }
            });
            emsw5.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked) {
                        emchartbook = emsw5.getTextOn().toString();
                    } else {
                        emchartbook = emsw5.getTextOff().toString();
                    }
                }
            });


        } else if (val.equalsIgnoreCase("Global")) {
            eurolayout.setVisibility(View.GONE);
            emergelayout.setVisibility(View.GONE);
            latamlayout.setVisibility(View.GONE);
            globallayout.setVisibility(View.VISIBLE);
            uslayout.setVisibility(View.GONE);


            gosw1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked) {
                        godaily = gosw1.getTextOn().toString();
                    } else {
                        godaily = gosw1.getTextOff().toString();
                    }
                }
            });
            gosw2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked) {
                        godata = gosw2.getTextOn().toString();
                    } else {
                        godata = gosw2.getTextOff().toString();
                    }
                }
            });


        }

    }

    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {

        Toast.makeText(parent.getContext(),
                "OnItemSelectedListener : " + parent.getItemAtPosition(pos).toString(),
                Toast.LENGTH_SHORT).show();
        String val = parent.getItemAtPosition(pos).toString();
        setuplayout(val);

    }

    @Override
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
    }
}
