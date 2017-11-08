package com.xgw.mybaselib.widget.roundview.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

import com.xgw.mybaselib.widget.roundview.config.BaseConfig;
import com.xgw.mybaselib.widget.roundview.config.RoundViewConfig;

/**
 * Created by XieGuangwei on 2017/11/1.
 * 自定义圆角相对布局
 */

public class RoundViewRelativeLayout extends RelativeLayout {
    private BaseConfig config;

    public RoundViewRelativeLayout(Context context) {
        this(context, null);
    }

    public RoundViewRelativeLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RoundViewRelativeLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        config = new RoundViewConfig(context, this, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int size[] = config.onMeasure(widthMeasureSpec, heightMeasureSpec);
        super.onMeasure(size[0], size[1]);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        config.onLayout(changed, l, t, r, b);
    }

    public BaseConfig getConfig() {
        return config;
    }
}
