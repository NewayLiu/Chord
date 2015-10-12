package com.thechord.chord.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Neway on 2015/10/12.
 */
public abstract class BaseFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view =  inflater.inflate(getFragmentLayout(),container,false);
        initView(view);
        registerListener();
        return view;
    }

    protected abstract int getFragmentLayout();

    protected abstract void initView(View view);

    protected abstract void registerListener();

}
