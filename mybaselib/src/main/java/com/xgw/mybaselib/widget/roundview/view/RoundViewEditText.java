package com.xgw.mybaselib.widget.roundview.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.EditText;

import com.xgw.mybaselib.widget.roundview.delegate.BaseDelegate;
import com.xgw.mybaselib.widget.roundview.delegate.RoundViewDelegateText;

/**
 * Created by XieGuangwei on 2017/11/1.
 * 圆角EditText
 */

public class RoundViewEditText extends EditText {
    private BaseDelegate delegate;

    public RoundViewEditText(Context context) {
        this(context, null);
    }

    public RoundViewEditText(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RoundViewEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        delegate = new RoundViewDelegateText(context, this, attrs);
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
