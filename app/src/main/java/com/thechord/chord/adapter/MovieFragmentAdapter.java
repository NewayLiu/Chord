package com.thechord.chord.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.thechord.chord.fragment.BaseFragment;

import java.util.List;

/**
 * Created by Neway on 2015/10/12.
 */
public class MovieFragmentAdapter extends FragmentPagerAdapter {

    private List<BaseFragment> fragmentList;

    public MovieFragmentAdapter(FragmentManager fm, List<BaseFragment> fragmentList) {
        super(fm);
        this.fragmentList = fragmentList;
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }
}
