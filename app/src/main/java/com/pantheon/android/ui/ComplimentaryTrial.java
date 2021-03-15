
package com.pantheon.android.ui;

import android.app.Activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.pantheon.android.R;
import com.pantheon.android.utility.EmailValidator;

public class ComplimentaryTrial extends AppCompatActivity {
    private EditText etName, etCompany, etEmailAddress;
    private CheckBox cbUsEconomy, cbLatAmEconomy, cbEuroEconomy, cbUkEconomy, cbAll;
    private Button btnActivateFreeTrail;
    private String interest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complimentary_trial);

        etName=(EditText)findViewById(R.id.etName);
        etCompany=(EditText)findViewById(R.id.etCompany);
        etEmailAddress=(EditText)findViewById(R.id.etEmailAddress);
        cbUsEconomy=(CheckBox)findViewById(R.id.cbUsEconomy);
        cbLatAmEconomy=(CheckBox)findViewById(R.id.cbLatAmEconomy);
        cbEuroEconomy=(CheckBox)findViewById(R.id.cbEuroEconomy);
        cbUkEconomy=(CheckBox)findViewById(R.id.cbUkEconomy);
        cbAll=(CheckBox)findViewById(R.id.cbAll);
        btnActivateFreeTrail=(Button)findViewById(R.id.btnActivateFreeTrail);

        btnActivateFreeTrail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = etName.getText().toString();
                String company = etCompany.getText().toString();
                String email = etEmailAddress.getText().toString();

                if (TextUtils.isEmpty(name)) {
                    etName.setError(getString(R.string.ename_required));
                    etName.requestFocus();
                } else if (TextUtils.isEmpty(company)) {
                    etCompany.setError(getString(R.string.ecompany_required));
                    etCompany.requestFocus();
                } else if (TextUtils.isEmpty(email)) {
                    etEmailAddress.setError(getString(R.string.erroremail_required));
                    etEmailAddress.requestFocus();
                } else if (!new EmailValidator().validate(email)) {
                    // check invalid email
                    etEmailAddress.setError(getString(R.string.errorinvalid_email));
                    etEmailAddress.requestFocus();
                } else if ((!cbUsEconomy.isChecked()) && (!cbEuroEconomy.isChecked()) && (!cbLatAmEconomy.isChecked()) && (!cbUkEconomy.isChecked()) && (!cbAll.isChecked())) {
                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(ComplimentaryTrial.this);
                    alertDialogBuilder.setMessage(getString(R.string.user_interest));
                    alertDialogBuilder.setPositiveButton("Ok",
                            new DialogInterface.OnClickListener() {

                                @Override
                                public void onClick(DialogInterface arg0, int arg1) {
                                }
                            });
                   AlertDialog alertDialog = alertDialogBuilder.create();
                    alertDialog.show();
                } else {
                    if (cbUsEconomy.isChecked()) {
                        interest = "US Economy";
                    } else if (cbEuroEconomy.isChecked()) {
                        interest = "Euro Economy";
                    } else if (cbLatAmEconomy.isChecked()) {
                        interest = "LatAm Economy";
                    } else if(cbUkEconomy.isChecked()){
                        interest = "UK Economy";
                    }else {
                        interest = "US Economy, " + "Euro Economy, " + "LatAm Economy" + "UK Economy";
                    }
                    Intent intent = new Intent(Intent.ACTION_SEND);
                    intent.setType("text/plain");
                    intent.putExtra(Intent.EXTRA_EMAIL, new String[]{getString(R.string.receive_email)});
                    intent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.send_subject));
                    intent.putExtra(Intent.EXTRA_TEXT, "Name: " + name + "\n" + "Company: " + company + "\n" + "Email: " + email + "\n" + "Areas of Interest: " + interest);
                    startActivity(intent);
                }

            }
        });

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_complimentary_trial, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        if(id == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }


}
