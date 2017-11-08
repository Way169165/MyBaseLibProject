package com.xgw.mybaselibproject;

import android.support.v4.content.ContextCompat;
import android.view.View;

import com.xgw.mybaselib.base.BaseActivity;
import com.xgw.mybaselib.utils.SizeUtils;
import com.xgw.mybaselib.widget.roundview.view.RoundViewButton;
import com.xgw.mybaselib.widget.roundview.view.RoundViewTextView;

import java.util.Random;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by XieGuangwei on 2017/11/8.
 */

public class RoundViewShapeTestActivity extends BaseActivity {

    @BindView(R.id.set_dynamic_rvb)
    RoundViewButton dynamicSetBtn;
    @BindView(R.id.drawable_rvtv)
    RoundViewTextView drawableRvtv;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_roundviewshape;
    }

    @Override
    public void initView() {
        setToolbarCenter(true,"自定义shape测试");
        dynamicSetBtn.getConfig()
                .setBackgroundColor(ContextCompat.getColor(this,R.color.green_300))
                .setBackgroundColorPressed(ContextCompat.getColor(this,R.color.green_700))
                .setFont("华康少女繁简完全版.ttf")
                .setTextColor(ContextCompat.getColor(this,R.color.black_alpha_240))
                .setTextColorPressed(ContextCompat.getColor(this,R.color.green_700))
                .setRadius(SizeUtils.dp2px(10))
                .setStrokeWidth(SizeUtils.dp2px(10))
                .setStrokeColor(ContextCompat.getColor(this,R.color.red_a700))
                .setStrokeColorPressed(ContextCompat.getColor(this,R.color.red_900))
                .setRippleEnable(true);
        drawableRvtv.getConfig()
                .setBackgroundColor(ContextCompat.getColor(this,R.color.white))
                .setBackgroundColorPressed(ContextCompat.getColor(this,R.color.red_800))
                .setDrawableBottom(ContextCompat.getDrawable(this,R.mipmap.android))
                .setDrawableBottomPressed(ContextCompat.getDrawable(this,R.mipmap.android_press))
                .setDrawableLeft(ContextCompat.getDrawable(this,R.mipmap.android))
                .setDrawableLeftPressed(ContextCompat.getDrawable(this,R.mipmap.android_press))
                .setDrawableRight(ContextCompat.getDrawable(this,R.mipmap.android))
                .setDrawableRightPressed(ContextCompat.getDrawable(this,R.mipmap.android_press))
                .setDrawableTop(ContextCompat.getDrawable(this,R.mipmap.android))
                .setDrawableTopPressed(ContextCompat.getDrawable(this,R.mipmap.android_press))
                .setRippleEnable(true)
                .setRadius(SizeUtils.dp2px(5))
                .setStrokeWidth(SizeUtils.dp2px(2))
                .setStrokeColor(ContextCompat.getColor(this,R.color.red_200))
                .setStrokeColorPressed(ContextCompat.getColor(this,R.color.red_800))
                .setTextColor(ContextCompat.getColor(this,R.color.black_alpha_128))
                .setTextColorPressed(ContextCompat.getColor(this,R.color.red_800));
    }

    @Override
    public void initData() {

    }

    @OnClick({R.id.change_rvb,R.id.change_drawable_rvb})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.change_rvb:

                // 随机颜色
                Random random = new Random();
                int ranColor = 0xff000000 | random.nextInt(0x00ffffff);
                int ranColor2 = 0xff000000 | random.nextInt(0x00ffffff);
                int ranColor3 = 0xff000000 | random.nextInt(0x00ffffff);
                int ranColor4 = 0xff000000 | random.nextInt(0x00ffffff);
                int ranColor5 = 0xff000000 | random.nextInt(0x00ffffff);
                int ranColor6 = 0xff000000 | random.nextInt(0x00ffffff);
                dynamicSetBtn.getConfig()
                        .setBackgroundColor(ranColor)
                        .setBackgroundColorPressed(ranColor2)
                        .setStrokeColor(ranColor3)
                        .setStrokeColorPressed(ranColor4)
                        .setTextColor(ranColor5)
                        .setTextColorPressed(ranColor6)
                        .refresh();
                break;
            case R .id.change_drawable_rvb:
                drawableRvtv.getConfig()
                        .setDrawableLeft(ContextCompat.getDrawable(this,R.mipmap.ic_launcher))
                        .refresh();
                break;
        }
    }
}
