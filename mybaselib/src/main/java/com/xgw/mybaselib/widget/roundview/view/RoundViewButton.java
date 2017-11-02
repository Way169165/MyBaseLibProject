package com.xgw.mybaselib.widget.roundview.view;

import android.content.Context;
import android.support.v7.widget.AppCompatButton;
import android.util.AttributeSet;

import com.xgw.mybaselib.R;
import com.xgw.mybaselib.widget.roundview.delegate.BaseDelegate;
import com.xgw.mybaselib.widget.roundview.delegate.RoundViewDelegateText;

/**
 * Created by XieGuangwei on 2017/11/1.
 * 圆角button
 */

public class RoundViewButton extends AppCompatButton {
    private BaseDelegate delegate;

    public RoundViewButton(Context context) {
        this(context, null);
    }

    public RoundViewButton(Context context, AttributeSet attrs) {
        this(context, attrs, R.attr.buttonStyle);
    }

    public RoundViewButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        delegate = new RoundViewDelegateText(context, this, attrs,defStyleAttr);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        delegate.onLayout(changed, left, top, right, bottom);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int size[] = delegate.onMeasure(widthMeasureSpec, heightMeasureSpec);
        super.onMeasure(size[0], size[1]);
    }
}
