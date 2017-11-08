package com.xgw.mybaselibproject;

import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Button;

import com.google.gson.Gson;
import com.xgw.mybaselib.base.BaseActivity;
import com.xgw.mybaselib.rxhttp.RxHttpUtils;
import com.xgw.mybaselib.rxhttp.bean.BaseResponse;
import com.xgw.mybaselib.rxhttp.helper.RxSchedulers;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.observers.DisposableObserver;

public class MainActivity extends BaseActivity {
    @BindView(R.id.global_btn)
    Button button;
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

    @OnClick({R.id.global_btn, R.id.single_btn1, R.id.single_btn2, R.id.recycler_activity_btn, R.id.webview_btn, R.id.fragment_pager_btn,R.id.fragment_custom_btn})
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
            case R.id.fragment_pager_btn:
                nextActivity(FragmentPagerTestActivity.class);
                break;
            case R.id.fragment_custom_btn:
                nextActivity(FragmentCustomActivity.class);
                break;
        }
    }
}
