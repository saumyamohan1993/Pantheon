package com.pantheon.android.ui;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.NumberPicker;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;

import com.pantheon.android.R;
import com.pantheon.android.adapter.PublicationAdapter;
import com.pantheon.android.bean.BaseBean;
import com.pantheon.android.bean.PublicationData;
import com.pantheon.android.bean.PublicationRegion;
import com.pantheon.android.http.BaseListener;
import com.pantheon.android.http.HttpConnectionUtil;
import com.pantheon.android.http.HttpConstant;
import com.pantheon.android.http.WebserviceType;
import com.pantheon.android.utility.SharedPreferenceManager;

import java.util.ArrayList;
import java.util.Calendar;

public class SearchActivity extends AppCompatActivity implements BaseListener {
    public final String US = "1174";
    public final String EURO = "1172";
    public final String LATAM = "1170";
    public final String UK = "1561";
    private final String APPTOKEN = "J50pjO0d6rH3wzY3";
    private final boolean MODPUBLIST = true;
    private final String SOURCE = "Android";
    private final String DATE = null;
    private final String SRCH = null;
    private final String TAGS = null;
    private final String CUSTOM1 = null;
    private SearchView mSearchView;
    private View filter;
    private MenuItem searchMenuItem;
    private ListView lvPublication;
    private PublicationAdapter adapter;
    private ArrayList<PublicationData> publicationList;
    BaseListener.OnWebServiceCompleteListener onWebServiceCompleteListener = new BaseListener.OnWebServiceCompleteListener() {
        @Override
        public void onWebServiceComplete(Object baseObject) {

            refreshAdapter((BaseBean) baseObject);
        }
    };
    private String UNAME = null;
    private String UID = null;
    private String UTOKEN = null;
    private String search;
    private SearchView mSearch;
    private String searchText;
    private String filterType = "Tag";
    private String TAG_FILTER = "Tag";
    private String TITLE_FILTER = "Title";
    private String AUTHOR_FILTER = "custom1";
    private String DATE1_FILTER = "DATE(YYYY)";
    private String AUTHOR_ID = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        SharedPreferenceManager preferenceManager = SharedPreferenceManager.getInstance();
        UNAME = preferenceManager.getUserName(this);
        UID = preferenceManager.getUserId(this);
        UTOKEN = preferenceManager.getUserToken(this);

        lvPublication = (ListView) findViewById(R.id.lvPublications);
        lvPublication.setTextFilterEnabled(true);
        lvPublication.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {

                System.out.println(arg2 + " --postion");
            }
        });

    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_search_screen, menu);
        filter = menu.findItem(R.id.search).getActionView();
        searchMenuItem = menu.findItem(R.id.search);
        mSearchView = (SearchView) searchMenuItem.getActionView();
        searchMenuItem.expandActionView();
        mSearchView.setQueryHint("Search by " + filterType);
        initializeSearchView();
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == android.R.id.home) {

            finish();
        }
        if (id == R.id.filter) {

            popUp();
        }
        return super.onOptionsItemSelected(item);
    }

    private void initializeSearchView() {
        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String search) {

                if (TextUtils.isEmpty(search)) {
                    filterType = TAG_FILTER;
                    mSearchView.setQueryHint("Search by " + filterType);
                    setSearchBarEnable();
                } else {
                    searchText = search;
                    getPublicationList();
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }

    private void popUp() {
        AlertDialog.Builder builderSingle = new AlertDialog.Builder(SearchActivity.this);
        builderSingle.setIcon(R.drawable.filter);
        builderSingle.setTitle(getString(R.string.search_by));
        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                SearchActivity.this,
                android.R.layout.select_dialog_item);
        arrayAdapter.add(getString(R.string.tag));
        arrayAdapter.add(getString(R.string.title));
        arrayAdapter.add(getString(R.string.author));
        arrayAdapter.add(getString(R.string.date1));
        arrayAdapter.add(getString(R.string.date2));
        arrayAdapter.add(getString(R.string.date3));

        builderSingle.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }

        );

        builderSingle.setAdapter(arrayAdapter, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        searchText = arrayAdapter.getItem(which);
                        if (searchText.equals(getString(R.string.title))) {
                            filterType = TITLE_FILTER;
                            mSearchView.setQueryHint("Search by " + filterType);
                        }
                        if (searchText.equals(getString(R.string.tag))) {
                            filterType = TAG_FILTER;
                            mSearchView.setQueryHint("Search by " + filterType);
                        }
                        if (searchText.equals(getString(R.string.author))) {
                            authorPop();
                        }
                        if (searchText.equals(getString(R.string.date1))) {
                            showYearPicker();
                        }
                        if (searchText.equals(getString(R.string.date2))) {
                            showMonthYear();
                        }
                        if (searchText.equals(getString(R.string.date3))) {
                            showDayMonthYear();
                        }
                    }
                }
        );
        builderSingle.show();

    }

    private void showYearPicker() {
        final Dialog d = new Dialog(SearchActivity.this);
        d.setTitle(R.string.date1);
        d.setContentView(R.layout.number_dialog);
        Button b1 = (Button) d.findViewById(R.id.button1);
        Button b2 = (Button) d.findViewById(R.id.button2);
        final NumberPicker np = (NumberPicker) d.findViewById(R.id.numberPicker1);
        np.setMaxValue(2030);
        np.setMinValue(1900);
        np.setValue(2016);
        np.setWrapSelectorWheel(false);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                filterType = DATE1_FILTER;
                setSearchBar(String.valueOf(np.getValue()));
                d.dismiss();
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                d.dismiss();
            }
        });
        d.show();
    }

    private void showMonthYear() {

        final Dialog d = new Dialog(SearchActivity.this);
        d.setTitle(R.string.date2);
        d.setContentView(R.layout.year_month_dialog);
        Button b1 = (Button) d.findViewById(R.id.button1);
        Button b2 = (Button) d.findViewById(R.id.button2);
        final NumberPicker np1 = (NumberPicker) d.findViewById(R.id.numberPicker1);
        np1.setMaxValue(31);
        np1.setMinValue(1);
        np1.setWrapSelectorWheel(false);
        final NumberPicker np2 = (NumberPicker) d.findViewById(R.id.numberPicker2);
        np2.setMaxValue(12);
        np2.setMinValue(1);
        np2.setWrapSelectorWheel(false);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                d.dismiss();
                filterType = DATE1_FILTER;
                String text = (np1.getValue() < 10 ? "0" : "") + np1.getValue();
                String text2 = (np2.getValue() < 10 ? "0" : "") + np2.getValue();
                setSearchBar(String.valueOf(text) + "/" + String.valueOf(text2));

            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                d.dismiss();
            }
        });
        d.show();
    }

    private void showDayMonthYear() {
        Calendar now = Calendar.getInstance();
        System.out.println("Current Year is : " + now.get(Calendar.YEAR));
        System.out.println("Current Month is : " + (now.get(Calendar.MONTH) + 1));
        System.out.println("Current Date is : " + now.get(Calendar.DATE));
        final Dialog d = new Dialog(SearchActivity.this);
        d.setTitle(R.string.date3);
        d.setContentView(R.layout.day_year_month);
        Button b1 = (Button) d.findViewById(R.id.button1);
        Button b2 = (Button) d.findViewById(R.id.button2);
        final NumberPicker np = (NumberPicker) d.findViewById(R.id.numberPicker3);
        np.setMaxValue(31);
        np.setMinValue(1);
        np.setValue(now.get(Calendar.DATE));
        np.setWrapSelectorWheel(false);

        final NumberPicker np1 = (NumberPicker) d.findViewById(R.id.numberPicker2);
        np1.setMaxValue(12);
        np1.setMinValue(1);
        np1.setValue((now.get(Calendar.MONTH) + 1));
        np1.setWrapSelectorWheel(false);

        final NumberPicker np2 = (NumberPicker) d.findViewById(R.id.numberPicker1);
        np2.setMaxValue(2100);
        np2.setMinValue(1900);
        np2.setValue(now.get(Calendar.YEAR));
        np2.setWrapSelectorWheel(false);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                filterType = DATE1_FILTER;
                String text = (np.getValue() < 10 ? "0" : "") + np.getValue();
                String text1 = (np1.getValue() < 10 ? "0" : "") + np1.getValue();
                setSearchBar(String.valueOf(text) + "/" + String.valueOf(text1) + "/" + String.valueOf(np2.getValue()));
                d.dismiss();
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                d.dismiss();
            }
        });
        d.show();

    }

    private void authorPop() {
        AlertDialog.Builder builderSingle = new AlertDialog.Builder(SearchActivity.this);
        builderSingle.setTitle(getString(R.string.author));
        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                SearchActivity.this,
                android.R.layout.select_dialog_item);
        arrayAdapter.add("lan");
        arrayAdapter.add("Andres");
        arrayAdapter.add("Claus");
        arrayAdapter.add("Samuel");
        builderSingle.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }
        );
        builderSingle.setAdapter(arrayAdapter, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int index) {
                        filterType = AUTHOR_FILTER;
                        if (index == 0) {
                            AUTHOR_ID = "7";
                        }
                        if (index == 1) {
                            AUTHOR_ID = "368";
                        }
                        if (index == 2) {
                            AUTHOR_ID = "373";
                        }
                        if (index == 3) {
                            AUTHOR_ID = "244590";
                        }

                        setSearchBar(arrayAdapter.getItem(index));
                    }
                }
                );
        builderSingle.show();
    }

    private void setSearchBar(String query) {
        mSearchView.setQuery(String.valueOf(query), false);
        mSearchView.clearFocus();
        mSearchView.setInputType(InputType.TYPE_NULL);
        searchText = query;
        getPublicationList();
    }

    private void setSearchBarEnable() {

        mSearchView.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_NORMAL);
        mSearchView.setFocusableInTouchMode(true);
    }

    private void getPublicationList() {
        PublicationRegion USbean = new PublicationRegion(HttpConstant.USMONITOR_URL);
        USbean.setAppToken(APPTOKEN);
        USbean.setModPubList(MODPUBLIST);
        USbean.setSource(SOURCE);
        USbean.setUName(UNAME);
        USbean.setUId(UID);
        USbean.setUToken(UTOKEN);

        if (filterType.equals(TAG_FILTER)) {
            USbean.setTags(searchText);
        }
        if (filterType.equals(TITLE_FILTER)) {
            USbean.setSrch(searchText);
        }
        if (filterType.equals(AUTHOR_FILTER)) {
            USbean.setCustom1(AUTHOR_ID);
        }
        if (filterType.equals(DATE1_FILTER)) {
            USbean.setDate(searchText);
        }
        USbean.setProgressEnable(true);
        HttpConnectionUtil.callWebService(USbean, this, WebserviceType.SEARCH, onWebServiceCompleteListener);
    }

    private void refreshAdapter(final BaseBean baseObject) {

        PublicationRegion USbean = (PublicationRegion) baseObject;
        publicationList = ((PublicationRegion) baseObject).getPublicationDataArrayList();
        System.out.println("result Publication..." + USbean.result);

        if (publicationList != null) {
            if (USbean.result == true) {
                adapter = new PublicationAdapter(this, publicationList);
                lvPublication.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }
        } else {
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(SearchActivity.this);
            alertDialogBuilder.setMessage("No publication found based on search criteria.");
            alertDialogBuilder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface arg0, int arg1) {

                    arg0.dismiss();
                }
            });

            AlertDialog alertDialog = alertDialogBuilder.create();
            alertDialog.show();
        }
    }
}



