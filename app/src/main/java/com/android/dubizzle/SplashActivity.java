package com.android.dubizzle;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.Window;
import android.view.WindowManager;

import com.android.dubizzle.mvi.MainActivity;

public class SplashActivity extends Activity {
    int SPLASH_TIME = 2000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);
        new Handler(Looper.getMainLooper()).postDelayed(this::startMainActivity, SPLASH_TIME);
    }

    private void startMainActivity() {
        finish();
        Intent intent = new Intent(SplashActivity.this, MainActivity.class);
        startActivity(intent);
    }

}