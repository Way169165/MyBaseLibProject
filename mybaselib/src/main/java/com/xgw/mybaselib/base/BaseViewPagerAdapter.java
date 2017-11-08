package com.xgw.mybaselib.base;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by XieGuangwei on 2017/11/7.
 * viewpager适配器
 */

public class BaseViewPagerAdapter extends FragmentPagerAdapter {
    private List<Fragment> tab_fragments;
    private String[] tv_Titles;

    public BaseViewPagerAdapter(FragmentManager fm,
                                String[] tv_Titles,
                                List<Fragment> tab_fragments) {
        super(fm);
        this.tv_Titles = tv_Titles;
        this.tab_fragments = tab_fragments;
    }

    public BaseViewPagerAdapter(FragmentManager fm,
                                List<Fragment> tab_fragments) {
        super(fm);
        this.tab_fragments = tab_fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return tab_fragments.get(position);
    }

    @Override
    public int getCount() {
        return tab_fragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if (tv_Titles == null) {
            return null;
        } else {
            return tv_Titles[position];
        }
    }
}
