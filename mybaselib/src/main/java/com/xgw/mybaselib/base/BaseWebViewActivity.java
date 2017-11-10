package com.xgw.mybaselib.base;

import android.annotation.SuppressLint;
import android.os.Build;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.xgw.mybaselib.R;

/**
 * Created by XieGuangwei on 2017/11/7.
 * 加载webview的activity基类
 */
@SuppressLint("JavascriptInterface")
public abstract class BaseWebViewActivity extends BaseActivity {
    protected WebView webView;
    protected ProgressBar progressBar;

    protected abstract String getUrl();

    @SuppressLint({"AddJavascriptInterface", "SetJavaScriptEnabled"})
    @Override
    public void initView() {
        webView = (WebView) findViewById(R.id.webView);
        progressBar = (ProgressBar) findViewById(R.id.progress_bar);

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

        //需要显示进度条才去添加
        if (hasProgress()) {
            webView.setWebChromeClient(new WebChromeClient() {
                @Override
                public void onProgressChanged(WebView view, int newProgress) {
                    if (newProgress == 100) {
                        dismissProgressLayout();
                    } else {
                        showProgressLayout();
                        progressBar.setProgress(newProgress);
                    }
                    super.onProgressChanged(view, newProgress);
                }
            });
        }

        webView.post(new Runnable() {
            @Override
            public void run() {
                webView.loadUrl(getUrl());
            }
        });
    }

    /**
     * 获取交互对象
     *
     * @return
     */
    protected abstract Object getJavaScriptInterface();

    /**
     * 获取交互桥字符串
     *
     * @return
     */
    protected abstract String getNavWebManager();

    /**
     * 是否需要进度条
     *
     * @return
     */
    protected abstract boolean hasProgress();

    @Override
    public void initData() {

    }

    /**
     * 显示加载进度页面
     */
    private void showProgressLayout() {
        progressBar.setVisibility(View.VISIBLE);
    }

    /**
     * 隐藏加载进度
     */
    private void dismissProgressLayout() {
        progressBar.setVisibility(View.GONE);
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
