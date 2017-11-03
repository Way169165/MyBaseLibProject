package com.xgw.mybaselib.widget.roundview.delegate;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.support.annotation.AttrRes;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

import com.socks.library.KLog;
import com.xgw.mybaselib.R;
import com.xgw.mybaselib.widget.roundview.font.FontHelper;

/**
 * Created by XieGuangwei on 2017/11/1.
 * 自定义圆角代理类（带文字的并且TextView的子类）
 */

public class RoundViewDelegateText extends RoundViewDelegate {
    private String font;
    private int textColor;
    private int textColorPress;
    private int textColorDisable;

    public RoundViewDelegateText(Context context, View mView) {
        super(context, mView);
    }

    public RoundViewDelegateText(Context context, View mView, AttributeSet atts) {
        super(context, mView, atts);
    }

    public RoundViewDelegateText(Context context, View mView, AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, mView, attrs, defStyleAttr);
    }

    @Override
    public void obtainAttributes(Context context, AttributeSet attrs, @AttrRes int defStyleAttr) {
        super.obtainAttributes(context, attrs, defStyleAttr);
        if (!(mView instanceof TextView)) {
            throw new UnsupportedOperationException("非TextView子类不能使用我！！！");
        }
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.RoundViewDelegateText, defStyleAttr, 0);
        font = typedArray.getString(R.styleable.RoundViewDelegateText_rv_font);
        //EditText extends TextView，所以此处获取text默认颜色可以通用TextView的方式
        textColor = typedArray.getColor(R.styleable.RoundViewDelegateText_rv_textColor, ((TextView) mView).getTextColors().getDefaultColor());
        textColorPress = typedArray.getColor(R.styleable.RoundViewDelegateText_rv_textColorPressed, textColor);
        textColorDisable = typedArray.getColor(R.styleable.RoundViewDelegateText_rv_textColorDisabled, textColor);
        typedArray.recycle();
    }

    @Override
    public void updateLayout() {
        super.updateLayout();
        //font
        if (!TextUtils.isEmpty(font)) {
            ((TextView) mView).setTypeface(FontHelper.get(mView.getContext(), font));
        }
        //text color
        ColorStateList colorStateList = new ColorStateList(
                new int[][]{
                        new int[]{-android.R.attr.state_enabled},//disable
                        new int[]{android.R.attr.state_pressed},//press
                        new int[]{},//normal
                },
                new int[]{
                        textColorDisable,
                        textColorPress,
                        textColor});

        ((TextView) mView).setTextColor(colorStateList);
    }

    /**
     * 设置字体（字体放在assets/fonts目录下）
     *
     * @param font
     * @return
     */
    @Override
    public BaseDelegate setFont(String font) {
        this.font = font;
        return this;
    }

    /**
     * 设置字体颜色（正常）
     *
     * @param textColor
     * @return
     */
    @Override
    public BaseDelegate setTextColor(int textColor) {
        this.textColor = textColor;
        return this;
    }

    /**
     * 设置字体颜色（按下）
     *
     * @param textColorPress
     * @return
     */
    @Override
    public BaseDelegate setTextColorPress(int textColorPress) {
        this.textColorPress = textColorPress;
        return this;
    }

    /**
     * 设置字体颜色（不能用的情况）
     *
     * @param textColorDisable
     * @return
     */
    @Override
    public BaseDelegate setTextColorDisable(int textColorDisable) {
        this.textColorDisable = textColorDisable;
        return this;
    }

    /**
     * 刷新
     */
    @Override
    public void refresh() {
        updateLayout();
    }
}
