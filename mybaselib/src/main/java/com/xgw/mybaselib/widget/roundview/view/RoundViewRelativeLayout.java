package com.xgw.mybaselib.widget.roundview.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

import com.xgw.mybaselib.widget.roundview.delegate.RoundViewDelegate;

/**
 * Created by XieGuangwei on 2017/11/1.
 * 自定义圆角相对布局
 */

public class RoundViewRelativeLayout extends RelativeLayout {
    private RoundViewDelegate delegate;

    public RoundViewRelativeLayout(Context context) {
        this(context, null);
    }

    public RoundViewRelativeLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RoundViewRelativeLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        delegate = new RoundViewDelegate(context, this, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int size[] = delegate.onMeasure(widthMeasureSpec, heightMeasureSpec);
        super.onMeasure(size[0], size[1]);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        delegate.onLayout(changed, l, t, r, b);
    }
}
