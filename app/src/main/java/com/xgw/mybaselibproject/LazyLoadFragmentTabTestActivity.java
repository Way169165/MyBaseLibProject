package com.xgw.mybaselibproject;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.view.View;

import com.xgw.mybaselib.base.BaseFragmentPagerActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by XieGuangwei on 2017/11/8.
 */

public class LazyLoadFragmentTabTestActivity extends BaseFragmentPagerActivity {
    @Override
    public void initView() {
        setToolbarCenter(true, "懒加载测试");
        super.initView();
        setTabMode(TabLayout.MODE_SCROLLABLE);
    }

    @Override
    protected String[] getTitles() {
        return new String[]{"lazy1", "lazy1", "lazy1", "lazy1", "lazy1", "lazy1", "lazy1", "lazy1"};
    }

    @Override
    protected List<Fragment> getFragments() {
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(new LazyLoadRecyclerTestFragment());
        fragments.add(new LazyLoadRecyclerTestFragment());
        fragments.add(new LazyLoadRecyclerTestFragment());
        fragments.add(new LazyLoadRecyclerTestFragment());
        fragments.add(new LazyLoadRecyclerTestFragment());
        fragments.add(new LazyLoadRecyclerTestFragment());
        fragments.add(new LazyLoadRecyclerTestFragment());
        fragments.add(new LazyLoadRecyclerTestFragment());
        return fragments;
    }

    @Override
    protected int getOffscreenPageLimit() {
        return 8;
    }

    @Override
    protected boolean hasDivider() {
        return true;
    }

    @Override
    protected int getDividerDrawableId() {
        return R.drawable.tab_layout_divider;
    }

    @Override
    protected int getDividerPadding() {
        return 10;
    }

    @Override
    protected boolean isCustomTabLayout() {
        return false;
    }

    @Override
    protected List<View> getCustomViews() {
        return null;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_fragment_pager;
    }
}
