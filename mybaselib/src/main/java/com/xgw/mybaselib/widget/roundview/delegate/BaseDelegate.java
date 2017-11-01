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
}
