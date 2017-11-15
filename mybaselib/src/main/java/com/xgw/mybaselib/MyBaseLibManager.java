package com.xgw.mybaselib;

import android.app.Application;

import com.socks.library.KLog;
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

    /**
     * 初始化工具类
     * @param application
     * @return
     */
    public MyBaseLibManager initUtils(Application application) {
        Utils.init(application);
        return this;
    }

    /**
     * 初始化log，设置是否显示（如：BuildConfig.LOG_DEBUG表示只在debug模式下显示)
     * @param isShowLog
     * @return
     */
    public MyBaseLibManager initLog(boolean isShowLog) {
        KLog.init(isShowLog);
        return this;
    }
}
