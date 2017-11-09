package com.xgw.mybaselibproject;

import android.os.Environment;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.socks.library.KLog;
import com.xgw.mybaselib.base.BaseActivity;
import com.xgw.mybaselib.rxhttp.RxHttpUtils;
import com.xgw.mybaselib.rxhttp.bean.BaseResponse;
import com.xgw.mybaselib.rxhttp.helper.DownloadUtils;
import com.xgw.mybaselib.rxhttp.helper.RxSchedulers;
import com.xgw.mybaselib.rxhttp.progress.CountingRequestBody;
import com.xgw.mybaselib.rxhttp.progress.ProgressResponseBody;
import com.xgw.mybaselib.widget.roundview.view.RoundViewButton;

import java.io.File;
import java.net.SocketTimeoutException;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;

/**
 * Created by XieGuangwei on 2017/11/9.
 */

public class UploadTestActivity extends BaseActivity {
    @BindView(R.id.progress_tv)
    TextView progressTv;
    @BindView(R.id.upload_btn)
    RoundViewButton uploadBtn;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_upload_test;
    }

    @Override
    public void initView() {
        setToolbarCenter(true, "返回", 0, "上传");
    }

    @Override
    public void initData() {

    }

    @OnClick({R.id.upload_btn})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.upload_btn:
                uploadBtn.setEnabled(false);
                uploadBtn.setText("正在上传...");
                File file = new File("xxx");
                Map<String, RequestBody> params = new HashMap<>();
                params.put("xxx", RequestBody.create(MediaType.parse("multipart/form-data"), "xxx"));
                RequestBody fileBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);
                CountingRequestBody countingRequestBody = new CountingRequestBody(fileBody, new CountingRequestBody.Listener() {
                    @Override
                    public void onRequestProgress(final long bytesWritten, final long contentLength) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                progressTv.setText("进度：" + (int) (bytesWritten * 100 / contentLength) + "%");
                            }
                        });
                    }
                });
                params.put("FileName\";filename=\"" + file.getName(), countingRequestBody);
                RxHttpUtils.getSingleConfig()
                        .setBaseUrl("http://www.xxx.com/")
                        .createApi(ApiService.class)
                        .uploadFile(params)
                        .compose(RxSchedulers.<BaseResponse<Gank>>io_main())
                        .subscribeWith(new DisposableObserver<BaseResponse<Gank>>() {
                            @Override
                            public void onNext(BaseResponse<Gank> gankBaseResponse) {
                                uploadBtn.setEnabled(true);
                                uploadBtn.setText("上传");
                                KLog.e("上传成功");
                            }

                            @Override
                            public void onError(Throwable e) {
                                uploadBtn.setEnabled(true);
                                uploadBtn.setText("上传");
                                KLog.e("上传失败：" + e.getMessage());
                                showToast(e.getMessage());
                            }

                            @Override
                            public void onComplete() {

                            }
                        });

                break;
        }
    }
}
