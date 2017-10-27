package com.xgw.mybaselib.base;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.xgw.mybaselib.AppManager;
import com.xgw.mybaselib.utils.ToastUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import butterknife.ButterKnife;
import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by weinai351353 on 2017/8/6.
 * activity基类
 */

public abstract class BaseActivity extends AppCompatActivity implements BaseViewInterface{


    /**
     * 获得布局id
     *
     * @return
     */
    protected abstract int getLayoutId();

    protected CompositeDisposable disposables = new CompositeDisposable();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        //将activity添加到管理栈
        AppManager.getAppManager().addActivity(this);
        EventBus.getDefault().register(this);

        //如需使用黄油刀注解，绑定黄油刀注解
        if (isUsingButterKnife()) {
            ButterKnife.bind(this);
        }

        //初始化操作
        initView();
        initData();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //将activity移出管理栈
        AppManager.getAppManager().finishActivity(this);
        if (disposables != null && disposables.size() > 0) {
            disposables.clear();
        }
        EventBus.getDefault().unregister(this);
    }


    /**
     * 从指定页面跳转到另一个页面
     *
     * @param firstCls
     * @param SecondCls
     */
    public void nextActivity(Class<?> firstCls, Class<?> SecondCls) {
        Intent[] intents = new Intent[]{Intent.makeRestartActivityTask(new ComponentName(this, SecondCls)), new Intent(this, firstCls)};
        this.startActivities(intents);
    }

    /**
     * 从当前页面跳转到指定页面
     *
     * @param cls
     */
    public void nextActivity(Class<?> cls) {
        Intent intent = new Intent(this, cls);
        this.startActivity(intent);
    }

    /**
     * 带返回参数的跳转
     *
     * @param cls
     * @param requestCode
     */
    public void nextActivityForResult(Class<?> cls, int requestCode) {
        Intent intent = new Intent(this, cls);
        this.startActivityForResult(intent, requestCode);
    }

    /**
     * 带返回参数的跳转，跳转传参
     *
     * @param cls
     * @param requestCode
     * @param bundle
     */
    public void nextActivityForResult(Class<?> cls, int requestCode, Bundle bundle) {
        Intent intent = new Intent(this, cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }

        this.startActivityForResult(intent, requestCode);
    }

    /**
     * 从当前页面跳转到其他页面，传递参数
     *
     * @param cls
     * @param bundle
     */
    public void nextActivity(Class<?> cls, Bundle bundle) {
        Intent intent = new Intent();
        intent.setClass(this, cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }

        this.startActivity(intent);
    }

    /**
     * 是否使用黄油刀注解
     *
     * @return 默认使用
     */
    public boolean isUsingButterKnife() {
        return true;
    }

    /**
     * 展示吐司
     */
    public void showToast(String message) {
        ToastUtils.showShort(message);
    }


    @Subscribe
    public void onEvent(String event) {

    }
}
