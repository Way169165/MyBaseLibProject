package com.xgw.mybaselibproject;

import android.app.Application;

import com.xgw.mybaselib.MyBaseLibAgent;

/**
 * Created by XieGuangwei on 2017/10/27.
 */

public class AppConfig extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        MyBaseLibAgent.init(this);
    }
}
