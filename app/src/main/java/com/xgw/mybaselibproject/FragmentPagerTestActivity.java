package com.xgw.mybaselibproject;

import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.view.View;

import com.xgw.mybaselib.base.BaseFragmentPagerActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by XieGuangwei on 2017/11/7.
 * 同样fragmentactivity测试
 */

public class FragmentPagerTestActivity extends BaseFragmentPagerActivity {
    @Override
    public void initView() {
        setToolbarCenter(true,"通用FragmentActivity测试");
        super.initView();
        setSelectedTabIndicatorColor(Color.RED);//设置选中时tab滚动条颜色
        setTabTextColors(Color.BLACK,Color.BLUE);//设置tab选中时文字颜色
        setTabLayoutBackgroundColor(Color.YELLOW);//设置tablayout背景色
        setTabMode(TabLayout.MODE_SCROLLABLE);//设置tab模式（可滚动）
        setTabIndicatorHeight(0);//设置滚动条高度（为0不显示）
    }

    @Override
    protected String[] getTitles() {
        return new String[]{"lazy1", "lazy2", "lazy3","lazy1", "lazy2", "lazy3","lazy1", "lazy2", "lazy3"};
    }

    @Override
    protected List<Fragment> getFragments() {
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(new FragmentPagerFragment());
        fragments.add(new LazyFragment());
        fragments.add(new LazyFragment());
        fragments.add(new LazyFragment());
        fragments.add(new LazyFragment());
        fragments.add(new LazyFragment());
        fragments.add(new LazyFragment());
        fragments.add(new LazyFragment());
        fragments.add(new LazyFragment());
        return fragments;
    }

    @Override
    protected int getOffscreenPageLimit() {
        return 10;
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
