/**
 * @Module Name/Class		:	SplashScreeenActivity
 * @Author Name            :	Sombir Singh Bisht
 * @Date :	Sept 2, 2015
 * @Purpose :	This page/functionality is used to provide Login Splash Screen.
 */
package com.pantheon.android.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;

import com.pantheon.android.R;
import com.pantheon.android.utility.SharedPreferenceManager;

import java.util.Timer;
import java.util.TimerTask;

public class SplashScreenActivity extends AppCompatActivity {
    private static final int SPLASH_TIME_OUT = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash_screen);

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {

            public void run() {
                SharedPreferenceManager preferenceManager = SharedPreferenceManager.getInstance();

                if (preferenceManager.getUserToken(SplashScreenActivity.this) == null) {
                    Intent intent = new Intent(SplashScreenActivity.this, LoginActivity.class);
                    preferenceManager.setLoginCheck(SplashScreenActivity.this, true);
                    startActivity(intent);
                    finish();
                } else {
                    Intent intent = new Intent(SplashScreenActivity.this, HomeScreenActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        }, SPLASH_TIME_OUT);
    }
}
