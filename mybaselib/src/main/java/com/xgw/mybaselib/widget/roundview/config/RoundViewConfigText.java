package com.xgw.mybaselib.widget.roundview.config;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.support.annotation.AttrRes;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

import com.xgw.mybaselib.R;
import com.xgw.mybaselib.widget.roundview.font.FontHelper;

/**
 * Created by XieGuangwei on 2017/11/1.
 * 自定义圆角代理类（带文字的并且TextView的子类）
 */

public class RoundViewConfigText extends RoundViewConfig {
    private String font;
    private int textColor;
    private int textColorPressed;
    private int textColorDisabled;

    private Drawable drawableLeft;
    private Drawable drawableLeftPressed;
    private Drawable drawableLeftDisabled;

    private Drawable drawableRight;
    private Drawable drawableRightPressed;
    private Drawable drawableRightDisabled;

    private Drawable drawableTop;
    private Drawable drawableTopPressed;
    private Drawable drawableTopDisabled;

    private Drawable drawableBottom;
    private Drawable drawableBottomPressed;
    private Drawable drawableBottomDisabled;

    public RoundViewConfigText(Context context, View mView) {
        super(context, mView);
    }

    public RoundViewConfigText(Context context, View mView, AttributeSet atts) {
        super(context, mView, atts);
    }

    public RoundViewConfigText(Context context, View mView, AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, mView, attrs, defStyleAttr);
    }

    @Override
    public void obtainAttributes(Context context, AttributeSet attrs, @AttrRes int defStyleAttr) {
        super.obtainAttributes(context, attrs, defStyleAttr);
        if (!(mView instanceof TextView)) {
            throw new UnsupportedOperationException("非TextView子类不能使用我！！！");
        }
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.RoundViewConfigText, defStyleAttr, 0);
        font = typedArray.getString(R.styleable.RoundViewConfigText_rv_font);
        //EditText extends TextView，所以此处获取text默认颜色可以通用TextView的方式
        textColor = typedArray.getColor(R.styleable.RoundViewConfigText_rv_textColor, ((TextView) mView).getTextColors().getDefaultColor());
        textColorPressed = typedArray.getColor(R.styleable.RoundViewConfigText_rv_textColorPressed, textColor);
        textColorDisabled = typedArray.getColor(R.styleable.RoundViewConfigText_rv_textColorDisabled, textColor);

        //drawable
        drawableLeft = typedArray.getDrawable(R.styleable.RoundViewConfigText_rv_drawableLeft);
        drawableLeftPressed = typedArray.getDrawable(R.styleable.RoundViewConfigText_rv_drawableLeftPressed);
        drawableLeftDisabled = typedArray.getDrawable(R.styleable.RoundViewConfigText_rv_drawableLeftDisabled);

        drawableRight = typedArray.getDrawable(R.styleable.RoundViewConfigText_rv_drawableRight);
        drawableRightPressed = typedArray.getDrawable(R.styleable.RoundViewConfigText_rv_drawableRightPressed);
        drawableRightDisabled = typedArray.getDrawable(R.styleable.RoundViewConfigText_rv_drawableRightDisabled);

        drawableTop = typedArray.getDrawable(R.styleable.RoundViewConfigText_rv_drawableTop);
        drawableTopPressed = typedArray.getDrawable(R.styleable.RoundViewConfigText_rv_drawableTopPressed);
        drawableTopDisabled = typedArray.getDrawable(R.styleable.RoundViewConfigText_rv_drawableTopDisabled);

        drawableBottom = typedArray.getDrawable(R.styleable.RoundViewConfigText_rv_drawableBottom);
        drawableBottomPressed = typedArray.getDrawable(R.styleable.RoundViewConfigText_rv_drawableBottomPressed);
        drawableBottomDisabled = typedArray.getDrawable(R.styleable.RoundViewConfigText_rv_drawableBottomDisabled);
        typedArray.recycle();
    }

    @Override
    public void updateLayout() {
        super.updateLayout();
        //drawable left top right bottom
        Drawable left = createTextStateListDrawable(drawableLeft, drawableLeftPressed, drawableLeftDisabled);
        Drawable top = createTextStateListDrawable(drawableTop, drawableTopPressed, drawableTopDisabled);
        Drawable right = createTextStateListDrawable(drawableRight, drawableRightPressed, drawableRightDisabled);
        Drawable bottom = createTextStateListDrawable(drawableBottom, drawableBottomPressed, drawableBottomDisabled);
        ((TextView) mView).setCompoundDrawablesWithIntrinsicBounds(left, top, right, bottom);

        //font
        if (!TextUtils.isEmpty(font)) {
            ((TextView) mView).setTypeface(FontHelper.get(mView.getContext(), font));
        }
        //text color
        ColorStateList colorStateList;
        if (isStateFocusedEqualsPressed) {
            colorStateList = new ColorStateList(
                    new int[][]{
                            new int[]{-android.R.attr.state_enabled},//disable
                            new int[]{android.R.attr.state_pressed},//press
                            new int[]{android.R.attr.state_focused},//focus
                            new int[]{},//normal
                    },
                    new int[]{
                            textColorDisabled,
                            textColorPressed,
                            textColorPressed,
                            textColor});
        } else if(isStateSelectedEqualsPressed){
            colorStateList = new ColorStateList(
                    new int[][]{
                            new int[]{-android.R.attr.state_enabled},//disable
                            new int[]{android.R.attr.state_pressed},//press
                            new int[]{android.R.attr.state_selected},//selected
                            new int[]{},//normal
                    },
                    new int[]{
                            textColorDisabled,
                            textColorPressed,
                            textColorPressed,
                            textColor});
        }else{
            colorStateList = new ColorStateList(
                    new int[][]{
                            new int[]{-android.R.attr.state_enabled},//disable
                            new int[]{android.R.attr.state_pressed},//press
                            new int[]{},//normal
                    },
                    new int[]{
                            textColorDisabled,
                            textColorPressed,
                            textColor});
        }
        ((TextView) mView).setTextColor(colorStateList);
    }

    /**
     * 创建drawable状态列表
     *
     * @return
     */
    private StateListDrawable createTextStateListDrawable(Drawable normalDrawable, Drawable pressDrawable, Drawable disableDrawable) {
        StateListDrawable states = new StateListDrawable();
        //disable
        states.addState(new int[]{-android.R.attr.state_enabled}, disableDrawable);
        //press
        states.addState(new int[]{android.R.attr.state_pressed}, pressDrawable);
        //focus
        if (isStateFocusedEqualsPressed) {
            states.addState(new int[]{android.R.attr.state_focused}, pressDrawable);
        }
        //selected
        if (isStateSelectedEqualsPressed) {
            states.addState(new int[]{android.R.attr.state_selected}, pressDrawable);
        }
        //normal
        states.addState(new int[]{}, normalDrawable);
        return states;
    }

    /**
     * 设置字体（字体放在assets/fonts目录下）
     *
     * @param font
     * @return
     */
    @Override
    public BaseConfig setFont(String font) {
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
    public BaseConfig setTextColor(int textColor) {
        this.textColor = textColor;
        return this;
    }

    /**
     * 设置字体颜色（按下）
     *
     * @param textColorPressed
     * @return
     */
    @Override
    public BaseConfig setTextColorPressed(int textColorPressed) {
        this.textColorPressed = textColorPressed;
        return this;
    }

    /**
     * 设置字体颜色（不能用的情况）
     *
     * @param textColorDisabled
     * @return
     */
    @Override
    public BaseConfig setTextColorDisabled(int textColorDisabled) {
        this.textColorDisabled = textColorDisabled;
        return this;
    }

    /**
     * 设置左边图标（mipmap/drawable都可以）
     *
     * @param drawableLeft
     * @return
     */
    @Override
    public BaseConfig setDrawableLeft(Drawable drawableLeft) {
        this.drawableLeft = drawableLeft;
        return this;
    }

    /**
     * 设置选中时左边图标（mipmap/drawable都可以）
     *
     * @param drawableLeftPressed
     * @return
     */
    @Override
    public BaseConfig setDrawableLeftPressed(Drawable drawableLeftPressed) {
        this.drawableLeftPressed = drawableLeftPressed;
        return this;
    }

    /**
     * 设置不可用时左边图标（mipmap/drawable都可以）
     *
     * @param drawableLeftDisabled
     * @return
     */
    @Override
    public BaseConfig setDrawableLeftDisabled(Drawable drawableLeftDisabled) {
        this.drawableLeftDisabled = drawableLeftDisabled;
        return this;
    }

    /**
     * 设置右边图标（mipmap/drawable都可以）
     *
     * @param drawableRight
     * @return
     */
    @Override
    public BaseConfig setDrawableRight(Drawable drawableRight) {
        this.drawableRight = drawableRight;
        return this;
    }

    /**
     * 设置选中时右边图标（mipmap/drawable都可以）
     *
     * @param drawableRightPressed
     * @return
     */
    @Override
    public BaseConfig setDrawableRightPressed(Drawable drawableRightPressed) {
        this.drawableRightPressed = drawableRightPressed;
        return this;
    }

    /**
     * 设置不可用时右边图标（mipmap/drawable都可以）
     *
     * @param drawableRightDisabled
     * @return
     */
    @Override
    public BaseConfig setDrawableRightDisabled(Drawable drawableRightDisabled) {
        this.drawableRightDisabled = drawableRightDisabled;
        return this;
    }

    /**
     * 设置上面图标（mipmap/drawable都可以）
     *
     * @param drawableTop
     * @return
     */
    @Override
    public BaseConfig setDrawableTop(Drawable drawableTop) {
        this.drawableTop = drawableTop;
        return this;
    }

    /**
     * 设置选中时上面图标（mipmap/drawable都可以）
     *
     * @param drawableTopPressed
     * @return
     */
    @Override
    public BaseConfig setDrawableTopPressed(Drawable drawableTopPressed) {
        this.drawableTopPressed = drawableTopPressed;
        return this;
    }

    /**
     * 设置不可用时上面图标（mipmap/drawable都可以）
     *
     * @param drawableTopDisabled
     * @return
     */
    @Override
    public BaseConfig setDrawableTopDisabled(Drawable drawableTopDisabled) {
        this.drawableTopDisabled = drawableTopDisabled;
        return this;
    }

    /**
     * 设置底部图标（mipmap/drawable都可以）
     *
     * @param drawableBottom
     * @return
     */
    @Override
    public BaseConfig setDrawableBottom(Drawable drawableBottom) {
        this.drawableBottom = drawableBottom;
        return this;
    }

    /**
     * 设置选中时底部图标（mipmap/drawable都可以）
     *
     * @param drawableBottomPressed
     * @return
     */
    @Override
    public BaseConfig setDrawableBottomPressed(Drawable drawableBottomPressed) {
        this.drawableBottomPressed = drawableBottomPressed;
        return this;
    }

    /**
     * 设置不可用时底部图标（mipmap/drawable都可以）
     *
     * @param drawableBottomDisabled
     * @return
     */
    @Override
    public BaseConfig setDrawableBottomDisabled(Drawable drawableBottomDisabled) {
        this.drawableBottomDisabled = drawableBottomDisabled;
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
