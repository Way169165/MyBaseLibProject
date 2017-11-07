package com.xgw.mybaselibproject;

import android.support.v7.app.AlertDialog;
import android.view.View;

import com.google.gson.Gson;
import com.xgw.mybaselib.base.BaseActivity;
import com.xgw.mybaselib.rxhttp.RxHttpUtils;
import com.xgw.mybaselib.rxhttp.bean.BaseResponse;
import com.xgw.mybaselib.rxhttp.helper.RxSchedulers;
import com.xgw.mybaselib.utils.SizeUtils;
import com.xgw.mybaselib.widget.roundview.view.RoundViewButton;

import java.util.List;
import java.util.Random;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.observers.DisposableObserver;

public class MainActivity extends BaseActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {
        setToolbarCenter(false, "测试项目");
    }

    @Override
    public void initData() {

    }

    @OnClick({R.id.global_btn, R.id.single_btn1, R.id.single_btn2, R.id.recycler_activity_btn, R.id.webview_btn})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.global_btn:
                RxHttpUtils.getGlobalConfig().createApi(ApiService.class)
                        .getMeizhiData(10)
                        .compose(RxSchedulers.<BaseResponse<List<Gank>>>io_main())
                        .subscribeWith(new DisposableObserver<BaseResponse<List<Gank>>>() {
                            @Override
                            public void onNext(BaseResponse<List<Gank>> listBaseResponse) {
                                new AlertDialog.Builder(MainActivity.this)
                                        .setTitle("成功")
                                        .setMessage(new Gson().toJson(listBaseResponse))
                                        .setCancelable(true)
                                        .setNegativeButton("确定", null).show();
                            }

                            @Override
                            public void onError(Throwable e) {

                            }

                            @Override
                            public void onComplete() {

                            }
                        });

                break;
            case R.id.single_btn1:
                RxHttpUtils.getSingleConfig()
                        .setBaseUrl(UrlConstants.BASE_URL)
                        .createApi(ApiService.class)
                        .getMeizhiData(20)
                        .compose(RxSchedulers.<BaseResponse<List<Gank>>>io_main())
                        .subscribeWith(new DisposableObserver<BaseResponse<List<Gank>>>() {
                            @Override
                            public void onNext(BaseResponse<List<Gank>> listBaseResponse) {

                                new AlertDialog.Builder(MainActivity.this)
                                        .setTitle("成功")
                                        .setMessage(new Gson().toJson(listBaseResponse))
                                        .setCancelable(true)
                                        .setNegativeButton("确定", null).show();
                            }

                            @Override
                            public void onError(Throwable e) {

                            }

                            @Override
                            public void onComplete() {

                            }
                        });
                break;
            case R.id.single_btn2:

                RxHttpUtils.getSingleConfig()
                        .setBaseUrl(UrlConstants.BASE_URL)
                        .createApi(ApiService.class)
                        .getMeizhiData(30)
                        .compose(RxSchedulers.<BaseResponse<List<Gank>>>io_main())
                        .subscribeWith(new DisposableObserver<BaseResponse<List<Gank>>>() {
                            @Override
                            public void onNext(BaseResponse<List<Gank>> listBaseResponse) {

                                new AlertDialog.Builder(MainActivity.this)
                                        .setTitle("成功")
                                        .setMessage(new Gson().toJson(listBaseResponse))
                                        .setCancelable(true)
                                        .setNegativeButton("确定", null).show();
                            }

                            @Override
                            public void onError(Throwable e) {

                            }

                            @Override
                            public void onComplete() {

                            }
                        });
                break;
            case R.id.recycler_activity_btn:
                nextActivity(RecyclerTestActivity.class);
                break;
            case R.id.webview_btn:
                nextActivity(WebViewTestActivity.class);
                break;
        }
    }
}
