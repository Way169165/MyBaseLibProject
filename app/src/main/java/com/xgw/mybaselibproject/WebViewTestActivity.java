package com.xgw.mybaselibproject;

import com.xgw.mybaselib.base.BaseWebViewActivity;

/**
 * Created by XieGuangwei on 2017/11/7.
 */

public class WebViewTestActivity extends BaseWebViewActivity {
    @Override
    public void initView() {
        setToolbarCenter(false,"网页测试");
        super.initView();
    }

    @Override
    protected String getUrl() {
        return "http://www.baidu.com";
    }

    @Override
    protected Object getJavaScriptInterface() {
        return null;
    }

    @Override
    protected String getNavWebManager() {
        return null;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_web_view;
    }
}
