package com.thechord.chord;

import android.os.Bundle;

import com.networkbench.agent.impl.NBSAppAgent;

import roboguice.activity.RoboFragmentActivity;

/**
 * Created by Neway on 2015/10/12.
 */
public abstract class BaseFragmentActivity extends RoboFragmentActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        NBSAppAgent.setLicenseKey("2f2af63b7e014c088dff827df03f99d1").withLocationServiceEnabled(true).start(this);
        setContentView(getActivityLayout());
        initView();
        registerListener();
    }

    protected abstract int getActivityLayout();

    protected abstract void initView();

    protected void registerListener(){

    }
}
