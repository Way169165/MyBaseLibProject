package com.xgw.mybaselib.rxhttp;

import com.xgw.mybaselib.rxhttp.helper.DownloadConfig;
import com.xgw.mybaselib.rxhttp.helper.GlobalConfig;
import com.xgw.mybaselib.rxhttp.helper.SingleConfig;
import com.xgw.mybaselib.rxhttp.helper.UploadConfig;

/**
 * Created by XieGuangwei on 2017/11/6.
 * rxjava网络请求
 */

public class RxHttpUtils {
    /**
     * 获取全局配置帮助对象
     *
     * @return
     */
    public static GlobalConfig getGlobalConfig() {
        return GlobalConfig.getInstance();
    }

    /**
     * 获取单个请求配置帮助类
     *
     * @return
     */
    public static SingleConfig getSingleConfig() {
        return new SingleConfig();
    }

    /**
     * 获取文件下载配置类
     * @return
     */
    public static DownloadConfig getDownloadConfig () {
        return new DownloadConfig();
    }

    /**
     * 获取文件上传配置类
     * @return
     */
    public static UploadConfig getUploadConfig () {
        return new UploadConfig();
    }
}
