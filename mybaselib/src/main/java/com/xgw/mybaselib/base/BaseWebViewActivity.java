package com.xgw.mybaselib.base;

import android.annotation.SuppressLint;
import android.os.Build;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
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

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                onFinished(view, url);
            }

            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                super.onReceivedError(view, request, error);
                onError(view, request, error);
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
                    onProgress(view, newProgress);
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

    /**
     * 加载进度（如果有选择有进度）
     *
     * @param view        当前加载的webview
     * @param newProgress 进度
     */
    protected void onProgress(WebView view, int newProgress) {

    }

    /**
     * 加载完毕
     *
     * @param view 加载的webview
     * @param url  加载的url
     */
    protected void onFinished(WebView view, String url) {

    }

    /**
     * 加载错误
     *
     * @param view    加载的webview
     * @param request 请求信息
     * @param error   错误信息
     */
    protected void onError(WebView view, WebResourceRequest request, WebResourceError error) {

    }

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
    public void onBackPressed() {
        if (webView.canGoBack()) {
            webView.goBack();// 返回前一个页面
        } else {
            super.onBackPressed();
        }
    }
}
