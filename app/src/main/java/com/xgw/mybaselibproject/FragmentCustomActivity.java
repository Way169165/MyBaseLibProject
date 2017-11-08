package com.xgw.mybaselibproject;

import android.support.v4.app.Fragment;
import android.view.View;

import com.xgw.mybaselib.base.BaseFragmentPagerActivity;
import com.xgw.mybaselib.widget.roundview.view.RoundViewTextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by XieGuangwei on 2017/11/7.
 * 通用tablayout自定义item布局测试
 */

public class FragmentCustomActivity extends BaseFragmentPagerActivity {
    @Override
    public void initView() {
        setToolbarCenter(true, "通用tablayout自定义item布局测试");
        super.initView();
        setTabIndicatorHeight(0);
    }

    @Override
    protected String[] getTitles() {
        return new String[0];
    }

    @Override
    protected List<Fragment> getFragments() {
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(new LazyFragment());
        fragments.add(new LazyFragment());
        fragments.add(new LazyFragment());
        return fragments;
    }

    @Override
    protected int getOffscreenPageLimit() {
        return 3;
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
        return 0;
    }

    @Override
    protected boolean isCustomTabLayout() {
        return true;
    }

    @Override
    protected List<View> getCustomViews() {
        List<View> items = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            View view = View.inflate(this, R.layout.item_tab_layout, null);
            RoundViewTextView roundViewTextView = (RoundViewTextView) view.findViewById(R.id.rvtv);
            roundViewTextView.getConfig().setStateSelectedEqualsPressed(true);
            items.add(view);
        }
        return items;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_fragment_custom;
    }
}
