package com.thechord.chord;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import roboguice.inject.ContentView;


@ContentView(R.layout.activity_splash)
public class SplashActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent();
                intent.setClass(SplashActivity.this,MainActivity.class);
                startActivity(intent);
            }
        },3 * 1000);
    }
}
