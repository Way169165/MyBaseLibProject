package com.xgw.mybaselibproject;

import android.support.v4.content.ContextCompat;

import com.socks.library.KLog;
import com.xgw.mybaselib.base.BaseRecyclerAdapter;
import com.xgw.mybaselib.base.BaseRecyclerFragment;
import com.xgw.mybaselib.rxhttp.RxHttpUtils;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by XieGuangwei on 2017/11/8.
 */

public class LazyLoadRecyclerTestFragment extends BaseRecyclerFragment<BaseResponse<List<Gank>>, Gank> {

    @Override
    protected boolean isLoadLazy() {
        return true;
    }

    @Override
    protected boolean isEnableLoadMore() {
        return true;
    }

    @Override
    protected BaseRecyclerAdapter<Gank> getAdapter() {
        return new RecyclerTestAdapter(R.layout.item_recycler_test, null, recyclerView);
    }

    @Override
    protected Observable<BaseResponse<List<Gank>>> getData(int pageNo) {
        return RxHttpUtils.getSingleConfig()
                .setBaseUrl(UrlConstants.BASE_URL)
                .createApi(ApiService.class)
                .getMaizhiData(adapter.getPageSize(), pageNo);
    }

    @Override
    protected void onResultSuccess(BaseResponse<List<Gank>> result, int pageNo) {
        KLog.e("加载成功，加载了第" + pageNo + "页");
        adapter.showMorePageData(result.getResults(), pageNo);
        //说明执行了onNext，但服务器返回的数据有问题。但基类又执行了pageNo++,因此得将基类的pageNo-1恢复正常。
        if (result.getResults() == null) {
            setPageNo(pageNo);
            KLog.e("返回数据有误，重新恢复pageNo后为：" + getPageNo());
        }
    }

    @Override
    protected int getLayoutManagerType() {
        return GRID_LAYOUT_MANAGER_TYPE;
    }

    @Override
    protected int getGridLayoutManagerSpanCount() {
        return 2;
    }

    @Override
    protected int[] getSwipeRefreshSchemaColors() {
        return new int[]{ContextCompat.getColor(getContext(), R.color.colorPrimary)};
    }

    @Override
    protected int getLayoutId() {
        return R.layout.recycler_view_layout;
    }
}
