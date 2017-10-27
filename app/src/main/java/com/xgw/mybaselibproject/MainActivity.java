package com.xgw.mybaselibproject;

import com.xgw.mybaselib.base.BaseActivity;

public class MainActivity extends BaseActivity {
    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {
        setToolbar(false,"测试项目");
    }

    @Override
    public void initData() {

    }
}
