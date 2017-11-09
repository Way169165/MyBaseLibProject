package com.xgw.mybaselib.rxhttp.helper;

import android.text.TextUtils;
import android.util.Log;

import com.xgw.mybaselib.rxhttp.cache.MyCacheInterceptor;
import com.xgw.mybaselib.rxhttp.client.HttpClient;
import com.xgw.mybaselib.rxhttp.client.RetrofitClient;
import com.xgw.mybaselib.utils.Utils;

import java.io.File;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * Created by XieGuangwei on 2017/11/6.
 * 全局请求配置帮助类（在application配置好，通用）
 * 配置好后，所有的用这一个，不在修改，若修改将影响全部
 */

public class GlobalConfig {
    private static GlobalConfig instance;

    public static GlobalConfig getInstance() {
        if (instance == null) {
            synchronized (GlobalConfig.class) {
                instance = new GlobalConfig();
            }
        }
        return instance;
    }


    /**
     * 设置baseUrl
     *
     * @param baseUrl
     * @return
     */
    public GlobalConfig setBaseUrl(String baseUrl) {
        RetrofitClient.getInstance().getRetrofitBuilder().baseUrl(baseUrl);
        return this;
    }

    /**
     * 设置读取超时时间
     *
     * @param second
     * @return
     */
    public GlobalConfig setReadTimeout(long second) {
        HttpClient.getInstance().getBuilder().readTimeout(second, TimeUnit.SECONDS);
        return this;
    }

    /**
     * 设置写入超时时间
     *
     * @param second
     * @return
     */
    public GlobalConfig setWriteTimeout(long second) {
        HttpClient.getInstance().getBuilder().readTimeout(second, TimeUnit.SECONDS);
        return this;
    }

    /**
     * 设置连接超时时间
     *
     * @param second
     * @return
     */
    public GlobalConfig setConnectTimeout(long second) {
        HttpClient.getInstance().getBuilder().readTimeout(second, TimeUnit.SECONDS);
        return this;
    }

    /**
     * 是否展示log
     *
     * @param isShowLog
     * @return
     */
    public GlobalConfig isShowLog(boolean isShowLog) {
        if (isShowLog) {
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
                @Override
                public void log(String message) {
                    Log.e("RxHttpUtils", message);
                }
            });
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            HttpClient.getInstance().getBuilder().addInterceptor(loggingInterceptor);
        }
        return this;
    }

    /**
     * 开启缓存
     *
     * @return
     */
    public GlobalConfig setCache(String cachePath, long maxCacheSize) {
        MyCacheInterceptor cacheInterceptor = new MyCacheInterceptor();
        Cache cache;
        //设置缓存路径不为空，切设置的缓存大小大于0，则新建缓存
        if (!TextUtils.isEmpty(cachePath) && maxCacheSize > 0) {
            cache = new Cache(new File(cachePath), maxCacheSize);
        } else {
            //否则默认缓存路径和大小
            cache = new Cache(new File(Utils.getApp().getCacheDir() + "/cache")
                    , 1024 * 1024 * 10);
        }
        HttpClient.getInstance().getBuilder().addInterceptor(cacheInterceptor)
                .addNetworkInterceptor(cacheInterceptor)
                .cache(cache);
        return this;
    }

    /**
     * 开启缓存
     *
     * @return
     */
    public GlobalConfig setCache() {
        setCache(null, 0);
        return this;
    }

    /**
     * 创建全局请求
     *
     * @param cls
     * @param <K>
     * @return
     */
    public <K> K createApi(Class<K> cls) {
        return RetrofitClient.getInstance().getRetrofit().create(cls);
    }
}
