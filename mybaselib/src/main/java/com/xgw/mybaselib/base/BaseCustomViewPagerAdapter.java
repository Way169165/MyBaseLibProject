package com.xgw.mybaselib.base;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.view.View;

import java.util.List;

/**
 * Created by XieGuangwei on 2017/11/7.
 * 自定义tab布局的适配器
 */

public class BaseCustomViewPagerAdapter extends FragmentPagerAdapter {


    private List<Fragment> tab_fragments;

    public BaseCustomViewPagerAdapter(FragmentManager fm,
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
}
