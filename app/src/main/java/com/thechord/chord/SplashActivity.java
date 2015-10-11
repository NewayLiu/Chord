package com.thechord.chord;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;


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

    @Override
    protected int getActivityLayout() {
        return R.layout.activity_splash;
    }

    @Override
    protected void initView() {

    }
}
