package com.xgw.mybaselibproject;

import com.xgw.mybaselib.base.BaseRecyclerActivity;
import com.xgw.mybaselib.base.BaseRecyclerAdapter;
import com.xgw.mybaselib.rxhttp.RxHttpUtils;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by XieGuangwei on 2017/11/7.
 * 通用列表activity测试
 */

public class RecyclerTestActivity extends BaseRecyclerActivity<BaseResponse<List<Gank>>, Gank> {
    @Override
    protected int getLayoutId() {
        return R.layout.activity_test_recycler;
    }

    @Override
    public void initView() {
        setToolbarCenter(true, "通用列表activity测试通用列表activity测试通用列表activity测试通用列表activity测试通用列表activity测试通用列表activity测试");
        super.initView();
    }

    @Override
    protected boolean isEnableLoadMore() {
        return false;
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
                .getMeizhiData(10);
    }

    @Override
    protected void onResultSuccess(BaseResponse<List<Gank>> result, int pageNo) {
        adapter.showSinglePageData(result.getResults());
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
        return new int[0];
    }
}
