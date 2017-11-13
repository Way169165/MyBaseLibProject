package com.xgw.mybaselib;

import android.app.Application;

import com.xgw.mybaselib.utils.Utils;

/**
 * Created by XieGuangwei on 2017/10/27.
 * 基类项目库代理，主要用来初始化一些操作
 */

public class MyBaseLibManager {
    private static MyBaseLibManager instance;

    public static MyBaseLibManager getInstance() {
        if (instance == null) {
            synchronized (MyBaseLibManager.class) {
                instance = new MyBaseLibManager();
            }
        }
        return instance;
    }

    public MyBaseLibManager initUtils(Application application) {
        Utils.init(application);
        return this;
    }
}
