package com.xgw.mybaselib.widget.roundview.delegate;

/**
 * Created by XieGuangwei on 2017/11/1.
 * 代理接口
 */

public interface BaseDelegate {
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
    BaseDelegate setBackgroundColor(int backgroundColor);

    /**
     * 设置背景颜色（按下）
     * @param backgroundColorPressed
     * @return
     */
    BaseDelegate setBackgroundColorPressed (int backgroundColorPressed);

    /**
     * 设置背景颜色（不能用的情况）
     * @param backgroundColorDisabled
     * @return
     */
    BaseDelegate setBackgroundColorDisabled (int backgroundColorDisabled);

    /**
     * 设置边框宽度
     * @param stroke
     * @return
     */
    BaseDelegate setStroke (int stroke);

    /**
     * 设置边框颜色（正常）
     * @param strokeColor
     * @return
     */
    BaseDelegate setStrokeColor (int strokeColor);

    /**
     * 设置边框颜色（按下）
     * @param strokeColorPressed
     * @return
     */
    BaseDelegate setStrokeColorPressed (int strokeColorPressed);

    /**
     * 设置边框颜色（不能用的情况）
     * @param strokeColorDisabled
     * @return
     */
    BaseDelegate setStrokeColorDisabled (int strokeColorDisabled);

    /**
     * 设置圆角（所有角）
     * @param radius
     * @return
     */
    BaseDelegate setRadius (int radius);

    /**
     * 设置圆角（左下）
     * @param radiusBottomLeft
     * @return
     */
    BaseDelegate setRadiusBottomLeft (int radiusBottomLeft);

    /**
     * 设置圆角（右下）
     * @param radiusBottomRight
     * @return
     */
    BaseDelegate setRadiusBottomRight(int radiusBottomRight);

    /**
     * 设置圆角（左上）
     * @param radiusTopLeft
     * @return
     */
    BaseDelegate setRadiusTopLeft(int radiusTopLeft);

    /**
     * 设置圆角（右上）
     * @param radiusTopRight
     * @return
     */
    BaseDelegate setRadiusTopRight(int radiusTopRight);

    /**
     * 设置宽、高是否一致
     * @param widthHeightEqual
     * @return
     */
    BaseDelegate setWidthHeightEqual(boolean widthHeightEqual);

    /**
     * 设置圆角大小是否是高度的一半
     * @param radiusHalfHeight
     * @return
     */
    BaseDelegate setRadiusHalfHeight(boolean radiusHalfHeight);

    /**
     * 设置是否有水波效果
     * @param rippleEnable
     * @return
     */
    BaseDelegate setRippleEnable(boolean rippleEnable);

    /**
     * 设置字体（字体放在assets/fonts目录下）
     * @param font
     * @return
     */
    BaseDelegate setFont(String font);

    /**
     * 设置字体颜色（正常）
     * @param textColor
     * @return
     */
    BaseDelegate setTextColor(int textColor);

    /**
     * 设置字体颜色（按下）
     * @param textColorPress
     * @return
     */
    BaseDelegate setTextColorPress(int textColorPress);

    /**
     * 设置字体颜色（不能用的情况）
     * @param textColorDisable
     * @return
     */
    BaseDelegate setTextColorDisable(int textColorDisable);

    /**
     * 刷新
     */
    void refresh();
}
