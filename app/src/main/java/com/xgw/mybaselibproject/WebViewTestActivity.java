package com.xgw.mybaselibproject;

import android.support.v4.content.ContextCompat;
import android.view.View;

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

    @Override
    public void initView() {
        setToolbarRightTv(true, "返回", 0, "网页测试", "按钮样式");
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
