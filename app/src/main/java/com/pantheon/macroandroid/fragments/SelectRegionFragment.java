package com.pantheon.macroandroid.fragments;

import android.app.Fragment;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.pantheon.macroandroid.R;
import com.pantheon.macroandroid.adapter.RegionAdapter;
import com.pantheon.macroandroid.model.Region;
import com.pantheon.macroandroid.ui.PublicationsListActivity;

import java.util.ArrayList;
import java.util.List;

public class SelectRegionFragment extends Fragment {
    private final String US = "1174";
    private final String EURO = "1172";
    private final String LATAM = "1170";
    private final String UK = "1561";
    private final String ASIA_MONITOR = "1863";
    private final String EMERGE_ASIA = "3604";
    private final String CHINA = "1863";
    private final String GLOBAL = "3379";

    private RecyclerView recyclerView;
    private RegionAdapter adapter;
    private List<Region> regionList;
    private LinearLayout llUsMonitor, llEurozone, llLatinAmerica, llUk, llAsiaMonitor;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_select_region, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        regionList = new ArrayList<>();
        adapter = new RegionAdapter(getContext(), regionList);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getContext(), 2);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(18), true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
        prepareImages();

        return view;
    }

    private void getPublications(String catid) {
        Intent intent = new Intent(getActivity(), PublicationsListActivity.class);
        intent.putExtra("catID", catid);
        startActivity(intent);
    }

    private void prepareImages() {
        int[] covers = new int[]{
                R.drawable.us,//0
                R.drawable.euro,//1
                R.drawable.lad,//2
                R.drawable.uk,//3
                R.drawable.china,//4
                R.drawable.emergeasia,//5
                R.drawable.global};

        Region a = new Region("UNITED STATES", covers[0], US);
        regionList.add(a);

        a = new Region("EUROZONE", covers[1], EURO);
        regionList.add(a);

        a = new Region("LATIN AMERICA", covers[2], LATAM);
        regionList.add(a);

        a = new Region("UNITED KINGDOM", covers[3], UK);
        regionList.add(a);

        a = new Region("CHINA+", covers[4], CHINA);
        regionList.add(a);

        a = new Region("EMERGING ASIA", covers[5], EMERGE_ASIA);
        regionList.add(a);

        a = new Region("GLOBAL", covers[6], GLOBAL);
        regionList.add(a);

//        a = new Album("ECONOMIC MONITOR", covers[4],ASIA_MONITOR);
//        regionList.add(a);
        adapter.notifyDataSetChanged();
    }

    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }

    public class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {

        private int spanCount;
        private int spacing;
        private boolean includeEdge;

        public GridSpacingItemDecoration(int spanCount, int spacing, boolean includeEdge) {
            this.spanCount = spanCount;
            this.spacing = spacing;
            this.includeEdge = includeEdge;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            int position = parent.getChildAdapterPosition(view); // item position
            int column = position % spanCount; // item column

            if (includeEdge) {
                outRect.left = spacing - column * spacing / spanCount; // spacing - column * ((1f / spanCount) * spacing)
                outRect.right = (column + 1) * spacing / spanCount; // (column + 1) * ((1f / spanCount) * spacing)

                if (position < spanCount) { // top edge
                    outRect.top = spacing;
                }
                outRect.bottom = spacing; // item bottom
            } else {
                outRect.left = column * spacing / spanCount; // column * ((1f / spanCount) * spacing)
                outRect.right = spacing - (column + 1) * spacing / spanCount; // spacing - (column + 1) * ((1f /    spanCount) * spacing)
                if (position >= spanCount) {
                    outRect.top = spacing; // item top
                }
            }
        }
    }


}