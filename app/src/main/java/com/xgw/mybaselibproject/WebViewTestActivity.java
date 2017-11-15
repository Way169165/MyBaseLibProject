package com.xgw.mybaselibproject;

import android.support.v4.content.ContextCompat;
import android.view.View;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.widget.TextView;

import com.socks.library.KLog;
import com.xgw.mybaselib.base.BaseWebViewActivity;
import com.xgw.mybaselib.widget.roundview.view.RoundViewTextView;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by XieGuangwei on 2017/11/7.
 */

public class WebViewTestActivity extends BaseWebViewActivity {
    @BindView(R.id.right_tv)
    RoundViewTextView rightTv;
    @BindView(R.id.title)
    TextView titleTv;

    @Override
    public void initView() {
        setToolbarRightTv(true, "网页测试网页测试网页测试网页测试网页测试网页测试网页测试", "按钮样式");
        super.initView();
        rightTv.getConfig()
                .setBackgroundColor(ContextCompat.getColor(this, R.color.white))
                .setBackgroundColorPressed(ContextCompat.getColor(this, R.color.blue_50))
                .setTextColor(ContextCompat.getColor(this, R.color.black))
                .setTextColorPressed(ContextCompat.getColor(this, R.color.black_alpha_112))
                .setRadius(5)
                .setStrokeColor(ContextCompat.getColor(this, R.color.green_100))
                .setStrokeColorPressed(ContextCompat.getColor(this, R.color.green_700))
                .setStrokeWidth(3);
    }

    @Override
    protected String getUrl() {
        return "http://www.baidu.com";
    }

    @Override
    protected Object getJavaScriptInterface() {
        return null;
    }

    @Override
    protected String getNavWebManager() {
        return null;
    }

    /**
     * 是否需要进度条
     *
     * @return
     */
    @Override
    protected boolean hasProgress() {
        return true;
    }

    /**
     * 加载进度（如果有选择有进度）
     *
     * @param view        当前加载的webview
     * @param newProgress 进度
     */
    @Override
    protected void onProgress(WebView view, int newProgress) {
        KLog.e("progress:" + newProgress);
    }

    /**
     * 加载完毕
     *
     * @param view 加载的webview
     * @param url  加载的url
     */
    @Override
    protected void onFinished(WebView view, String url) {
        KLog.e("onFinished,url:" + url);
    }

    /**
     * 加载错误
     *
     * @param view    加载的webview
     * @param request 请求信息
     * @param error   错误信息
     */
    @Override
    protected void onError(WebView view, WebResourceRequest request, WebResourceError error) {
        KLog.e("onError");
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_web_view;
    }

    @OnClick({R.id.right_tv})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.right_tv:
                showToast("点击右边文字");
                break;
        }
    }
}
