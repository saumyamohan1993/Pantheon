
package com.pantheon.android.ui;

import android.app.Activity;
import android.os.Bundle;

import android.text.Html;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.pantheon.android.R;
import com.pantheon.android.bean.Reset;
import com.pantheon.android.http.BaseListener;
import com.pantheon.android.http.HttpConnectionUtil;
import com.pantheon.android.http.HttpConstant;
import com.pantheon.android.http.WebserviceType;
import com.pantheon.android.utility.EmailValidator;


public class ForgotPasswordActivity extends AppCompatActivity implements BaseListener {
    private TextView tvForgotPasswordStatement;
    private Button btnResetPassword;
    private EditText etmailid;

    private final String APPTOKEN = "J50pjO0d6rH3wzY3";
    private final boolean DORESET = true;
    private final String SOURCE = "Android";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        tvForgotPasswordStatement=(TextView)findViewById(R.id.tvForgotPasswordStatement);
        btnResetPassword=(Button)findViewById(R.id.btnResetPassword);
        etmailid=(EditText)findViewById(R.id.etmailid);

        String customColorText=getResources().getString(R.string.forgot);
        tvForgotPasswordStatement.setText(Html.fromHtml(customColorText));

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        btnResetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = etmailid.getText().toString();

                if (TextUtils.isEmpty(email)) {
                    etmailid.setError(getString(R.string.eemail_required));
                }else if (!new EmailValidator().validate(email)) {
                    // check invalid email
                    etmailid.setError(getString(R.string.errorinvalid_email));
                }else {
                    reset(etmailid.getText().toString());
                }
            }
        });
    }


    private void reset(String email) {
        Reset resetBean = new Reset(HttpConstant.RESET_URL);
        resetBean.setApptoken(APPTOKEN);
        resetBean.setDoreset(DORESET);
        resetBean.setSource(SOURCE);
        resetBean.setUname(email);

        HttpConnectionUtil.callWebService(resetBean, this, WebserviceType.RESET, onWebServiceCompleteListener);
    }

    BaseListener.OnWebServiceCompleteListener onWebServiceCompleteListener=new BaseListener.OnWebServiceCompleteListener() {
        @Override
        public void onWebServiceComplete(Object baseObject) {
            Reset resetBean=(Reset)baseObject;

            System.out.println("Result forget "+resetBean.result);

            if(resetBean.result == true){

                Toast.makeText(ForgotPasswordActivity.this, "You'll receive an email shortly containing new password.", Toast.LENGTH_LONG).show();
                etmailid.setText(null);
            }
        }
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_forgot_password, menu);
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
