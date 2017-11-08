package com.xgw.mybaselib.widget.roundview.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

import com.xgw.mybaselib.widget.roundview.config.BaseConfig;
import com.xgw.mybaselib.widget.roundview.config.RoundViewConfigText;

/**
 * Created by XieGuangwei on 2017/11/1.
 * 圆角textView
 */

public class RoundViewTextView extends TextView {
    private BaseConfig config;

    public RoundViewTextView(Context context) {
        this(context, null);
    }

    public RoundViewTextView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RoundViewTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        config = new RoundViewConfigText(context, this, attrs);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        config.onLayout(changed, left, top, right, bottom);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int size[] = config.onMeasure(widthMeasureSpec, heightMeasureSpec);
        super.onMeasure(size[0], size[1]);
    }

    public BaseConfig getConfig() {
        return config;
    }
}
