package com.xgw.mybaselib.rxhttp.client;

import okhttp3.OkHttpClient;

/**
 * Created by XieGuangwei on 2017/11/6.
 * okhttp管理类
 */

public class HttpClient {
    private static HttpClient instance;
    private static OkHttpClient.Builder builder;

    private HttpClient () {
        builder = new OkHttpClient.Builder();
    }
    public static HttpClient getInstance () {
        if (instance == null) {
            synchronized (HttpClient.class) {
                instance = new HttpClient();
            }
        }
        return instance;
    }

    public OkHttpClient.Builder getBuilder() {
        return builder;
    }
}
