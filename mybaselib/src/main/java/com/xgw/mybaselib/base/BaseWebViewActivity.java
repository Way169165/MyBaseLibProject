package com.xgw.mybaselib.base;

import android.annotation.SuppressLint;
import android.os.Build;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.xgw.mybaselib.R;

/**
 * Created by XieGuangwei on 2017/11/7.
 * 加载webview的activity基类
 */
@SuppressLint("JavascriptInterface")
public abstract class BaseWebViewActivity extends BaseActivity {
    protected WebView webView;

    protected abstract String getUrl();

    @SuppressLint({"AddJavascriptInterface", "SetJavaScriptEnabled"})
    @Override
    public void initView() {
        webView = (WebView) findViewById(R.id.webView);

        WebSettings webSettings = webView.getSettings();
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setBuiltInZoomControls(false);
        webSettings.setDatabaseEnabled(true);
        webSettings.setDomStorageEnabled(true);
        webSettings.setAllowContentAccess(true);
        webSettings.setAllowFileAccess(true);
        webSettings.setAllowContentAccess(true);
        webSettings.setAppCacheEnabled(false);

        //只支持api16以上
        if (Build.VERSION.SDK_INT >= 16) {
            webSettings.setAllowUniversalAccessFromFileURLs(true);
            webSettings.setAllowFileAccessFromFileURLs(true);
        }

        //设置javascript可用
        webSettings.setJavaScriptEnabled(true);
        //阻止弹出浏览器
        webView.setWebViewClient(new WebViewClient() {

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                view.loadUrl(getUrl());
                return true;
            }
        });

        //注册javascript监听
        if (getJavaScriptInterface() != null && !TextUtils.isEmpty(getNavWebManager())) {
            webView.addJavascriptInterface(getJavaScriptInterface(), getNavWebManager());
        }

        webView.loadUrl(getUrl());
    }

    protected abstract Object getJavaScriptInterface();

    protected abstract String getNavWebManager();

    @Override
    public void initData() {

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (webView.canGoBack()) {
                webView.goBack();// 返回前一个页面
            } else {
                onBackPressed();
            }
        }
        return true;
    }
}
