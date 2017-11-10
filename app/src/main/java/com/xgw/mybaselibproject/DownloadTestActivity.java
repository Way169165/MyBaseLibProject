package com.xgw.mybaselibproject;

import android.os.Environment;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.socks.library.KLog;
import com.xgw.mybaselib.base.BaseActivity;
import com.xgw.mybaselib.rxhttp.RxHttpUtils;
import com.xgw.mybaselib.rxhttp.helper.DownloadUtils;
import com.xgw.mybaselib.rxhttp.helper.RxSchedulers;
import com.xgw.mybaselib.rxhttp.progress.ProgressResponseBody;
import com.xgw.mybaselib.utils.FileUtils;
import com.xgw.mybaselib.widget.roundview.view.RoundViewButton;

import java.io.File;
import java.net.SocketTimeoutException;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

/**
 * Created by XieGuangwei on 2017/11/9.
 */

public class DownloadTestActivity extends BaseActivity {
    @BindView(R.id.progress_tv)
    TextView progressTv;
    @BindView(R.id.download_btn)
    RoundViewButton downloadBtn;
    @BindView(R.id.display_iv)
    ImageView displayIv;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_download_test;
    }

    @Override
    public void initView() {
        setToolbarCenter(true,"返回",R.mipmap.android,"下载");
    }

    @Override
    public void initData() {

    }

    @OnClick({R.id.download_btn})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.download_btn:
                downloadBtn.setEnabled(false);
                downloadBtn.setText("正在下载...");
                Disposable disposable = RxHttpUtils.getDownloadConfig()
                        .setProgressListener(new ProgressResponseBody.ProgressListener() {
                            @Override
                            public void update(final long bytesRead, final long contentLength, boolean done) {
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        int progress = (int) (bytesRead * 100 / contentLength);
                                        progressTv.setText("进度：" + progress + "%");
                                    }
                                });
                            }
                        })
                        .createApi(ApiService.class)
                        .downloadTest("http://pic4.nipic.com/20091217/3885730_124701000519_2.jpg")
                        .subscribeOn(Schedulers.io())
                        .map(new Function<ResponseBody, File>() {
                            @Override
                            public File apply(@NonNull ResponseBody responseBody) throws Exception {
                                File file = new File(Environment.getExternalStorageDirectory().getPath(), "aaa.jepg");
                                DownloadUtils.saveDownloadFile(responseBody.byteStream(), file);
                                return file;
                            }
                        })
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(new DisposableObserver<File>() {
                            @Override
                            public void onNext(File file) {
                                KLog.e("下载成功");
                                downloadBtn.setEnabled(true);
                                downloadBtn.setText("下载");
                                Glide.with(DownloadTestActivity.this)
                                        .load(file)
                                        .into(displayIv);
                            }

                            @Override
                            public void onError(Throwable e) {
                                KLog.e("下载异常：" + e.getMessage());
                                if (e instanceof SocketTimeoutException) {
                                    showToast("连接超时");
                                }
                                downloadBtn.setEnabled(true);
                                downloadBtn.setText("下载");
                            }

                            @Override
                            public void onComplete() {

                            }
                        });
                disposables.add(disposable);
                break;
        }
    }
}
