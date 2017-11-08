package com.xgw.mybaselib.base;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.LinearLayout;

import com.xgw.mybaselib.R;
import com.xgw.mybaselib.utils.SizeUtils;

import java.util.List;

/**
 * Created by XieGuangwei on 2017/11/7.
 * tablayout+viewpager+fragment依附的fragment
 */

public abstract class BaseFragmentPagerFragment extends BaseFragment {
    protected ViewPager viewPager;
    protected TabLayout tabLayout;

    @Override
    public void initView() {
        if (getView() == null) {
            return;
        }
        viewPager = (ViewPager)getView().findViewById(R.id.viewPager);
        tabLayout = (TabLayout)getView().findViewById(R.id.tabLayout);
        if (isCustomTabLayout()) {
            viewPager.setAdapter(new BaseCustomViewPagerAdapter(getChildFragmentManager(),getFragments()));
        } else {
            viewPager.setAdapter(new BaseViewPagerAdapter(getChildFragmentManager(), getTitles(), getFragments()));
        }
        viewPager.setOffscreenPageLimit(getOffscreenPageLimit());//缓存页
        tabLayout.setupWithViewPager(viewPager);
        if (hasDivider() && getDividerDrawableId() != 0) {
            //设置分割线
            LinearLayout linearLayout = (LinearLayout) tabLayout.getChildAt(0);
            linearLayout.setShowDividers(LinearLayout.SHOW_DIVIDER_MIDDLE);
            linearLayout.setDividerDrawable(ContextCompat.getDrawable(getActivity(),
                    getDividerDrawableId())); //设置分割线的样式
            linearLayout.setDividerPadding(SizeUtils.dp2px(getDividerPadding())); //设置分割线间隔
        }
        if (isCustomTabLayout() && getCustomViews()!= null) {
            for (int i = 0;i < tabLayout.getTabCount();i ++) {
                TabLayout.Tab tab = tabLayout.getTabAt(i);
                if (tab != null && getCustomViews().get(i) != null)
                    tab.setCustomView(getCustomViews().get(i));
            }
        }
    }

    @Override
    public void initData() {

    }

    /**
     * 设置tab文字
     *
     * @return
     */
    protected abstract String[] getTitles();

    /**
     * 获取fragment列表
     *
     * @return
     */
    protected abstract List<Fragment> getFragments();

    /**
     * 获取缓存页数
     *
     * @return
     */
    protected abstract int getOffscreenPageLimit();

    /**
     * 是否设置分割线（设置了该条，一定得设置getDividerDrawableId()）
     *
     * @return
     */
    protected abstract boolean hasDivider();

    /**
     * 获取分割线drawable资源id
     *
     * @return
     */
    protected abstract int getDividerDrawableId();

    /**
     * 设置分割线距离上下边距
     *
     * @return
     */
    protected abstract int getDividerPadding();

    /**
     * 是否自定义tablayout布局
     * @return
     */
    protected abstract boolean isCustomTabLayout();

    /**
     * 获取自定义布局内容
     * @return
     */
    protected abstract List<View> getCustomViews();

    /**
     * 设置底部横条高度（为0就是无横条）
     *
     * @return
     */
    protected void setTabIndicatorHeight(int height) {
        if (tabLayout != null) {
            tabLayout.setSelectedTabIndicatorHeight(height);
        }
    }

    /**
     * 设置tab字体正常颜色、选中颜色
     *
     * @param normalColor
     * @param selectedColor
     */
    protected void setTabTextColors(int normalColor, int selectedColor) {
        if (tabLayout != null) {
            tabLayout.setTabTextColors(normalColor, selectedColor);
        }
    }

    /**
     * 设置tab下面横条滚动条颜色
     *
     * @param color
     */
    protected void setSelectedTabIndicatorColor(int color) {
        if (tabLayout != null) {
            tabLayout.setSelectedTabIndicatorColor(color);
        }
    }

    /**
     * 设置tablayout背景色
     *
     * @param color
     */
    protected void setTabLayoutBackgroundColor(int color) {
        if (tabLayout != null) {
            tabLayout.setBackgroundColor(color);
        }
    }

    /**
     * 设置tablayout滚动模式
     *
     * @param mode TabLayout.MODE_SCROLLABLE TabLayout.MODE_FIXED
     */
    protected void setTabMode(int mode) {
        if (tabLayout != null) {
            tabLayout.setTabMode(mode);
        }
    }
}
