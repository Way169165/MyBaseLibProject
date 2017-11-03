package com.xgw.mybaselibproject;

import android.graphics.Color;
import android.view.View;

import com.xgw.mybaselib.base.BaseActivity;
import com.xgw.mybaselib.widget.roundview.view.RoundViewButton;

import java.util.Random;

public class MainActivity extends BaseActivity {
    private RoundViewButton dynamicSetBtn;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {
        setToolbarCenter(false, "测试项目");
        dynamicSetBtn = (RoundViewButton) findViewById(R.id.set_dynamic_rvb);
        dynamicSetBtn.getDelegate()
                .setBackgroundColor(getResources().getColor(R.color.green_300))
                .setBackgroundColorPressed(getResources().getColor(R.color.green_700))
                .setFont("华康少女繁简完全版.ttf")
                .setTextColor(getResources().getColor(R.color.black_alpha_240))
                .setTextColorPress(getResources().getColor(R.color.green_700))
                .setRadius(20)
                .setStroke(20)
                .setStrokeColor(getResources().getColor(R.color.red_a700))
                .setStrokeColorPressed(getResources().getColor(R.color.red_900))
                .setRippleEnable(true);
        findViewById(R.id.change_rvb).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 随机颜色
                Random random = new Random();
                int ranColor = 0xff000000 | random.nextInt(0x00ffffff);
                int ranColor2 = 0xff000000 | random.nextInt(0x00ffffff);
                int ranColor3 = 0xff000000 | random.nextInt(0x00ffffff);
                int ranColor4 = 0xff000000 | random.nextInt(0x00ffffff);
                int ranColor5 = 0xff000000 | random.nextInt(0x00ffffff);
                int ranColor6 = 0xff000000 | random.nextInt(0x00ffffff);
                dynamicSetBtn.getDelegate()
                        .setBackgroundColor(ranColor)
                        .setBackgroundColorPressed(ranColor2)
                        .setStrokeColor(ranColor3)
                        .setStrokeColorPressed(ranColor4)
                        .setTextColor(ranColor5)
                        .setTextColorPress(ranColor6)
                        .refresh();
            }
        });
    }

    @Override
    public void initData() {

    }
}
