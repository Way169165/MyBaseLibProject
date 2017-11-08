package com.xgw.mybaselib.widget.roundview.config;

import android.graphics.drawable.Drawable;

/**
 * Created by XieGuangwei on 2017/11/1.
 * 代理接口
 */

public interface BaseConfig {
    /**
     * 更新布局
     */
    void updateLayout();

    /**
     * 确定ViewGroup位置
     * @param changed
     * @param left
     * @param top
     * @param right
     * @param bottom
     */
    void onLayout(boolean changed, int left, int top, int right, int bottom);

    /**
     * 测量view大小
     * @param widthMeasureSpec
     * @param heightMeasureSpec
     * @return
     */
    int[] onMeasure(int widthMeasureSpec, int heightMeasureSpec);

    /**
     * 设置背景颜色（正常）
     * @param backgroundColor
     * @return
     */
    BaseConfig setBackgroundColor(int backgroundColor);

    /**
     * 设置背景颜色（按下）
     * @param backgroundColorPressed
     * @return
     */
    BaseConfig setBackgroundColorPressed(int backgroundColorPressed);

    /**
     * 设置背景颜色（不能用的情况）
     * @param backgroundColorDisabled
     * @return
     */
    BaseConfig setBackgroundColorDisabled(int backgroundColorDisabled);

    /**
     * 设置边框宽度
     * @param strokeWidth
     * @return
     */
    BaseConfig setStrokeWidth(int strokeWidth);

    /**
     * 设置边框颜色（正常）
     * @param strokeColor
     * @return
     */
    BaseConfig setStrokeColor(int strokeColor);

    /**
     * 设置边框颜色（按下）
     * @param strokeColorPressed
     * @return
     */
    BaseConfig setStrokeColorPressed(int strokeColorPressed);

    /**
     * 设置边框颜色（不能用的情况）
     * @param strokeColorDisabled
     * @return
     */
    BaseConfig setStrokeColorDisabled(int strokeColorDisabled);

    /**
     * 设置圆角（所有角）
     * @param radius
     * @return
     */
    BaseConfig setRadius(int radius);

    /**
     * 设置圆角（左下）
     * @param radiusBottomLeft
     * @return
     */
    BaseConfig setRadiusBottomLeft(int radiusBottomLeft);

    /**
     * 设置圆角（右下）
     * @param radiusBottomRight
     * @return
     */
    BaseConfig setRadiusBottomRight(int radiusBottomRight);

    /**
     * 设置圆角（左上）
     * @param radiusTopLeft
     * @return
     */
    BaseConfig setRadiusTopLeft(int radiusTopLeft);

    /**
     * 设置圆角（右上）
     * @param radiusTopRight
     * @return
     */
    BaseConfig setRadiusTopRight(int radiusTopRight);

    /**
     * 设置宽、高是否一致
     * @param widthHeightEqual
     * @return
     */
    BaseConfig setWidthHeightEqual(boolean widthHeightEqual);

    /**
     * 设置圆角大小是否是高度的一半
     * @param radiusHalfHeight
     * @return
     */
    BaseConfig setRadiusHalfHeight(boolean radiusHalfHeight);

    /**
     * 设置是否有水波效果
     * @param rippleEnable
     * @return
     */
    BaseConfig setRippleEnable(boolean rippleEnable);

    /**
     * 获取焦点时状态跟按下是否一样
     * @param stateFocusEqualsPressed
     * @return
     */
    BaseConfig setStateFocusEqualsPressed(boolean stateFocusEqualsPressed);

    /**
     * 被选中时是否跟按下时状态一样
     * @param stateSelectedEqualsPressed
     * @return
     */
    BaseConfig setStateSelectedEqualsPressed(boolean stateSelectedEqualsPressed);

    /**
     * 设置字体（字体放在assets/fonts目录下）
     * @param font
     * @return
     */
    BaseConfig setFont(String font);

    /**
     * 设置字体颜色（正常）
     * @param textColor
     * @return
     */
    BaseConfig setTextColor(int textColor);

    /**
     * 设置字体颜色（按下）
     * @param textColorPressed
     * @return
     */
    BaseConfig setTextColorPressed(int textColorPressed);

    /**
     * 设置字体颜色（不能用的情况）
     * @param textColorDisabled
     * @return
     */
    BaseConfig setTextColorDisabled(int textColorDisabled);

    /**
     * 设置左边图标（mipmap/drawable都可以）
     * @param drawableLeft
     * @return
     */
    BaseConfig setDrawableLeft(Drawable drawableLeft);

    /**
     * 设置选中时左边图标（mipmap/drawable都可以）
     * @param drawableLeftPressed
     * @return
     */
    BaseConfig setDrawableLeftPressed(Drawable drawableLeftPressed);

    /**
     * 设置不可用时左边图标（mipmap/drawable都可以）
     * @param drawableLeftDisabled
     * @return
     */
    BaseConfig setDrawableLeftDisabled(Drawable drawableLeftDisabled);

    /**
     * 设置右边图标（mipmap/drawable都可以）
     * @param drawableRight
     * @return
     */
    BaseConfig setDrawableRight(Drawable drawableRight);

    /**
     * 设置选中时右边图标（mipmap/drawable都可以）
     * @param drawableRightPressed
     * @return
     */
    BaseConfig setDrawableRightPressed(Drawable drawableRightPressed);

    /**
     * 设置不可用时右边图标（mipmap/drawable都可以）
     * @param drawableRightDisabled
     * @return
     */
    BaseConfig setDrawableRightDisabled(Drawable drawableRightDisabled);

    /**
     * 设置上面图标（mipmap/drawable都可以）
     * @param drawableTop
     * @return
     */
    BaseConfig setDrawableTop(Drawable drawableTop);

    /**
     * 设置选中时上面图标（mipmap/drawable都可以）
     * @param drawableTopPressed
     * @return
     */
    BaseConfig setDrawableTopPressed(Drawable drawableTopPressed);

    /**
     * 设置不可用时上面图标（mipmap/drawable都可以）
     * @param drawableTopDisabled
     * @return
     */
    BaseConfig setDrawableTopDisabled(Drawable drawableTopDisabled);

    /**
     * 设置底部图标（mipmap/drawable都可以）
     * @param drawableBottom
     * @return
     */
    BaseConfig setDrawableBottom(Drawable drawableBottom);

    /**
     * 设置选中时底部图标（mipmap/drawable都可以）
     * @param drawableBottomPressed
     * @return
     */
    BaseConfig setDrawableBottomPressed(Drawable drawableBottomPressed);

    /**
     * 设置不可用时底部图标（mipmap/drawable都可以）
     * @param drawableBottomDisabled
     * @return
     */
    BaseConfig setDrawableBottomDisabled(Drawable drawableBottomDisabled);

    /**
     * 刷新
     */
    void refresh();
}
