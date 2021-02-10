/**
 @Page/Module Name/Class	:	ContactusActivity
 @Author Name		:	Mr. Sombir Singh Bisht
 @Date				:	Aug 27,  2015
 @Purpose			:	This page/functionality is used to provide Contact Us Screen.
 */
package com.pantheon.android.ui;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
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

import com.pantheon.android.R;


public class ContactusActivity extends AppCompatActivity {
    private LinearLayout llGeneralEnquires, llIanShepherdson, llClausVistesen, llAndresAbadia, llSamuelTombs,llFreyaBemish;
    private RelativeLayout rlGeneralPhoneno, rlIanPhoneno, rlClausPhoneno, rlAndresPhoneno;
    private RelativeLayout rlGeneralMessage, rlIanMessage, rlClausMessage, rlAndresMessage;
    private RelativeLayout rlGeneralTweet, rlIanTweet, rlClausTweet, rlAndresTweet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contactus);

        llGeneralEnquires = (LinearLayout) findViewById(R.id.llGeneralEnquires);
        llIanShepherdson = (LinearLayout) findViewById(R.id.llIanShepherdson);
        llClausVistesen = (LinearLayout) findViewById(R.id.llClausVistesen);
        llAndresAbadia = (LinearLayout) findViewById(R.id.llAndresAbadia);
        llSamuelTombs = (LinearLayout) findViewById(R.id.llSamuelTombs);
        llFreyaBemish = (LinearLayout)findViewById(R.id.llFreyaBeamish);
        

        llGeneralEnquires.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                generalEnquires();
            }
        });

        llIanShepherdson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ianShepherdson();
            }
        });


        llClausVistesen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                clausVistesen();
            }
        });

        llAndresAbadia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                andresAbadia();
            }
        });

        llSamuelTombs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                samuelTombs();
            }
        });

        llFreyaBemish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                freyaBemish();
            }
        });

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
    }

    private void generalEnquires(){

        final Dialog dialog = new Dialog(ContactusActivity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.contactus_custom_dialog);
        dialog.setTitle(null);

        TextView tvHeading=(TextView)dialog.findViewById(R.id.tvHeading);
        TextView tvPhoneno=(TextView)dialog.findViewById(R.id.tvPhoneno);
        TextView tvMailid=(TextView)dialog.findViewById(R.id.tvMailid);
        TextView tvTweet=(TextView)dialog.findViewById(R.id.tvTweet);

        tvHeading.setText("GENERAL ENQUIRES");
        tvPhoneno.setText("+19146103830");
        tvMailid.setText("info@pantheonmacro.com");
        tvTweet.setText("@PantheonMacro");

        RelativeLayout rlPhoneno = (RelativeLayout)dialog.findViewById(R.id.rlPhoneno);
        RelativeLayout rlMessage = (RelativeLayout)dialog.findViewById(R.id.rlMessage);
        RelativeLayout rlTweet = (RelativeLayout)dialog.findViewById(R.id.rlTweet);

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


    private void ianShepherdson(){

        final Dialog dialog = new Dialog(ContactusActivity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);

        dialog.setContentView(R.layout.contactus_custom_dialog);
        dialog.setTitle(null);

        TextView tvHeading=(TextView)dialog.findViewById(R.id.tvHeading);
        TextView tvSubHeading=(TextView)dialog.findViewById(R.id.tvSubHeading);
        TextView tvPhoneno=(TextView)dialog.findViewById(R.id.tvPhoneno);
        TextView tvMailid=(TextView)dialog.findViewById(R.id.tvMailid);
        TextView tvTweet=(TextView)dialog.findViewById(R.id.tvTweet);

        tvHeading.setText("IAN SHEPHERDSON");
        tvSubHeading.setText("(Chief U.S. Economist)");
        tvPhoneno.setText("+19146103835");
        tvMailid.setText("ian@pantheonmacro.com");
        tvTweet.setText("@IanShepherdson");

        RelativeLayout rlPhoneno = (RelativeLayout)dialog.findViewById(R.id.rlPhoneno);
        RelativeLayout rlMessage = (RelativeLayout)dialog.findViewById(R.id.rlMessage);
        RelativeLayout rlTweet = (RelativeLayout)dialog.findViewById(R.id.rlTweet);

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


    private void clausVistesen(){
        final Dialog dialog = new Dialog(ContactusActivity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.contactus_custom_dialog);
        dialog.setTitle(null);

        TextView tvHeading=(TextView)dialog.findViewById(R.id.tvHeading);
        TextView tvSubHeading=(TextView)dialog.findViewById(R.id.tvSubHeading);
        TextView tvPhoneno=(TextView)dialog.findViewById(R.id.tvPhoneno);
        TextView tvMailid=(TextView)dialog.findViewById(R.id.tvMailid);
        TextView tvTweet=(TextView)dialog.findViewById(R.id.tvTweet);

        tvHeading.setText("CLAUS VISTESEN");
        tvSubHeading.setText("(Chief Eurozone Economist)");
        tvPhoneno.setText("+441912600308");
        tvMailid.setText("claus@pantheonmacro.com");
        tvTweet.setText("@ClausVistesen");

        RelativeLayout rlPhoneno = (RelativeLayout)dialog.findViewById(R.id.rlPhoneno);
        RelativeLayout rlMessage = (RelativeLayout)dialog.findViewById(R.id.rlMessage);
        RelativeLayout rlTweet = (RelativeLayout)dialog.findViewById(R.id.rlTweet);

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


    private void andresAbadia(){

        final Dialog dialog = new Dialog(ContactusActivity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.contactus_custom_dialog);
        dialog.setTitle(null);

        TextView tvHeading=(TextView)dialog.findViewById(R.id.tvHeading);
        TextView tvSubHeading=(TextView)dialog.findViewById(R.id.tvSubHeading);
        TextView tvPhoneno=(TextView)dialog.findViewById(R.id.tvPhoneno);
        TextView tvMailid=(TextView)dialog.findViewById(R.id.tvMailid);
        TextView tvTweet=(TextView)dialog.findViewById(R.id.tvTweet);

        tvHeading.setText("ANDRES ABADIA");
        tvSubHeading.setText("(Senior International Economist)");
        tvPhoneno.setText("+441912600309");
        tvMailid.setText("andres@pantheonmacro.com");
        tvTweet.setText("@Andres__Abadia");

        RelativeLayout rlPhoneno = (RelativeLayout)dialog.findViewById(R.id.rlPhoneno);
        RelativeLayout rlMessage = (RelativeLayout)dialog.findViewById(R.id.rlMessage);
        RelativeLayout rlTweet = (RelativeLayout)dialog.findViewById(R.id.rlTweet);

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

    private void samuelTombs(){

        final Dialog dialog = new Dialog(ContactusActivity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.contactus_custom_dialog);
        dialog.setTitle(null);

        TextView tvHeading=(TextView)dialog.findViewById(R.id.tvHeading);
        TextView tvSubHeading=(TextView)dialog.findViewById(R.id.tvSubHeading);
        TextView tvPhoneno=(TextView)dialog.findViewById(R.id.tvPhoneno);
        TextView tvMailid=(TextView)dialog.findViewById(R.id.tvMailid);
        TextView tvTweet=(TextView)dialog.findViewById(R.id.tvTweet);

        tvHeading.setText("Samuel Tombs");
        tvSubHeading.setText("(Senior U.K. Economist)");
        tvPhoneno.setText("+442037447430");
        tvMailid.setText("samuel@pantheonmacro.com");
        tvTweet.setText("@SamuelTombs");

        RelativeLayout rlPhoneno = (RelativeLayout)dialog.findViewById(R.id.rlPhoneno);
        RelativeLayout rlMessage = (RelativeLayout)dialog.findViewById(R.id.rlMessage);
        RelativeLayout rlTweet = (RelativeLayout)dialog.findViewById(R.id.rlTweet);

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



    private void freyaBemish(){

        final Dialog dialog = new Dialog(ContactusActivity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.contactus_custom_dialog);
        dialog.setTitle(null);

        TextView tvHeading=(TextView)dialog.findViewById(R.id.tvHeading);
        TextView tvSubHeading=(TextView)dialog.findViewById(R.id.tvSubHeading);
        TextView tvPhoneno=(TextView)dialog.findViewById(R.id.tvPhoneno);
        TextView tvMailid=(TextView)dialog.findViewById(R.id.tvMailid);
        TextView tvTweet=(TextView)dialog.findViewById(R.id.tvTweet);

        tvHeading.setText("Freya Beamish");
        tvSubHeading.setText("(Chief Asia Economist)");
        tvPhoneno.setText("Not Available");
        tvMailid.setText("freya@pantheonmacro.com");
        tvTweet.setText("@freyabeamish");

        RelativeLayout rlPhoneno = (RelativeLayout)dialog.findViewById(R.id.rlPhoneno);
        RelativeLayout rlMessage = (RelativeLayout)dialog.findViewById(R.id.rlMessage);
        RelativeLayout rlTweet = (RelativeLayout)dialog.findViewById(R.id.rlTweet);

        /*rlPhoneno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:+442037447430"));
                startActivity(intent);
                dialog.dismiss();
            }
        });*/

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
        if(id == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
