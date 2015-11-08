package com.thechord.chord.fragment;

import android.os.Bundle;
import android.view.View;

import roboguice.fragment.RoboFragment;


/**
 * Created by Neway on 2015/10/12.
 */
public abstract class BaseFragment extends RoboFragment {


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        registerListener();
    }

    protected void registerListener(){

    }
}


