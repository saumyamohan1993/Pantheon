/**
 @Module Name/Class		:	SelectRegionFragment
 @Author Name			:	Mr. Sombir Singh Bisht
 @Date					:	Aug 26, 2015
 @Purpose				:	This class is used to provide Home Screen.
 */
package com.pantheon.android.fragments;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.pantheon.android.R;
import com.pantheon.android.ui.PublicationsListActivity;

public class SelectRegionFragment extends Fragment {
    private LinearLayout llUsMonitor, llEurozone, llLatinAmerica, llUk,llAsiaMonitor;
    private final String US = "1174";
    private final String EURO = "1172";
    private final String LATAM = "1170";
    private final String UK = "1561";
    private final String ASIA_MONITOR="1863";
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_select_region, container, false);

        llUsMonitor = (LinearLayout) view.findViewById(R.id.llUsMonitor);
        llEurozone = (LinearLayout) view.findViewById(R.id.llEurozone);
        llLatinAmerica = (LinearLayout) view.findViewById(R.id.llLatinAmerica);
        llUk = (LinearLayout) view.findViewById(R.id.llUk);
        llAsiaMonitor=(LinearLayout)view.findViewById(R.id.llAsiaMonitor);


        llUsMonitor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getPublications(US);
            }
        });

        llEurozone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getPublications(EURO);
            }
        });

        llLatinAmerica.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getPublications(LATAM);
            }
        });

        llUk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getPublications(UK);
            }
        });

        llAsiaMonitor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getPublications(ASIA_MONITOR);
            }
        });

        return view;
    }

    /**
     @Function Name		:	getPublications
     @Author Name		:	Mr. Sombir Singh Bisht
     @Date				:	Aug, 26 2015
     @Param			    :	catid  | String | Category id for different Publication Zones
     @Purpose			:	To select Publication zone in which user interested.
     */
    private void getPublications(String catid){
        Intent intent = new Intent(getActivity(), PublicationsListActivity.class);
        intent.putExtra("catID",catid);
        startActivity(intent);
    }
}