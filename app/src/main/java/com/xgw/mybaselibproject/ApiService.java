package com.xgw.mybaselibproject;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PartMap;
import retrofit2.http.Path;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

/**
 * Created by XieGuangwei on 2017/11/7.
 */

public interface ApiService {
    @GET("data/福利/10/{page}")
    Observable<BaseResponse<List<Gank>>> getMeizhiData(
            @Path("page") int page);

    @GET("data/福利/{pageCount}/{pageIndex}")
    Observable<BaseResponse<List<Gank>>> getMaizhiData(@Path("pageCount") int pageCount, @Path("pageIndex") int pageIndex);

    /**
     * 下载
     *
     * @param url
     * @return
     */
    @Streaming
    @GET
    Observable<ResponseBody> downloadTest(@Url String url);

    /*上传测试*/
    @Multipart
    @POST("xxx")
    Observable<BaseResponse<Gank>> uploadFile(@PartMap Map<String, RequestBody> params);
}
