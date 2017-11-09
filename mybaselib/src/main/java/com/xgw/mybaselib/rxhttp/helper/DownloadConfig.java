package com.xgw.mybaselib.rxhttp.helper;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.xgw.mybaselib.rxhttp.progress.ProgressResponseBody;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by XieGuangwei on 2017/11/9.
 * 文件下载配置帮助类
 */

public class DownloadConfig {
    private String baseUrl = "https://github.com/Way169165/";
    private long readTimeout;
    private long writeTimeout;
    private long connectTimeout;
    private ProgressResponseBody.ProgressListener progressListener;

    public DownloadConfig setReadTimeout(long readTimeout) {
        this.readTimeout = readTimeout;
        return this;
    }

    public DownloadConfig setWriteTimeout(long writeTimeout) {
        this.writeTimeout = writeTimeout;
        return this;
    }

    public DownloadConfig setConnectTimeout(long connectTimeout) {
        this.connectTimeout = connectTimeout;
        return this;
    }

    public DownloadConfig setProgressListener(ProgressResponseBody.ProgressListener progressListener) {
        this.progressListener = progressListener;
        return this;
    }

    public <K> K createApi(Class<K> cls) {
        return getRetrofit().create(cls);
    }

    private Retrofit getRetrofit() {
        Retrofit.Builder builder = new Retrofit.Builder();
        return builder.baseUrl(baseUrl)
                .client(getOkHttpClient())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    private OkHttpClient getOkHttpClient() {
        Interceptor interceptor = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Response originalResponse = chain.proceed(chain.request());
                Response.Builder builder = originalResponse.newBuilder();
                if (progressListener != null) {
                    builder.body(new ProgressResponseBody(originalResponse.body(), progressListener));
                }
                return builder.build();
            }
        };

        return new OkHttpClient.Builder()
                .connectTimeout(connectTimeout == 0 ? 30 : connectTimeout, TimeUnit.SECONDS)
                .readTimeout(readTimeout == 0 ? 30 : readTimeout, TimeUnit.SECONDS)
                .writeTimeout(writeTimeout == 0 ? 30 : writeTimeout, TimeUnit.SECONDS)
                .addInterceptor(interceptor)
                .build();
    }
}
