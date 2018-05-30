package com.ledt.adapter;

import android.app.*;
import android.support.v4.app.*;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lenovo on 2018/5/30.
 */

public class MyPagerAdapter extends FragmentPagerAdapter {
    List<Fragment>fragmentLists;
    ArrayList<String>titlelist;
    public MyPagerAdapter(FragmentManager fm,List<Fragment> fragmentLists,ArrayList<String>titlelist) {
        super(fm);
        this.fragmentLists=fragmentLists;
        this.titlelist=titlelist;
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentLists.get(position);
    }

    @Override
    public int getCount() {
        return fragmentLists.size();
    }

    //可以直接给tablayout
    @Override
    public CharSequence getPageTitle(int position) {
        return titlelist.get(position);
    }
}
