package com.xgw.mybaselib.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.socks.library.KLog;

/**
 * Created by XieGuangwei on 2017/11/8.
 * 懒加载fragment基类
 */

public abstract class BaseLazyFragment extends BaseFragment {
    protected boolean isViewCreated;//onCreateView是否执行完
    protected boolean isUIVisible;//UI是否可见
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        isViewCreated = true;
        lazyLoad();
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        //isVisibleToUser这个boolean值表示:该Fragment的UI 用户是否可见
        if (isVisibleToUser) {
            isUIVisible = true;
            lazyLoad();
        } else {
            isUIVisible = false;
        }
    }

    private void lazyLoad() {
        //这里进行双重标记判断,是因为setUserVisibleHint会多次回调,并且会在onCreateView执行前回调,必须确保onCreateView加载完毕且页面可见,才加载数据
        if (isViewCreated && isUIVisible) {
            startLazyLoad();
            //数据加载完毕,恢复标记,防止重复加载
            isViewCreated = false;
            isUIVisible = false;

            KLog.e("可见,加载数据");
        }
    }


    protected abstract void startLazyLoad();


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        //页面销毁,恢复标记
        isViewCreated = false;
        isUIVisible = false;

        KLog.e("销毁了");
    }
}
