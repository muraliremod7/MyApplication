package com.omnispace.marketing.Activities;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.preference.PreferenceManager;

import com.omnispace.marketing.R;

public class SplashScreen extends Activity {

    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler()
    {
        public void handleMessage(Message msg)
        {
            Intent i = new Intent(SplashScreen.this, DataActivity.class);
            startActivity(i);
            finish();
        }
    };
    private static int SPLASH_TIME_OUT = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        if (!prefs.getBoolean("first_time", false)) {
            SharedPreferences.Editor editor = prefs.edit();
            editor.putBoolean("first_time", true);
            editor.commit();
            Intent i = new Intent(SplashScreen.this, SplashScreen.class);
            startActivity(i);
            finish();
        } else {
            setContentView(R.layout.splash_screen);
            handler.sendEmptyMessageDelayed(0, 2000);
        }
    }
    }

