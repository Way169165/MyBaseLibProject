package com.xgw.mybaselibproject;

import android.support.v4.app.Fragment;
import android.view.View;

import com.xgw.mybaselib.base.BaseFragmentPagerFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by XieGuangwei on 2017/11/7.
 * 通用fragmentpagerfragment测试
 */

public class FragmentTabTestFragment extends BaseFragmentPagerFragment {
    @Override
    protected String[] getTitles() {
        return new String[]{"fragment1","fragment1","fragment1"};
    }

    @Override
    protected List<Fragment> getFragments() {

        List<Fragment> fragments = new ArrayList<>();
        fragments.add(new TestFragment());
        fragments.add(new TestFragment());
        fragments.add(new TestFragment());
        return fragments;
    }

    @Override
    protected int getOffscreenPageLimit() {
        return 3;
    }

    @Override
    protected boolean hasDivider() {
        return false;
    }

    @Override
    protected int getDividerDrawableId() {
        return 0;
    }

    @Override
    protected int getDividerPadding() {
        return 0;
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
        return R.layout.view_pager_top_tab_layout;
    }
}
