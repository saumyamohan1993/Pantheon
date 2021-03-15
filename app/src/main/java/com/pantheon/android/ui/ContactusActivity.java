package com.pantheon.android.ui;

import android.app.Dialog;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.pantheon.android.R;
import com.pantheon.android.adapter.ContactusAdapter;
import com.pantheon.android.fragments.SelectRegionFragment;
import com.pantheon.android.model.Region;

import java.util.ArrayList;
import java.util.List;


public class ContactusActivity extends AppCompatActivity {
    private LinearLayout llGeneralEnquires, llIanShepherdson, llClausVistesen, llAndresAbadia, llSamuelTombs, llFreyaBemish, llMiguel;
    private RelativeLayout rlGeneralPhoneno, rlIanPhoneno, rlClausPhoneno, rlAndresPhoneno;
    private RelativeLayout rlGeneralMessage, rlIanMessage, rlClausMessage, rlAndresMessage;
    private RelativeLayout rlGeneralTweet, rlIanTweet, rlClausTweet, rlAndresTweet;
    private RecyclerView recyclerView;
    private ContactusAdapter adapter;
    private List<Region> regionList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contactus);

        llGeneralEnquires = (LinearLayout) findViewById(R.id.llGeneralEnquires);
//        llIanShepherdson = (LinearLayout) findViewById(R.id.llIanShepherdson);
//        llClausVistesen = (LinearLayout) findViewById(R.id.llClausVistesen);
//        llAndresAbadia = (LinearLayout) findViewById(R.id.llAndresAbadia);
//        llSamuelTombs = (LinearLayout) findViewById(R.id.llSamuelTombs);
//        llFreyaBemish = (LinearLayout) findViewById(R.id.llFreyaBeamish);
//        llMiguel = (LinearLayout) findViewById(R.id.llMiguel);


        recyclerView = findViewById(R.id.contactusrecycle);
        regionList = new ArrayList<>();
        adapter = new ContactusAdapter(ContactusActivity.this, regionList);
//        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(ContactusActivity.this, 2);
//        recyclerView.setLayoutManager(mLayoutManager);
//        recyclerView.setAdapter(adapter);

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(ContactusActivity.this, 2);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new ContactusActivity.GridSpacingItemDecoration(2, dpToPx(8), true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

        prepareImages();


        llGeneralEnquires.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                generalEnquires();
            }
        });

//        llIanShepherdson.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                ianShepherdson();
//            }
//        });
//
//
//        llClausVistesen.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                clausVistesen();
//            }
//        });
//
//        llAndresAbadia.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                andresAbadia();
//            }
//        });
//
//        llSamuelTombs.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                samuelTombs();
//            }
//        });
//
//        llFreyaBemish.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                freyaBemish();
//            }
//        });
//        llMiguel.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                MiguelChanco();
//            }
//        });

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
    }

    private void generalEnquires() {

        final Dialog dialog = new Dialog(ContactusActivity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.contactus_custom_dialog);
        dialog.setTitle(null);

        TextView tvHeading = (TextView) dialog.findViewById(R.id.tvHeading);
        TextView tvPhoneno = (TextView) dialog.findViewById(R.id.tvPhoneno);
        TextView tvMailid = (TextView) dialog.findViewById(R.id.tvMailid);
        TextView tvTweet = (TextView) dialog.findViewById(R.id.tvTweet);

        tvHeading.setText("GENERAL ENQUIRES");
        tvPhoneno.setText("+19146103830");
        tvMailid.setText("info@pantheonmacro.com");
        tvTweet.setText("@PantheonMacro");

        RelativeLayout rlPhoneno = (RelativeLayout) dialog.findViewById(R.id.rlPhoneno);
        RelativeLayout rlMessage = (RelativeLayout) dialog.findViewById(R.id.rlMessage);
        RelativeLayout rlTweet = (RelativeLayout) dialog.findViewById(R.id.rlTweet);

        rlPhoneno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:+19146103830"));
                startActivity(intent);
                dialog.dismiss();
            }
        });

        rlMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_EMAIL, new String[]{getString(R.string.tvGeneral_mail)});
                startActivity(intent);
                dialog.dismiss();
            }
        });

        rlTweet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://twitter.com" + "/PantheonMacro"));
                    startActivity(intent);
                    dialog.dismiss();
                } catch (Exception e) {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://twitter.com" + "/PantheonMacro"));
                    startActivity(intent);
                    dialog.dismiss();
                }
            }
        });

        dialog.show();
    }


    public void ianShepherdson() {

        final Dialog dialog = new Dialog(ContactusActivity.this);
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
        final Dialog dialog = new Dialog(ContactusActivity.this);
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

        final Dialog dialog = new Dialog(ContactusActivity.this);
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

        final Dialog dialog = new Dialog(ContactusActivity.this);
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

        final Dialog dialog = new Dialog(ContactusActivity.this);
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

        final Dialog dialog = new Dialog(ContactusActivity.this);
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


    private void prepareImages() {
        int[] covers = new int[]{
                R.drawable.img2,//0
                R.drawable.img3,//1
                R.drawable.img4,//2
                R.drawable.samuel,//3
                R.drawable.img5,//4
                R.drawable.miguel};

        Region a = new Region(
                getString(R.string.tvIan_text),
                getString(R.string.tvIan_subtext),
                getString(R.string.tvIan_Phoneno),
                getString(R.string. tvIan_mail),
                getString(R.string.tvIan_tweet), covers[0],
                "#E9F9FE");
        regionList.add(a);

      a = new Region(
                getString(R.string.tvClaus_text),
                getString(R.string.tvClaus_subtext),
                getString(R.string.tvClaus_Phoneno),
                getString(R.string. tvClaus_mail),
                getString(R.string.tvClaus_tweet), covers[1],
             "#E0E4E5");
        regionList.add(a);

         a = new Region(
                getString(R.string.tvAndres_text),
                getString(R.string.tvAndres_subtext),
                getString(R.string.tvAndres_Phoneno),
                getString(R.string. tvAndres_mail),
                getString(R.string.tvAndres_tweet), covers[2],"#E0E4E5");
        regionList.add(a);
        a = new Region(
                getString(R.string.tvSamuel_text),
                getString(R.string.tvSamuel_subtext),
                getString(R.string.tvSamuel_Phoneno),
                getString(R.string. tvSamuel_mail),
                getString(R.string.tvSamuel_tweet), covers[3],"#E9F9FE");
        regionList.add(a);

         a = new Region(
                getString(R.string.tvFreya_text),
                getString(R.string.tvFreya_subtext),
                getString(R.string.tvFreya_Phoneno),
                getString(R.string. tvFreya_mail),
                getString(R.string.tvFreya_tweet), covers[4],"#E9F9FE");
        regionList.add(a);


         a = new Region(
                getString(R.string.tvMiguel),
                getString(R.string.tvMiguel_subtext),
                getString(R.string.tvMiguel_Phoneno),
                getString(R.string. tvMiguel_mail),
                getString(R.string.tvMiguel_tweet), covers[5],"#E0E4E5");
        regionList.add(a);

        adapter.notifyDataSetChanged();
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

    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_contactus, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
