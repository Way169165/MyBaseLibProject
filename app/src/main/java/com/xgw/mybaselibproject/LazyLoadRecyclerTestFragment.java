package com.xgw.mybaselibproject;
import com.socks.library.KLog;
import com.xgw.mybaselib.base.BaseRecyclerAdapter;
import com.xgw.mybaselib.base.BaseRecyclerFragment;
import com.xgw.mybaselib.rxhttp.RxHttpUtils;
import com.xgw.mybaselib.rxhttp.bean.BaseResponse;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by XieGuangwei on 2017/11/8.
 */

public class LazyLoadRecyclerTestFragment extends BaseRecyclerFragment<BaseResponse<List<Gank>>,Gank> {

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
                .getMaizhiData(adapter.getPageSize(),pageNo);
    }

    @Override
    protected void onResultSuccess(BaseResponse<List<Gank>> result,int pageNo) {
        KLog.e("pageNo--->" + pageNo);
        adapter.showMorePageData(result.getResults(),pageNo);
    }

    @Override
    protected void onResultError(Throwable e) {

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
        return new int[]{R.color.colorPrimary};
    }

    @Override
    protected int getLayoutId() {
        return R.layout.recycler_view_layout;
    }
}
