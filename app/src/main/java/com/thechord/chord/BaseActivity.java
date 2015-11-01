package com.thechord.chord;

import android.app.Activity;
import android.os.Bundle;

import com.networkbench.agent.impl.NBSAppAgent;

import roboguice.RoboGuice;
import roboguice.activity.RoboActivity;

/**
 * Created by Neway on 2015/8/27.
 */
public abstract class BaseActivity extends RoboActivity {


    static {
        RoboGuice.setUseAnnotationDatabases(false);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        NBSAppAgent.setLicenseKey("2f2af63b7e014c088dff827df03f99d1").withLocationServiceEnabled(true).start(this);
        registerListener();
    }

    protected void registerListener(){

    }

}
