package com.xgw.mybaselibproject;

import com.xgw.mybaselib.rxhttp.bean.BaseResponse;

import java.util.List;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by XieGuangwei on 2017/11/7.
 */

public interface ApiService {
    @GET("data/福利/10/{page}")
    Observable<BaseResponse<List<Gank>>> getMeizhiData(
            @Path("page") int page);
    @GET("data/福利/{pageCount}/{pageIndex}")
    Observable<BaseResponse<List<Gank>>> getMaizhiData(@Path("pageCount") int pageCount,@Path("pageIndex") int pageIndex);
}
