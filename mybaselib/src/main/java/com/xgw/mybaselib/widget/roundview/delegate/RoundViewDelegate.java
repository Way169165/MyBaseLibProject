package com.xgw.mybaselib.widget.roundview.delegate;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.RippleDrawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Build;
import android.support.annotation.AttrRes;
import android.util.AttributeSet;
import android.view.View;

import com.socks.library.KLog;
import com.xgw.mybaselib.R;

import java.util.Arrays;

/**
 * Created by XieGuangwei on 2017/11/1.
 * 通用自定义圆角布局代理类
 */

public class RoundViewDelegate implements BaseDelegate {
    /**
     * view
     */
    protected View mView;
    /*背景颜色*/
    protected int backgroundColor;
    protected int backgroundColorPressed;
    protected int backgroundColorDisabled;
    /*周长线条*/
    protected int stroke;
    protected int strokeColor;
    protected int strokeColorPressed;
    protected int strokeColorDisabled;
    /* 圆角*/
    protected int radius;
    protected int radiusBottomLeft;
    protected int radiusBottomRight;
    protected int radiusTopLeft;
    protected int radiusTopRight;
    /*参数*/
    protected boolean isWidthHeightEqual;
    protected boolean isRadiusHalfHeight;
    //上面两个同时满足，即为一个圆形
    protected boolean isRippleEnable;

    public RoundViewDelegate(Context context, View mView) {
        this(context, mView, null);
    }

    public RoundViewDelegate(Context context, View mView, AttributeSet atts) {
        this(context, mView, atts, 0);
    }

    public RoundViewDelegate(Context context, View mView, AttributeSet attrs, @AttrRes int defStyleAttr) {
        this.mView = mView;
        obtainAttributes(context, attrs, defStyleAttr);
    }

    /**
     * 初始化参数
     *
     * @param context
     * @param attrs
     * @param defStyleAttr
     */
    public void obtainAttributes(Context context, AttributeSet attrs, @AttrRes int defStyleAttr) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.RoundViewDelegate, defStyleAttr, 0);
        //background
        backgroundColor = typedArray.getColor(R.styleable.RoundViewDelegate_rv_backgroundColor, Color.TRANSPARENT);
        backgroundColorPressed = typedArray.getColor(R.styleable.RoundViewDelegate_rv_backgroundPressColor, backgroundColor);
        backgroundColorDisabled = typedArray.getColor(R.styleable.RoundViewDelegate_rv_backgroundDisableColor, backgroundColor);

        //stroke
        stroke = typedArray.getDimensionPixelSize(R.styleable.RoundViewDelegate_rv_strokeWidth, 0);
        strokeColor = typedArray.getColor(R.styleable.RoundViewDelegate_rv_strokeColor, Color.TRANSPARENT);
        strokeColorPressed = typedArray.getColor(R.styleable.RoundViewDelegate_rv_strokePressColor, strokeColor);
        strokeColorDisabled = typedArray.getColor(R.styleable.RoundViewDelegate_rv_strokeDisableColor, strokeColor);

        //radius
        radius = typedArray.getDimensionPixelSize(R.styleable.RoundViewDelegate_rv_radius, 0);
        radiusTopLeft = typedArray.getDimensionPixelSize(R.styleable.RoundViewDelegate_rv_radiusTopLeft, -1);
        radiusTopRight = typedArray.getDimensionPixelSize(R.styleable.RoundViewDelegate_rv_radiusTopRight, -1);
        radiusBottomLeft = typedArray.getDimensionPixelSize(R.styleable.RoundViewDelegate_rv_radiusBottomLeft, -1);
        radiusBottomRight = typedArray.getDimensionPixelSize(R.styleable.RoundViewDelegate_rv_radiusBottomRight, -1);

        //params
        isWidthHeightEqual = typedArray.getBoolean(R.styleable.RoundViewDelegate_rv_isWidthHeightEqual, false);
        isRadiusHalfHeight = typedArray.getBoolean(R.styleable.RoundViewDelegate_rv_isRadiusHalfHeight, false);
        isRippleEnable = typedArray.getBoolean(R.styleable.RoundViewDelegate_rv_isRippleEnable, false);
        typedArray.recycle();
    }

    /**
     * 确定ViewGroup位置
     *
     * @param changed
     * @param left
     * @param top
     * @param right
     * @param bottom
     */
    @Override
    public void onLayout(boolean changed, int left, int top, int right, int bottom) {
        //radius高度为控件高度的一半的处理
        if (isRadiusHalfHeight) {
            radius = mView.getHeight() / 2;
        }
        updateLayout();
    }

    /**
     * 测量view大小
     *
     * @param widthMeasureSpec
     * @param heightMeasureSpec
     * @return
     */
    @Override
    public int[] onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int[] size = new int[2];
        //长宽相等的情况
        if (isWidthHeightEqual && mView.getWidth() > 0 && mView.getHeight() > 0) {
            int max = Math.max(mView.getWidth(), mView.getHeight());
            int measureSpec = View.MeasureSpec.makeMeasureSpec(max, View.MeasureSpec.EXACTLY);
            size[0] = measureSpec;
            size[1] = measureSpec;
            return size;
        }
        //长宽不相等，直接返回测量宽高
        size[0] = widthMeasureSpec;
        size[1] = heightMeasureSpec;
        return size;
    }

    /**
     * 设置背景颜色（正常）
     *
     * @param backgroundColor
     * @return
     */
    @Override
    public BaseDelegate setBackgroundColor(int backgroundColor) {
        this.backgroundColor = backgroundColor;
        return this;
    }

    /**
     * 设置背景颜色（按下）
     *
     * @param backgroundColorPressed
     * @return
     */
    @Override
    public BaseDelegate setBackgroundColorPressed(int backgroundColorPressed) {
        this.backgroundColorPressed = backgroundColorPressed;
        return this;
    }

    /**
     * 设置背景颜色（不能用的情况）
     *
     * @param backgroundColorDisabled
     * @return
     */
    @Override
    public BaseDelegate setBackgroundColorDisabled(int backgroundColorDisabled) {
        this.backgroundColorDisabled = backgroundColorDisabled;
        return this;
    }

    /**
     * 设置边框宽度
     *
     * @param stroke
     * @return
     */
    @Override
    public BaseDelegate setStroke(int stroke) {
        this.stroke = stroke;
        return this;
    }

    /**
     * 设置边框颜色（正常）
     *
     * @param strokeColor
     * @return
     */
    @Override
    public BaseDelegate setStrokeColor(int strokeColor) {
        this.strokeColor = strokeColor;
        return this;
    }

    /**
     * 设置边框颜色（按下）
     *
     * @param strokeColorPressed
     * @return
     */
    @Override
    public BaseDelegate setStrokeColorPressed(int strokeColorPressed) {
        this.strokeColorPressed = strokeColorPressed;
        return this;
    }

    /**
     * 设置边框颜色（不能用的情况）
     *
     * @param strokeColorDisabled
     * @return
     */
    @Override
    public BaseDelegate setStrokeColorDisabled(int strokeColorDisabled) {
        this.strokeColorDisabled = strokeColorDisabled;
        return this;
    }

    /**
     * 设置圆角（所有角）
     *
     * @param radius
     * @return
     */
    @Override
    public BaseDelegate setRadius(int radius) {
        this.radius = radius;
        return this;
    }

    /**
     * 设置圆角（左下）
     *
     * @param radiusBottomLeft
     * @return
     */
    @Override
    public BaseDelegate setRadiusBottomLeft(int radiusBottomLeft) {
        this.radiusBottomLeft = radiusBottomLeft;
        return this;
    }

    /**
     * 设置圆角（右下）
     *
     * @param radiusBottomRight
     * @return
     */
    @Override
    public BaseDelegate setRadiusBottomRight(int radiusBottomRight) {
        this.radiusBottomRight = radiusBottomRight;
        return this;
    }

    /**
     * 设置圆角（左上）
     *
     * @param radiusTopLeft
     * @return
     */
    @Override
    public BaseDelegate setRadiusTopLeft(int radiusTopLeft) {
        this.radiusTopLeft = radiusTopLeft;
        return this;
    }

    /**
     * 设置圆角（右上）
     *
     * @param radiusTopRight
     * @return
     */
    @Override
    public BaseDelegate setRadiusTopRight(int radiusTopRight) {
        this.radiusTopRight = radiusTopRight;
        return this;
    }

    /**
     * 设置宽、高是否一致
     *
     * @param widthHeightEqual
     * @return
     */
    @Override
    public BaseDelegate setWidthHeightEqual(boolean widthHeightEqual) {
        this.isWidthHeightEqual = widthHeightEqual;
        return this;
    }

    /**
     * 设置圆角大小是否是高度的一半
     *
     * @param radiusHalfHeight
     * @return
     */
    @Override
    public BaseDelegate setRadiusHalfHeight(boolean radiusHalfHeight) {
        this.isRadiusHalfHeight = radiusHalfHeight;
        return this;
    }

    /**
     * 设置是否有水波效果
     *
     * @param rippleEnable
     * @return
     */
    @Override
    public BaseDelegate setRippleEnable(boolean rippleEnable) {
        this.isRippleEnable = rippleEnable;
        return this;
    }

    /**
     * 设置字体（字体放在assets/fonts目录下）
     *
     * @param font
     * @return
     */
    @Override
    public BaseDelegate setFont(String font) {
        return null;
    }

    /**
     * 设置字体颜色（正常）
     *
     * @param textColor
     * @return
     */
    @Override
    public BaseDelegate setTextColor(int textColor) {
        return null;
    }

    /**
     * 设置字体颜色（按下）
     *
     * @param textColorPress
     * @return
     */
    @Override
    public BaseDelegate setTextColorPress(int textColorPress) {
        return null;
    }

    /**
     * 设置字体颜色（不能用的情况）
     *
     * @param textColorDisable
     * @return
     */
    @Override
    public BaseDelegate setTextColorDisable(int textColorDisable) {
        return null;
    }

    /**
     * 刷新
     */
    @Override
    public void refresh() {
        updateLayout();
    }

    /**
     * 更新布局
     */
    @Override
    public void updateLayout() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP && isRippleEnable) {
            mView.setBackground(createRippleDrawable());
        } else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                mView.setBackground(createStateListDrawable());
            } else {
                mView.setBackgroundDrawable(createStateListDrawable());
            }
        }
    }

    /**
     * 创建drawable状态列表
     *
     * @return
     */
    private StateListDrawable createStateListDrawable() {
        StateListDrawable states = new StateListDrawable();
        //disable
        states.addState(new int[]{-android.R.attr.state_enabled}, createGradientDrawable(backgroundColorDisabled, strokeColorDisabled));
        //press
        states.addState(new int[]{android.R.attr.state_pressed}, createGradientDrawable(backgroundColorPressed, strokeColorPressed));
        //normal
        states.addState(new int[]{}, createGradientDrawable(backgroundColor, strokeColor));
        return states;
    }

    /**
     * 创建shape---设定背景、线条、圆角
     *
     * @param backgroundColor 背景颜色
     * @param strokeColor     线条颜色
     * @return 返回drawable
     */
    private Drawable createGradientDrawable(int backgroundColor, int strokeColor) {
        GradientDrawable shape = new GradientDrawable();
        shape.setColor(backgroundColor);

        final float[] radiusArray = new float[8];
        Arrays.fill(radiusArray, radius);

        if (radiusTopLeft >= 0) {
            radiusArray[0] = radiusTopLeft;
            radiusArray[1] = radiusTopLeft;
        }

        if (radiusTopRight >= 0) {
            radiusArray[2] = radiusTopRight;
            radiusArray[3] = radiusTopRight;
        }

        if (radiusBottomRight >= 0) {
            radiusArray[4] = radiusBottomRight;
            radiusArray[5] = radiusBottomRight;
        }

        if (radiusBottomLeft >= 0) {
            radiusArray[6] = radiusBottomLeft;
            radiusArray[7] = radiusBottomLeft;
        }
        shape.setCornerRadii(radiusArray);
        if (stroke != 0) {
            shape.setStroke(stroke, strokeColor);
        }
        return shape;
    }

    /**
     * 创建水波选择drawable
     *
     * @return
     */
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private Drawable createRippleDrawable() {
        if (backgroundColorPressed == Color.TRANSPARENT && strokeColorPressed != Color.TRANSPARENT) {
            // 如果背景选中颜色为透明，并且边框选中颜色不为空，则设置边框选中效果
            return new RippleDrawable(ColorStateList.valueOf(strokeColorPressed), getRippleStateListDrawable(), createGradientDrawable(Color.TRANSPARENT, Color.WHITE));
        } else {
            return new RippleDrawable(ColorStateList.valueOf(backgroundColorPressed), getRippleStateListDrawable(), createGradientDrawable(Color.WHITE, Color.WHITE));
        }
    }

    /**
     * 创建水波点击状态列表drawable
     *
     * @return
     */
    private Drawable getRippleStateListDrawable() {
        StateListDrawable states = new StateListDrawable();
        states.addState(new int[]{-android.R.attr.state_enabled}, createGradientDrawable(backgroundColorDisabled, strokeColorDisabled));
        states.addState(new int[]{}, createGradientDrawable(backgroundColor, strokeColor));
        return states;
    }

}
