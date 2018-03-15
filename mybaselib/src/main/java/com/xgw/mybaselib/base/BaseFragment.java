package com.xgw.mybaselib.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xgw.mybaselib.utils.ToastUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import butterknife.ButterKnife;
import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by XieGuangwei on 2017/8/7.
 * fragment基类
 */

public abstract class BaseFragment extends Fragment {
    /**
     * 获得布局id
     *
     * @return
     */
    protected abstract int getLayoutId();

    protected CompositeDisposable disposables = new CompositeDisposable();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return View.inflate(getContext(), getLayoutId(), null);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        initView();
        initData();
    }


    /**
     * 初始化view
     */
    protected abstract void initView();

    /**
     * 初始化必要数据
     */
    protected abstract void initData();

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onPause() {
        super.onPause();
        if (disposables.size() > 0) {
            disposables.clear();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe
    public void onEvent(String string) {

    }

    public void nextActivity(Class<?> cls) {
        Intent intent = new Intent(getContext(), cls);
        startActivity(intent);
    }

    public void nextActivity(Class<?> cls, Bundle bundle) {
        Intent intent = new Intent();
        intent.setClass(getContext(), cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }

    /**
     * 带返回参数的跳转
     *
     * @param cls
     * @param requestCode
     */
    public void nextActivityForResult(Class<?> cls, int requestCode) {
        Intent intent = new Intent(getContext(), cls);
        this.startActivityForResult(intent, requestCode);
    }

    /**
     * 带返回参数的跳转，传递参数
     * @param cls
     * @param requestCode
     * @param bundle
     */
    public void nextActivityForResult(Class<?> cls,int requestCode,Bundle bundle) {
        Intent intent = new Intent(getContext(),cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        this.startActivityForResult(intent,requestCode);
    }

    /**
     * 展示吐司
     */
    public void showToast(String message) {
        ToastUtils.showShort(message);
    }
}
