package com.xgw.mybaselibproject;

import android.app.Application;

import com.xgw.mybaselib.MyBaseLibDelegate;
import com.xgw.mybaselib.rxhttp.RxHttpUtils;

/**
 * Created by XieGuangwei on 2017/10/27.
 */

public class AppConfig extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        MyBaseLibDelegate.init(this);
        RxHttpUtils.getGlobalConfig()
                .isShowLog(true)
                .setCache()
                .setBaseUrl(UrlConstants.BASE_URL)
                .setConnectTimeout(10)
                .setReadTimeout(10)
                .setWriteTimeout(10);
    }
}
