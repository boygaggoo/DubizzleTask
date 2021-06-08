package com.android.dubizzle;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.android.dubizzle.mvi.ui.MainActivity;
import com.android.dubizzle.mvi.util.HCheckInternet;

import static com.android.dubizzle.mvi.util.Constants.SPLASH_TIME;

public class SplashActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);
        HCheckInternet nH_checkInternet = new HCheckInternet();
        if (nH_checkInternet.isNetworkRedconnect(this)) {
            // * There is a network connection * /
            new Handler(Looper.getMainLooper()).postDelayed(this::startMainActivity, SPLASH_TIME);
        } else {
            // * There is no network connection * /
            Toast.makeText(getApplicationContext(),"No Internet",Toast.LENGTH_SHORT).show();
        }
    }

    private void startMainActivity() {

        finish();
        Intent intent = new Intent(SplashActivity.this, MainActivity.class);
        startActivity(intent);
    }

}