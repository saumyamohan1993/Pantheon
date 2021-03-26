package com.pantheon.macroandroid.ui;

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

import com.pantheon.macroandroid.R;
import com.pantheon.macroandroid.bean.Reset;
import com.pantheon.macroandroid.http.BaseListener;
import com.pantheon.macroandroid.http.HttpConnectionUtil;
import com.pantheon.macroandroid.http.HttpConstant;
import com.pantheon.macroandroid.http.WebserviceType;
import com.pantheon.macroandroid.utility.EmailValidator;

public class ForgotPasswordActivity extends AppCompatActivity implements BaseListener {
    private final String APPTOKEN = "J50pjO0d6rH3wzY3";
    private final boolean DORESET = true;
    private final String SOURCE = "Android";
    private TextView tvForgotPasswordStatement;
    private Button btnResetPassword;
    private EditText etmailid;
    BaseListener.OnWebServiceCompleteListener onWebServiceCompleteListener = new BaseListener.OnWebServiceCompleteListener() {
        @Override
        public void onWebServiceComplete(Object baseObject) {
            Reset resetBean = (Reset) baseObject;
            if (resetBean.result == true) {
                Toast.makeText(ForgotPasswordActivity.this, "You'll receive an email shortly containing new password.", Toast.LENGTH_LONG).show();
                etmailid.setText(null);
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        tvForgotPasswordStatement = (TextView) findViewById(R.id.tvForgotPasswordStatement);
        btnResetPassword = (Button) findViewById(R.id.btnResetPassword);
        etmailid = (EditText) findViewById(R.id.etmailid);

        String customColorText = getResources().getString(R.string.forgot);
        tvForgotPasswordStatement.setText(Html.fromHtml(customColorText));

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        btnResetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = etmailid.getText().toString();
                if (TextUtils.isEmpty(email)) {
                    etmailid.setError(getString(R.string.eemail_required));
                } else if (!new EmailValidator().validate(email)) {
                    etmailid.setError(getString(R.string.errorinvalid_email));
                } else {
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_forgot_password, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        if (id == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
