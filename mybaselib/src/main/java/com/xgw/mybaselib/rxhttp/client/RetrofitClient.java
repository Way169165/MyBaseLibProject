package com.xgw.mybaselib.rxhttp.client;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by XieGuangwei on 2017/11/6.
 * retrofit管理类
 */

public class RetrofitClient {
    private static RetrofitClient instance;
    private static Retrofit.Builder mRetrofitBuilder;
    private static OkHttpClient.Builder mOkHttpClientBuilder;

    public static RetrofitClient getInstance() {
        if (instance == null) {
            synchronized (RetrofitClient.class) {
                instance = new RetrofitClient();
            }
        }
        return instance;
    }

    private RetrofitClient() {
        mOkHttpClientBuilder = HttpClient.getInstance().getBuilder();
        mRetrofitBuilder = new Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(mOkHttpClientBuilder.build());
    }

    public Retrofit.Builder getRetrofitBuilder() {
        return mRetrofitBuilder;
    }

    public Retrofit getRetrofit() {
        return mRetrofitBuilder.client(mOkHttpClientBuilder.build()).build();
    }
}
