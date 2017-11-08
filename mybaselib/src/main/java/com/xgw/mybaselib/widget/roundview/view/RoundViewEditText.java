package com.xgw.mybaselib.widget.roundview.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.EditText;

import com.xgw.mybaselib.widget.roundview.config.BaseConfig;
import com.xgw.mybaselib.widget.roundview.config.RoundViewConfigText;

/**
 * Created by XieGuangwei on 2017/11/1.
 * 圆角EditText
 */

public class RoundViewEditText extends EditText {
    private BaseConfig config;

    public RoundViewEditText(Context context) {
        this(context, null);
    }

    public RoundViewEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
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
