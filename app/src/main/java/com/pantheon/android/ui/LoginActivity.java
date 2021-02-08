/**
 @Page/Module Name/Class	:	LoginActivity
 @Author Name		:	Mr. Sombir Singh Bisht
 @Date				:	Aug 24,  2015
 @Purpose			:	This page/functionality is used to provide Login Screen.
 */
package com.pantheon.android.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.pantheon.android.R;
import com.pantheon.android.bean.Login;
import com.pantheon.android.http.BaseListener;
import com.pantheon.android.http.HttpConnectionUtil;
import com.pantheon.android.http.HttpConstant;
import com.pantheon.android.http.WebserviceType;
import com.pantheon.android.utility.AppUtility;
import com.pantheon.android.utility.EmailValidator;
import com.pantheon.android.utility.SharedPreferenceManager;


public class LoginActivity extends Activity implements BaseListener{
    private EditText etEmailAddress,etPassword;
    private Button btnSignIn,btnRequestTrail;
    private TextView tvForgotPassword;
    private CheckBox cbRememberCheck;
    private RelativeLayout rlRememberCheck;

    private final String APPTOKEN="J50pjO0d6rH3wzY3";
    private final String SOURCE="Android";
    private final boolean DOLOGIN=true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);

        etEmailAddress=(EditText)findViewById(R.id.etEmailAddress);
        etPassword=(EditText)findViewById(R.id.etPassword);
        btnSignIn=(Button)findViewById(R.id.btnSignIn);
        btnRequestTrail=(Button)findViewById(R.id.btnRequestTrail);
        tvForgotPassword=(TextView)findViewById(R.id.tvForgotPassword);
        cbRememberCheck=(CheckBox)findViewById(R.id.cbRememberCheck);
        rlRememberCheck=(RelativeLayout)findViewById(R.id.rlRememberCheck);

        final SharedPreferenceManager preferenceManager = SharedPreferenceManager.getInstance();
        etEmailAddress.setText(preferenceManager.getEmailStatus(this));
        etPassword.setText(preferenceManager.getPasswordStatus(this));
        cbRememberCheck.setChecked(preferenceManager.getRememberStatus(this));

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = etEmailAddress.getText().toString();
                String password = etPassword.getText().toString();

                if (TextUtils.isEmpty(email)) {
                    etEmailAddress.setError(getString(R.string.eemail_required));
                    etEmailAddress.requestFocus();
                } else if (!new EmailValidator().validate(email)) {
                    // To check invalid email
                    etEmailAddress.setError(getString(R.string.einvalid_email));
                    etEmailAddress.requestFocus();
                } else if (TextUtils.isEmpty(password)) {
                    etPassword.setError(getString(R.string.epassword_required));
                } else if (password.length() < 6) {
                    // if password contains less than 6 characters
                    etPassword.setError(getString(R.string.epassword_short));
                    etPassword.requestFocus();
                } else {
                    doLogin(etEmailAddress.getText().toString(), etPassword.getText().toString());
                }
            }
        });

        tvForgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, ForgotPasswordActivity.class);
                startActivity(intent);
            }
        });


        rlRememberCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferenceManager preferenceManager = SharedPreferenceManager.getInstance();

                if(!SharedPreferenceManager.getRememberStatus(LoginActivity.this)) {
                    cbRememberCheck.setChecked(true);
                    preferenceManager.setRememberStatus(LoginActivity.this, true);
                    saveEmailPassword();

                }else{
                    cbRememberCheck.setChecked(false);
                    preferenceManager.setRememberStatus(LoginActivity.this, false);
                    saveEmailPassword();
                }
            }
        });

        cbRememberCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferenceManager preferenceManager = SharedPreferenceManager.getInstance();

                if (!SharedPreferenceManager.getRememberStatus(LoginActivity.this)) {
                    cbRememberCheck.setChecked(true);
                    preferenceManager.setRememberStatus(LoginActivity.this, true);
                    saveEmailPassword();

                } else {
                    cbRememberCheck.setChecked(false);
                    preferenceManager.setRememberStatus(LoginActivity.this, false);
                    saveEmailPassword();
                }
            }
        });

        btnRequestTrail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(LoginActivity.this, ComplimentaryTrial.class);
                startActivity(intent);
            }
        });
    }

    /**
     @Function Name		:	doLogin
     @Author Name		:	Mr. Sombir Singh Bisht
     @Date				:	Aug, 24 2015
     @Param			    :	email, password  | String | User will login by using correct credentials.
     @Purpose			:   To login the app.
     */
    private void doLogin(String email,String password){
        Log.e("login", "doLogin: "+HttpConstant.LOGIN_URL );
        Login loginBean=new Login(HttpConstant.LOGIN_URL);
        loginBean.setUname(email);
        loginBean.setUpass(password);
        loginBean.setAppToken(APPTOKEN);
        loginBean.setDoLogin(DOLOGIN);
        loginBean.setSource(SOURCE);
        loginBean.setProgressEnable(true);
        saveEmailPassword();

        HttpConnectionUtil.callWebService(loginBean,this, WebserviceType.LOGIN,onWebServiceCompleteListener);
    }

    BaseListener.OnWebServiceCompleteListener onWebServiceCompleteListener=new BaseListener.OnWebServiceCompleteListener() {
        @Override
        public void onWebServiceComplete(Object baseObject) {
            Login loginBean=(Login)baseObject;

            System.out.println("Result2"+loginBean.result);
            Log.e("login", "loginresult: "+loginBean );

            if(loginBean.result == true){
                Intent intent= new Intent(LoginActivity.this,HomeScreenActivity.class);
                startActivity(intent);
                SharedPreferenceManager preferenceManager = SharedPreferenceManager.getInstance();
                preferenceManager.setRememberStatus(LoginActivity.this, true);
                preferenceManager.setUserEmail(LoginActivity.this, etEmailAddress.getText().toString());
                finish();

            }else{
                SharedPreferenceManager preferenceManager = SharedPreferenceManager.getInstance();
                preferenceManager.setUserEmail(LoginActivity.this, null);
                preferenceManager.setRememberStatus(LoginActivity.this, false);
                AppUtility.showAlertDialog(LoginActivity.this, "Login Status", "Please check your credentials.");
            }
        }
    };

    /**
     @Function Name		:	saveEmailPassword
     @Author Name		:	Mr. Sombir Singh Bisht
     @Date				:	Aug, 24 2015
     @Param			    :	NA
     @Purpose			:   To save email, password and remember status into local database.
     */
    public void saveEmailPassword(){
        if(cbRememberCheck.isChecked()) {
            SharedPreferenceManager preferenceManager = SharedPreferenceManager.getInstance();
            preferenceManager.setEmailStatus(this, etEmailAddress.getText().toString());
            preferenceManager.setPasswordStatus(this, etPassword.getText().toString());
            preferenceManager.setRememberStatus(LoginActivity.this, true);
            cbRememberCheck.setChecked(true);
        }
        else{
            SharedPreferenceManager preferenceManager=SharedPreferenceManager.getInstance();
            preferenceManager.setEmailStatus(this, null);
            preferenceManager.setPasswordStatus(this, null);
            preferenceManager.setRememberStatus(LoginActivity.this, false);
        }
    }
}
