package com.xgw.mybaselib;

import android.app.Application;

import com.xgw.mybaselib.utils.Utils;

/**
 * Created by XieGuangwei on 2017/10/27.
 * 基类项目库代理，主要用来初始化一些操作
 */

public class MyBaseLibAgent {
    public static void init(Application application) {
        Utils.init(application);
    }
}
